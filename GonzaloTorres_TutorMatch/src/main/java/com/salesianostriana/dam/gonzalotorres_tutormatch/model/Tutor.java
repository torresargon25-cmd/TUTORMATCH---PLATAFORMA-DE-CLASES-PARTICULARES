package com.salesianostriana.dam.gonzalotorres_tutormatch.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tutor")
@PrimaryKeyJoinColumn(name = "id")
public class Tutor extends Usuario {

    private String dni;
    private String especialidad;
    private Double tarifaHora;
    private Boolean disponibilidad;
    private Double puntuacionNivel;

    @OneToMany(mappedBy = "tutor")
    private List<SesionTutoria> sesiones = new ArrayList<>();
}
