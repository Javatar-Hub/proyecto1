package com.sistema.olimpiadas.servicio;

import com.sistema.olimpiadas.modelo.Calificacion;
import com.sistema.olimpiadas.repositorios.CalificacionRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CalificacionServicioImpl implements CalificacionServicio {

  @Autowired
  private CalificacionRepository calificacionRepository;

  @Override
  @Transactional
  public Calificacion guardarCalificacion(Calificacion calificacion) {
    return calificacionRepository.save(calificacion);
  }

  @Override
  @Transactional(readOnly = true)
  public Calificacion findOne(Long id) {
    return calificacionRepository.findById(id).orElse(null);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Calificacion> findCalificacionPorCompetidor(Long competidor) {
    return calificacionRepository.findByUsuario(competidor);
  }

}