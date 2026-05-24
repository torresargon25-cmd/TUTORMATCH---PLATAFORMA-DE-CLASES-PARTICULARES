package com.salesianostriana.dam.gonzalotorres_tutormatch.model;

import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.EtapaEducativa;

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
@Entity //Preguntar a Luismi
public class Materia {

		@Id @GeneratedValue
	    private Long id;
	    private String nombre; 
	    private String dificultad;  
	    private String descripcion; 
	    private EtapaEducativa etapa;
	    
	}
	

