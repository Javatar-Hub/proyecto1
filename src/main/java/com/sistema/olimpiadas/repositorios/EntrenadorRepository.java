package com.sistema.olimpiadas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.olimpiadas.modelo.Usuario;

@Repository
public interface EntrenadorRepository extends JpaRepository<Usuario, Long>{
    public Usuario findByEmail(String email);
}
