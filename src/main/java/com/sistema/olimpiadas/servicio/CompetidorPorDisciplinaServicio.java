package com.sistema.olimpiadas.servicio;

import java.util.List;

import com.sistema.olimpiadas.modelo.CompetidorPorDisciplina;



public interface CompetidorPorDisciplinaServicio {
    public List<CompetidorPorDisciplina> visualizarCompetidores();



    public CompetidorPorDisciplina guardarCompetidorPorDisciplina(CompetidorPorDisciplina competidorPorDisciplina);

    public CompetidorPorDisciplina findOne(Long id);

    public void eliminarCompetidorPorDisciplina(Long id);

    public List<CompetidorPorDisciplina> getbyKeyword(String keyword);
}
