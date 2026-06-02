package com.salesianostriana.dam.gonzalotorres_tutormatch.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.Rol;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "tutor")
@PrimaryKeyJoinColumn(name = "id")
@EqualsAndHashCode(callSuper = false)

@Valid
public class Tutor extends Usuario{

	@Pattern(regexp = "^[0-9]{8}[A-Z]$", message = "El DNI debe tener 8 números y una letra mayúscula")
	@NotBlank(message = "El DNI es obligatorio")
    private String dni;
	
	@NotBlank(message = "La especialidad es obligatoria")
    private String especialidad;
    
	@NotNull(message = "La tarifa es obligatoria")
	@Positive(message = "La tarifa debe ser mayor que 0")
    private Double tarifaHora;
    private Double puntuacionNivel;
    private String imagen;
   
    
    @OneToMany(mappedBy = "tutor")
    private List<SesionTutoria> sesiones = new ArrayList<>();
}
