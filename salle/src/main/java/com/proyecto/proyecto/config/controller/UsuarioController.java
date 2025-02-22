package com.proyecto.proyecto.UsuarioController;

import java.security.SecureRandom;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.proyecto.proyecto.entity.Usuario;
import com.proyecto.proyecto.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        String nombreUsuario = generateUniqueUsername();
        String contrasena = generateRandomPassword();
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setContrasena(contrasena);
        return usuarioRepository.save(usuario);
    }

    @PutMapping
    public ResponseEntity<Usuario> actualizarUsuario(@RequestBody Usuario usuario) {
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable int id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.ok("Usuario eliminado con éxito");
        } else {
            return ResponseEntity.ok("Usuario no encontrado");
        }
    }

    private String generateUniqueUsername() {
        String baseUsername = "Usuario";
        int counter = 1;
        String newUsername = baseUsername + counter;
        while (usuarioRepository.existsByNombreUsuario(newUsername)) {
            counter++;
            newUsername = baseUsername + counter;
        }
        return newUsername;
    }

    private String generateRandomPassword() {
        SecureRandom random = new SecureRandom();
        int length = 6 + random.nextInt(5);
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    }
}
