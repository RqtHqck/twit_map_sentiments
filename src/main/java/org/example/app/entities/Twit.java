package org.example.app.entities;
import org.example.app.entities.Point;

public class Twit {
    private Point location;
    private String date;
    private String message;

    public Twit(Point location, String date, String message) {
        this.location = location;
        this.date = date;
        this.message = message;
    }

    public Point getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Twit{" +
                "location=" + location +
                ", date='" + date + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
