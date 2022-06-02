package com.sistema.olimpiadas.controlador;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.sistema.olimpiadas.modelo.Disciplina;
import com.sistema.olimpiadas.modelo.Juez;
import com.sistema.olimpiadas.repositorios.DisciplinaRepository;
import com.sistema.olimpiadas.servicio.JuezServicio;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class JuezController {

  @Autowired
  private JuezServicio juezServicio;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index() {
    return "index";
  }

  @GetMapping("/ver/{id}")
  public String verDetallesDelJuez(@PathVariable(value = "id") Long id, Map<String, Object> modelo,
      RedirectAttributes flash) {
    Juez juez = juezServicio.findOne(id);
    if (juez == null) {
      flash.addFlashAttribute("error", "El juez no existe en la base de datos");
      return "redirect:/listar";
    }

    modelo.put("juez", juez);
    modelo.put("titulo", "Detalles del juez " + juez.getNombre());
    return "ver";
  }

  @GetMapping({ "/listar" })
  public String listarJueces(@RequestParam(name = "page", defaultValue = "0") int page, Model modelo) {
    Pageable pageRequest = PageRequest.of(page, 10);
    Page<Juez> jueces = juezServicio.visualizarJueces(pageRequest);
    PageRender<Juez> pageRender = new PageRender<>("/listar", jueces);

    modelo.addAttribute("titulo", "Listado de jueces");
    modelo.addAttribute("jueces", jueces);
    modelo.addAttribute("page", pageRender);

    return "listar";
  }

  @Autowired
  private DisciplinaRepository disciplinaRepository;

  @GetMapping("/form")
  public String mostrarFormularioDeRegistroJuez(Model modelo) {
    List<Disciplina> disciplinas = disciplinaRepository.findAll();

    modelo.addAttribute("juez", new Juez());
    modelo.addAttribute("disciplinas", disciplinas);
    ((Map<String, Object>) modelo).put("titulo", "Registro de Jueces");

    return "crear_juez";
  }

  @PostMapping("/form")
  public String guardarJuez(@Valid Juez juez, BindingResult result, Model modelo,
      @RequestParam("file") MultipartFile foto, RedirectAttributes flash,
      SessionStatus status) {
    if (result.hasErrors()) {
      modelo.addAttribute("titulo", "Registro de juez");
      return "form";
    }

    if (!foto.isEmpty())

    {
      Path directorioImagenes = Paths.get("src/main/resources/static/images/");
      String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

      try {
        byte[] bytesImg = foto.getBytes();
        Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + foto.getOriginalFilename());
        Files.write(rutaCompleta, bytesImg);

        juez.setFoto(foto.getOriginalFilename());
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    }

    System.out.println("Juez ID: " + juez.getId());
    String mensaje = (juezServicio.guardarJuez(juez) != null) ? "El juez ha sido editado con exito"
        : "Juez registrado con exito";
    status.setComplete();
    flash.addFlashAttribute("success", mensaje);
    return "redirect:/listar";
  }

  @GetMapping("/form/{id}")
  public String editarJuez(@PathVariable(value = "id") Long id, Model modelo,
      RedirectAttributes flash) {
    List<Disciplina> disciplinas = disciplinaRepository.findAll();
    modelo.addAttribute("juez", new Juez());
    modelo.addAttribute("disciplinas", disciplinas);
    Juez juez = null;
    if (id > 0) {
      juez = juezServicio.findOne(id);
      if (juez == null) {
        flash.addFlashAttribute("error", "El ID del juez no existe en la base de datos");
        return "redirect:/listar";
      }
    } else {
      flash.addFlashAttribute("error", "El ID del juez no puede ser cero");
      return "redirect:/listar";
    }

    ((Map<String, Object>) modelo).put("juez", juez);
    ((Map<String, Object>) modelo).put("titulo", "Edición de juez");
    return "crear_juez";
  }

  @GetMapping("/eliminar/{id}")
  public String eliminarJuez(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
    if (id > 0) {
      juezServicio.eliminarJuez(id);
      flash.addFlashAttribute("success", "Juez eliminado con exito");
    }
    return "redirect:/listar";
  }

}