package com.salesianostriana.dam.gonzalotorres_tutormatch.model;

import java.time.LocalDateTime;

import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.EstadoSesion;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class SesionTutoria {

	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
   
    private LocalDateTime fecha; 
    private Double duracionHoras; 
    private Double costeTotal;
    
    @Enumerated(EnumType.STRING)
    private EstadoSesion estado;
    
    private String observaciones; 
    
    @ManyToOne
    @JoinColumn (name = "estudiante_id")
    private Estudiante estudiante;
    
    @ManyToOne
    @JoinColumn (name = "tutor_id")
    private Tutor tutor; 
    
    @ManyToOne
    @JoinColumn(name = "materia_id")
    private Materia materia;
}
