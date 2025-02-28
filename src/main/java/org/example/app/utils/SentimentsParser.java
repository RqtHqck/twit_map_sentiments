package org.example.app.utils;

import org.example.app.entities.Sentiment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SentimentsParser {

    public static List<Sentiment> parse(List<String[]> csvData) {
        List<Sentiment> sentiments = new ArrayList<>();
        boolean isFirstLine = true;

        for (String[] row : csvData) {
            if (isFirstLine) {  // Пропускаем заголовок
                isFirstLine = false;
                continue;
            }

            // Пропускаем строки с ошибками или пустые строки
            if (row.length < 2 || row[0].isEmpty() || row[1].isEmpty()) {
                continue;  // Пропускаем пустые или некорректные строки
            }

            try {
                String sentiment = row[0].trim();
                // Пытаемся преобразовать строку в число с плавающей точкой, включая отрицательные значения
                double value = Double.parseDouble(row[1].trim());

                sentiments.add(new Sentiment(sentiment, value));
            } catch (NumberFormatException e) {
                System.err.println("Ошибка парсинга: " + Arrays.toString(row));
            }
        }

        return sentiments;
    }

}
