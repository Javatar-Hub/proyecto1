package com.sistema.olimpiadas.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sistema.olimpiadas.modelo.Usuario;
import com.sistema.olimpiadas.servicio.EntrenadorServicio;

@Controller
public class RegistroControlador {

	@Autowired
	private EntrenadorServicio servicio;

	@GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}

	@GetMapping("/listarEntrenadores")
	public String verPaginaDeInicio(Model modelo) {
		modelo.addAttribute("usuarios", servicio.listarUsuarios());
		return "listarEntrenadores";
	}

	// aqui

	@RequestMapping("/listarEntrenadores")
	public String busqueda(Usuario entrenador, Model modelo, String keyword) {
		if (keyword != null) {
			List<Usuario> usuario = servicio.getbyKeyword(keyword);
			modelo.addAttribute("usuarios", usuario);
		} else {
			List<Usuario> usuario = servicio.getAllUsers();
			modelo.addAttribute("usuarios", usuario);
		}
		modelo.addAttribute("titulo", "Listado de Entrenadores");
		return "listarEntrenadores";
	}
}
