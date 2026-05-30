package com.salesianostriana.dam.gonzalotorres_tutormatch.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.salesianostriana.dam.gonzalotorres_tutormatch.exception.UsuarioNoEncontradoException;
import com.salesianostriana.dam.gonzalotorres_tutormatch.repository.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) 
            throws UsernameNotFoundException {
    	return usuarioRepository.findByUsername(username)
    	        .orElseThrow(() -> new UsuarioNoEncontradoException(username));
    }
}
