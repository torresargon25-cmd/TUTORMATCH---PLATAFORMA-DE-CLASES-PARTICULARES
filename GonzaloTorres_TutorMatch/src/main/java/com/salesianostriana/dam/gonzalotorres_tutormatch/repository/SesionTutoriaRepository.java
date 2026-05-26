package com.salesianostriana.dam.gonzalotorres_tutormatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.gonzalotorres_tutormatch.model.SesionTutoria;

@Repository
public interface SesionTutoriaRepository extends JpaRepository<SesionTutoria, Long>{

	
	
}
