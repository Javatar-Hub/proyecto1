package com.sistema.olimpiadas.controlador.dto;

public class EntrenadorRegistroDTO {
	private Long id;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private int edad;
	private String email;
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() { // New
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) { // New
		this.apellidoMaterno = apellidoMaterno;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public EntrenadorRegistroDTO(String nombre, String apellidoPaterno, String apellidoMaterno, int edad, String email,
			String password) {
		super();
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.edad = edad;
		this.email = email;
		this.password = password;
	}

	public EntrenadorRegistroDTO() {
	}
}
