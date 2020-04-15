package by.chekun.exception.handler;

import by.chekun.controller.exception.IllegalRequestException;
import by.chekun.service.exception.ResourceNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.net.BindException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ControllerAdvice
public class DefaultExceptionHandler {

    private static final Logger logger = LogManager.getLogger(DefaultExceptionHandler.class);

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleResourceNotFound(ResourceNotFoundException e) {
        /*
         *  Exception occurs when passed id is null. Status 404.
         */
        logger.error(e.getMessage());
        String message = Objects.isNull(e.getMessage()) ? "" : e.getMessage();
        return new ResponseEntity<>(
                new ErrorMessage("Resource not found! " + message),
                HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(IllegalRequestException.class)
    public ResponseEntity<List<ErrorMessage>> handleValidation(IllegalRequestException e) {
        /*
         *  Validation exceptions handling. Status code 400.
         */
        List<ErrorMessage> errors = new ArrayList<>();
        e.getErrors().forEach(er -> errors.add(
                new ErrorMessage(String.format("Incorrect value for field %s:  %s.", er.getField(), er.getDefaultMessage())))
        );
        errors.forEach(er -> logger.error(er.getMessage()));
        return new ResponseEntity<>(
                errors,
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, BindException.class,
            UnsatisfiedServletRequestParameterException.class, IllegalArgumentException.class,
            MethodArgumentTypeMismatchException.class, HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ErrorMessage> handleJsonMappingException(Exception e) {
        /*
         * Exception occurs when passed id is null. Status 400.
         */
        logger.error(e.getMessage());
        return new ResponseEntity<>(new ErrorMessage("Request parameters are not valid!"), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleOthersException(Exception e) {
        /*
         *  Handles all other exceptions. Status code 500.
         */
        e.printStackTrace();
        logger.error(e.getMessage());
        return new ResponseEntity<>(
                new ErrorMessage("Internal server error."),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
