package com.sistema.olimpiadas.servicio;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.sistema.olimpiadas.controlador.dto.EntrenadorRegistroDTO;
import com.sistema.olimpiadas.modelo.Usuario;

public interface EntrenadorServicio extends UserDetailsService {

    public Usuario guardar(EntrenadorRegistroDTO registroDTO);

    public List<Usuario> listarUsuarios();
}
