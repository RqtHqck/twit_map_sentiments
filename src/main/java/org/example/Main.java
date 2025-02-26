package org.example;

//import org.example.app.service.StateService;
//import org.example.app.service.SentimentService;

import org.example.app.entities.State;
import org.example.app.utils.StateParser;

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



            // Создаем Drawer и рисуем полигоны
            Drawer drawer = new Drawer();
            drawer.drawStatePolygons(states.get(0).getPolygons());

            Scene scene = new Scene(drawer.getMapPane(), 800, 600);
            primaryStage.setTitle("State Map");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
