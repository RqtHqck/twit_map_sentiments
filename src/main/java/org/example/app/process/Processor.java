package org.example.app.process;

import org.example.app.entities.Sentiment;
import org.example.app.entities.State;
import org.example.app.entities.Twit;
import org.example.app.services.SentimentService;
import org.example.app.services.StateService;
import org.example.app.utils.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Processor {
    public static void runProgram() throws IOException {
        // Read all 3 files into ParsedData and save them into parameters
        readData();
        // Evaluate twits by sentiments, add sentiment value into field in Twit.
        List<Twit> evaluatedTwits = evaluateTwitsWithSentiments();
//        for (Twit twit : evaluatedTwits) {
//            System.out.println(twit);
//        }

        // Group twits by state by stateName
        Map<String, List<Twit>> stateTwits = group_twits_by_state(evaluatedTwits);
        for (Map.Entry<String, List<Twit>> entry : stateTwits.entrySet()) {
            System.out.println("Штат: " + entry.getKey());
            for (Twit twit : entry.getValue()) {
                System.out.println("  - " + twit);
            }
        }

        // Calculate state average sentiments
        Map<String, Double> sentiments = calculate_average_sentiments(stateTwits);
        for (Map.Entry<String, Double> entry : sentiments.entrySet()) {
            System.out.println("Штат: " + entry.getKey() + ", Сумма настроений: " + entry.getValue());
        }



//        for (State state : ParsedData.getStates()) {
//            System.out.println(state);
//        }
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


    public static Map<String, List<Twit>> group_twits_by_state(List<Twit> twits) {
        List<State> states = ParsedData.getStates();
        Map<String, List<Twit>> stateTwits = new HashMap<>();

        for (Twit twit : twits) {
            String assignedState = null;
            double minDistance = Double.MAX_VALUE;

            // 1. Сначала проверяем, входит ли твит в один из штатов
            for (State state : states) {
                if (StateService.containsTwit(state, twit)) {
                    assignedState = state.getName();
                    break; // Если нашли штат, дальше не проверяем
                }
            }

            // 2. Если не нашли, ищем ближайший штат
            if (assignedState == null) {
                for (State state : states) {
                    double distance = GeoUtil.distance(twit.getLocation(), state.getCentroid());
                    if (distance < minDistance) {
                        minDistance = distance;
                        assignedState = state.getName(); // Запоминаем ближайший штат
                    }
                }
            }

            // Добавляем твит в соответствующий список
            stateTwits.computeIfAbsent(assignedState, k -> new ArrayList<>()).add(twit);
        }

        return stateTwits;
    }

    public static Map<String, Double> calculate_average_sentiments(Map<String, List<Twit>> stateTwits) {
        Map<String, Double> statesSentiments = new HashMap<>();

        for (Map.Entry<String, List<Twit>> entry : stateTwits.entrySet()) {
            String state = entry.getKey();
            List<Twit> twits = entry.getValue();

            double totalSentiment = 0;
            for (Twit twit : twits) {
                totalSentiment += twit.getSentiment();
            }

            statesSentiments.put(state, totalSentiment);
        }

        return statesSentiments;
    }


}




//        // Создаем главное окно
//        JFrame frame = new JFrame("State Map Drawer");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(800, 600);
//
//        // Создаем панель отрисовки
//        JPanel panel = new JPanel() {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                DrawerService drawerService = new DrawerService();
//                drawerService.drawStates(g, states);  // Рисуем полигоны
//            }
//        };
//
//        panel.setPreferredSize(new Dimension(800, 600));
//        frame.add(panel);
//        frame.pack();  // Устанавливаем корректные размеры
//        frame.setVisible(true);
//
//        // Принудительно вызываем отрисовку
//        panel.revalidate();
//        panel.repaint()