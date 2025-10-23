package com.login.exemplo.Controller;

import ch.qos.logback.core.status.Status;
import com.login.exemplo.Entity.Usuario;
import com.login.exemplo.Repostories.UsuarioRepository;
import jdk.jshell.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Stack;

@RestController
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping(value = "usuario/cadastro")
    public ResponseEntity<Usuario> saveUser(@RequestBody Usuario user) {
        Usuario usuario = new Usuario(user.getName(), user.getEmail(), user.getPassword());
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("login")
    public ResponseEntity<?> findUser(@RequestBody Usuario user) {
        Usuario findUser = usuarioRepository.findByEmail(user.getEmail());
        if (findUser == null) {
            return ResponseEntity.ok("Usuário não encontrado");
        } else {
            if (findUser.getPassword().equals(user.getPassword())) {
                return ResponseEntity.ok("Logado com sucesso");
            } else {
                return ResponseEntity.ok("Senha incorreta");
            }
        }
    }

    @GetMapping(value = "usuario/listar")
    public List<Usuario>listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping(value = "{id}")
    public Optional<Usuario>usuariosId(@PathVariable int id){
        return usuarioRepository.findById(id);
    }

    @DeleteMapping(value = "{id}")
    public  ResponseEntity<?> deleteId(@PathVariable int id) {
        usuarioRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuário deletado com sucesso");
    }
}
