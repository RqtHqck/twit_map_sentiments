package org.example;

//import org.example.app.service.StateService;
//import org.example.app.service.SentimentService;
import javax.swing.*;

import org.example.app.entities.Sentiment;
import org.example.app.entities.State;
import org.example.app.entities.Twit;
import org.example.app.process.Processor;
import org.example.app.services.DrawerService;
import org.example.app.utils.FileReaderUtil;
import org.example.app.utils.SentimentsParser;
import org.example.app.utils.StateParser;
import org.example.app.utils.TwitParser;

import java.awt.*;
import java.io.IOException;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        try {
            Processor.runProgram();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
