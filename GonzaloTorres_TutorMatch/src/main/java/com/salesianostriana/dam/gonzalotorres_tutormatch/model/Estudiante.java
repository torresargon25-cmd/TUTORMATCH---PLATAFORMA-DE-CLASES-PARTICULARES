package com.salesianostriana.dam.gonzalotorres_tutormatch.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Estudiante {
	
	@Id @GeneratedValue
	private Long id;
	
	private String nombre;
	private String apellidos;
	private String contactoEmerg;
	private ArrayList <String> listaAsign;
	private String cursoAcademico;
	private Date fechaNac;
	private LocalDateTime fechaAlta;
	private String email;
	private boolean nivel;
	private String telefono;
	
}
