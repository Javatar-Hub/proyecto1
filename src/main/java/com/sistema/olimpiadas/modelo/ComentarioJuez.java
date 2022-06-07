package com.sistema.olimpiadas.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "comentarios")
public class ComentarioJuez {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcomentario;

    @Column(name = "idcompetidor", nullable = false)
    private Long idcompetidor;
    
    @NotEmpty
    @Column(name = "Comentario", nullable = false, length = 140)
    private String comentario;

    @Column(name = "Juez", length = 50)
    private String juez;

    public ComentarioJuez(){
        super();
    }

    public ComentarioJuez(Long idcomentario, @NotEmpty String comentario, String juez, Long idcompetidor) {
        super();
        this.idcomentario = idcomentario;
        this.juez = juez;
        this.comentario = comentario;
        this.idcompetidor = idcompetidor;
    }

    public Long getIdcompetidor() {
        return idcompetidor;
    }

    public void setIdcompetidor(Long idcompetidor) {
        this.idcompetidor = idcompetidor;
    }

    public Long getId(){
        return idcomentario;
    }

    public void setId(Long id) {
        this.idcomentario = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getJuez() {
        return juez;
    }

    public void setJuez(String juez) {
        this.juez = juez;
    }

}
