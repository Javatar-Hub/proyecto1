package com.sistema.olimpiadas.repositorios;

import com.sistema.olimpiadas.modelo.Disciplina;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

  @Query("select count(d) from Disciplina d ")
  long countByIdDisciplina();

}
