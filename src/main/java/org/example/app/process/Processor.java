package org.example.app.process;

import org.example.app.entities.Sentiment;
import org.example.app.entities.State;
import org.example.app.entities.Twit;
import org.example.app.utils.*;

import java.io.IOException;
import java.util.List;

public class Processor {
    public static void runProgram() throws IOException {
        // Read all 3 files into ParsedData and save them into parameters
        readData();

    }


    public static ParsedData readData() throws IOException {

        // Read states
        List<State> states = StateParser.parseStates(FileReaderUtil.readJsonFile("states.json"));
        List<Sentiment> sentiments = SentimentsParser.parse(FileReaderUtil.readCsvFile("sentiments.csv", ","));
        List<Twit> twits = TwitParser.parse(FileReaderUtil.readTxtFile("cali_tweets2014.txt"));
        return new ParsedData(states, twits, sentiments);
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