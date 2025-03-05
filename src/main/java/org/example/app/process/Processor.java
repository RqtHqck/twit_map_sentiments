package org.example.app.process;

import org.example.app.entities.Sentiment;
import org.example.app.entities.State;
import org.example.app.entities.Twit;
import org.example.app.services.DrawerService;
import org.example.app.services.SentimentService;
import org.example.app.services.StateMapDrawer;
import org.example.app.services.StateService;
import org.example.app.utils.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;


public class Processor {
    public static void runProgram() throws IOException {
        // Read all 3 files into ParsedData and save them into parameters
        readData();

        // Evaluate twits by sentiments, add sentiment value into field in Twit.
        List<Twit> twitsWithSentiments = evaluateTwitsWithSentiments();

        // Group twits by state by stateName
        Map<State, List<Twit>> stateTwits = group_twits_by_state(twitsWithSentiments);
//        for (Map.Entry<State, List<Twit>> entry : stateTwits.entrySet()) {
//            System.out.println("Штат: " + entry.getKey().getName() + ", твиты: " + entry.getValue());
//        }

        // Calculate state average sentiments
        Map<State, Optional<Double>> statesSentiments = calculate_average_sentiments(stateTwits);
        for (Map.Entry<State, Optional<Double>> entry : statesSentiments.entrySet()) {
            System.out.println(entry.getKey().getName() + ": " +
                    entry.getValue());
        }

        // Категоризация настроения по типам
        Map<State, String> categorizedStates = SentimentService.categorizeStates(statesSentiments);

//        // Draw map
        StateMapDrawer mapWindowService = new StateMapDrawer(ParsedData.getStates(), twitsWithSentiments, categorizedStates);
//        mapWindowService.createAndShowMapWindow();


    }


    public static void readData() throws IOException {
        // Read states
        List<State> states = StateParser.parseStates(FileReaderUtil.readJsonFile("states.json"));
        List<Sentiment> sentiments = SentimentsParser.parse(FileReaderUtil.readCsvFile("sentiments.csv", ","));
        List<Twit> twits = TwitParser.parse(FileReaderUtil.readTxtFile("cali_tweets2014.txt"));
        ParsedData.initializeData(states, twits, sentiments);
    }


    public static List<Twit> evaluateTwitsWithSentiments() {
        List<Twit> twits = ParsedData.getTwits();
        List<Twit> evaluatedTwits = new ArrayList<>();

        for (Twit twit : twits) {
            Double sentiment = SentimentService.analyzeSentiment(twit);  // Получаем сентимент для твита
            if (sentiment != null) {
                twit.setSentimental(sentiment);  // Устанавливаем сентимент
            } else {
                twit.setSentimental(0.0);  // Если нет сентиментных слов, устанавливаем 0.0
            }
            evaluatedTwits.add(twit);  // Добавляем твит в новый список
        }

        return evaluatedTwits;  // Возвращаем обновленный список
    }


    public static Map<State, List<Twit>> group_twits_by_state(List<Twit> twits) {
        List<State> states = ParsedData.getStates();
        Map<State, List<Twit>> stateTwits = new HashMap<>();

        // Заполняем мапу всеми штатами, даже если у них нет твитов
        for (State state : states) {
            stateTwits.put(state, new ArrayList<>());
        }

        for (Twit twit : twits) {
            State assignedState = null;
            double minDistance = Double.MAX_VALUE;

            // 1. Проверяем, входит ли твит в один из штатов
            for (State state : states) {
                if (StateService.containsTwit(state, twit)) {
                    assignedState = state;
                    break;
                }
            }

            // 2. Если не нашли, ищем ближайший штат
            if (assignedState == null) {
                for (State state : states) {
                    double distance = GeoUtil.distance(twit.getLocation(), state.getCentroid());
                    if (distance < minDistance) {
                        minDistance = distance;
                        assignedState = state;
                    }
                }
            }

            // Добавляем твит в найденный штат (штат уже есть в мапе)
            stateTwits.get(assignedState).add(twit);
        }

        return stateTwits;
    }


    public static Map<State, Optional<Double>> calculate_average_sentiments(Map<State, List<Twit>> stateTwits) {
        Map<State, Optional<Double>> statesSentiments = new HashMap<>();

        for (Map.Entry<State, List<Twit>> entry : stateTwits.entrySet()) {
            State state = entry.getKey();
            List<Twit> twits = entry.getValue();

            if (twits.isEmpty()) {
                statesSentiments.put(state, Optional.empty()); // Явно указываем "нет данных"
            } else {
                double totalSentiment = 0;
                for (Twit twit : twits) {
                    totalSentiment += twit.getSentiment();
                }
                double averageSentiment = totalSentiment / twits.size();
                statesSentiments.put(state, Optional.of(averageSentiment));
            }
        }

        return statesSentiments;
    }
}
