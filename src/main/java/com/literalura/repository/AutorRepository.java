package com.literalura.repository;

import java.util.List; 
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.literalura.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    // ←  NUEVO
    Optional<Autor> findByNombre(String nombre);

    // la query de “autores vivos” 
    List<Autor> findByNacimientoLessThanEqualAndFallecimientoGreaterThanEqualOrFallecimientoIsNull(
            int nacimiento, int fallecimiento);
}

