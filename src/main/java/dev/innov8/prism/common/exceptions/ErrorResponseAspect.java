package dev.innov8.prism.common.exceptions;

import com.fasterxml.jackson.core.JacksonException;
import dev.innov8.prism.common.dtos.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorResponseAspect {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
        InvalidRequestException.class,
        JacksonException.class,
        BindException.class,
        HttpMessageConversionException.class,
        JpaObjectRetrievalFailureException.class
    })
    public ErrorResponse handleBadRequests(Exception e) {
        return new ErrorResponse(400, e);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationExceptions(MethodArgumentNotValidException e) {
        List<String> invalidFieldNames = e.getFieldErrors().stream().map(FieldError::getField).collect(Collectors.toList());
        List<Object> defaultMessages = e.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        String msg = String.format("The following request body values were found to be invalid: %s; respective causes: %s", invalidFieldNames, defaultMessages);
        return new ErrorResponse(400, msg, e);
    }

    @ExceptionHandler({
        AuthenticationException.class,
        InvalidTokenException.class
    })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleAuthenticationException(Exception e) {
        return new ErrorResponse(401, e);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleAuthorizationException(AuthorizationException e) {
        return new ErrorResponse(403, e);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ErrorResponse(404, e);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleResourceNotFoundException(ResourcePersistenceException e) {
        return new ErrorResponse(409, e);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleOtherExceptions(Exception e) {
        e.printStackTrace();
        return new ErrorResponse(500, "An internal server exception occurred. Please check the server logs for more info.", e);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ErrorResponse handleUnsupportedMediaType(HttpMediaTypeNotSupportedException e) {
        return new ErrorResponse(415, e);
    }


}
