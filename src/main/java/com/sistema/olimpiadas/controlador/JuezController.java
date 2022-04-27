package com.sistema.olimpiadas.controlador;

import com.sistema.olimpiadas.modelo.Juez;
import com.sistema.olimpiadas.servicio.JuezServicio;
import com.sistema.olimpiadas.util.paginacion.PageRender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JuezController {

  @Autowired
  private JuezServicio juezServicio;

  @GetMapping({ "/", "/listar", "" })
  public String listarJueces(@RequestParam(name = "page", defaultValue = "0") int page, Model modelo) {
    Pageable pageRequest = PageRequest.of(page, 10);
    Page<Juez> jueces = juezServicio.visualizarJueces(pageRequest);
    PageRender<Juez> pageRender = new PageRender<>("/listar", jueces);

    modelo.addAttribute("titulo", "Listado de jueces");
    modelo.addAttribute("jueces", jueces);
    modelo.addAttribute("page", pageRender);

    return "listar";
  }

}