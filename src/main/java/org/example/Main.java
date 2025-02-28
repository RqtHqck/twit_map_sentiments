package org.example;

//import org.example.app.service.StateService;
//import org.example.app.service.SentimentService;
import javax.swing.*;

import org.example.app.entities.Sentiment;
import org.example.app.entities.State;
import org.example.app.services.DrawerService;
import org.example.app.utils.FileReaderUtil;
import org.example.app.utils.SentimentsParser;
import org.example.app.utils.StateParser;

import java.awt.*;
import java.io.IOException;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        try {
            // Read states
            List<State> states = StateParser.parseStates(FileReaderUtil.readJsonFile("states.json"));
            List<Sentiment> sentiments = SentimentsParser.parse(FileReaderUtil.readCsvFile("sentiments.csv", ","));


            for (Sentiment s : sentiments) {
                System.out.println(s);
            }

//            // Выводим на экран
//            for (State state : states) {
//                System.out.println(state);
//            }
//
//            // Создаем главное окно
//            JFrame frame = new JFrame("State Map Drawer");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setSize(800, 600);
//
//            // Создаем панель отрисовки
//            JPanel panel = new JPanel() {
//                @Override
//                protected void paintComponent(Graphics g) {
//                    super.paintComponent(g);
//                    DrawerService drawerService = new DrawerService();
//                    drawerService.drawStates(g, states);  // Рисуем полигоны
//                }
//            };
//
//            panel.setPreferredSize(new Dimension(800, 600));
//            frame.add(panel);
//            frame.pack();  // Устанавливаем корректные размеры
//            frame.setVisible(true);
//
//            // Принудительно вызываем отрисовку
//            panel.revalidate();
//            panel.repaint();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
