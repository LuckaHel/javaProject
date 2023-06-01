package cz.muni.fi.pb162.project.geometry;

import java.util.Arrays;

/**
 * @author Lucia Helmeciova
 */
public  class ArrayPolygon extends SimplePolygon {

   private Vertex2D[] vertices;

    /**
     * constructor
     *
     * @param vertices - array of Vertex2D
     */
    public ArrayPolygon(Vertex2D[] vertices) {
        super(vertices);
        Vertex2D[] arr2 = Arrays.copyOf(vertices, vertices.length);
        this.vertices = arr2;
    }

    /**
     * @param i - index of wanted vertex
     * @return - vertex on index
     */
    @Override
    public Vertex2D getVertex(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Incorrect index");
        }
        return vertices[i % vertices.length];
    }

    /**
     * @param o - object
     * @return true if objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        ArrayPolygon that = (ArrayPolygon) o;
        if (that.getVertices().length != this.getVertices().length) {
            return false;
        }
        for (int i = 0; i < that.getVertices().length; i++) {
            if (this.getVertices()[i].getX() != that.getVertices()[i].getX() || this.getVertices()[i].getY() !=
                    that.getVertices()[i].getY()) {
                return false;
            }
        }
        return true;

    }

    /**
     * @return hash code
     */

    @Override
    public int hashCode() {
        return Arrays.hashCode(vertices);
    }

    /**
     * @return vertices
     */
    public Vertex2D[] getVertices() {
        return vertices;
    }

    /**
     * @return number of vertices
     */
    @Override
    public int getNumVertices() {
        return this.getVertices().length;
    }
}
