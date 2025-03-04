package org.example.app.utils;

import org.example.app.entities.Point;

public class Algoritmix {
    public static boolean rayIntersectsSegment(Point p, Point a, Point b) {
        // Определяет куда падает точка. Перебирает точки из Polygon и сравнивает их положения
        if (a.getLatitude() > b.getLatitude()) { // Упорядочим точки
            Point temp = a;
            a = b;
            b = temp;
        }

        // Если точка вне вертикального диапазона отрезка, нет пересечения
        if (p.getLatitude() < a.getLatitude() || p.getLatitude() > b.getLatitude()) {
            return false;
        }

        // Если точка находится левее обоих концов, пересечение есть
        if (p.getLongitude() < Math.min(a.getLongitude(), b.getLongitude())) {
            return true;
        }

        // Вычисляем пересечение
        if (p.getLongitude() > Math.max(a.getLongitude(), b.getLongitude())) {
            return false;
        }

        // Проверяем пересечение горизонтальной линии точки с отрезком
        double xIntersect = a.getLongitude() + (b.getLongitude() - a.getLongitude()) *
                (p.getLatitude() - a.getLatitude()) / (b.getLatitude() - a.getLatitude());

        return p.getLongitude() < xIntersect;
    }

}
