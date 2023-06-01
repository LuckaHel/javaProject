package cz.muni.fi.pb162.project.exception;

/**
 * @author lucka helmeciova
 */
public class MissingVerticesException extends RuntimeException{
    /**
     *
     * @param text what went wrong
     */
    public MissingVerticesException (String text){
        super(text);
    }

    /**
     *
     * @param message what went wrong
     * @param cause cause of error
     */
    public MissingVerticesException(String message, Throwable cause) {
        super(message, cause);
    }
}
