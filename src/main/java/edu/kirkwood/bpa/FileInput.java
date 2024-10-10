package edu.kirkwood.bpa;

import java.util.*;
import java.io.*;
import java.nio.file.*;

public class FileInput {
    private static String pathToFiles = "src/data_files/";

    public static List<String> readAllLines(String fileName) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(pathToFiles + fileName));
        } catch(IOException e) {
            System.out.println("File not found");
            System.exit(0);
        }
        return lines;
    }
}