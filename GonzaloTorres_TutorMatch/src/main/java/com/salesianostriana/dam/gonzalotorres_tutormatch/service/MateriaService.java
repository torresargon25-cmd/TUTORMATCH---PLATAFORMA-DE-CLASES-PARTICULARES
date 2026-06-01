package com.salesianostriana.dam.gonzalotorres_tutormatch.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.gonzalotorres_tutormatch.model.Materia;
import com.salesianostriana.dam.gonzalotorres_tutormatch.repository.MateriaRepository;
import com.salesianostriana.dam.gonzalotorres_tutormatch.service.base.BaseServiceImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MateriaService extends BaseServiceImpl<Materia, Long, MateriaRepository>{

	private final MateriaRepository materiarepository;
	
	public List<Object[]> findMateriasMasDemandadas() {
        return repository.findMateriasMasDemandadas();
    }
}
