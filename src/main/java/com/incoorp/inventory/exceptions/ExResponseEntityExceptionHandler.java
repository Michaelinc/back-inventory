package com.incoorp.inventory.exceptions;

import com.incoorp.inventory.exceptions.responses.BadRequestException;
import com.incoorp.inventory.exceptions.responses.InternalServerErrorException;
import java.util.Date;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author Cristian Camilo Gallego
 */
@ControllerAdvice
@RestController
public class ExResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    /**
     * ExceptionHandler para la excepción NOT_FOUND.
     *
     * @param ex Excepción.
     * @param request Información de la petición.
     * @return ResponseException con información de la excepción.
     */
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ResponseException>
            handleNotFoundException(final NotFoundException ex,
                    final WebRequest request) {
        ResponseException exceptionResponse = new ResponseException(new Date(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * ExceptionHandler para la excepción BAD_REQUEST.
     *
     * @param ex Información de la excepción.
     * @param request Información de la petición.
     * @return ResponseException con información de la excepción.
     */
    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ResponseException>
            handleBadRequestException(final BadRequestException ex,
                    final WebRequest request) {
        ResponseException exceptionResponse = new ResponseException(new Date(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * ExceptionHandler para la excepción INTERNAL_SERVER_ERROR.
     *
     * @param ex Información de la excepción.
     * @param request Información de la petición.
     * @return ResponseException con información de la excepción.
     */
    @ExceptionHandler(InternalServerErrorException.class)
    public final ResponseEntity<ResponseException>
            handleInternalServerErrorException(final InternalServerErrorException ex,
                    final WebRequest request) {
        ResponseException exc = new ResponseException(new Date(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exc, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
