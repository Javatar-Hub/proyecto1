package com.sistema.olimpiadas.repositorios;

import com.sistema.olimpiadas.modelo.CompetidorPorDisciplina;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface CompetidorPorDisciplinaRepository extends JpaRepository<CompetidorPorDisciplina, Long> {

    @Query(value = "select * from competidores c  where c.nombre like %:keyword% or c.apellido_paterno like %:keyword%", nativeQuery = true)
    List<CompetidorPorDisciplina> findByKeyword(@Param("keyword") String keyword);

    @Query("select count(c) from CompetidorPorDisciplina c ")
    long countByIdCompetidores();

    @Query(value = "select * from competidores e  where e.nombre_de_usuario like %:keyword% ", nativeQuery = true)
    CompetidorPorDisciplina findByEmail(@Param("keyword") String keyword);

    @Query(value = "select * from competidores x where x.nombre like %:keyword% ", nativeQuery = true)
    CompetidorPorDisciplina findByNombre(@Param("keyword") String keyword);

    @Query(value = "select * from competidores x where x.disciplina like %:keyword% ", nativeQuery = true)
    List<CompetidorPorDisciplina> findByDisciplina(@Param("keyword") String keyword);

}
