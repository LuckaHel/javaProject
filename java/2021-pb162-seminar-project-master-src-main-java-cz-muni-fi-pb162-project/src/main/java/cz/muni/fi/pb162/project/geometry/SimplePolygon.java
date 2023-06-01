package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.exception.MissingVerticesException;
import cz.muni.fi.pb162.project.utils.SimpleMath;


/**
 * @author Lucka Helmeciova
 */
public abstract class SimplePolygon implements Polygon {
    /**
     *
     * @param vertices array of vertices
     * @throws MissingVerticesException too little vertices
     * @throws IllegalArgumentException null argument
     */
    public SimplePolygon(Vertex2D[] vertices) throws MissingVerticesException, IllegalArgumentException{
        if (vertices == null) {
            throw new IllegalArgumentException("Wrong number of vertices");
        }
        int abacus = 0;
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] == null) {
                throw new IllegalArgumentException("Not a polygon");
            }
            abacus += 1;
        }
        if (abacus < 3) {
            throw new MissingVerticesException("Not a polygon");
        }
    }

    protected SimplePolygon() {
    }

    /**
     * @return width
     */
    @Override
    public double getWidth() {
        return SimpleMath.maxX(this) - SimpleMath.minX(this);
    }

    /**
     * @return height
     */
    @Override
    public double getHeight() {
        return SimpleMath.maxY(this) - SimpleMath.minY(this);
    }

    /**
     *
     * @param index vertex index, not negative number
     * @return Vertex2D
     */
    public abstract Vertex2D getVertex(int index);

    /**
     * @return number of vertices
     */
    @Override
    public abstract int getNumVertices();

    /**
     * @return info string
     */

    @Override
    public String toString() {
        String result = "Polygon: vertices = ";
        for (int i = 0; i < this.getNumVertices() - 1; i++) {
            result += this.getVertex(i).toString() + " ";

        }
        return result += this.getVertex(this.getNumVertices() - 1).toString();
    }

}
