package org.example.app.utils;

import org.example.app.entities.Sentiment;

import java.util.ArrayList;
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

            if (row.length < 3) continue; // Пропускаем некорректные строки

            try {
                String sentiment = row[0].trim();
                double text = Integer.parseInt(row[1].trim());

                sentiments.add(new Sentiment(sentiment, text));
            } catch (NumberFormatException e) {
                System.err.println("Ошибка парсинга ID: " + row[0]);
            }
        }

        return sentiments;
    }
}
