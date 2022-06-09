
package com.sistema.olimpiadas.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "calificaciones")
public class Calificacion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idcalificacion;

  @Column(name = "competidor", nullable = false)
  private Long competidor;

  @Column(name = "calificacion", nullable = false)
  private int calificacion;

  @Column(name = "juez", length = 50)
  private String juez;

  public Calificacion() {
    super();
  }

  public Calificacion(Long idcalificacion, int calificacion, String juez, Long competidor) {
    super();
    this.idcalificacion = idcalificacion;
    this.juez = juez;
    this.calificacion = calificacion;
    this.competidor = competidor;
  }

  public Long getCompetidor() {
    return competidor;
  }

  public void setCompetidor(Long competidor) {
    this.competidor = competidor;
  }

  public Long getIdcalificacion() {
    return idcalificacion;
  }

  public void setIdcalificacion(Long id) {
    this.idcalificacion = id;
  }

  public int getCalificacion() {
    return calificacion;
  }

  public void setCalificacion(int calificacion) {
    this.calificacion = calificacion;
  }

  public String getJuez() {
    return juez;
  }

  public void setJuez(String juez) {
    this.juez = juez;
  }

}