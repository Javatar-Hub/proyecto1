package com.sistema.olimpiadas.repositorios;

import com.sistema.olimpiadas.modelo.Disciplina;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

  @Query("select count(d) from Disciplina d ")
  long countByIdDisciplina();

  @Query(value = "select * from disciplinas e  where e.nombre like %:keyword% ", nativeQuery = true)
  Disciplina findByNombre(@Param("keyword") String keyword);

}
