package com.sistema.olimpiadas.controlador;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.sistema.olimpiadas.modelo.Disciplina;
import com.sistema.olimpiadas.modelo.Juez;
import com.sistema.olimpiadas.modelo.ComentarioJuez;
import com.sistema.olimpiadas.modelo.CompetidorPorDisciplina;
import com.sistema.olimpiadas.repositorios.ComentarioJuezRepository;
import com.sistema.olimpiadas.repositorios.CompetidorPorDisciplinaRepository;
import com.sistema.olimpiadas.repositorios.DisciplinaRepository;
import com.sistema.olimpiadas.repositorios.JuezRepository;
import com.sistema.olimpiadas.servicio.ComentarioJuezServicio;
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
public class ComentarioJuezController {

  @Autowired
  private CompetidorPorDisciplinaServicio competidorPorDisciplinaServicio;

  @Autowired
  private CompetidorPorDisciplinaRepository competidorPorDisciplinaRepository;

  @Autowired
  private ComentarioJuezServicio comentarioServicio;

  @Autowired
  private JuezRepository juezRepository;

  private final Logger LOG = LoggerFactory.getLogger(CompetidorPorDisciplinaController.class);

  @GetMapping("/listarComentarios")
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
    return "listarComentarios";
  }

  @GetMapping("/consultar_comentarios")
  public String consultarComentarios(Long id, Model modelo,
      RedirectAttributes flash) {
    String email = SecurityContextHolder.getContext()
        .getAuthentication().getName();
    System.out.println();
    CompetidorPorDisciplina competidor = competidorPorDisciplinaRepository.findByEmail(email);
    System.out.println(competidor.getId());
    List<ComentarioJuez> comentarios = comentarioServicio.findComentariosPorCompetidor(competidor.getId());
    modelo.addAttribute("comentarios", comentarios);
    return "consultar_comentarios";
  }

  @GetMapping("/crear_comentario")
  public String mostrarAgregarComentario(Map<String, Object> modelo) {
    ComentarioJuez comentario = new ComentarioJuez();
    modelo.put("comentario", comentario);
    modelo.put("titulo", "Agregar comentario");
    return "crear_comentario";
  }

  @PostMapping("/crear_comentario")
  public String guardarComentario(ComentarioJuez comentario) {
    comentarioServicio.guardarComentarioJuez(comentario);
    return "redirect:/listarComentarios";
  }

  @GetMapping("/crear_comentario/{id}")
  public String agregarComentario(@PathVariable(value = "id") Long id, Model modelo,
      RedirectAttributes flash) {
    ComentarioJuez comentario = new ComentarioJuez();
    comentario.setIdcompetidor(id);
    modelo.addAttribute("comentario", comentario);
    CompetidorPorDisciplina competidorPorDisciplina = null;
    if (id > 0) {
      competidorPorDisciplina = competidorPorDisciplinaServicio.findOne(id);
      if (competidorPorDisciplina == null) {
        flash.addFlashAttribute("error", "El ID del Competidor Por Disciplina no existe en la base de datos");
        return "redirect:/listarComentarios";
      }
    } else {
      flash.addFlashAttribute("error", "El ID del Competidor Por Disciplina no puede ser cero");
      return "redirect:/listarComentarios";
    }

    ((Map<String, Object>) modelo).put("titulo", "Agregar comentario");
    return "crear_comentario";
  }

}