package com.mem;

import com.mem.xls.RowReaderException;
import com.mem.xls.XlsxRowItemReader;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ocbb {
    public static String OSBB_FILE = "Квитанції-листопад_2023_житлові_ОК_Сергій.xls";
    public static String OSBB_REESTR_FILE = null;
    public static String BANK_FILE = OSBB_FILE;
    public static String RESULT_FILE_XLS = "листопад_2023.xls";

    public static void main(String[] args) throws Exception{
        List<Address> osbbAddresses = osbb();
        List<Address> bankAddresses = bank();
        List<Address> osbbReestr = reestr();

        List<Address> osbbAddresses_1 = osbb();
        List<Address> bankAddresses_1 = bank();
        List<Address> osbbReestr_1 = reestr();

        List<WeakReference<HSSFWorkbook>> workbooks = new ArrayList<WeakReference<HSSFWorkbook>>();
        for (int i = 0; i < 100; i++) {
            if (i == 89) {
                System.out.println(i);
            }
            workbooks.add(createXls(osbbAddresses, bankAddresses, osbbReestr, i + "_"));
        }

        for (int i = 0; i < 100; i++) {
            if (workbooks.get(i).get() == null) {
                System.out.println("null - " + i);
            }
        }

        System.out.println("");
    }

    public static List<Address> bank() throws Exception {
        System.out.println("bank");
        XlsxRowItemReader reader;
        try {
            reader = new XlsxRowItemReader(BANK_FILE);
        } catch (RowReaderException rex) {
            System.out.println( "Error read " + BANK_FILE + " {" + rex.toString() + "}");
            return Collections.EMPTY_LIST;
        }

        reader.setSheet(1);

        List<Address> result = new ArrayList<Address>();
        boolean save = false;
        while (reader.hasNext()) {
            Row row = reader.getRowIterator().next();
            Iterator<Cell> cellIterator = row.cellIterator();

            if (row.getCell(0).getCellType() != Cell.CELL_TYPE_NUMERIC )
                continue;

            String flat = Integer.valueOf(new DataFormatter().formatCellValue(row.getCell(1))).toString();
            if (flat == null || flat.trim().equals("0"))
                continue;

            List<Address> flats = new ArrayList<Address>();//result.stream().filter(address -> address.getFlatNumber().equals(flat)).collect(Collectors.toList());
            for (int i = 0; i < result.size(); i++) {
                if (result.get(i).getFlatNumber().equals(flat)) {
                    flats.add(result.get(i));
                }
            }

            if (flats.isEmpty()) {
                result.add(new Address());
                result.get(result.size()-1).setFlat(flat);
                result.get(result.size()-1).setFlatNumber(flat);
                result.get(result.size()-1).setPay(new BigDecimal(row.getCell(0).getNumericCellValue()).setScale(2, RoundingMode.HALF_UP).doubleValue());
            } else {
                if (flats.size() > 1) {
                    throw new Exception("Duplicate flat");
                }
                flats.get(0).setPay(new BigDecimal(flats.get(0).getPay() + Double.valueOf(row.getCell(0).getNumericCellValue())).setScale(2, RoundingMode.HALF_UP).doubleValue());
            }
        }

        System.out.println(result);

        return result;
    }

    public static List<Address> osbb() {
        System.out.println("osbb");
        XlsxRowItemReader reader;
        try {
            reader = new XlsxRowItemReader(OSBB_FILE);
        } catch (RowReaderException rex) {
            System.out.println( "Error read " + OSBB_FILE + " {" + rex.toString() + "}");
            return Collections.EMPTY_LIST;
        }
        reader.setSheet(0);

        List<Address> result = new ArrayList<Address>();
        while (reader.hasNext()) {
            Row row = reader.getRowIterator().next();
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (cell.getCellType() == Cell.CELL_TYPE_STRING &&  "Вид платежу".equals(cell.getStringCellValue())) {
                    result.add(new Address());
                    String flat = row.getCell(0).getStringCellValue();
                    result.get(result.size()-1).setFlat(flat);
                }
                if (cell.getCellType() == Cell.CELL_TYPE_STRING && "Разом:".equals(cell.getStringCellValue())) {
                    System.out.println(row.getCell(10));
                    double pay = row.getCell(10).getNumericCellValue();
                    result.get(result.size()-1).setPay( Double.valueOf(new BigDecimal(pay).setScale(2, RoundingMode.HALF_UP).doubleValue()));
                }

            }
        }

        System.out.println(result);
        return result;
    }

    public static List<Address> reestr() throws Exception {
        System.out.println("reestr");
        XlsxRowItemReader reader;
        try {
            reader = new XlsxRowItemReader(OSBB_REESTR_FILE);
        } catch (RowReaderException rex) {
            System.out.println( "Error read " + OSBB_REESTR_FILE + " {" + rex.toString() + "}");
            return Collections.EMPTY_LIST;
        }
        reader.setSheet(0);

        List<Address> result = new ArrayList<Address>();
        boolean save = false;
        while (reader.hasNext()) {
            Row row = reader.getRowIterator().next();
            Iterator<Cell> cellIterator = row.cellIterator();

            if (!save && row.getCell(0).getCellType() == Cell.CELL_TYPE_STRING &&  "Дата".equals(row.getCell(0).getStringCellValue())) {
                save = true;
                //reader.getRowIterator().next();
                continue;
            }
            if (!save) continue;

            try {
                String flat = Integer.valueOf(new DataFormatter().formatCellValue(row.getCell(1))).toString();
                List<Address> flats = new ArrayList<Address>();//result.stream().filter(address -> address.getFlatNumber().equals(flat)).collect(Collectors.toList());
                for (int i = 0; i < result.size(); i++) {
                    if (result.get(i).getFlatNumber().equals(flat)) {
                        flats.add(result.get(i));
                    }
                }

                if (flats.isEmpty()) {
                    result.add(new Address());
                    result.get(result.size()-1).setFlat(flat);
                    result.get(result.size()-1).setFlatNumber(flat);
                    result.get(result.size()-1).setPay(new BigDecimal(row.getCell(4).getNumericCellValue()).setScale(2, RoundingMode.HALF_UP).doubleValue());
                } else {
                    if (flats.size() > 1) {
                        throw new Exception("Duplicate flat");
                    }
                    flats.get(0).setPay(new BigDecimal(flats.get(0).getPay() + Double.valueOf(row.getCell(4).getNumericCellValue())).setScale(2, RoundingMode.HALF_UP).doubleValue());
                }
            } catch (NumberFormatException nex) {
                break;
            }
        }

        System.out.println(result);

        return result;
    }

    public static WeakReference<HSSFWorkbook> createXls(List<Address> osbbAddresses, List<Address> bankAddresses, List<Address> osbbReestr, String suffix) throws IOException {
        HSSFWorkbook _workbook = new HSSFWorkbook();
        WeakReference<HSSFWorkbook> workbook = new WeakReference<HSSFWorkbook>(_workbook);
        _workbook = null;

        HSSFSheet sheet = workbook.get().createSheet("Совпадающие оплаты");
        HSSFSheet sheet2 = workbook.get().createSheet("Оплачено больше");
        HSSFSheet sheet3 = workbook.get().createSheet("Оплачено меньше");
        String[] heads = {"Квартира", "Выставленная сумма", "Оплаченная сумма"};
        int rowCountSheet = 0;
        int rowCountSheet2 = 0;
        int rowCountSheet3 = 0;
        int columnCount = 0;
        Row row0Sheet = sheet.createRow(++rowCountSheet);
        Row row0Sheet2 = sheet2.createRow(++rowCountSheet2);
        Row row0Sheet3 = sheet3.createRow(++rowCountSheet3);
        for (String field : heads) {
            Cell cell = row0Sheet.createCell(++columnCount);
            cell.setCellValue(field);

            Cell cell2 = row0Sheet2.createCell(columnCount);
            cell2.setCellValue(field);

            Cell cell3 = row0Sheet3.createCell(columnCount);
            cell3.setCellValue(field);
        }

        Double payOsbb = 0.0;
        for(Address address : osbbAddresses ) {
            for(Address bankAddr : bankAddresses ) {
                if (address.getFlatNumber().length() > 0 && bankAddr.getFlatNumber().length() > 0 &&
                        address.getFlatNumber().equals(bankAddr.getFlatNumber())) {
                    address.setUnknown(false); bankAddr.setUnknown(false);
                    bankAddr.setUnknown(false); bankAddr.setUnknown(false);

                    Row row;
                    if (address.getPay().equals(bankAddr.getPay())) {
                        row = sheet.createRow(++rowCountSheet);
                    } else
                    if (address.getPay().compareTo(bankAddr.getPay()) < 0) {
                        row = sheet2.createRow(++rowCountSheet2);
                    } else {
                        row = sheet3.createRow(++rowCountSheet3);
                    }

                    Cell cellFlat = row.createCell(1);
                    cellFlat.setCellValue(address.getFlatNumber());

                    Cell cellPayOsbb = row.createCell(2);
                    cellPayOsbb.setCellValue(address.getPay()/*.toString().replace(".", ",")*/);

                    Cell cellPayBank = row.createCell(3);
                    cellPayBank.setCellValue(bankAddr.getPay()/*.toString().replace(".", ",")*/);
                }
            }
            payOsbb = Double.valueOf(payOsbb.doubleValue() + address.getPay().doubleValue());

        }

        //List<Address> unknowns = Collections.EMPTY_LIST;

        for (int i = 0; i < osbbAddresses.size(); i++) {
            Address unknown = osbbAddresses.get(i);
            if (unknown.isUnknown()) {
                Row row = sheet3.createRow(++rowCountSheet3);
                Cell cellFlat = row.createCell(1);
                cellFlat.setCellValue(unknown.getFlatNumber());

                Cell cellPayOsbb = row.createCell(2);
                cellPayOsbb.setCellValue(unknown.getPay());

                Cell cellPayBank = row.createCell(3);
                cellPayBank.setCellValue(0);

                //unknowns.add(unknown);
            }
        }

        for (int i = 0; i < bankAddresses.size(); i++) {
            Address unknown = bankAddresses.get(i);

            Row row = sheet3.createRow(++rowCountSheet3);
            Cell cellFlat = row.createCell(1);
            cellFlat.setCellValue(unknown.getFlatNumber());

            Cell cellPayOsbb = row.createCell(2);
            cellPayOsbb.setCellValue(0);

            Cell cellPayBank = row.createCell(3);
            cellPayBank.setCellValue(unknown.getPay());
        }

        Double payBank = 0.0;
        for(Address bankAddr : bankAddresses ) {
            payBank = Double.valueOf(payBank.doubleValue() + bankAddr.getPay().doubleValue());
        }

        sheet.createRow(++rowCountSheet);
        Row rowOsbb = sheet.createRow(++rowCountSheet);
        Cell cellOsbb = rowOsbb.createCell(0);
        cellOsbb.setCellValue("Итого выставлено:");
        Cell cellOsbbFullCount = rowOsbb.createCell(2);
        cellOsbbFullCount.setCellValue(payOsbb);

        sheet.createRow(++rowCountSheet);

        Row rowBank = sheet.createRow(++rowCountSheet);
        Cell cellBank = rowBank.createCell(0);
        cellBank.setCellValue("Итого оплачено:");
        Cell cellBankFullCount = rowBank.createCell(2);
        cellBankFullCount.setCellValue(payBank);

        if (!osbbReestr.isEmpty()) {
            Double payReestrFull = 0.0;
            Double payReestr = 0.0;
            HSSFSheet sheet4 = workbook.get().createSheet("Реестр оплат");
            int rowCountSheet4 = 0;
            Row row0Sheet4 = sheet4.createRow(++rowCountSheet4);
            Cell cell = row0Sheet4.createCell(1);
            cell.setCellValue("Квартира");
            Cell cell1 = row0Sheet4.createCell(2);
            cell1.setCellValue("Сумма по банку");
            Cell cell2 = row0Sheet4.createCell(3);
            cell2.setCellValue("Сумма по реестру");

            for(Address bankAddr : bankAddresses ) {
                boolean found = false;
                for(Address address : osbbReestr ) {
                    if (address.getFlatNumber().length() > 0 && bankAddr.getFlatNumber().length() > 0 &&
                            address.getFlatNumber().equals(bankAddr.getFlatNumber())) {

                        found = true;
                        if(!address.getPay().equals(bankAddr.getPay())) {
                            Row row = sheet4.createRow(++rowCountSheet4);

                            Cell cellFlat = row.createCell(1);
                            cellFlat.setCellValue(address.getFlatNumber());

                            Cell cellPayBank = row.createCell(2);
                            cellPayBank.setCellValue(bankAddr.getPay()/*.toString().replace(".", ",")*/);
                            Cell cellPayOsbb = row.createCell(3);
                            cellPayOsbb.setCellValue(address.getPay()/*.toString().replace(".", ",")*/);

                            payReestr = Double.valueOf(payReestr.doubleValue() + Math.abs(bankAddr.getPay().doubleValue() - address.getPay().doubleValue()));

                        }

                    }
                }
                if (!found) {
                    Row row = sheet4.createRow(++rowCountSheet4);

                    Cell cellFlat = row.createCell(1);
                    cellFlat.setCellValue(bankAddr.getFlatNumber());

                    Cell cellPayBank = row.createCell(2);
                    cellPayBank.setCellValue(bankAddr.getPay().toString());

                    Cell cellPayOsbb = row.createCell(2);
                    cellPayOsbb.setCellValue("?");

                }
            }
            for(Address address : osbbReestr ) {
                payReestrFull = Double.valueOf(payReestrFull.doubleValue() + address.getPay().doubleValue());
            }

            sheet4.createRow(++rowCountSheet4);
            Row row = sheet4.createRow(++rowCountSheet4);
            Cell cellFlat = row.createCell(0);
            cellFlat.setCellValue("Разница (сумма):");

            Cell cellPayOsbb = row.createCell(2);
            cellPayOsbb.setCellValue(payReestr);

            Row rowReestr = sheet.createRow(++rowCountSheet);
            Cell cellReestr = rowReestr.createCell(0);
            cellReestr.setCellValue("Итого по реестру:");
            Cell cellReestrFullCount = rowReestr.createCell(2);
            cellReestrFullCount.setCellValue(payReestrFull);

            Row rowReestr2 = sheet.createRow(++rowCountSheet);
            Cell cellReestr2 = rowReestr2.createCell(0);
            cellReestr2.setCellValue("Разница:");
            Cell cellReestrFullCount2 = rowReestr2.createCell(2);
            cellReestrFullCount2.setCellValue(payBank - payReestrFull);

        }

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(suffix + RESULT_FILE_XLS );
            workbook.get().write(outputStream);
        } catch (Exception ex) {
            if (outputStream != null) {
                //outputStream.close();
            }
        }

        return workbook;
    }

    static class  Address {
        private String flat;
        private Double pay;
        private String flatNumber;
        private boolean unknown = true;

        public String getFlat() {
            return flat;
        }

        public void setFlat(String flat) {
            this.flat = flat.replaceAll(";", "").replaceAll("\\n", "");
            this.flatNumber = getNumber();
        }

        public Double getPay() {
            return pay;
        }

        public void setFlatNumber(String flatNumber) {
            this.flatNumber = flatNumber;
        }

        public void setPay(Double pay) {
            this.pay = pay;
        }

        public boolean isUnknown() {
            return unknown;
        }

        public void setUnknown(boolean unknown) {
            this.unknown = unknown;
        }

        public String getNumber() {
            String number = "";
            try {
                Pattern pattern = Pattern.compile("^(о/р)\\s(\\d+).+");
                Matcher matcher = pattern.matcher(flat);
                if (matcher.find()) {
                    number = matcher.group(2);
                }
/*
                number = flat.substring(flat.indexOf("кв"), flat.length() );
                number = number.replaceAll(" ", "");
                number = number.replaceAll("\\.", "");
                number = number.replaceAll("\\,", "");
*/

                int ind = 0;
                for (int i = 2; i < number.toCharArray().length; i++) {
                    char ch = number.toCharArray()[i];
                    if (!Character.isDigit(ch)) {
                        ind = i;
                        break;
                    }
                }
                if (ind > 0)
                    number = number.substring(0, ind);

            } catch (Exception e) {}

            if(number.indexOf("кв") != -1) {
                return  number.substring(number.indexOf("кв") + 2);
            }
            return  number;
        }

        public String getFlatNumber() {
            return flatNumber;
        }

        @Override
        public String toString() {
            return "Address{" +
                    "flat='" + flat + '\'' +
                    ", pay='" + pay + '\'' +
                    ", number='" + flatNumber + '\'' +
                    '}' +
                    "\n";
        }
    }
}
