package com.salesianostriana.dam.gonzalotorres_tutormatch.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.gonzalotorres_tutormatch.enums.EstadoSesion;
import com.salesianostriana.dam.gonzalotorres_tutormatch.model.SesionTutoria;

@Repository
public interface SesionTutoriaRepository extends JpaRepository<SesionTutoria, Long> {

    @Query("SELECT s FROM SesionTutoria s WHERE s.tutor.id = :tutorId " +
           "AND s.fechaInicio < :fechaFin AND s.fechaFin > :fechaInicio")
    List<SesionTutoria> findSolapadasPorTutor(
            @Param("tutorId") Long tutorId,
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin);

    @Query("SELECT s FROM SesionTutoria s WHERE s.estudiante.id = :estudianteId " +
           "AND s.fechaInicio < :fechaFin AND s.fechaFin > :fechaInicio")
    List<SesionTutoria> findSolapadasPorEstudiante(
            @Param("estudianteId") Long estudianteId,
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin);

    @Query("SELECT s FROM SesionTutoria s WHERE s.tutor.id = :tutorId " +
           "AND s.id != :sesionId " +
           "AND s.fechaInicio < :fechaFin AND s.fechaFin > :fechaInicio")
    List<SesionTutoria> findSolapadasPorTutorExcluyendo(
            @Param("tutorId") Long tutorId,
            @Param("sesionId") Long sesionId,
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin);

    @Query("SELECT s FROM SesionTutoria s WHERE s.estudiante.id = :estudianteId " +
           "AND s.id != :sesionId " +
           "AND s.fechaInicio < :fechaFin AND s.fechaFin > :fechaInicio")
    List<SesionTutoria> findSolapadasPorEstudianteExcluyendo(
            @Param("estudianteId") Long estudianteId,
            @Param("sesionId") Long sesionId,
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin);
    
    @Query("SELECT s FROM SesionTutoria s WHERE s.estudiante.username = :username")
    List<SesionTutoria> findByEstudianteUsername(@Param("username") String username);
    
    @Query("SELECT s FROM SesionTutoria s WHERE s.estudiante.username = :username AND s.estado = :estado")
    List<SesionTutoria> findByEstudianteUsernameAndEstado(
            @Param("username") String username,
            @Param("estado") EstadoSesion estado);
    
    @Query("SELECT s FROM SesionTutoria s WHERE s.tutor.id = :tutorId AND s.estado = 'PROGRAMADA'")
    List<SesionTutoria> findSesionesProgramadasPorTutor(@Param("tutorId") Long tutorId);
    
    @Query("SELECT s FROM SesionTutoria s WHERE s.fechaInicio >= :fechaDesde AND s.fechaFin <= :fechaHasta")
    List<SesionTutoria> findSesionesentreDechas(
            @Param("fechaDesde") LocalDateTime fechaDesde,
            @Param("fechaHasta") LocalDateTime fechaHasta);
}
