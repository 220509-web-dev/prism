package dev.innov8.prism.common.exceptions;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(Throwable cause) {
        super(cause);
    }

    public InvalidRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRequestException(String msg) {
        super(msg);
    }
}
