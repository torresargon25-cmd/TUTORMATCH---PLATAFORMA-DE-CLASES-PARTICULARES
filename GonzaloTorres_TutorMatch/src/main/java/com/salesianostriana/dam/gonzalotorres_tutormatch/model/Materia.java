package com.salesianostriana.dam.gonzalotorres_tutormatch.model;

import java.util.ArrayList;
import java.util.List;

import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.EtapaEducativa;
import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.NivelDificultadM;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private String imagen;

    @Enumerated(EnumType.STRING)
    private NivelDificultadM dificultad;
    
    @Enumerated(EnumType.STRING)
    private EtapaEducativa etapa;
    
    @OneToMany(mappedBy = "materia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SesionTutoria> sesiones = new ArrayList<>();
}
	

