package com.sistema.olimpiadas.controlador;

import com.sistema.olimpiadas.servicio.JuezServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class JuezController {

  @Autowired
  private JuezServicio juezServicio;
}