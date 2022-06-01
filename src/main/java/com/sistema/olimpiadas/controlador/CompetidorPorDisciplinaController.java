package com.sistema.olimpiadas.controlador;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.sistema.olimpiadas.modelo.Disciplina;
import com.sistema.olimpiadas.modelo.CompetidorPorDisciplina;
import com.sistema.olimpiadas.repositorios.DisciplinaRepository;
import com.sistema.olimpiadas.servicio.CompetidorPorDisciplinaServicio;
import com.sistema.olimpiadas.util.paginacion.PageRender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public class CompetidorPorDisciplinaController {

  @Autowired
  private CompetidorPorDisciplinaServicio competidorPorDisciplinaServicio;

  private final Logger LOG = LoggerFactory.getLogger(CompetidorPorDisciplinaController.class);

  @GetMapping("/verCompetidores/{id}")
  public String verDetallesDelCompetidorPorDisciplina(@PathVariable(value = "id") Long id, Map<String, Object> modelo,
      RedirectAttributes flash) {
    CompetidorPorDisciplina competidorPorDisciplina = competidorPorDisciplinaServicio.findOne(id);
    if (competidorPorDisciplina == null) {
      flash.addFlashAttribute("error", "El CompetidorPorDisciplina no existe en la base de datos");
      return "redirect:/listar";
    }

    modelo.put("CompetidorPorDisciplina", competidorPorDisciplina);
    modelo.put("titulo", "Detalles del Competidor Por Disciplina " + competidorPorDisciplina.getNombre());
    return "verCompetidores";
  }

  @GetMapping({ "/listarCompetidores" })
  public String listarCompetidores(@RequestParam(name = "page", defaultValue = "0") int page, Model modelo) {
    Pageable pageRequest = PageRequest.of(page, 10);
    Page<CompetidorPorDisciplina> competidores = competidorPorDisciplinaServicio.visualizarCompetidores(pageRequest);
    PageRender<CompetidorPorDisciplina> pageRender = new PageRender<>("/listarCompetidores", competidores);

    modelo.addAttribute("titulo", "Listado de competidores");
    modelo.addAttribute("competidores", competidores);
    modelo.addAttribute("page", pageRender);

    return "listarCompetidores";
  }

  @Autowired
  private DisciplinaRepository disciplinaRepository;

  @GetMapping("/crear_competidor")
  public String mostrarFormularioDeRegistroCompetidorPorDisciplina(Model modelo) {
    List<Disciplina> disciplinas = disciplinaRepository.findAll();

    modelo.addAttribute("competidorPorDisciplina", new CompetidorPorDisciplina());
    modelo.addAttribute("disciplinas", disciplinas);
    ((Map<String, Object>) modelo).put("titulo", "Registro de competidores");

    return "crear_competidor";
  }

  @PostMapping("/crear_competidor")
  public String guardarCompetidorPorDisciplina(@Valid CompetidorPorDisciplina competidorPorDisciplina,
      BindingResult result, Model modelo,
      @RequestParam("file") MultipartFile foto, RedirectAttributes flash,
      SessionStatus status) {
    if (result.hasErrors()) {
      modelo.addAttribute("titulo", "Registro de Competidor Por Disciplina");
      return "crear_competidor";
    }

    if (!foto.isEmpty())

    {
      Path directorioImagenes = Paths.get("src/main/resources/static/images/");
      String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

      try {
        byte[] bytesImg = foto.getBytes();
        Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + foto.getOriginalFilename());
        Files.write(rutaCompleta, bytesImg);

        competidorPorDisciplina.setFoto(foto.getOriginalFilename());
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    }

    String mensaje = (competidorPorDisciplina.getId() != null)
        ? "El Competidor Por Disciplina ha sido editado con exito"
        : "Competidor Por Disciplina registrado con exito";

    competidorPorDisciplinaServicio.guardarCompetidorPorDisciplina(competidorPorDisciplina);
    status.setComplete();
    flash.addFlashAttribute("success", mensaje);
    return "redirect:/listarCompetidores";
  }

  @GetMapping("/crear_competidor/{id}")
  public String editarCompetidorPorDisciplina(@PathVariable(value = "id") Long id, Model modelo,
      RedirectAttributes flash) {
    List<Disciplina> disciplinas = disciplinaRepository.findAll();
    modelo.addAttribute("competidorPorDisciplina", new CompetidorPorDisciplina());
    modelo.addAttribute("disciplinas", disciplinas);
    CompetidorPorDisciplina competidorPorDisciplina = null;
    if (id > 0) {
      competidorPorDisciplina = competidorPorDisciplinaServicio.findOne(id);
      if (competidorPorDisciplina == null) {
        flash.addFlashAttribute("error", "El ID del Competidor Por Disciplina no existe en la base de datos");
        return "redirect:/listarCompetidores";
      }
    } else {
      flash.addFlashAttribute("error", "El ID del Competidor Por Disciplina no puede ser cero");
      return "redirect:/listarCompetidores";
    }

    ((Map<String, Object>) modelo).put("competidorPorDisciplina", competidorPorDisciplina);
    ((Map<String, Object>) modelo).put("titulo", "Edición de Competidor Por Disciplina");
    return "crear_competidor";
  }

  @GetMapping("/eliminarComp/{id}")
  public String eliminarCompetidorPorDisciplina(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
    if (id > 0) {
      competidorPorDisciplinaServicio.eliminarCompetidorPorDisciplina(id);
      flash.addFlashAttribute("success", "Competidor Por Disciplina eliminado con exito");
    }
    return "redirect:/listarCompetidores";
  }

  @RequestMapping("/listarCompetidores")
  public String busqueda(CompetidorPorDisciplina competidor, Model modelo, @RequestParam String keyword){
    if(keyword!=null){
      List<CompetidorPorDisciplina> list = competidorPorDisciplinaServicio.getbyKeyword(keyword);
      modelo.addAttribute("list",list);
      LOG.info("Busqueda");
    }else{
      List<CompetidorPorDisciplina> list=competidorPorDisciplinaServicio.visualizarCompetidores();
      modelo.addAttribute("list",list);
     LOG.info("Pase");
    }
    return "listarCompetidores";
  }

}