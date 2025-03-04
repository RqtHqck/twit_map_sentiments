package org.example.app.services;


import org.example.app.entities.Sentiment;
import org.example.app.entities.State;
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
        // Аналзирует есть ли в твите слова из sentiments
        List<Sentiment> sentiments = ParsedData.getSentiments();

        // Map для быстродействия при переборе.
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

    // Метод для категоризации настроений
    public static Map<State, String> categorizeStates(Map<State, Double> sentiments) {
        Map<State, String> categories = new HashMap<>();

        // Определение категорий и цветов
        for (Map.Entry<State, Double> entry : sentiments.entrySet()) {
            State state = entry.getKey();
            double sentimentValue = entry.getValue();
            String color = getColor(sentimentValue);
            categories.put(state, color);
        }

        return categories;
    }


    private static String getColor(double value) {
        // Получнеие цвета в зависимости от значения настроения
        if (value == 0.0) {
            return "#FFFFFF"; // Белый для нейтральных настроений
        }

        // Если значение больше 1, то используем яркий желтый
        if (value > 1) {
            return "#FFFF00"; // Яркий желтый для "очень позитивно"
        }

        // Если значение меньше -1, то используем яркий синий
        if (value < -1) {
            return "#0000FF"; // Яркий синий для "очень негативно"
        }

        // Для значений в пределах от -1 до 1
        // Положительные значения (от 0 до 1) — желтый, где более яркое — ближе к 1
        if (value > 0) {
            int intensity = (int) (255 - 255 * value); // Уменьшаем яркость, чем меньше значение
            return String.format("#%02X%02X00", intensity, intensity); // Желтый оттенок
        }

        // Отрицательные значения (от -1 до 0) — синий, где более яркое — ближе к -1
        int intensity = (int) (255 - 255 * Math.abs(value)); // Уменьшаем яркость, чем меньше значение
        return String.format("#%02X%02X%02X", intensity, intensity, 255); // Синий оттенок
    }


}