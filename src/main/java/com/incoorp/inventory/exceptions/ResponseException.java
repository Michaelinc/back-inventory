package com.incoorp.inventory.exceptions;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author Cristian Camilo Gallego
 */
@Data
public class ResponseException {

    /**
     * Fecha de la excepción.
     */
    private Date timestamp;
    /**
     * Mensaje de la excepción.
     */
    private String message;
    /**
     * Detalles de la excepción.
     */
    private String detalles;

    /**
     * Constructor de la clase ResponseException.
     *
     * @param timestamp Fecha de la excepción.
     * @param message Mensaje de la excepción.
     * @param detalles Detalles de la excepción.
     */
    public ResponseException(final Date timestamp, final String message,
            final String detalles) {
        this.timestamp = timestamp;
        this.message = message;
        this.detalles = detalles;
    }

}
