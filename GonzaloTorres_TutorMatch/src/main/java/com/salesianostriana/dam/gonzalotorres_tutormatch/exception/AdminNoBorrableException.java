package com.salesianostriana.dam.gonzalotorres_tutormatch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AdminNoBorrableException extends RuntimeException {
    public AdminNoBorrableException() {
        super("No se puede eliminar al administrador del sistema");
    }
}
