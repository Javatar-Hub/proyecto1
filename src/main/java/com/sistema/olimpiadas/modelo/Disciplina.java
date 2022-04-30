package com.sistema.olimpiadas.modelo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "disciplinas")
public class Disciplina{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @Column(name= "nombre_disciplina", nullable = false , length = 50)
    private String nombre_disciplina;

    //constructor vacio de la disciplina
    public Disciplina(){}

    public Disciplina(long id, String nombre_disciplina){
        super();
        this.id=id;
        this.nombre_disciplina=nombre_disciplina;
    }

    public Disciplina(String nombre_disciplina){
        super();
        this.nombre_disciplina=nombre_disciplina;
    }

    public long getID(){
        return this.id;
    }

    public void setID(long id){
        this.id=id;
    }

    public String getNombre_Discipina(){
        return this.nombre_disciplina;
    }

    public void setNombre_Disciplina(String nombre_disciplina){
        this.nombre_disciplina=nombre_disciplina;
    }

    @Override
    public String toString(){
        return "{"+
            "id=" + getID() + "'" +
            ", nombre_disciplinas" + getNombre_Discipina() + "'" +
        "}";
    }
}
