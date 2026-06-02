package com.salesianostriana.dam.gonzalotorres_tutormatch.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.Rol;
import com.salesianostriana.dam.gonzalotorres_tutormatch.model.Estudiante;
import com.salesianostriana.dam.gonzalotorres_tutormatch.repository.EstudianteRepository;
import com.salesianostriana.dam.gonzalotorres_tutormatch.service.base.BaseServiceImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstudianteService extends BaseServiceImpl<Estudiante, Long, EstudianteRepository> {

    private final EstudianteRepository estudianterepository;
    
    public Optional<Estudiante> buscarPorUsername(String username) {
        return estudianterepository.findByUsername(username);
    }

    public List<Object[]> findEstudiantesConMasTutorias() {
        return estudianterepository.findEstudiantesConMasTutorias();
    }
}
