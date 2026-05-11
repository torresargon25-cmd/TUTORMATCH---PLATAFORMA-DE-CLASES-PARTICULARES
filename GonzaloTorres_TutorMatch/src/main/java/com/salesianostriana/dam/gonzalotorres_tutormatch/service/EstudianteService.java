package com.salesianostriana.dam.gonzalotorres_tutormatch.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.gonzalotorres_tutormatch.model.Estudiante;
import com.salesianostriana.dam.gonzalotorres_tutormatch.repository.EstudianteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstudianteService {

	
	
	private List <Estudiante> listaEstudiantes = new ArrayList <Estudiante>();
	
	public void agregar (Estudiante e) {
		listaEstudiantes.add(e);
		
	}
	public List <Estudiante> getLista(){
		return listaEstudiantes;
	}
}
