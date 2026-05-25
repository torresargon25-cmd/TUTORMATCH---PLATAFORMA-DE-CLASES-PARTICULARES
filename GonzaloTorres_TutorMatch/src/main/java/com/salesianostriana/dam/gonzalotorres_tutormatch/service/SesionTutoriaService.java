package com.salesianostriana.dam.gonzalotorres_tutormatch.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.gonzalotorres_tutormatch.model.SesionTutoria;
import com.salesianostriana.dam.gonzalotorres_tutormatch.repository.SesionTutoriaRepository;
import com.salesianostriana.dam.gonzalotorres_tutormatch.service.base.BaseServiceImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SesionTutoriaService extends BaseServiceImpl<SesionTutoria, Long, SesionTutoriaRepository>{

	private SesionTutoria sesiontutoria;
	
}
