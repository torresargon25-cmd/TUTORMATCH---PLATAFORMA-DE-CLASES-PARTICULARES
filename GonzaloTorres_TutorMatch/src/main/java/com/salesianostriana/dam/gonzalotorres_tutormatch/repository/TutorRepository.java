package com.salesianostriana.dam.gonzalotorres_tutormatch.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.gonzalotorres_tutormatch.model.Tutor;

@Repository
public interface TutorRepository extends JpaRepository<Tutor,Long>{

	@Query("SELECT t FROM Tutor t WHERE LOWER(t.especialidad) LIKE LOWER(CONCAT('%', :especialidad, '%'))")
    List<Tutor> findByEspecialidadContains(@Param("especialidad") String especialidad);
	
	@Query("SELECT s.tutor, COUNT(s) as total FROM SesionTutoria s GROUP BY s.tutor ORDER BY total DESC")
	List<Object[]> findTutoresConMasSesiones();
	
	@Query("SELECT t FROM Tutor t WHERE t.username = :username")
	Optional<Tutor> findByUsername(@Param("username") String username);
}
