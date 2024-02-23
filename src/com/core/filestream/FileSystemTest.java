package com.core.filestream;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FileSystemTest {
    public static void main(String[] args) {
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
    }
}
