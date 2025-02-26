package org.example.app.services;

import org.example.app.entities.Point;
import org.example.app.entities.Polygon;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
//import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
//import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;
//import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygonImpl;

//import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;


public class DrawerService {
    private JMapViewer mapViewer;

    public DrawerService() {
        mapViewer = new JMapViewer(); // Инициализация карты
    }
//
//    public StackPane getMapPane() {
//        StackPane stackPane = new StackPane();
//        stackPane.getChildren().add(mapViewer);
//        return stackPane;
//    }
//
//    public void drawPolygon(Polygon polygon) {
//        List<MapMarker> polygonPoints = new ArrayList<>();
//
//        for (Point point : polygon.getPoints()) {
//            polygonPoints.add(new org.openstreetmap.gui.jmapviewer.MapMarkerDot(point.getLatitude(), point.getLongitude()));
//        }
//
//        MapPolygon mapPolygon = new MapPolygonImpl(polygonPoints);
//        mapViewer.addMapPolygon(mapPolygon);
//    }
//
//    public void drawStatePolygons(List<Polygon> polygons) {
//        for (Polygon polygon : polygons) {
//            drawPolygon(polygon); // Отрисовываем каждый полигон
//        }
//    }
//
//    public void clearMap() {
//        mapViewer.removeAllMapMarkers();  // Очистка карты
//        mapViewer.removeAllMapPolygons(); // Очистка полигона
//    }

}
