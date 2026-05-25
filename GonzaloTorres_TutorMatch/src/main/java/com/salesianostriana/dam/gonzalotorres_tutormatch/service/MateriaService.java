package com.salesianostriana.dam.gonzalotorres_tutormatch.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.gonzalotorres_tutormatch.model.Materia;
import com.salesianostriana.dam.gonzalotorres_tutormatch.repository.MateriaRepository;
import com.salesianostriana.dam.gonzalotorres_tutormatch.service.base.BaseServiceImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MateriaService extends BaseServiceImpl<Materia, Long, MateriaRepository>{

	private MateriaRepository materiarepository;
	
}
