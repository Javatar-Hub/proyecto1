package com.sistema.olimpiadas.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sistema.olimpiadas.modelo.Usuario;

@Repository
public interface EntrenadorRepository extends JpaRepository<Usuario, Long> {
    public Usuario findByEmail(String email);

    // @Query("SELECT user FROM Usuario user where ?3 in user.roles")
    // List<Usuario> findUserByRole(int role);

    @Query(value = "select * from usuarios u  where u.nombre like %:keyword% or u.apellidoPaterno like %:keyword%", nativeQuery = true)
    List<Usuario> findByKeyword(@Param("keyword") String keyword);

}
