package com.sistema.olimpiadas.servicio;

import java.util.List;

import com.sistema.olimpiadas.modelo.CompetidorPorDisciplina;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompetidorPorDisciplinaServicio {
    public List<CompetidorPorDisciplina> visualizarCompetidores();

    public Page<CompetidorPorDisciplina> visualizarCompetidores(Pageable pageable);

    public CompetidorPorDisciplina guardarCompetidorPorDisciplina(CompetidorPorDisciplina competidorPorDisciplina);

    public CompetidorPorDisciplina findOne(Long id);

    public void eliminarCompetidorPorDisciplina(Long id);

    public List<CompetidorPorDisciplina> findByNombre(String q) throws Exception;

    public List<CompetidorPorDisciplina> findByApellidopaterno(String q) throws Exception;

    public List<CompetidorPorDisciplina> findByApellidomaterno(String q) throws Exception;

}
