package com.login.exemplo.controller;

import com.login.exemplo.dto.UsuarioRequestDTO;
import com.login.exemplo.dto.UsuarioResponseDTO;
import com.login.exemplo.entity.Usuario;
import com.login.exemplo.repostories.UsuarioRepository;
import com.login.exemplo.service.UsuarioService;
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
    UsuarioService usuarioService;

    @PostMapping(value = "cadastro")
    public ResponseEntity<?> saveUser(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(usuarioService.saveUser(usuarioRequestDTO));
    }

    @PostMapping("login")
    public ResponseEntity<?> findUser(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
//        Usuario findUser = usuarioRequestDTO.getEmail(usuarioRequestDTO.getEmail());
        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body(usuarioService.findUser(usuarioRequestDTO));
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
            return ResponseEntity.ok(usuarioService.deletar(id));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.atualizar(id));

    }
}



