package cz.muni.fi.pb162.project.geometry;

import static cz.muni.fi.pb162.project.geometry.Color.BLACK;

/**
 * @author Lucia Helmeciova
 */

public class RegularOctagon extends GeneralRegularPolygon {
    /**
     * @param center - of polygon
     * @param radius - of polygon
     */

    public RegularOctagon(Vertex2D center, double radius) {
        super(center, 8, radius);
        setColor(BLACK);

    }


}
