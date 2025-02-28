package org.example.app.utils;

import org.example.app.entities.Sentiment;
import org.example.app.entities.State;
import org.example.app.entities.Twit;

import java.util.List;

public class ParsedData {

    // Статические поля
    private static List<State> states;
    private static List<Twit> twits;
    private static List<Sentiment> sentiments;

    // Статический конструктор для инициализации данных
    public static void initializeData(List<State> states, List<Twit> twits, List<Sentiment> sentiments) {
        ParsedData.states = states;
        ParsedData.twits = twits;
        ParsedData.sentiments = sentiments;
    }

    // Статические методы для доступа к данным
    public static List<State> getStates() {
        return states;
    }

    public static List<Twit> getTwits() {
        return twits;
    }

    public static List<Sentiment> getSentiments() {
        return sentiments;
    }

    // Статический метод для вывода всех твитов в консоль
    public static void printTwits() {
        for (Twit twit : twits) {
            System.out.println(twit);
        }
    }

    // Статический метод для вывода всех сентиментов в консоль
    public static void printSentiments() {
        for (Sentiment sentiment : sentiments) {
            System.out.println(sentiment);
        }
    }

    // Статический метод для вывода всех состояний в консоль
    public static void printStates() {
        for (State state : states) {
            System.out.println(state);
        }
    }

    // Статический метод для вывода всех данных (твиты, сентименты, состояния)
    public static void printAllData() {
        printTwits();
        printSentiments();
        printStates();
    }
}
