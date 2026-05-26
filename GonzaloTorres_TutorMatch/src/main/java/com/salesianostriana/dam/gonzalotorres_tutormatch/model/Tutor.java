package com.salesianostriana.dam.gonzalotorres_tutormatch.model;

import jakarta.persistence.*;
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
public class Tutor extends Usuario {

    private String dni;
    private String especialidad;
    private Double tarifaHora;
    private Double puntuacionNivel;
    private String imagen;
    
    @Enumerated(EnumType.STRING)
    protected Rol rol;
    

    @OneToMany(mappedBy = "tutor")
    private List<SesionTutoria> sesiones = new ArrayList<>();
}
