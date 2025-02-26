package org.example.app.entities;
import java.time.LocalDate;

public class Twit {
    private double latitude;  // Широта
    private double longitude; // Долгота
    private LocalDate date;   // Дата
    private String text;      // Текст твита
    private Double sentiment; // Настроение твита (может быть null)

}
