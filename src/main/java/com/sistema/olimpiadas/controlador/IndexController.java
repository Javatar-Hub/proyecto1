package com.sistema.olimpiadas.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sistema.olimpiadas.servicio.CompetidorPorDisciplinaServicio;
import com.sistema.olimpiadas.servicio.DisciplinaServicio;
import com.sistema.olimpiadas.servicio.JuezServicio;

@Controller
public class IndexController {

  @Autowired
  private JuezServicio juezServicio;
  @Autowired
  private DisciplinaServicio disciplinaServicio;
  @Autowired
  private CompetidorPorDisciplinaServicio competidorPorDisciplinaServicio;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index(Model modelo) {
    modelo.addAttribute("jueces", juezServicio.cuentaJueces());
    modelo.addAttribute("disciplinas", disciplinaServicio.cuentaDisciplinas());
    modelo.addAttribute("competidores", competidorPorDisciplinaServicio.cuentaCompetidores());
    return "index";
  }
}
