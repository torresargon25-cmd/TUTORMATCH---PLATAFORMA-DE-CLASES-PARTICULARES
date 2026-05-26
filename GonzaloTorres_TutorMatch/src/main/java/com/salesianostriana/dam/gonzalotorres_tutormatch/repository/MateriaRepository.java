package com.salesianostriana.dam.gonzalotorres_tutormatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.gonzalotorres_tutormatch.model.Materia;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long>{

}
