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
import jakarta.validation.constraints.NotNull;
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
   
	@NotNull(message = "La fecha de inicio es obligatoria")
	private LocalDateTime fechaInicio;
	
	@NotNull(message = "La fecha de fin es obligatoria")
	private LocalDateTime fechaFin; 
    private Double costeTotal;
    
    @NotNull(message = "El estado es obligatorio")
    @Enumerated(EnumType.STRING)
    private EstadoSesion estado;
    
    private String observaciones; 
    
    @NotNull(message = "El estudiante es obligatorio")
    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    @NotNull(message = "El tutor es obligatorio")
    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    @NotNull(message = "La materia es obligatoria")
    @ManyToOne
    @JoinColumn(name = "materia_id")
    private Materia materia;
}
