package com.salesianostriana.dam.gonzalotorres_tutormatch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.gonzalotorres_tutormatch.model.Materia;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long>{

	@Query("SELECT s.materia, COUNT(s) as total FROM SesionTutoria s GROUP BY s.materia ORDER BY total DESC")
	List<Object[]> findMateriasMasDemandadas();
	
}
