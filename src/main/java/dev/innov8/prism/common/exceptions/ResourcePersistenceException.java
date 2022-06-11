package dev.innov8.prism.common.exceptions;

@SuppressWarnings("unused")
public class ResourcePersistenceException extends RuntimeException {

    public ResourcePersistenceException(String message) {
        super(message);
    }

    public ResourcePersistenceException(Throwable cause) {
        super(cause);
    }

    public ResourcePersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

}
