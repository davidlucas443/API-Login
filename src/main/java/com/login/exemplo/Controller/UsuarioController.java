package com.login.exemplo.Controller;

import com.login.exemplo.Entity.Usuario;
import com.login.exemplo.Repostories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> findUser(@RequestBody Usuario user){
        Usuario findUser = usuarioRepository.findByEmail(user.getEmail());
        if (findUser == null){
            return ResponseEntity.ok("Usuário não encontrado");
        }
        else {
            if (findUser.getPassword().equals(user.getPassword())) {
                return ResponseEntity.ok("Logado com sucesso");
            }
            else{
                return ResponseEntity.ok("Senha incorreta");
            }
          }
        }
        @GetMapping(value = "usuario/listar")
        public ResponseEntity<?> findAll() {
            return ResponseEntity.ok(usuarioRepository.findAll());
    }
}
