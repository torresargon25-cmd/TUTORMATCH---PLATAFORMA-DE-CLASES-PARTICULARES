package com.salesianostriana.dam.gonzalotorres_tutormatch.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.salesianostriana.dam.gonzalotorres_tutormatch.model.Estudiante;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    Optional<Estudiante> findByUsername(String username);

    @Query("SELECT s.estudiante, COUNT(s) as total FROM SesionTutoria s GROUP BY s.estudiante ORDER BY total DESC")
    List<Object[]> findEstudiantesConMasTutorias();
}