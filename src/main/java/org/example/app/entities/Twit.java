package org.example.app.entities;

public class Twit {
    private Point location;
    private String date;
    private String message;
    private double sentimentValue;

    public Twit(Point location, String date, String message) {
        this.location = location;
        this.date = date;
        this.message = message;
        this.sentimentValue = 0.0;
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

    public double getSentimentScore() {
        return sentimentValue;
    }

    public void setSentimental(double sentimentValue) {
        this.sentimentValue = sentimentValue;
    }


    @Override
    public String toString() {
        return "Twit{" +
                "location=" + getLocation() +
                ", " + getDate() + '\'' +
                " | " + getMessage() + '\'' +
                ", " + getSentimentScore() + '\'' +
                '}';
    }
}
