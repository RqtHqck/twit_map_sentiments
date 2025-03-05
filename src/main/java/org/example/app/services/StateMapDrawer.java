package org.example.app.services;

import org.example.app.entities.State;
import org.example.app.entities.Twit;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class StateMapDrawer extends JFrame {

    private List<State> states;
    private List<Twit> twitsWithSentiments;
    private Map<State, String> categorizedStates;

    public StateMapDrawer(List<State> states, List<Twit> twitsWithSentiments, Map<State, String> categorizedStates) {
        this.states = states;
        this.twitsWithSentiments = twitsWithSentiments;
        this.categorizedStates = categorizedStates;

        setTitle("State Map Drawer");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                DrawerService drawerService = new DrawerService();

                // Рисуем штаты с цветами
                drawerService.drawStates(g, states, categorizedStates);

                // Рисуем твиты
                drawerService.drawTwits(g, twitsWithSentiments);
            }
        };

        add(panel);
        setVisible(true);
    }
}
