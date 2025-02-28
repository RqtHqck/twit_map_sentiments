package org.example.app.entities;

public class Sentiment {
    private String word; // Слово
    private double value; // Вес настроения

    public Sentiment(String word, double value) {
        this.word = word;
        this.value = value;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" + getWord() + '-' + getValue() + '}';
    }
}