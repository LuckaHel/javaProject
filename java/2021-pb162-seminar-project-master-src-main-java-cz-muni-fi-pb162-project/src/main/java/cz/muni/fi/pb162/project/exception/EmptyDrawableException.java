package cz.muni.fi.pb162.project.exception;

/**
 * @author lucka helmeciova
 */
public class EmptyDrawableException extends Exception {
    /**
     * @param text error message
     */
    public EmptyDrawableException(String text) {
        super(text);
    }

    /**
     * @param message error
     * @param cause   what caused it
     */
    public EmptyDrawableException(String message, Throwable cause) {
        super(message, cause);
    }

}
