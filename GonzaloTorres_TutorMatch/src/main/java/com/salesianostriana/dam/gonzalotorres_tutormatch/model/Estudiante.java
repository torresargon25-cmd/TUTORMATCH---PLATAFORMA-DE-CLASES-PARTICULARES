package com.salesianostriana.dam.gonzalotorres_tutormatch.model;

import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.NivelEstudiante;
import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.Rol;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "estudiante")
@PrimaryKeyJoinColumn(name = "id")
@EqualsAndHashCode(callSuper = false)
@Valid
public class Estudiante extends Usuario {

	@NotNull(message = "El nivel es obligatorio")
	@Enumerated(EnumType.STRING)
    private NivelEstudiante nivel;

	@NotBlank(message = "El teléfono es obligatorio")
	@Pattern(regexp = "^[0-9]{9}$", message = "El teléfono debe tener 9 dígitos")
    private String telefono;
    
    private boolean esMenor;

    private String nombreTutorLeg;
    private String apellidosTutorLeg;
    private String dniTutorLeg;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SesionTutoria> listaSesiones = new ArrayList<>();
   
}