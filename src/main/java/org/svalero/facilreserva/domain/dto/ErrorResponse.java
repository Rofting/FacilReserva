package org.svalero.facilreserva.domain.dto;


import lombok.Data;

import java.util.Map;

@Data
public class ErrorResponse {

    private int code;
    private String message;
    private Map<String, String> errorMessages;

    // Constructor para errores generales
    private ErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    // Constructor para errores de validación
    private ErrorResponse(Map<String, String> errorMessages) {
        this.code = 400; // Código de error por defecto para solicitudes incorrectas
        this.message = "Bad Request";
        this.errorMessages = errorMessages;
    }

    // Método estático para manejar errores generales
    public static ErrorResponse generalError(int code, String message) {
        return new ErrorResponse(code, message);
    }

    // Método estático para manejar errores de validación
    public static ErrorResponse validationError(Map<String, String> errors) {
        return new ErrorResponse(errors);
    }

    // Método estático para errores específicos de un restaurante (ejemplo de customización)
    public static ErrorResponse restaurantError(String message) {
        return new ErrorResponse(404, "Restaurant Error: " + message);
    }

    // Método estático para errores específicos de una reserva (ejemplo de customización)
    public static ErrorResponse reservationError(String message) {
        return new ErrorResponse(404, "Reservation Error: " + message);
    }
}