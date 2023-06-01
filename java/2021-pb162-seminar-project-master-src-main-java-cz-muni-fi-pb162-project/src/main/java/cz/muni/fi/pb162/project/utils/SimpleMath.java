package cz.muni.fi.pb162.project.utils;

import cz.muni.fi.pb162.project.geometry.Polygon;


/**
 * @author Lucia Helmeciova
 */

public class SimpleMath {
    /**
     * @param polygon object
     * @return min X coordinate
     */
    public static double minX(Polygon polygon) {
        double myMin = polygon.getVertex(0).getX();
        for (int i = 1; i <= polygon.getNumVertices(); i++) {
            if (polygon.getVertex(i).getX() < myMin) {
                myMin = polygon.getVertex(i).getX();
            }
        }
        return myMin;
    }

    /**
     * @param polygon object
     * @return min Y coordinate
     */
    public static double minY(Polygon polygon) {
        double myMin = polygon.getVertex(0).getY();
        for (int i = 1; i <= polygon.getNumVertices(); i++) {
            if (polygon.getVertex(i).getY() < myMin) {
                myMin = polygon.getVertex(i).getY();
            }
        }
        return myMin;
    }

    /**
     * @param polygon object
     * @return max X coordinate
     */
    public static double maxX(Polygon polygon) {
        double myMax = polygon.getVertex(0).getX();
        for (int i = 1; i <= polygon.getNumVertices(); i++) {
            if (polygon.getVertex(i).getX() > myMax) {
                myMax = polygon.getVertex(i).getX();
            }
        }
        return myMax;

    }

    /**
     * @param polygon object
     * @return max Y coordinate
     */
    public static double maxY(Polygon polygon) {
        double myMax = polygon.getVertex(0).getY();
        for (int i = 1; i <= polygon.getNumVertices(); i++) {
            if (polygon.getVertex(i).getY() > myMax) {
                myMax = polygon.getVertex(i).getY();
            }
        }
        return myMax;
    }
}

