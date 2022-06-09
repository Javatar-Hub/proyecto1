package com.sistema.olimpiadas.controlador;

import java.util.Map;

import com.sistema.olimpiadas.modelo.Disciplina;
import com.sistema.olimpiadas.servicio.DisciplinaServicio;
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
public class DisciplinaController {
  @Autowired
  private DisciplinaServicio disciplinaServicio;

  @GetMapping("/verDisciplinas/{id}")
  public String verDetallesDisciplina(@PathVariable(value = "id") Long id, Map<String, Object> modelo,
      RedirectAttributes flash) {
    Disciplina disciplina = disciplinaServicio.findOne(id);
    if (disciplina == null) {
      flash.addFlashAttribute("error", "Esa disciplina no existe en la base de datos");
      return "redirect:/listarDisciplinas";
    }

    modelo.put("disciplina", disciplina);
    modelo.put("titulo", "Detalles de la disciplina " + disciplina.getNombre());
    return "verDisciplinas";
  }

  @GetMapping({ "/listarDisciplinas" })
  public String listarDisciplinas(@RequestParam(name = "page", defaultValue = "0") int page, Model modelo) {
    Pageable pageRequest = PageRequest.of(page, 10);
    Page<Disciplina> disciplinas = disciplinaServicio.visualizarDisciplinas(pageRequest);
    PageRender<Disciplina> pageRender = new PageRender<>("/listarDisciplinas", disciplinas);

    modelo.addAttribute("titulo", "Listado de Disciplinas");
    modelo.addAttribute("Disciplinas", disciplinas);
    modelo.addAttribute("page", pageRender);

    return "listarDisciplinas";
  }

  @GetMapping("/crear_disciplina")
  public String mostrarFormularioDeRegistroDisciplina(Map<String, Object> modelo) {
    Disciplina disciplina = new Disciplina();
    modelo.put("disciplina", disciplina);
    modelo.put("titulo", "Registro de Disciplinas");
    return "crear_disciplina";
  }

  @PostMapping("/crear_disciplina")
  public String guardarDisciplina(Disciplina disciplina) {
    disciplinaServicio.guardarDisciplina(disciplina);
    return "redirect:/listarDisciplinas";
  }

  @GetMapping("/crear_disciplina/{id}")
  public String editarDisciplina(@PathVariable(value = "id") Long id, Map<String, Object> modelo,
      RedirectAttributes flash) {
    Disciplina disciplina = null;
    if (id > 0) {
      disciplina = disciplinaServicio.findOne(id);
      if (disciplina == null) {
        flash.addFlashAttribute("error", "El ID de la disciplina no existe en la base de datos");
        return "redirect:/listarDisciplinas";
      }
    } else {
      flash.addFlashAttribute("error", "El ID de una disciplina no puede ser cero");
      return "redirect:/listarDisciplinas";
    }

    modelo.put("disciplina", disciplina);
    modelo.put("titulo", "Edición de disciplina");
    return "crear_disciplina";
  }

  @GetMapping("/eliminaDis/{id}")
  public String eliminarDisciplina(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
    if (id > 0) {
      disciplinaServicio.eliminarDisciplina(id);
      flash.addFlashAttribute("success", "Disciplina eliminada con exito");
    }
    return "redirect:/listarDisciplinas";
  }

}
