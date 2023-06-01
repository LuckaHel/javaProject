package cz.muni.fi.pb162.project.geometry;

/**
 * @author Lucia Helmeciova
 */
public class Square extends GeneralRegularPolygon implements Circular {


    /**
     * @param center   - center of circle
     * @param diameter - radius of circle
     */
    public Square(Vertex2D center, double diameter) {
        super(center, 4, diameter / 2);
        /**vertices[0] = new Vertex2D (center.getX() -diameter/2, center.getY());
         vertices[1] = new Vertex2D(center.getX(),center.getY() - diameter/2);
         vertices[2] = new Vertex2D(center.getX() + diameter/2, center.getY());
         vertices[3] = new Vertex2D(center.getX(),center.getY() + diameter/2);*/
    }

    /**
     * @param object creates new square object
     */

    public Square(Circular object) {
        this(new Vertex2D(object.getCenter().getX(), object.getCenter().getY()), object.getRadius() * 2);
    }

    /***
     *
     * @return info about square
     */
    public String toString() {
        return "Square: vertices=" + getVertex(0).toString() + " " + getVertex(1).toString() + " "
                + getVertex(2).toString() + " "
                + getVertex(3).toString();
    }
}
