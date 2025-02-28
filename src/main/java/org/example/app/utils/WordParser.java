package org.example.app.utils;

import java.util.ArrayList;
import java.util.List;

public class WordParser {
    public static List<String> extractWords(String text) {
        List<String> words = new ArrayList<>();
        // Извлекаем все слова, состоящие только из букв
        String[] wordArray = text.split("[^a-zA-Z]+");
        for (String word : wordArray) {
            if (!word.isEmpty()) {
                words.add(word.toLowerCase());  // Преобразуем слово в нижний регистр
            }
        }
        return words;
    }

}
