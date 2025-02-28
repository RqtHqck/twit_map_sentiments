package org.example.app.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.app.entities.Point;
import org.example.app.entities.Polygon;
import org.example.app.entities.State;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StateParser {

    public static List<State> parseStates(String inputJson) {

        List<State> states = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // СОздаём нодовое дерево JSON
            JsonNode rootNode = objectMapper.readTree(inputJson);

            Iterator<String> stateNames = rootNode.fieldNames();

            while (stateNames.hasNext()) {
                String stateName = stateNames.next();
                State state = parseState(rootNode, stateName);

                states.add(state);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return states;
    }

    private static State parseState(JsonNode rootNode, String stateName) {

        // Создаем объект State
        List<Polygon> polygons = new ArrayList<>();
        JsonNode polygonsNode = rootNode.get(stateName);

        if (polygonsNode != null && polygonsNode.isArray()) {
            for (JsonNode polygonNode : polygonsNode) {
                Polygon polygon = parsePolygon(polygonNode);
                polygons.add(polygon);
            }
        }

        return new State(stateName, polygons);
    }

    private static Polygon parsePolygon(JsonNode polygonNode) {

        // Создаем объект Polygon
        List<Point> points = new ArrayList<>();

        if (polygonNode.isArray()) {

            // Проверяем, есть ли внутренние массивы (острова)
            if (!polygonNode.isEmpty() && polygonNode.get(0).isArray()) {
                if (polygonNode.get(0).size() == 2 && polygonNode.get(0).get(0).isNumber()) {
                    parsePoints(polygonNode, points);
                } else {
                    parseIslands(polygonNode, points);
                }
            }
        }

        return new Polygon(points);
    }

    private static void parseIslands(JsonNode polygonNode, List<Point> points) {

        // Обрабатываем острова
        for (JsonNode islandNode : polygonNode) {
            if (islandNode.isArray()) {
                parsePoints(islandNode, points);
            }
        }
    }

    private static void parsePoints(JsonNode pointsNode, List<Point> points) {

        // Обрабатываем все точки в полигоне
        for (JsonNode pointNode : pointsNode) {
            if (pointNode.isArray() && pointNode.size() == 2) {
                Point point = parsePoint(pointNode);
                points.add(point);
            }
        }
    }

    private static Point parsePoint(JsonNode pointNode) {

        // Создаем объект Point
        double longitude = pointNode.get(0).asDouble();
        double latitude = pointNode.get(1).asDouble();
        return new Point(latitude, longitude);
    }
}
