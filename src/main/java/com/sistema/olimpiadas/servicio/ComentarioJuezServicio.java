package com.sistema.olimpiadas.servicio;

import java.util.List;

import com.sistema.olimpiadas.modelo.ComentarioJuez;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ComentarioJuezServicio {
  public List<ComentarioJuez> visualizarComentarios();

  public Page<ComentarioJuez> visualizarComentarios(Pageable pageable);

  public ComentarioJuez guardarComentario(ComentarioJuez comentario);

  public ComentarioJuez findOne(Long id);

  public void eliminarComentario(Long id);
}