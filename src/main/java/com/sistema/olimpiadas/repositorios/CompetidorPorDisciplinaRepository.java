package com.sistema.olimpiadas.repositorios;

import com.sistema.olimpiadas.modelo.CompetidorPorDisciplina;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface CompetidorPorDisciplinaRepository extends JpaRepository<CompetidorPorDisciplina, Long> {

    @Query(value = "SELECT * FROM competidores WHERE competidores.nombre LIKE %q%",nativeQuery = true)
    List<CompetidorPorDisciplina> findByNombre(@Param("q") String q);

    @Query(value = "SELECT * FROM competidores WHERE competidores.apellido_paterno LIKE %q%",nativeQuery = true)
    List<CompetidorPorDisciplina> findByApellidopaterno(@Param("q") String q);

    @Query(value = "SELECT * FROM competidores WHERE competidores.apellido_materno LIKE %q%",nativeQuery = true)
    List<CompetidorPorDisciplina> findByApellidomaterno(@Param("q") String q);

}
