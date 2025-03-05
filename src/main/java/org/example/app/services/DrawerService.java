package org.example.app.services;
import org.example.app.entities.State;
import org.example.app.entities.Polygon;
import org.example.app.entities.Point;
import org.example.app.entities.Twit;

import java.awt.*;
import java.awt.geom.GeneralPath;
import javax.swing.*;
import java.util.List;
import java.util.Map;


public class DrawerService {

    private static final double SCALE = 6.0; // Коэффициент масштабирования
    private static final int OFFSET_X = 600;
    private static final int OFFSET_Y = 800;

    // Метод для рисования одного полигона
    private void drawPolygon(Graphics2D g2d, List<Point> points, Color color) {
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

        g2d.setColor(color);
        g2d.fill(path); // Заливаем полигон цветом
        g2d.setColor(Color.BLACK); // Обводка черным цветом
        g2d.draw(path); // Рисуем контур
    }

    // Метод для рисования всех полигонов (штатов)
    public void drawStates(Graphics g, List<State> states, Map<State, String> categorizedStates) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(1)); // Толщина линии

        for (State state : states) {
            // Получаем цвет для данного штата из categorizedStates
            String hexColor = categorizedStates.get(state);
            Color color = Color.decode(hexColor); // Преобразуем строку в объект Color

            for (Polygon polygon : state.getPolygons()) {
                drawPolygon(g2d, polygon.getPoints(), color); // Рисуем полигон с переданным цветом
            }

            // Рисуем название штата в центре полигона
            Point centroid = state.getCentroid();
            g2d.setColor(Color.BLACK);
            g2d.drawString(state.getName(), (int) centroid.getLongitude(), (int) centroid.getLatitude());
        }
    }

    // Метод для рисования твитов
    public void drawTwits(Graphics g, List<Twit> twitsWithSentiments) {
        Graphics2D g2d = (Graphics2D) g;

        for (Twit twit : twitsWithSentiments) {
            // Используем значение настроения для выбора цвета
            Color sentimentColor = getSentimentColor(twit.getSentiment());

            // Рисуем точку для каждого твита
            int x = (int) ((twit.getLocation().getLongitude() + 180) * SCALE);
            int y = (int) ((90 - twit.getLocation().getLatitude()) * SCALE);

            g2d.setColor(sentimentColor);
            g2d.fillOval(x, y, 5, 5); // Рисуем маленькую точку
        }
    }

    // Метод для получения цвета на основе настроения
    private Color getSentimentColor(double sentimentValue) {
        if (sentimentValue > 1) {
            return Color.YELLOW; // Позитивный
        } else if (sentimentValue < -1) {
            return Color.BLUE; // Негативный
        } else {
            return Color.GRAY; // Нейтральный
        }
    }
}
