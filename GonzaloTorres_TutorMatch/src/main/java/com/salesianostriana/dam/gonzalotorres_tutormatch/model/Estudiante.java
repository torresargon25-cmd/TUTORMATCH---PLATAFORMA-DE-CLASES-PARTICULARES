package com.salesianostriana.dam.gonzalotorres_tutormatch.model;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.NivelEstudiante;
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
public class Estudiante {
	
		@Id @GeneratedValue
	    private Long id;
		
	    private String nombre; 
	    private String apellidos;
	    
	    @Column(unique = true)
	    private String email;
	    
	    private String password;
	    
	    @Enumerated(EnumType.STRING)
	    private Rol rol;
	    
	    @Enumerated(EnumType.STRING)
	    private NivelEstudiante nivel;
	    
	    private String telefono; 
	    private LocalDate fechaNac; 
	    private LocalDateTime fechaAlta; 
	    
	    // Datos Tutor Legal 
	    private String nombreTutorLeg;
	    private String apellidosTutorLeg;
	    private String dniTutorLeg;
	    
	    @OneToMany(mappedBy = "estudiante")
	    private List <SesionTutoria> listaSesiones = new ArrayList <>();
	}
	

