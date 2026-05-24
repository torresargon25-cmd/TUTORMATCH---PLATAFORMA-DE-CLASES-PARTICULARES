package com.salesianostriana.dam.gonzalotorres_tutormatch.model;

import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.Rol;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Admin {

    @Id @GeneratedValue
    private Long id;

    private String nombre;
    private String apellidos;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;  
}
