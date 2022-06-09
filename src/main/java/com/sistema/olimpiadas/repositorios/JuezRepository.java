package com.sistema.olimpiadas.repositorios;

import com.sistema.olimpiadas.modelo.Juez;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JuezRepository extends JpaRepository<Juez, Long> {
  @Query("select count(j) from Juez j ")
  long countById();

  @Query(value = "select * from jueces e  where e.nombre_de_usuario like %:keyword% ", nativeQuery = true)
  Juez findByEmail(@Param("keyword") String keyword);
}