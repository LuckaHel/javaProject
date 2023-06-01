package cz.muni.fi.pb162.project.exception;

/**
 * @author lucka helmeciova
 */
public class TransparentColorException extends Exception {
    /**
     * @param text of exception
     */
    public TransparentColorException(String text) {
        super(text);
    }

    /**
     * @param message what went wrong
     * @param cause   what exception caused it
     */
    public TransparentColorException(String message, Throwable cause) {
        super(message, cause);
    }
}
