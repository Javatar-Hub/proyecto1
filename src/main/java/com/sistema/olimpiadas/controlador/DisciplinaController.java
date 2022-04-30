package com.sistema.olimpiadas.controlador;
import java.util.Map;

import com.sistema.olimpiadas.modelo.Disciplina;
import com.sistema.olimpiadas.servicio.DisciplinaServicio;
import com.sistema.olimpiadas.util.paginacion.PageRender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Controller
public class DisciplinaController {
    @Autowired
    private DisciplinaServicio disciplinaServicio;


    @GetMapping("/ver/{id}")
    public String verDetallesDisciplina(@PathVariable(value = "id") Long id,Map<String,Object> modelo, RedirectAttributes flash){
        Disciplina disciplina =disciplinaServicio.findOne(id);
        if(disciplina==null){
            flash.addFlashAttribute("error" , "Esa disciplina no existe en la base de datos");
            return "redirect:/listar";
        }

        modelo.put("Discipina",disciplina);
        modelo.put("titulo", "Detalles de la disciplina" + disciplina.getNombre_Discipina());
        return "ver";
    }

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
