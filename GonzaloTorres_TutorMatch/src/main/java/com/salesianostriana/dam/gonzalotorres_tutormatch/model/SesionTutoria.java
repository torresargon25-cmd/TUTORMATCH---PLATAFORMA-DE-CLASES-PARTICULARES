package com.salesianostriana.dam.gonzalotorres_tutormatch.model;

import java.time.LocalDateTime;

import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.EstadoSesion;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SesionTutoria {

	
	@Id @GeneratedValue
	private Long id;
    
    private Tutor tutor; 
    private Estudiante estudiante;
    private Materia materia; 
    private LocalDateTime fecha; 
    private Double duracionHoras; 
    private Double costeTotal;  
    private EstadoSesion estado; 
    private String observaciones; 
	
}
