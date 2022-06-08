package com.sistema.olimpiadas.servicio;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.olimpiadas.controlador.dto.EntrenadorRegistroDTO;
import com.sistema.olimpiadas.repositorios.EntrenadorRepository;
import com.sistema.olimpiadas.modelo.Rol;
import com.sistema.olimpiadas.modelo.Usuario;

@Service
public class EntrenadorServicioImpl implements EntrenadorServicio {

	private EntrenadorRepository entrenadorRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public EntrenadorServicioImpl(EntrenadorRepository entrenadorRepository) {
		super();
		this.entrenadorRepository = entrenadorRepository;
	}

	@Override
	public Usuario guardar(EntrenadorRegistroDTO registroDTO) {
		Usuario usuario = new Usuario(registroDTO.getNombre(),
				registroDTO.getApellidoPaterno(), registroDTO.getApellidoMaterno(), registroDTO.getEdad(),
				registroDTO.getEmail(),
				passwordEncoder.encode(registroDTO.getPassword()), Arrays.asList(new Rol("ENTRENADOR")));
		return entrenadorRepository.save(usuario);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = entrenadorRepository.findByEmail(username);
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
		return new User(usuario.getEmail(), usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
	}

	@Override
	public List<Usuario> listarUsuarios() {
		return entrenadorRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> getAllUsers() {
		List<Usuario> list = (List<Usuario>) entrenadorRepository.findAll();
		return list;
	}

	public List<Usuario> getbyKeyword(String keyword) {
		return entrenadorRepository.findByKeyword(keyword);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> visualizarEntrenadores() {
		List<Usuario> list = (List<Usuario>) entrenadorRepository.findAll();
		return list;

	}

	@Override
	public long cuentaUsuarios() {
		return entrenadorRepository.countByIdUsuarios();
	}

}
