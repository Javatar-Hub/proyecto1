package com.sistema.olimpiadas.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "comentarios")
public class ComentarioJuez {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "Comentario", nullable = false, length = 140)
    private String comentario;

    @NotEmpty
    @Column(name = "Comentario", nullable = false, length = 140)
    private Disciplina disciplina;

    @NotEmpty
    @Column(name = "Comentario", nullable = false, length = 140)
    private Juez juez;

    @NotEmpty
    @Column(name = "Comentario", nullable = false, length = 140)
    private CompetidorPorDisciplina competidor;

    public ComentarioJuez(Long id, @NotEmpty String comentario, @NotEmpty Disciplina disciplina, @NotEmpty Juez juez,
            @NotEmpty CompetidorPorDisciplina competidor) {
        super();
        this.id = id;
        this.comentario = comentario;
        this.disciplina = disciplina;
        this.juez = juez;
        this.competidor = competidor;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Juez getJuez() {
        return juez;
    }

    public void setJuez(Juez juez) {
        this.juez = juez;
    }

    public CompetidorPorDisciplina getCompetidor() {
        return competidor;
    }

    public void setCompetidor(CompetidorPorDisciplina competidor) {
        this.competidor = competidor;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

}
