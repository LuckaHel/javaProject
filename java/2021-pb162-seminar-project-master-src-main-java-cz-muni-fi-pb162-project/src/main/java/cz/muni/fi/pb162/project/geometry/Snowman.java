package cz.muni.fi.pb162.project.geometry;

/**
 * @author Lucia Helmeciova
 */
public class Snowman {
    private double defaultFactor = 0.8;
    private RegularPolygon[] polygons = new RegularPolygon[3];

    /**
     * @param polygon         - takes General polygon
     * @param reductionFactor and reduction factor
     */

    public Snowman(RegularPolygon polygon, double reductionFactor) {
        polygons[0] = new GeneralRegularPolygon(new Vertex2D(polygon.getCenter().getX(), polygon.getCenter().getY())
                , polygon.getNumEdges(), polygon.getRadius());

        if (reductionFactor <= 0 || reductionFactor > 1) {
            reductionFactor = defaultFactor;
        }
        for (int i = 0; i < 2; i++) {
            double myRadius = polygons[i].getRadius();
            double xCoordinate = polygons[i].getCenter().getX();
            double yCoordinate = polygons[i].getCenter().getY();
            double myFactor = myRadius * reductionFactor;
            RegularPolygon myPolygon = new GeneralRegularPolygon(new Vertex2D(xCoordinate, yCoordinate +
                    myRadius + myFactor), polygon.getNumEdges(), myFactor) {
            };
            polygons[i + 1] = myPolygon;
        }
    }


    /**
     * @return array of polygons to draw
     */
    public RegularPolygon[] getBalls() {
        return polygons;
    }

}
