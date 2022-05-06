package com.sistema.olimpiadas.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "competidores")
public class CompetidorPorDisciplina {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty
  @Column(name = "nombre", nullable = false, length = 50)
  private String nombre;

  @NotEmpty
  @Column(name = "apellido_paterno", nullable = false, length = 50)
  private String paterno;

  @NotEmpty
  @Column(name = "apellido_materno", nullable = false, length = 50)
  private String materno;

  @NotEmpty
  @Column(name = "Nombre_de_usuario", nullable = false, length = 50, unique = true)
  private String usuario;

  @NotNull
  @Column(name = "Edad", nullable = false, length = 2)
  private int edad;

  @Column(name = "Foto", nullable = false, length = 50)
  private String foto;

  @NotEmpty
  @Column(name = "Disciplina", nullable = true, length = 50)
  private String disciplina;

  @NotEmpty
  @Column(name = "Plantel", nullable = true, length = 50)
  private String plantel;

  // Contructor vacio
  public CompetidorPorDisciplina() {

  }

  public CompetidorPorDisciplina(Long id, String nombre, String paterno, String materno, String usuario, int edad,
      String password, String foto, String disciplina, String plantel) {
    super();
    this.id = id;
    this.nombre = nombre;
    this.paterno = paterno;
    this.materno = materno;
    this.usuario = usuario;
    this.edad = edad;
    this.foto = foto;
    this.disciplina = disciplina;
    this.plantel = plantel;
  }

  public CompetidorPorDisciplina(String nombre, String paterno, String materno, String usuario, int edad,
      String password, String foto, String disciplina, String plantel) {
    super();
    this.nombre = nombre;
    this.paterno = paterno;
    this.materno = materno;
    this.usuario = usuario;
    this.edad = edad;
    this.foto = foto;
    this.disciplina = disciplina;
    this.plantel = plantel;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getPaterno() {
    return this.paterno;
  }

  public void setPaterno(String paterno) {
    this.paterno = paterno;
  }

  public String getMaterno() {
    return this.materno;
  }

  public void setMaterno(String materno) {
    this.materno = materno;
  }

  public String getUsuario() {
    return this.usuario;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  public int getEdad() {
    return this.edad;
  }

  public void setEdad(int edad) {
    this.edad = edad;
  }

  public String getFoto() {
    return this.foto;
  }

  public void setFoto(String foto) {
    this.foto = foto;
  }

  public String getDisciplina() {
    return this.disciplina;
  }

  public void setDisciplina(String disciplina) {
    this.disciplina = disciplina;
  }

  public String getPlantel() {
    return this.plantel;
  }

  public void setPlantel(String plantel) {
    this.plantel = plantel;
  }

  @Override
  public String toString() {
    return "{" +
        " id='" + getId() + "'" +
        ", nombre='" + getNombre() + "'" +
        ", paterno='" + getPaterno() + "'" +
        ", materno='" + getMaterno() + "'" +
        ", usuario='" + getUsuario() + "'" +
        ", edad='" + getEdad() + "'" +
        ", foto='" + getFoto() + "'" +
        ", disciplina='" + getDisciplina() + "'" +
        ", plantel='" + getPlantel() + "'" +
        "}";
  }

}
