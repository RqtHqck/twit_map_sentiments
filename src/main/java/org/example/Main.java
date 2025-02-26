package org.example;

//import org.example.app.service.StateService;
//import org.example.app.service.SentimentService;
import javax.swing.*;

import org.example.app.entities.State;
import org.example.app.services.DrawerService;
import org.example.app.utils.StateParser;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        try {
            // Получаем путь к ресурсу states.json
            ClassLoader classLoader = Main.class.getClassLoader();
            File stateFile = new File(classLoader.getResource("states.json").getFile());
            // Чтение содержимого файла в строку
            String inputJson = new String(Files.readAllBytes(Paths.get(stateFile.toURI())));

            // states
            List<State> states = StateParser.parseStates(inputJson);

            // Выводим на экран
            for (State state : states) {
                System.out.println(state);
            }
// Создаем главное окно
            JFrame frame = new JFrame("State Map Drawer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            // Создаем панель отрисовки
            JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    System.out.println("paintComponent is called"); // Лог вызова метода
                    DrawerService drawerService = new DrawerService();
                    drawerService.drawStates(g, states);  // Рисуем полигоны
                }
            };

            panel.setPreferredSize(new Dimension(800, 600));
            frame.add(panel);
            frame.pack();  // Устанавливаем корректные размеры
            frame.setVisible(true);

            // Принудительно вызываем отрисовку
            panel.revalidate();
            panel.repaint();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
