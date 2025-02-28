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


    @Override
    public String toString() {
        return "State{" + name + '\'' +
                ", averageSentiment=" + averageSentiment +
                ", polygons=" + polygons +
                '}';
    }
}
