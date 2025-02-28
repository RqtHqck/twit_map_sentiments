package org.example.app.services;

import org.example.app.entities.Point;
import org.example.app.entities.State;
import org.example.app.entities.Twit;


import org.example.app.entities.Polygon;

import java.util.List;

public class StateService {
    public static boolean containsTwit(State state, Twit twit) {
        for (Polygon polygon : state.getPolygons()) {
            if (PolygonService.isPointInsidePolygon(polygon, twit.getLocation())) {
                return true;
            }
        }
        return false;
    }
}
