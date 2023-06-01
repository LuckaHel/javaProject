package cz.muni.fi.pb162.project.geometry;

/**
 * @author Lucia Helmeciova
 */
public class Circle extends GeneralRegularPolygon implements Circular, Measurable {


    /**
     * @param center1 - center of circle
     * @param radius1 - radius of circle
     */
    public Circle(Vertex2D center1, double radius1) {
        super(center1, Integer.MAX_VALUE, radius1);
        setColor(Color.RED);
    }


    /**
     * creates basic circle
     */
    public Circle() {
        this(new Vertex2D(0, 0), 1.0);
    }


    /**
     * @return info about circle
     */

    public String toString() {
        return ("Circle: center=" + "[" + getCenter().getX() + ", " + getCenter().getY() + "]," +
                " " + "radius=" + getRadius());
    }

    /**
     * @return len of circle edge, therefore 0
     */
    @Override
    public double getEdgeLength() {
        return 0;
    }
}
