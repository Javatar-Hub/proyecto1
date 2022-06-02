package com.sistema.olimpiadas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sistema.olimpiadas.modelo.ComentarioJuez;

public interface ComentarioJuezRepository extends JpaRepository<ComentarioJuez, Long> {
    
}