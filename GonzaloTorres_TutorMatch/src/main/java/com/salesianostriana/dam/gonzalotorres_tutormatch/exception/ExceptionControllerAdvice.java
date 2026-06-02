package com.salesianostriana.dam.gonzalotorres_tutormatch.exception;

import java.util.NoSuchElementException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

	@ControllerAdvice
	public class ExceptionControllerAdvice {

	
	    @ExceptionHandler(SesionSolapadaException.class)
	    public String handleSesionSolapada(SesionSolapadaException ex, Model model) {
	        model.addAttribute("errorTitulo", "Sesión Solapada");
	        model.addAttribute("errorMensaje", ex.getMessage());
	        return "error";
	    }
	
	    @ExceptionHandler(DuracionInvalidaException.class)
	    public String handleDuracionInvalida(DuracionInvalidaException ex, Model model) {
	        model.addAttribute("errorTitulo", "Duración Inválida");
	        model.addAttribute("errorMensaje", ex.getMessage());
	        return "error";
	    }
	
	    @ExceptionHandler(TarifaInvalidaException.class)
	    public String handleTarifaInvalida(TarifaInvalidaException ex, Model model) {
	        model.addAttribute("errorTitulo", "Tarifa Inválida");
	        model.addAttribute("errorMensaje", ex.getMessage());
	        return "error";
	    }
	
	    @ExceptionHandler(NoSuchElementException.class)
	    public String handleNotFound(NoSuchElementException ex, Model model) {
	        model.addAttribute("errorTitulo", "Elemento No Encontrado");
	        model.addAttribute("errorMensaje", "El elemento solicitado no existe en la base de datos.");
	        return "error";
	    }
	    @ExceptionHandler(NivelEstudianteInvalidoException.class)
	    public String handleNivelInvalido(NivelEstudianteInvalidoException ex, Model model) {
	        model.addAttribute("errorTitulo", "Nivel No Compatible");
	        model.addAttribute("errorMensaje", ex.getMessage());
	        return "error";
	    }
	    @ExceptionHandler(AdminNoBorrableException.class)
	    public String handleAdminNoborrable(AdminNoBorrableException ex, Model model) {
	        model.addAttribute("errorTitulo", "Operación No Permitida");
	        model.addAttribute("errorMensaje", ex.getMessage());
	        return "error";
	    }
	}
