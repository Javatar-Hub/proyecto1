package com.sistema.olimpiadas.repositorios;

import com.sistema.olimpiadas.modelo.Calificacion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {
  @Query(value = "select * from calificaciones c where c.competidor = :usuario", nativeQuery = true)
  List<Calificacion> findByUsuario(@Param("usuario") Long usuario);

  @Query("select count(c) from Calificacion c ")
  long countByIdCalificaciones();
}
