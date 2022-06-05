package com.sistema.olimpiadas.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sistema.olimpiadas.servicio.EntrenadorServicio;

@Controller
public class RegistroController {
	@Autowired
	private EntrenadorServicio servicio;

	@GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}

	@GetMapping("/")
	public String verPaginaDeInicio(Model modelo) {
		modelo.addAttribute("usuarios", servicio.listarUsuarios());
		return "index";
	}
}
