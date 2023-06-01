package cz.muni.fi.pb162.project.comparator;

import cz.muni.fi.pb162.project.geometry.Vertex2D;

import java.util.Comparator;

/**
 * @author lucka helmeciova
 */
public class VertexInverseComparator implements Comparator<Vertex2D> {
    /**
     * @param o1 vertex 1
     * @param o2 vertex 2
     * @return int according to equals method
     */
    @Override
    public int compare(Vertex2D o1, Vertex2D o2) {
        if (o1.getX() == o2.getX()) {
            return (int) (o2.getY() - o1.getY());
        }
        if (o2.getX() - o1.getX() < 0) {
            return -1;
        } else {
            return 1;
        }
    }
}
