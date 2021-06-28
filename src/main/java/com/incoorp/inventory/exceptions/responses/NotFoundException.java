package com.incoorp.inventory.exceptions.responses;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Cristian Camilo Gallego
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    /**
     * Captura el mensaje de la excepción NOT_FOUND.
     *
     * @param exception Mensaje de la excepción.
     */
    public NotFoundException(final String exception) {
        super(exception);
    }

}
