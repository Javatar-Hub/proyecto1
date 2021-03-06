package com.sistema.olimpiadas.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sistema.olimpiadas.controlador.dto.EntrenadorRegistroDTO;

import com.sistema.olimpiadas.servicio.EntrenadorServicio;

@Controller
@RequestMapping("/registro")
public class RegistroEntrenadorController {

	@Autowired
	private EntrenadorServicio entrenadorServicio;

	public RegistroEntrenadorController(EntrenadorServicio entrenadorServicio) {
		super();
		this.entrenadorServicio = entrenadorServicio;
	}

	@ModelAttribute("usuario")
	public EntrenadorRegistroDTO retornarNuevoEntrenadorRegistroDTO() {
		return new EntrenadorRegistroDTO();
	}

	@GetMapping
	public String mostrarFormularioDeRegistro() {
		return "registro";
	}

	@PostMapping
	public String registrarCuentaDeEntrenador(@ModelAttribute("usuario") EntrenadorRegistroDTO registroDTO) {
		entrenadorServicio.guardar(registroDTO);
		return "redirect:/registro?exito";
	}

	// @GetMapping("/login")
	// public String iniciarSesion() {
	// return "login";
	// }

	// @GetMapping("/listarEntrenadores")
	// public String verPaginaDeInicio(Model modelo) {
	// modelo.addAttribute("usuarios", entrenadorServicio.listarUsuarios());
	// return "listarEntrenadores";
	// }

	// aqui

	// @RequestMapping("/listarEntrenadores")
	// public String viewHomePage(Model model, @Param("keyword") String keyword) {
	// List<Usuario> listProducts = entrenadorServicio.listAll(keyword);
	// model.addAttribute("Usuarios", listProducts);
	// model.addAttribute("keyword", keyword);

	// return "listarEntrenadores";
	// }

}
