package com.salesianostriana.dam.gonzalotorres_tutormatch.model;

import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.Rol;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario implements UserDetails{


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String nombre;
    protected String apellidos;

    @Column(unique = true, nullable = false)
    protected String email;

    @Column(unique = true, nullable = false)
    protected String username;
    
    protected String password;
    
    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + rol.name()));
    }
	
}
