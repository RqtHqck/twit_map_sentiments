package org.example.app.entities;

import java.util.List;

public class Polygon {
    private List<Point> points;


    public Polygon(List<Point> points) {
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }


    public Point getCentroid() {
        double centroidLat = 0;
        double centroidLon = 0;
        int n = points.size();

        for (Point point : points) {
            centroidLat += point.getLatitude();
            centroidLon += point.getLongitude();
        }

        return new Point(centroidLat / n, centroidLon / n);
    }



    @Override
    public String toString() {
        return "Polygon{" + points +
                '}';
    }

}
