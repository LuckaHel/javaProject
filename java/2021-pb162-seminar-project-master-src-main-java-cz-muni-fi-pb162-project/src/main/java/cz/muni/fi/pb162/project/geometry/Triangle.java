package cz.muni.fi.pb162.project.geometry;


/**
 * @author Lucia Helmeciova
 */
public class Triangle extends ArrayPolygon implements Measurable {
    private final Triangle[] arrayOfTriangles = new Triangle[3];
    private static final double CONSTANT = 0.001;


    /**
     * @param vertex2D1 - vertex1
     * @param vertex2D2 - vertex2
     * @param vertex2D3 - vertex3
     */
    public Triangle(Vertex2D vertex2D1, Vertex2D vertex2D2, Vertex2D vertex2D3) {
        super(new Vertex2D[]{vertex2D1, vertex2D2, vertex2D3});
    }

    /**
     * @param vertex2D1 - in for Vertex2D takes first vertex
     * @param vertex2D2 - in for Vertex2D takes second vertex
     * @param vertex2D3 - in for Vertex2D takes third vertex
     * @param depth     - depth of recursion
     */


    public Triangle(Vertex2D vertex2D1, Vertex2D vertex2D2, Vertex2D vertex2D3, double depth) {
        this(vertex2D1, vertex2D2, vertex2D3);
        divide(depth);

    }

    /**
     * @return bool depending on whether triangle is or is not equilateral
     */
    public boolean isEquilateral() {
        double side1 = getVertices()[0].distance(getVertices()[1]);
        double side2 = getVertices()[0].distance(getVertices()[2]);
        double side3 = getVertices()[1].distance(getVertices()[2]);
        return Math.abs(side1 - side2) < CONSTANT & Math.abs(side1 - side3)
                < CONSTANT & Math.abs(side2 - side3) < CONSTANT;
    }

    /**
     * makes fractal triangle
     *
     * @param depth - depth of recurcion
     */
    public void divide(double depth) {
        if (depth != 0) {
            this.divide();
            for (int i = 0; i < 3; i++) {
                this.arrayOfTriangles[i].divide(depth - 1);
            }
        }
    }


    /**
     * @return formatted string
     */
    public String toString() {
        return ("Triangle: vertices=" + getVertex(0).toString() + " "
                + getVertex(1).toString() + " "
                + getVertex(2).toString());

    }

    /**
     * @param index -
     * @return null if index is incorrect or arrayOfTriangles otherwise
     */
    public Triangle getSubTriangle(int index) {
        if (index < 0 || index > 2) {
            return null;
        }
        return arrayOfTriangles[index];
    }

    /**
     * @return true / false depending on whether it is was possible to divide triangle
     */

    public boolean divide() {
        if (isDivided()) {
            return false;
        }
        Vertex2D v1 = getVertex(0);
        Vertex2D v2 = getVertex(1);
        Vertex2D v3 = getVertex(2);
        Vertex2D v01 = v1.createMiddle(v2);
        Vertex2D v02 = v1.createMiddle(v3);
        Vertex2D v03 = v2.createMiddle(v3);
        arrayOfTriangles[0] = new Triangle(v1, v01, v02);
        arrayOfTriangles[1] = new Triangle(v2, v01, v03);
        arrayOfTriangles[2] = new Triangle(v3, v02, v03);

        return true;

    }


    /**
     * @return boolean true if is divided, false otherwise
     */
    public boolean isDivided() {
        if (arrayOfTriangles[0] != null & arrayOfTriangles[1] != null & arrayOfTriangles[2] != null) {
            return true;
        }
        return false;
    }
}
