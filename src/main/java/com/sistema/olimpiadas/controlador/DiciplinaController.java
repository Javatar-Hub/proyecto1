package com.sistema.olimpiadas.controlador;
import com.sistema.olimpiadas.modelo.Disciplina;
import com.sistema.olimpiadas.servicio.DisciplinaServicio;
import com.sistema.olimpiadas.util.paginacion.PageRender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Controller
public class DiciplinaController {
    @Autowired
    private DisciplinaServicio disciplinaServicio;

    @GetMapping({"/", "/listar",""})
    public String listarDisciplinas(@RequestParam(name = "page", defaultValue = "0") int page, Model modelo){
        Pageable pageRequest=PageRequest.of(page,10);
        Page<Disciplina> disciplinas = disciplinaServicio.visualizarDisciplinas(pageRequest);
        PageRender<Disciplina> pageRender= new PageRender<>("/listar", disciplinas);

        modelo.addAttribute("titulo","Listado de Disciplinas");
        modelo.addAttribute("Disciplinas", disciplinas);
        modelo.addAttribute("page", pageRender);

        return "listar";
    }
}
