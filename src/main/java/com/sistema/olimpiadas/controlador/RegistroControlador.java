package com.sistema.olimpiadas.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sistema.olimpiadas.modelo.Usuario;
import com.sistema.olimpiadas.servicio.EntrenadorServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class RegistroControlador {

	private final Logger LOG = LoggerFactory.getLogger(RegistroControlador.class);

	@Autowired
	private EntrenadorServicio servicio;

	@GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}

	@GetMapping("/listarEntrenadores")
	public String verlistaEntrenadores(Model modelo) {
		modelo.addAttribute("usuarios", servicio.visualizarEntrenadores());
		return "listarEntrenadores";
	}

	@RequestMapping("/listarEntrenadores")
	public String busqueda(Usuario competidor, Model modelo, String keyword) {
		if (keyword != null) {
			List<Usuario> usuario = servicio.getbyKeyword(keyword);
			modelo.addAttribute("usuarios", usuario);
			LOG.info("Busqueda");
		} else {
			List<Usuario> usuario = servicio.visualizarEntrenadores();
			modelo.addAttribute("usuarios", usuario);
			LOG.info("Pase");
		}
		modelo.addAttribute("usuarios", "Listado de Entrenadores");
		return "listarEntrenadores";
	}

}
