package org.example.app.services;


import org.example.app.entities.Sentiment;
import org.example.app.entities.Twit;
import org.example.app.utils.ParsedData;
import org.example.app.utils.WordParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentimentService {
    public static Double analyzeSentiment(Twit twit) {
        List<Sentiment> sentiments = ParsedData.getSentiments();

        Map<String, Double> sentimentMap = new HashMap<>();
        for (Sentiment sentiment : sentiments) {
            sentimentMap.put(sentiment.getWord().toLowerCase(), sentiment.getValue());  // Сохраняем слова в нижнем регистре
        }


// Извлекаем слова из твита
        List<String> words = WordParser.extractWords(twit.getMessage());

        double sentimentScore = 0.0;
        boolean foundSentiment = false;

        // Проходим по каждому слову в твите и проверяем, есть ли оно среди сентиментных слов
        for (String word : words) {
            // Преобразуем слово в нижний регистр, чтобы избежать проблем с регистром
            String lowerCaseWord = word.toLowerCase();

            // Проверяем, есть ли слово в sentimentMap
            if (sentimentMap.containsKey(lowerCaseWord)) {
                sentimentScore += sentimentMap.get(lowerCaseWord);  // Добавляем значение сентимента
                foundSentiment = true;
            }
        }

        // Если нашли сентиментные слова, возвращаем результат, иначе null
        return foundSentiment ? sentimentScore : null;

    }

}
