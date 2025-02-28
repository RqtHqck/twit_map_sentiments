package org.example.app.utils;

import org.example.app.entities.Sentiment;
import org.example.app.entities.State;
import org.example.app.entities.Twit;

import java.util.List;

public class ParsedData {
    private List<State> states;
    private List<Twit> twits;
    private List<Sentiment> sentiments;

    public ParsedData(List<State> states, List<Twit> twits, List<Sentiment> sentiments) {
        this.states = states;
        this.twits = twits;
        this.sentiments = sentiments;
    }

    public List<State> getStates() {
        return states;
    }

    public List<Twit> getTwits() {
        return twits;
    }

    public List<Sentiment> getSentiments() {
        return sentiments;
    }

    // Метод для вывода всех твитов в консоль
    public void printTwits() {
        for (Twit twit : twits) {
            System.out.println(twit);
        }
    }

    // Метод для вывода всех сентиментов в консоль
    public void printSentiments() {
        for (Sentiment sentiment : sentiments) {
            System.out.println(sentiment);
        }
    }

    // Метод для вывода всех состояний в консоль
    public void printStates() {
        for (State state : states) {
            System.out.println(state);
        }
    }

    // Метод для вывода всех данных (твиты, сентименты, состояния)
    public void printAllData() {
        printTwits();
        printSentiments();
        printStates();
    }
}
