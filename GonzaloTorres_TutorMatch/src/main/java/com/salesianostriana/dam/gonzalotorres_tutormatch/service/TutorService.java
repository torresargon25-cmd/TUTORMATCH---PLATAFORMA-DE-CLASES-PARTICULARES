package com.salesianostriana.dam.gonzalotorres_tutormatch.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.salesianostriana.dam.gonzalotorres_tutormatch.exception.TarifaInvalidaException;
import com.salesianostriana.dam.gonzalotorres_tutormatch.model.Tutor;
import com.salesianostriana.dam.gonzalotorres_tutormatch.repository.TutorRepository;
import com.salesianostriana.dam.gonzalotorres_tutormatch.service.base.BaseServiceImpl;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TutorService extends BaseServiceImpl<Tutor, Long, TutorRepository> {

    public void validarTarifa(Double tarifaHora) {
        if (tarifaHora == null || tarifaHora <= 0) {
            throw new TarifaInvalidaException("La tarifa por hora debe ser mayor que 0");
        }
        if (tarifaHora > 500) {
            throw new TarifaInvalidaException("La tarifa por hora no puede superar los 500 €");
        }
    }
    
    public List<Tutor> buscarPorEspecialidad(String especialidad) {
        return repository.findByEspecialidadContains(especialidad);
    }
    
    public List<Object[]> findTutoresConMasSesiones() {
        return repository.findTutoresConMasSesiones();
    }
    
    public Optional<Tutor> buscarPorUsername(String username) {
        return repository.findByUsername(username);
    }
}
