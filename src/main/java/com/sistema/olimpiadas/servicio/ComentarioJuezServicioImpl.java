package com.sistema.olimpiadas.servicio;

import java.util.List;

import com.sistema.olimpiadas.modelo.ComentarioJuez;
import com.sistema.olimpiadas.repositorios.ComentarioJuezRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ComentarioJuezServicioImpl implements ComentarioJuezServicio {

  @Autowired
  private ComentarioJuezRepository ComentarioJuezRepository;

  @Override
  @Transactional(readOnly = true)
  public List<ComentarioJuez> visualizarComentarios() {
    return (List<ComentarioJuez>) ComentarioJuezRepository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Page<ComentarioJuez> visualizarComentarios(Pageable pageable) {
    return ComentarioJuezRepository.findAll(pageable);
  }

  @Override
  @Transactional
  public ComentarioJuez guardarComentario(ComentarioJuez ComentarioJuez) {
    return ComentarioJuezRepository.save(ComentarioJuez);
  }

  @Override
  @Transactional(readOnly = true)
  public ComentarioJuez findOne(Long id) {
    return ComentarioJuezRepository.findById(id).orElse(null);
  }

  @Override
  @Transactional
  public void eliminarComentario(Long id) {
    ComentarioJuezRepository.deleteById(id);

  }

}