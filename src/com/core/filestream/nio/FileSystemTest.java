package com.core.filestream.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileSystemTest {
    public static void main(String[] args) {
        findFiles();
        pathWalk();
        readFile();
    }

    private static void findFiles() {
        System.out.println("--------findFiles-------------");
        Path path = FileSystems.getDefault().getPath("src");
        if (Files.exists(path) && Files.isDirectory(path)) {
            int maxDepth = 5;
            try (Stream<Path> streamDir = Files.find(path, maxDepth,
                    (p, a) -> String.valueOf(p).endsWith(".java"))) {
                long counter = streamDir
                        .map(String::valueOf)
                        .peek(System.out::println)
                        .count();
                System.out.println("found: " + counter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    private static void pathWalk() {
        System.out.println("--------findFiles-------------");
        Path start = Paths.get("src");
        int maxDepth = 3;
        try (Stream<Path> pathStream = Files.walk(start, maxDepth)) {
            pathStream.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    private static void readFile() {
        System.out.println("--------readFile-------------");
        try(PrintStream printStream = new PrintStream("data/fstout.txt", "utf-8")) {
            printStream.print("это строка");
            printStream.print(" это вторая без перехода строка");
            printStream.println();
            printStream.println("это третья строка");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println("1 read method");
        Path path = Paths.get("data/fstout.txt");
        try (Stream<String> stream = Files.newBufferedReader(path).lines()) {
            stream.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("2 read method");
        try(Stream<String> streamLines = Files.lines(path)) {
            String result = streamLines.collect(Collectors.joining());
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
