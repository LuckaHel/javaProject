package cz.muni.fi.pb162.project.geometry;

import static cz.muni.fi.pb162.project.geometry.Color.BLACK;

/**
 * @author Lucia Helmeciova
 */
public class GeneralRegularPolygon implements RegularPolygon, Colored {
    private Vertex2D center;
    private int numberOfEdges;
    private double radius;
    private Color myColor = BLACK;

    /**
     * @param center        - center of polygon
     * @param numberOfEdges - of polygon
     * @param radius        - of polygon
     */
    public GeneralRegularPolygon(Vertex2D center, int numberOfEdges, double radius) {
        this.center = center;
        this.numberOfEdges = numberOfEdges;
        this.radius = radius;
    }

    /**
     * @return center - of polygon
     */
    @Override
    public Vertex2D getCenter() {
        return center;
    }

    /***
     *
     * @return radius - of polygon
     */

    @Override
    public double getRadius() {
        return radius;
    }

    /**
     * @return color - of polygon
     */
    @Override
    public Color getColor() {
        return myColor;
    }

    /**
     * @param color sets color to polygon
     */

    @Override
    public void setColor(Color color) {
        this.myColor = color;
    }

    /**
     * @return width - of polygon
     */

    @Override
    public double getWidth() {
        return radius * 2;
    }

    /**
     * @return height - of polygon
     */
    @Override
    public double getHeight() {
        return radius * 2;
    }

    /**
     * @return num of edges - of polygon
     */

    @Override
    public int getNumEdges() {
        return numberOfEdges;
    }

    /**
     * @return edgelenght - of polygon
     */

    @Override
    public double getEdgeLength() {
        return 2 * radius * Math.sin(Math.PI / numberOfEdges);
    }

    /**
     * @param index integral number of index
     * @return new vertex
     */

    @Override
    public Vertex2D getVertex(int index) {
        double x = center.getX() - radius * Math.cos(index * 2 * Math.PI / numberOfEdges);
        double y = center.getY() - radius * Math.sin(index * 2 * Math.PI / numberOfEdges);
        return new Vertex2D(x, y);
    }

    /**
     * @return info - of polygon
     */

    public String toString() {
        return numberOfEdges + "-gon: " + "center=" + center + ", " + "radius=" + radius + ", " + "color=" + myColor;
    }
}
