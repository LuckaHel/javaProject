package cz.muni.fi.pb162.project.geometry;

import java.util.Objects;

/**
 * @author Lucia Helmeciova
 */

public final class Vertex2D implements Comparable<Vertex2D> {

    private final double x;
    private final double y;

    /**
     * @param xCoordinate - takes first coordinate
     * @param yCoordinate - takes second coordinate
     */
    public Vertex2D(double xCoordinate, double yCoordinate) {
        this.x = xCoordinate;
        this.y = yCoordinate;
    }


    /**
     * takes class Vertex2D and returns a formatted coordinate in form [X,Y]
     * <p>
     *
     * @return String, formatted coordinates .
     */
    @Override
    public String toString() {
        return ("[" + x + ", " + y + "]");
    }

    /**
     * @param vertex2D - two vertices
     * @return - Vertex2D - creates middle vertex of this two
     */
    public Vertex2D createMiddle(Vertex2D vertex2D) {
        double x1 = (x + vertex2D.x) / 2;
        double y2 = (y + vertex2D.y) / 2;
        Vertex2D result = new Vertex2D(x1, y2);
        return result;
    }

    /**
     * @param point - takes one point
     * @return - distance
     */

    public double distance(Vertex2D point) {
        if (point == null) {
            return -1.0;
        }
        return Math.sqrt(Math.pow((x - point.x), 2) +
                Math.pow((y - point.y), 2));
    }

    /**
     * @return - x
     */

    public double getX() {
        return x;
    }

    /**
     * @return - y
     */
    public double getY() {
        return y;
    }

    /**
     * @param o input object
     * @return true/false
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        Vertex2D that = (Vertex2D) o;
        return this.x == that.x && this.y == that.y;
    }

    /**
     * @return hash number
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * @param o vertex
     * @return int according to comparing
     */
    @Override
    public int compareTo(Vertex2D o) {
        if (x == o.x) {
            return (int) (y - o.y);
        }
        if (x > o.x) {
            return 1;
        } else {
            return -1;
        }
    }
}
