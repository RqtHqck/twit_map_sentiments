package org.example.app.services;

import org.example.app.entities.Point;
import org.example.app.entities.Polygon;
import org.example.app.utils.Algoritmix;

import java.util.List;


public class PolygonService {
    public static boolean isPointInsidePolygon(Polygon polygon, Point point) {
        // Говорит находится ли внутри полигона точка
        List<Point> points = polygon.getPoints();
        int count = 0;
        int n = points.size();

        for (int i = 0; i < n; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get((i + 1) % n); // Следующая вершина (последняя соединяется с первой)
            if (Algoritmix.rayIntersectsSegment(point, p1, p2)) {
                count++;
            }
        }
        return count % 2 == 1; // Нечетное число пересечений = внутри
    }
}
