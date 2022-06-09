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

    @Query("SELECT user FROM Usuario user where ?1 in user.roles")
    List<Usuario> findUserByRole(int role);

    // @Query(value = "select * from usuarios u where u.nombre like %:keyword% or
    // u.apellidoPaterno like %:keyword%", nativeQuery = true)
    // List<Usuario> findByKeyword(@Param("keyword") String keyword);

    // @Query("SELECT p FROM Usuario p WHERE CONCAT(p.nombre, p.apellidoPaterno)
    // LIKE %?1%")
    // public List<Usuario> search(String keyword);

    @Query(value = "select * from usuarios u  where u.nombre like %:keyword% or u.apellidoPaterno like %:keyword%", nativeQuery = true)
    List<Usuario> findByKeyword(@Param("keyword") String keyword);

    @Query("select count(u) from Usuario u ")
    long countByIdUsuarios();

}
