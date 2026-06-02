package com.salesianostriana.dam.gonzalotorres_tutormatch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public class DuracionInvalidaException extends RuntimeException {
		public DuracionInvalidaException(String mensaje) {
			super(mensaje);
		}
	}
