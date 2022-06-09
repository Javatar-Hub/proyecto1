package com.sistema.olimpiadas.servicio;

import java.util.List;
import com.sistema.olimpiadas.modelo.Disciplina;
import com.sistema.olimpiadas.repositorios.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DisciplinaServicioImpl implements DisciplinaServicio {

  @Autowired
  private DisciplinaRepository disciplinaRepository;

  @Override
  @Transactional(readOnly = true)
  public List<Disciplina> visualizarDisciplinas() {
    return (List<Disciplina>) disciplinaRepository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Page<Disciplina> visualizarDisciplinas(Pageable pageable) {
    return disciplinaRepository.findAll(pageable);
  }

  @Override
  @Transactional
  public Disciplina guardarDisciplina(Disciplina disciplina) {
    return disciplinaRepository.save(disciplina);
  }

  @Override
  @Transactional(readOnly = true)
  public Disciplina findOne(Long id) {
    return disciplinaRepository.findById(id).orElse(null);
  }

  @Override
  @Transactional
  public void eliminarDisciplina(Long id) {
    disciplinaRepository.deleteById(id);

  }

  @Override
  public long cuentaDisciplinas() {
    return disciplinaRepository.countByIdDisciplina();
  }

}
