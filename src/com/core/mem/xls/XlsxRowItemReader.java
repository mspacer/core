package com.core.mem.xls;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class XlsxRowItemReader implements RowItemReader<Map<String, String>> {

    private Workbook myWorkBook;
    private Sheet mySheet;
    private Iterator<Row> rowIterator;
    private List<String> columns;
    private InputStream is;

    public XlsxRowItemReader(String localResource) {
        try {
            init(ResourceUtils.resourceInputStream(localResource));
        } catch (Exception e) {
            throw new RowReaderException(e);
        }
    }

    public XlsxRowItemReader(InputStream is) {
        init(is);
    }

    private void init(InputStream is) {
        boolean initializedSuccessfully = false;

        try {
            this.is = is;
            // Finds the workbook instance for XLSX file
            myWorkBook = new HSSFWorkbook(is);

            setSheet(0);

            initializedSuccessfully = true;

        } catch (IOException e) {
            throw new RowReaderException("Input/output error while reading the input stream ", e);
        } finally {
            if (!initializedSuccessfully)
                close();
        }
    }

    public void setSheet(String sheet) {
        int index = myWorkBook.getSheetIndex(sheet.toUpperCase());
        if (index < 0) {
            throw new RowReaderException("no sheet found with name '" + sheet + "'");
        }
        setSheet(index);
    }

    public void setSheet(int sheet) {
        setSheet(myWorkBook.getSheetAt(sheet));
    }

    private void setSheet(Sheet sheet) {
        // Return first sheet from the XLSX workbook
        mySheet = sheet;

        // Get iterator to all the rows in current sheet
        rowIterator = mySheet.iterator();

/*
        columns = new LinkedList<String>();

        Map<String, Integer> sameColumnNameIndex = new HashMap<String, Integer>();
        
        if (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            // For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();

            int blankCount = 0;
            
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                
                String columnName = null;

                if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                    columnName = normalizeColumnString(cell.getStringCellValue());
                } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                    columnName = normalizeColumnString(cell.getBooleanCellValue() + "");
                } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    columnName = normalizeColumnString(cell.getNumericCellValue() + "");
                } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
                    columnName = normalizeColumnString("BLANK_" + blankCount++);
                } else {
                    throw new UnsupportedOperationException("Unsupported type for column");
                }

                Integer index = sameColumnNameIndex.get(columnName);
                if (index == null) {
                    columns.add(columnName);
                    sameColumnNameIndex.put(columnName, Integer.valueOf(1));
                } else {
                    columns.add(columnName + "_" + index);
                    sameColumnNameIndex.put(columnName, Integer.valueOf(++index));
                }
               
            }


        }*/

    }

    @Override
    public boolean hasNext() {
        return rowIterator.hasNext();
    }

    @Override
    public Map<String, String> next() {
        Row row = rowIterator.next();

        // For each row, iterate through each columns
        Iterator<Cell> cellIterator = row.cellIterator();

        Map<String, String> result = new HashMap<String, String>();

        while (cellIterator.hasNext()) {

            Cell cell = cellIterator.next();

            if (cell.getColumnIndex() >= columns.size())
                continue;
            
            String column = columns.get(cell.getColumnIndex());
            
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    result.put(column, normalizeString(cell.getStringCellValue()));
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        result.put(column, normalizeString(new SimpleDateFormat("dd.MM.yyyy").format(cell.getDateCellValue())));
                    } else {
                        result.put(column, normalizeString((double) cell.getNumericCellValue() + ""));
                    }
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    result.put(column, normalizeString(cell.getBooleanCellValue() + ""));
                    break;
                default:
            }

        }

        return result;
    }

    private String normalizeColumnString(String value) {
        return value.trim().toUpperCase().replaceAll(" ", "_");
    }

    private String normalizeString(String value) {
        return value.trim();
    }

    public void close() {

        try {
            if (myWorkBook != null)
                myWorkBook.close();
        } catch (IOException ok) {
            ok.printStackTrace();
        }

        try {
            if (is != null)
                is.close();
        } catch (IOException ok) {
            ok.printStackTrace();
        }

    }

    public List<String> getColumns() {
        return Collections.unmodifiableList(columns);
    }

    public Iterator<Row> getRowIterator() {
        return rowIterator;
    }
}
