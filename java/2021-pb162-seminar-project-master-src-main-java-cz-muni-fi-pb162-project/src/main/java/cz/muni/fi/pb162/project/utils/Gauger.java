package cz.muni.fi.pb162.project.utils;
import cz.muni.fi.pb162.project.geometry.Measurable;
import cz.muni.fi.pb162.project.geometry.Triangle;

/**
 * @author Lucia Helmeciova
 */
public class Gauger{

    /**
     *
     * @param object measurable object
     */
    public static void printMeasurement(Measurable object) {
        System.out.println("Width: " + object.getWidth());
        System.out.println("Height: " + object.getHeight());
        }

    /**
     *
     * @param triangle triangle object
     */
    public static void printMeasurement(Triangle triangle){
        System.out.println(triangle.toString());
        Measurable measurable = triangle;
        printMeasurement(measurable);
    }
}
