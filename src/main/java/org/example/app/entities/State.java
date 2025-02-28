package org.example.app.entities;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class State {
    private String name;
    private List<Polygon> polygons;
    private double averageSentiment;

    public State(String name, List<Polygon> polygons) {
        this.name = name;
        this.polygons = polygons;
        this.averageSentiment = 0.0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAverageSentiment() {
        return averageSentiment;
    }

    public void setAverageSentiment(double value) {
        this.averageSentiment += value;
    }


    public List<Polygon> getPolygons() {
        return polygons;
    }


    public void setPolygons(List<Polygon> polygons) {
        this.polygons = polygons;
    }

    public Point getCentroid() {
        if (polygons.isEmpty()) {
            throw new IllegalStateException("У штата нет полигонов");
        }

        // Если у штата только один полигон, просто вычисляем его центроид
        if (polygons.size() == 1) {
            return polygons.getFirst().getCentroid();
        }

        // Для нескольких полигонов усредняем центроиды
        double totalLat = 0;
        double totalLon = 0;
        int count = 0;

        for (Polygon polygon : polygons) {
            Point centroid = polygon.getCentroid();
            totalLat += centroid.getLatitude();
            totalLon += centroid.getLongitude();
            count++;
        }

        return new Point(totalLat / count, totalLon / count);
    }



    @Override
    public String toString() {
        return "State{" + name + '\'' +
                ", averageSen   timent=" + averageSentiment +
                ", polygons=" + polygons +
                '}';
    }
}
