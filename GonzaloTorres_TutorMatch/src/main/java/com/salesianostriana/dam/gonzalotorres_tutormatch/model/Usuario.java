package com.salesianostriana.dam.gonzalotorres_tutormatch.model;

import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.Rol;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Valid
public abstract class Usuario implements UserDetails{


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

	@NotBlank(message = "El nombre es obligatorio")
    protected String nombre;
	
	@NotBlank(message = "Los apellidos son obligatorios")
    protected String apellidos;

	@Email(message = "El email no es válido")
	@NotBlank(message = "El email es obligatorio")
    @Column(unique = true, nullable = false)
    protected String email;

    @Column(unique = true, nullable = false)
    protected String username;
    
    @NotBlank(message = "La contraseña es obligatoria")
    protected String password;
    
    @NotNull(message = "El rol es obligatorio")
    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + rol.name()));
    }
	
}
