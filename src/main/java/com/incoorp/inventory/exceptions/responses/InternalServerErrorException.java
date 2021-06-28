package com.incoorp.inventory.exceptions.responses;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Cristian Camilo Gallego
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends RuntimeException {

    /**
     * Captura el mensaje de la excepción INTERNAL_SERVER_ERROR.
     *
     * @param exception Mensaje de la excepción.
     */
    public InternalServerErrorException(final String exception) {
        super(exception);
    }

}
