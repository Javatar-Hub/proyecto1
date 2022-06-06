package com.sistema.olimpiadas.controlador;

import java.util.List;
import java.util.Map;

import com.sistema.olimpiadas.modelo.ComentarioJuez;
import com.sistema.olimpiadas.modelo.CompetidorPorDisciplina;
import com.sistema.olimpiadas.modelo.Disciplina;
import com.sistema.olimpiadas.servicio.ComentarioJuezServicio;
import com.sistema.olimpiadas.servicio.CompetidorPorDisciplinaImpl;
import com.sistema.olimpiadas.servicio.DisciplinaServicio;
import com.sistema.olimpiadas.servicio.DisciplinaServicioImpl;
import com.sistema.olimpiadas.util.paginacion.PageRender;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ComentarioJuezController {
  @Autowired
  private DisciplinaServicio disciplinaServicio;

  @GetMapping({ "/listarComentarios" })
  public String listarComentarios(@RequestParam(name = "page", defaultValue = "0") int page, Model modelo) {
    Pageable pageRequest = PageRequest.of(page, 10);
    Page<Disciplina> disciplinas = disciplinaServicio.visualizarDisciplinas(pageRequest);
    PageRender<Disciplina> pageRender = new PageRender<>("/listarComentarios", disciplinas);

    modelo.addAttribute("titulo", "Listado de Disciplina");
    modelo.addAttribute("Disciplinas", disciplinas);
    modelo.addAttribute("page", pageRender);

    return "listarComentarios";
  }

}
