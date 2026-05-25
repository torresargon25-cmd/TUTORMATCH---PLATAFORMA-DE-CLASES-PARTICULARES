package com.salesianostriana.dam.gonzalotorres_tutormatch.model;

import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.NivelEstudiante;
import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.Rol;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "estudiante")
@PrimaryKeyJoinColumn(name = "id")
@EqualsAndHashCode(callSuper = false)
public class Estudiante extends Usuario {

	@Enumerated(EnumType.STRING)
    private NivelEstudiante nivel;

    private String telefono;
    
    private boolean esMenor;

    private String nombreTutorLeg;
    private String apellidosTutorLeg;
    private String dniTutorLeg;

    @OneToMany(mappedBy = "estudiante")
    private List<SesionTutoria> listaSesiones = new ArrayList<>();

	public Estudiante(Long id, String nombre, String apellidos, String email, String password, Rol rol) {
		super(id, nombre, apellidos, email, password, rol);
		// TODO Auto-generated constructor stub
	}
    
    
}