package com.sistema.olimpiadas.controlador;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.sistema.olimpiadas.modelo.Disciplina;
import com.sistema.olimpiadas.modelo.Juez;
import com.sistema.olimpiadas.modelo.Calificacion;

import com.sistema.olimpiadas.modelo.CompetidorPorDisciplina;

import com.sistema.olimpiadas.repositorios.CompetidorPorDisciplinaRepository;
import com.sistema.olimpiadas.repositorios.DisciplinaRepository;
import com.sistema.olimpiadas.repositorios.JuezRepository;
import com.sistema.olimpiadas.servicio.CalificacionServicio;

import com.sistema.olimpiadas.servicio.CompetidorPorDisciplinaServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class CalificacionController {

  @Autowired
  private CompetidorPorDisciplinaServicio competidorPorDisciplinaServicio;

  @Autowired
  private DisciplinaRepository disciplinaRepository;

  @Autowired
  private CalificacionServicio calificacionServicio;

  @Autowired
  private CompetidorPorDisciplinaRepository competidorPorDisciplinaRepository;

  @Autowired
  private JuezRepository juezRepository;

  private final Logger LOG = LoggerFactory.getLogger(CompetidorPorDisciplinaController.class);

  @GetMapping("/listarCalificaciones")
  public String busqueda(CompetidorPorDisciplina competidor, Model modelo, String keyword) {
    String email = SecurityContextHolder.getContext()
        .getAuthentication().getName();
    Juez juez = juezRepository.findByEmail(email);
    if (keyword != null) {
      List<CompetidorPorDisciplina> competidorPorDisciplina = competidorPorDisciplinaServicio.getbyKeyword(keyword);
      modelo.addAttribute("competidores", competidorPorDisciplina);
      LOG.info("Busqueda");
    } else {
      List<CompetidorPorDisciplina> competidorPorDisciplina = competidorPorDisciplinaRepository
          .findByDisciplina(juez.getDisciplina());
      modelo.addAttribute("competidores", competidorPorDisciplina);
      LOG.info("Pase");
    }
    modelo.addAttribute("titulo", "Listado de Competidores");
    return "listarCalificaciones";
  }

  @GetMapping("/consultar_calificaciones")
  public String consultarCalificaciones(Long id, Model modelo,
      RedirectAttributes flash) {
    String email = SecurityContextHolder.getContext()
        .getAuthentication().getName();
    CompetidorPorDisciplina competidor = competidorPorDisciplinaRepository.findByEmail(email);
    List<Calificacion> calificaciones = calificacionServicio.findCalificacionPorCompetidor(competidor.getId());
    modelo.addAttribute("calificaciones", calificaciones);
    return "consultar_calificaciones";
  }

  @GetMapping("/crear_calificacion")
  public String mostrarAgregarCalficacion(Map<String, Object> modelo) {
    Calificacion calificacion = new Calificacion();
    modelo.put("calificacion", calificacion);
    modelo.put("titulo", "Agregar calificacion");
    return "crear_calificacion";
  }

  @PostMapping("/crear_calificacion")
  public String guardarCalificacion(Calificacion calificacion) {
    calificacionServicio.guardarCalificacion(calificacion);
    return "redirect:/listarCalificaciones";
  }

  @GetMapping("/crear_calificacion/{id}")
  public String agregarCalificacion(@PathVariable(value = "id") Long id, Model modelo,
      RedirectAttributes flash) {
    System.out.println("id: " + id);
    Calificacion calificacion = new Calificacion();
    calificacion.setCompetidor(id);
    modelo.addAttribute("calificacion", calificacion);
    CompetidorPorDisciplina competidorPorDisciplina = null;
    if (id > 0) {
      competidorPorDisciplina = competidorPorDisciplinaServicio.findOne(id);
      if (competidorPorDisciplina == null) {
        flash.addFlashAttribute("error", "El ID del Competidor Por Disciplina no existe en la base de datos");
        return "redirect:/listarCalificaciones";
      }
    } else {
      flash.addFlashAttribute("error", "El ID del Competidor Por Disciplina no puede ser cero");
      return "redirect:/listarCalificaciones";
    }

    ((Map<String, Object>) modelo).put("titulo", "Agregar calificacion");
    return "crear_calificacion";
  }

}