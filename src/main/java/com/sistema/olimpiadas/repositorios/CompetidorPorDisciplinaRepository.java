package com.sistema.olimpiadas.repositorios;

import com.sistema.olimpiadas.modelo.CompetidorPorDisciplina;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface CompetidorPorDisciplinaRepository extends JpaRepository<CompetidorPorDisciplina, Long> {

    @Query(value = "select * from competidores c  where c.nombre like %:keyword% or c.paterno like %:keyword%", nativeQuery = true)
    List<CompetidorPorDisciplina> findByKeyword(@Param("keyword") String keyword);
}


