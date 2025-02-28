package org.example.app.entities;

import java.util.List;

public class Polygon {
    private List<Point> points;
    private double averageSentiment;

    public Polygon(List<Point> points) {
        this.points = points;
        this.averageSentiment = 0.0;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Polygon{" + points +
                '}';
    }
}
