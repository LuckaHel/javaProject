package cz.muni.fi.pb162.project.geometry;


import cz.muni.fi.pb162.project.exception.EmptyDrawableException;
import cz.muni.fi.pb162.project.exception.MissingVerticesException;
import cz.muni.fi.pb162.project.exception.TransparentColorException;

import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;
;


/**
 * @author Lucka helmeciova
 */
public class Paper implements Drawable, PolygonFactory {
    private HashSet<ColoredPolygon> polygonCollection;
    private Color mycolor = Color.BLACK;

    /**
     * simple constructor
     */
    public Paper() {
        polygonCollection = new HashSet<>();

    }

    /**
     * @param myCollection drawable collection
     */

    public Paper(Drawable myCollection) {
        this.polygonCollection = new HashSet<>();
        this.polygonCollection.addAll(myCollection.getAllDrawnPolygons());

    }

    /**
     * @param color the color which the following drawn objects are going to have
     */
    @Override
    public void changeColor(Color color) {
        this.mycolor = color;

    }

    /**
     * @param polygon polygon to be drawn
     */
    @Override
    public void drawPolygon(Polygon polygon) throws TransparentColorException {
        if (mycolor.equals(Color.WHITE)) {
            throw new TransparentColorException("The color you are using won't be visible, you are using "
                    + this.mycolor);
        }
        if (polygon == null) {
            return;
        }
        ColoredPolygon coloredPolygon = new ColoredPolygon(polygon, mycolor);
        for (Object poly : polygonCollection) {
            if (poly.equals(coloredPolygon)) {
                return;
            }
        }
        polygonCollection.add(coloredPolygon);

    }

    /**
     * @param polygon polygon to be ereased
     */
    @Override
    public void erasePolygon(ColoredPolygon polygon) {
        polygonCollection.clear();
    }

    /**
     * ereases all polygons
     */
    @Override
    public void eraseAll() throws EmptyDrawableException {
        if (polygonCollection.isEmpty()) {
            throw new EmptyDrawableException("Paper is already clear");
        }
        polygonCollection.removeAll(polygonCollection);

    }

    /**
     * @return collection of drawn polygons
     */
    @Override
    public Collection<ColoredPolygon> getAllDrawnPolygons() {
        return Collections.unmodifiableCollection(polygonCollection);
    }

    /**
     * @return num of unique vertices
     */

    @Override
    public int uniqueVerticesAmount() {
        Set<Vertex2D> mySet = new HashSet<>();
        for (ColoredPolygon poly : polygonCollection) {
            Polygon polygon = poly.getPolygon();
            for (int i = 0; i < polygon.getNumVertices(); i++) {
                mySet.add(polygon.getVertex(i));
            }
        }
        return mySet.size();
    }

    /**
     * @param vertices collection which the polygon can be built from; the collection is not modified
     * @return polygon
     * @throws MissingVerticesException
     */
    @Override
    public Polygon tryToCreatePolygon(List<Vertex2D> vertices) throws MissingVerticesException {
        if (vertices == null) {
            throw new NullPointerException();
        }
        List<Vertex2D> myList = new ArrayList<>();
        myList.addAll(vertices);
        if (myList.size() < 3) {
            throw new MissingVerticesException("Not enough vertices");
        }
        try {
            CollectionPolygon result = new CollectionPolygon(myList);

        } catch (IllegalArgumentException ex) {
            List<Vertex2D> myList1 = new ArrayList<>();
            for (Vertex2D vertex : vertices) {
                if (vertex != null) {
                    myList1.add(vertex);
                }
            }

            CollectionPolygon result = new CollectionPolygon(myList1);

            return result;
        }
        CollectionPolygon result = new CollectionPolygon(myList);
        return result;
    }

    /**
     * @param collectionPolygons collection of polygons (every polygon is collection of vertices)
     * @throws EmptyDrawableException
     * @throws MissingVerticesException
     */

    @Override
    public void tryToDrawPolygons(List<List<Vertex2D>> collectionPolygons) throws EmptyDrawableException,
            MissingVerticesException {
        if (collectionPolygons == null) {
            throw new EmptyDrawableException("No vertices in tryDrawPolygon");
        }
        boolean anyPainted = false;
        for (List<Vertex2D> list : collectionPolygons) {
            try {
                Polygon myPolygon = tryToCreatePolygon(list);
                drawPolygon(myPolygon);
                anyPainted = true;
            } catch (TransparentColorException a) {
                changeColor(Color.BLACK);
            } catch (NullPointerException | MissingVerticesException t) {
            }
        }
        if (!anyPainted) {
            Throwable a = new MissingVerticesException("");
            throw new EmptyDrawableException("nothing to draw here mate", a);
        }

    }

    /**
     * @param color *
     * @return all polygons of given color
     */

    public Collection<Polygon> getPolygonsWithColor(Color color) {
        List<Polygon> result = new ArrayList<>();
        List<ColoredPolygon> even = polygonCollection.stream().filter(s -> s.getColor().equals(color))
                .collect(Collectors.toList());
        for (ColoredPolygon thing : even) {
            result.add(thing.getPolygon());
        }
        return Collections.unmodifiableCollection(result);


    }
}
