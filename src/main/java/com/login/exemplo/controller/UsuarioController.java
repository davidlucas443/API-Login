package com.login.exemplo.controller;

import com.login.exemplo.dto.UsuarioRequestDTO;
import com.login.exemplo.dto.UsuarioResponseDTO;
import com.login.exemplo.entity.Usuario;
import com.login.exemplo.repostories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping(value = "cadastro")
    public ResponseEntity<?> saveUser(@Valid @RequestBody UsuarioRequestDTO user) {
        Usuario usuario = new Usuario(user.getName(), user.getEmail(), user.getPassword());
        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Deu certo pohaaaaaaaaaaaaaaaa");
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
//
//    @GetMapping(value = "usuario/listar")
//    public List<Usuario> listarUsuarios() {
//        return usuarioRepository.findAll();
//    }


//    @GetMapping(value = "{id}")
//    public List<UsuarioResponseDTO> listarUsuarios(@PathVariable int id) {
//        return usuarioRepository.findById(id);
//    }

    @GetMapping(value = "listar")
    public List<UsuarioResponseDTO>listarUsuario(){
        List<Usuario> usuarios = usuarioRepository.findAll();
//        List<UsuarioResponseDTO>listDeUsuarios = new ArrayList<>();
        List<UsuarioResponseDTO>listaDeUsuarios =
                usuarios.stream().map(UsuarioResponseDTO::new).toList();
        for(Usuario usuario : usuarios){
            listaDeUsuarios.add(new UsuarioResponseDTO(usuario));
        }
        return listaDeUsuarios;

    }



//    @GetMapping(value = "{id}")
//    public Optional<Usuario> usuariosId(@PathVariable int id) {
//        return usuarioRepository.findById(id);
//    }

//    @DeleteMapping(value = "{id}")
//    public ResponseEntity<?> deleteId(@PathVariable int id) {
//        usuarioRepository.deleteById(id);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuário deletado com sucesso");
//    }

//    @DeleteMapping("{id}")
//    public ResponseEntity<?> deletar(@PathVariable Integer id) {
//
//        if (usuarioRepository.existsById(id)) {
//            usuarioRepository.deleteById(id);
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {

        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Excluído com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse ID não existe");
        }

    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(@Valid @PathVariable int id, @RequestBody UsuarioRequestDTO novoUsuario) {

        Optional<Usuario> UsuarioExiste = usuarioRepository.findById(id);

        if (UsuarioExiste.isPresent()) {
            Usuario Usuario = UsuarioExiste.get();
            Usuario.setName(novoUsuario.getName());
            Usuario.setPassword(novoUsuario.getPassword());
            usuarioRepository.save(Usuario);
            return ResponseEntity.status(HttpStatus.OK).body(Usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse ID não existe");
        }

    }
}



