package org.example.app.services;
import org.example.app.entities.State;
import org.example.app.entities.Polygon;
import org.example.app.entities.Point;

import java.awt.*;
import java.awt.geom.GeneralPath;
import javax.swing.*;
import java.util.List;


public class DrawerService {

    private static final double SCALE = 6.0; // Коэффициент масштабирования
    private static final int OFFSET_X = 0;
    private static final int OFFSET_Y = 0;

    // Метод для рисования одного полигона
    private void drawPolygon(Graphics2D g2d, List<Point> points) {
        if (points == null || points.isEmpty()) {
            return;
        }

        GeneralPath path = new GeneralPath();

        // Преобразование первой точки
        int startX = (int) ((points.get(0).getLongitude() + 180) * SCALE) - OFFSET_X;
        int startY = (int) ((90 - points.get(0).getLatitude()) * SCALE) - OFFSET_Y;

        path.moveTo(startX, startY);

        // Преобразуем остальные точки
        for (int i = 1; i < points.size(); i++) {
            int x = (int) ((points.get(i).getLongitude() + 180) * SCALE) - OFFSET_X;
            int y = (int) ((90 - points.get(i).getLatitude()) * SCALE) - OFFSET_Y;

            path.lineTo(x, y);
        }

        path.closePath();

        g2d.setColor(Color.BLUE);
        g2d.fill(path);
        g2d.setColor(Color.BLACK);
        g2d.draw(path);
    }


    // Метод для рисования всех полигонов
    public void drawStates(Graphics g, List<State> states) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(1)); // Толщина линии

        for (State state : states) {
            for (Polygon polygon : state.getPolygons()) {
                drawPolygon(g2d, polygon.getPoints());
            }
        }
    }

}
