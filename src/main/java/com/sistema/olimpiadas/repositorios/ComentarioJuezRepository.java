package com.sistema.olimpiadas.repositorios;
import com.sistema.olimpiadas.modelo.ComentarioJuez;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ComentarioJuezRepository extends JpaRepository<ComentarioJuez, Long>{
    @Query(value = "select * from comentarios c where c.idcompetidor = :competidor", nativeQuery = true)
    List<ComentarioJuez> findByCompetidor(@Param("competidor") Long competidor);
}
