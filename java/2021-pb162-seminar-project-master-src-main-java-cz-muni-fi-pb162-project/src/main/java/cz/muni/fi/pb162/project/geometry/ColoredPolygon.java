package cz.muni.fi.pb162.project.geometry;

import java.util.Objects;

/**
 * @author Lucia Helmeciova
 */
public class ColoredPolygon {
    private Color color;
    private Polygon polygon;

    /**
     * @param myPolygon takes polygon
     * @param myColor   takes color
     */

    public ColoredPolygon(Polygon myPolygon, Color myColor) {
        this.polygon = myPolygon;
        this.color = myColor;
    }

    /**
     * @return color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param o object o
     * @return true if they are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ColoredPolygon that = (ColoredPolygon) o;
        return color == that.color && Objects.equals(polygon, that.polygon);
    }

    /**
     * @return returns hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(color, polygon);
    }

    /**
     * @return polygon
     */
    public Polygon getPolygon() {
        return polygon;
    }

}
