package com.sistema.olimpiadas.servicio;

import java.util.List;

import com.sistema.olimpiadas.modelo.Juez;
import com.sistema.olimpiadas.repositorios.JuezRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JuezServicioImpl implements JuezServicio {

  @Autowired
  private JuezRepository juezRepository;

  @Override
  @Transactional(readOnly = true)
  public List<Juez> visualizarJueces() {
    return (List<Juez>) juezRepository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Page<Juez> visualizarJueces(Pageable pageable) {
    return juezRepository.findAll(pageable);
  }

  @Override
  @Transactional
  public Juez guardarJuez(Juez juez) {
    return juezRepository.save(juez);
  }

  @Override
  @Transactional(readOnly = true)
  public Juez findOne(Long id) {
    return juezRepository.findById(id).orElse(null);
  }

  @Override
  @Transactional
  public void eliminarJuez(Long id) {
    juezRepository.deleteById(id);

  }

  @Override
  public long cuentaJueces() {
    return juezRepository.countById();
  }

}
