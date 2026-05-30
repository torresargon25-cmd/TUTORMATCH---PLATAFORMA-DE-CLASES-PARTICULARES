package com.salesianostriana.dam.gonzalotorres_tutormatch.exception;

public class UsuarioNoEncontradoException extends RuntimeException {
    public UsuarioNoEncontradoException(String username) {
        super("Usuario no encontrado: " + username);
    }
}