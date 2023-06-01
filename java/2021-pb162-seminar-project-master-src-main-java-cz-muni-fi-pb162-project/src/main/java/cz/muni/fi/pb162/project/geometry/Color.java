package cz.muni.fi.pb162.project.geometry;

/**
 * @author Lucia Helmeciova
 */

public enum Color {
    RED,
    YELLOW,
    ORANGE,
    BLACK,
    WHITE,
    BLUE,
    GREEN;

    /**
     * @return color name
     */
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
