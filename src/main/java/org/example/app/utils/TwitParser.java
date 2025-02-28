package org.example.app.utils;

import org.example.app.entities.Point;
import org.example.app.entities.Twit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwitParser {
    public static List<Twit> parse(List<String> lines) {
        List<Twit> twits = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split("\t"); // Разделитель — табуляция (\t)
            if (parts.length < 4) {
                System.out.println("Пропущена некорректная строка: " + line);
                continue;
            }

            try {
                // Парсим координаты
                String[] coordinates = parts[0].replace("[", "").replace("]", "").split(", ");
                double latitude = Double.parseDouble(coordinates[0]);
                double longitude = Double.parseDouble(coordinates[1]);
                Point location = new Point(latitude, longitude);

                // Дата
                String date = parts[2].trim();

                // Сообщение
                String message = parts[3].trim();

                // Создаем Twit
                Twit twit = new Twit(location, date, message);
                twits.add(twit);
            } catch (Exception e) {
                System.err.println("Ошибка парсинга строки: " + line);
            }
        }

        System.out.println("Парсер завершен. Количество твитов: " + twits.size());
        return twits;
    }
}
