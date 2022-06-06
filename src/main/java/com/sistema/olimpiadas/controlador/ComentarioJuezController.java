package com.sistema.olimpiadas.controlador;

import java.util.Map;

import com.sistema.olimpiadas.modelo.ComentarioJuez;
import com.sistema.olimpiadas.servicio.ComentarioJuezServicio;
import com.sistema.olimpiadas.util.paginacion.PageRender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ComentarioJuezController {
  @Autowired
  private ComentarioJuezServicio comentarioJuezServicio;

  @GetMapping({ "/listar" })
  public String listarJueces(@RequestParam(name = "page", defaultValue = "0") int page, Model modelo) {
    Pageable pageRequest = PageRequest.of(page, 10);
    Page<ComentarioJuez> jueces = comentarioJuezServicio.visualizarComentarios(pageRequest);
    PageRender<ComentarioJuez> pageRender = new PageRender<>("/listar", jueces);

    modelo.addAttribute("titulo", "Listado de jueces");
    modelo.addAttribute("jueces", jueces);
    modelo.addAttribute("page", pageRender);

    return "listar";
  }

}
