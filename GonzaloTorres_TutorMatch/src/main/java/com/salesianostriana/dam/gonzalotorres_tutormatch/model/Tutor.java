package com.salesianostriana.dam.gonzalotorres_tutormatch.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.Rol;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Tutor {

			@Id @GeneratedValue
			private Long id;
			
		    private String nombre;
		    private String apellidos;
		    private String dni;
		    
		    @Column(unique = true)
		    private String email; 
		    
		    private String password;
		    
		    @Enumerated(EnumType.STRING)
		    private Rol rol;
		    
		    private String especialidad; 
		    private Double tarifaHora; 
		    private Boolean disponibilidad; 
		    private Double puntuacionNivel;
		    private LocalDate fechaNac;
		    private LocalDateTime fechaAlta; 
		    
		    @OneToMany (mappedBy = "tutor")
		    private List<SesionTutoria> sesiones;
		    
	
}
