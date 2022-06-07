package com.sistema.olimpiadas.servicio;

import java.util.List;

import com.sistema.olimpiadas.modelo.Juez;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JuezServicio {
  public List<Juez> visualizarJueces();

  public Page<Juez> visualizarJueces(Pageable pageable);

  public Juez guardarJuez(Juez juez);

  public Juez findOne(Long id);

  public void eliminarJuez(Long id);

  public long cuentaJueces();
}