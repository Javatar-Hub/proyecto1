package com.sistema.olimpiadas.servicio;

import java.util.List;

import com.sistema.olimpiadas.modelo.Calificacion;

public interface CalificacionServicio {

  public Calificacion guardarCalificacion(Calificacion calificacion);

  public Calificacion findOne(Long id);

  public List<Calificacion> findCalificacionPorCompetidor(Long competidor);

  public long cuentaCalificaciones();

}
