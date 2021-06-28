package com.incoorp.inventory.exceptions.responses;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Cristian Camilo Gallego
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    /**
     * Captura el mensaje de la excepción BAD_REQUEST.
     *
     * @param exception Mensaje de la excepción.
     */
    public BadRequestException(final String exception) {
        super(exception);
    }

}
