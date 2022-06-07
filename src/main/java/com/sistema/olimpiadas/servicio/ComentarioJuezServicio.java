package com.sistema.olimpiadas.servicio;

import java.util.List;

import com.sistema.olimpiadas.modelo.ComentarioJuez;

public interface ComentarioJuezServicio {

    public ComentarioJuez guardarComentarioJuez(ComentarioJuez comentario);

    public ComentarioJuez findOne(Long id);

    public List<ComentarioJuez> findComentariosPorCompetidor(Long idcompetidor);
    
}
