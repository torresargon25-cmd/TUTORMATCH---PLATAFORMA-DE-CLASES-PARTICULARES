package com.salesianostriana.dam.gonzalotorres_tutormatch.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.Rol;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tutor")
@PrimaryKeyJoinColumn(name = "id")
@EqualsAndHashCode(callSuper = false)
public class Tutor extends Usuario {

    private String dni;
    private String especialidad;
    private Double tarifaHora;
    private Boolean disponibilidad;
    private Double puntuacionNivel;
    private String imagen;
    
    public Tutor(Long id, String nombre, String apellidos, String email,
            String password, Rol rol, String dni, String especialidad,
            Double tarifaHora, Boolean disponibilidad, Double puntuacionNivel) {
    	super(id, nombre, apellidos, email, password, rol);
    	this.dni = dni;
    	this.especialidad = especialidad;
    	this.tarifaHora = tarifaHora;
    	this.disponibilidad = disponibilidad;
    	this.puntuacionNivel = puntuacionNivel;
    }

    @OneToMany(mappedBy = "tutor")
    private List<SesionTutoria> sesiones = new ArrayList<>();
}
