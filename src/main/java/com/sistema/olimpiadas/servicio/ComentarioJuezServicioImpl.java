package com.sistema.olimpiadas.servicio;

import com.sistema.olimpiadas.modelo.ComentarioJuez;
import com.sistema.olimpiadas.repositorios.ComentarioJuezRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ComentarioJuezServicioImpl implements ComentarioJuezServicio {

  @Autowired
  private ComentarioJuezRepository comentarioJuezRepository;

  @Override
  @Transactional
  public ComentarioJuez guardarComentarioJuez(ComentarioJuez comentario) {
    return comentarioJuezRepository.save(comentario);
  }

  @Override
  @Transactional(readOnly = true)
  public ComentarioJuez findOne(Long id) {
    return comentarioJuezRepository.findById(id).orElse(null);
  }

  @Override
  @Transactional(readOnly = true)
  public List<ComentarioJuez> findComentariosPorCompetidor(Long idcompetidor) {
    return comentarioJuezRepository.findByCompetidor(idcompetidor);
  }

  @Override
  public long cuentaComentarios() {
    return comentarioJuezRepository.countByIdComentarios();
  }

}