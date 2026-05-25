package com.salesianostriana.dam.gonzalotorres_tutormatch.model;

import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.NivelEstudiante;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "estudiante")
@PrimaryKeyJoinColumn(name = "id")
public class Estudiante extends Usuario {

    @Enumerated(EnumType.STRING)
    private NivelEstudiante nivel;

    private String telefono;

    private String nombreTutorLeg;
    private String apellidosTutorLeg;
    private String dniTutorLeg;

    @OneToMany(mappedBy = "estudiante")
    private List<SesionTutoria> listaSesiones = new ArrayList<>();
}