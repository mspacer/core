package com.core.filestream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.stream.Collectors;
import java.util.zip.Deflater;

/**
 * <p>Для работы с архивами в спецификации Java существуют два пакета — java. util.zip и java.util.jar,
 * соответственно для архивов zip и jar. Различие форматов jar и zip заключается только в расширении архива zip.
 * <p>Пакет java.util.jar аналогичен пакету java.util.zip, за исключением реализации конструкторов и метода
 * void putNextEntry(ZipEntry e) класса JarOutputStream.
 * <p>
 */
public class ArchTest {

    public final static int BUFFER = 2048;

    public static void main(String[] args) {
        //Чтобы переделать все ниже представленные примеры на использование zip-архива, достаточно всюду в коде заменить Jar на Zip
        try {
            zipData();
            unZipData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void zipData() throws IOException {
        Path dirPath = Paths.get("data");
        if (Files.notExists(dirPath) || !Files.isDirectory(dirPath)) {
            throw new FileNotFoundException(dirPath + " not found");
        }

        List<Path> listFilesToJar = null;
        try {
            listFilesToJar = Files.walk(dirPath, 10).filter(f -> !Files.isDirectory(f)).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Path[] temp = {};
        Path[] filesToJar = listFilesToJar.toArray(temp);
        // actually archiving

        File fJar = new File("jarData.jar");
        fJar.delete();
        try (FileOutputStream outputStream = new FileOutputStream(fJar);
             JarOutputStream jarOutputStream = new JarOutputStream(outputStream)) {
            byte[] buffer = new byte[BUFFER];
            jarOutputStream.setLevel(Deflater.DEFAULT_COMPRESSION);

            for (int i = 0; i < filesToJar.length; i++) {
                String file = filesToJar[i].toString();
                jarOutputStream.putNextEntry(new JarEntry(file));

                try (FileInputStream in = new FileInputStream(file)) {
                    int len;
                    while ((len = in.read(buffer)) > 0) {
                        jarOutputStream.write(buffer, 0, len);
                    }
                    jarOutputStream.closeEntry();
                } catch (FileNotFoundException e) {
                    System.err.println("File not found " + e);
                }
            }
        } catch (IllegalArgumentException e) {
            System.err.println("incorrect data " + e);
        } catch (IOException e) {
            System.err.println("I/O error " + e);
        }
    }

    private static void unZipData() throws IOException {
        try (JarFile jarFile = new JarFile("jarData.jar")) {
            jarFile.stream().forEach(entry -> {
                String entryname = entry.getName();
                System.out.println("Extracting: " + entry);
                Path destinationPath = Paths.get("D:\\TEMP\\", entryname);
                // create directory structure
                destinationPath.getParent().toFile().mkdirs();
                // unpack the record, if it is not a directory
                if (!entry.isDirectory()) {
                    writeFile(jarFile, entry, destinationPath);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeFile(JarFile jar, JarEntry entry, Path destinationPath) {
        int currentByte;
        byte data[] = new byte[BUFFER];

        try (BufferedInputStream bufferedInput =  new BufferedInputStream(jar.getInputStream(entry));
             FileOutputStream fileOutput =  new FileOutputStream(destinationPath.toString());
             BufferedOutputStream bufferedOutput =  new BufferedOutputStream(fileOutput, BUFFER)) {
            // write the file to disk
            while ((currentByte = bufferedInput.read(data, 0, BUFFER)) > 0) {
                bufferedOutput.write(data, 0, currentByte);
            }
            bufferedOutput.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
