package com.sistema.olimpiadas.servicio;

import java.util.List;

import com.sistema.olimpiadas.modelo.CompetidorPorDisciplina;
import com.sistema.olimpiadas.repositorios.CompetidorPorDisciplinaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompetidorPorDisciplinaImpl implements CompetidorPorDisciplinaServicio {

  @Autowired
  private CompetidorPorDisciplinaRepository competidorPorDisciplinaRepository;

  @Override
  @Transactional(readOnly = true)
  public List<CompetidorPorDisciplina> visualizarCompetidores() {
    return (List<CompetidorPorDisciplina>) competidorPorDisciplinaRepository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Page<CompetidorPorDisciplina> visualizarCompetidores(Pageable pageable) {
    return competidorPorDisciplinaRepository.findAll(pageable);
  }

  @Override
  @Transactional
  public CompetidorPorDisciplina guardarCompetidorPorDisciplina(CompetidorPorDisciplina competidorPorDisciplina) {
    return competidorPorDisciplinaRepository.save(competidorPorDisciplina);
  }

  @Override
  @Transactional(readOnly = true)
  public CompetidorPorDisciplina findOne(Long id) {
    return competidorPorDisciplinaRepository.findById(id).orElse(null);
  }

  @Override
  @Transactional
  public void eliminarCompetidorPorDisciplina(Long id) {
    competidorPorDisciplinaRepository.deleteById(id);

  }


}
