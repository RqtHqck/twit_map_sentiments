package org.example.app.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtil {
    public static String readJsonFile(String fileName) throws IOException {
        ClassLoader classLoader = FileReaderUtil.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        System.out.println("Файл json найден: " + file.getAbsolutePath());
        return new String(Files.readAllBytes(Paths.get(file.toURI())));
    }


    public static List<String[]> readCsvFile(String fileName, String delimiter) throws IOException {
        List<String[]> data = new ArrayList<>();
        ClassLoader classLoader = FileReaderUtil.class.getClassLoader();

        // Проверяем, найден ли ресурс
        java.net.URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new FileNotFoundException("Файл не найден: " + fileName);
        }

        File file = new File(resource.getFile());
        System.out.println("Файл csv найден: " + file.getAbsolutePath());

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line.split(delimiter));
            }
        }
        return data;
    }



}
