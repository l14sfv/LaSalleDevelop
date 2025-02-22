package com.proyecto.proyecto.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyecto.proyecto.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByNombreUsuarioAndContrasena(String nombreUsuario, String contrasena);
    boolean existsByNombreUsuario(String nombreUsuario);
}