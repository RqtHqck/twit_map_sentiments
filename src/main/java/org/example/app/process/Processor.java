package org.example.app.process;

import org.example.app.entities.Polygon;
import org.example.app.entities.Sentiment;
import org.example.app.entities.State;
import org.example.app.entities.Twit;
import org.example.app.services.SentimentService;
import org.example.app.services.StateService;
import org.example.app.utils.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;


public class Processor {
    public static void runProgram() throws IOException {
        // Read all 3 files into ParsedData and save them into parameters
        readData();
        // Evaluate twits by sentiments, add sentiment value into field in Twit.
        List<Twit> evaluatedTwits = evaluateTwitsWithSentiments();
        for (Twit twit : evaluatedTwits) {
            System.out.println(twit);
        }

        stateSentimentAssign(evaluatedTwits);
        for (State state : ParsedData.getStates()) {
            System.out.println(state);
        }

//        System.out.println(state);
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


    public static void stateSentimentAssign(List<Twit> twists) {
        List<State> states = ParsedData.getStates();
        List<Twit> evaluatedTwits = new ArrayList<>();


        for (Twit twit : twists) {
            for (State state: states) {
//                System.out.println(state);
                if (StateService.containsTwit(state, twit)) {  // Проверяем, принадлежит ли твит штату
                    state.setAverageSentiment(twit.getSentimentScore());
//                    System.out.println(state);
                }

            }
        }
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