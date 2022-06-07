package com.sistema.olimpiadas.repositorios;

import com.sistema.olimpiadas.modelo.Juez;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JuezRepository extends JpaRepository<Juez, Long> {
  @Query("select count(j) from Juez j ")
  long countById();
}