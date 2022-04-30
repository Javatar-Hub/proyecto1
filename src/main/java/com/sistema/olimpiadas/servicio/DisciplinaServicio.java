package com.sistema.olimpiadas.servicio;

import java.util.List;
import com.sistema.olimpiadas.modelo.Disciplina;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface DisciplinaServicio {
    public List<Disciplina> visualizarDisciplinas();
    public Page<Disciplina> visualizarDisciplinas(Pageable pageable);
    public Disciplina guardarDisciplina(Disciplina disciplina);
    public Disciplina findOne(Long id);
    public Disciplina eliminarDisciplina(Disciplina disciplina);

}
