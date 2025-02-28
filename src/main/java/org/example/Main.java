package org.example;

import org.example.app.process.Processor;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        try {
            Processor.runProgram();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
