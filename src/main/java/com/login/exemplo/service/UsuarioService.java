package com.login.exemplo.service;

import com.login.exemplo.dto.UsuarioRequestDTO;
import com.login.exemplo.dto.UsuarioResponseDTO;
import com.login.exemplo.entity.Usuario;
import com.login.exemplo.repostories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public UsuarioResponseDTO saveUser(UsuarioRequestDTO usuarioRequestDTO){
        Usuario usuario = new Usuario(usuarioRequestDTO.getName(),
                usuarioRequestDTO.getEmail(),
                usuarioRequestDTO.getPassword());
        usuarioRepository.save(usuario);
        UsuarioResponseDTO user = new UsuarioResponseDTO(usuario);
        return user;
    }
    @PostMapping("login")
    public String findUser(UsuarioRequestDTO usuarioRequestDTO) {
        Usuario findUser = usuarioRepository.findByEmail(usuarioRequestDTO.getEmail());
        if (findUser == null) {
            return "Usuário não encontrado";
        } else {
            if (findUser.getPassword().equals(usuarioRequestDTO.getPassword())) {
                return "Logado com sucesso";
            } else {
                return "Senha incorreta";
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

    public List<UsuarioResponseDTO> listarUsuario(){
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


    public String deletar(int id) {

        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return "Excluído com sucesso!";
        } else {
            return "Esse ID não existe";
        }
    }

    public String atualizar(int id) {

        Optional<Usuario> UsuarioExiste = usuarioRepository.findById(id);
        if (UsuarioExiste.isPresent()) {
            Usuario usuario = UsuarioExiste.get();
            usuario.setName(usuario.getName());
            usuario.setPassword(usuario.getPassword());
            usuarioRepository.save(usuario);
            UsuarioResponseDTO teste = new UsuarioResponseDTO(usuario);
            return "Usuario atualizado";
        } else {
            return "Esse ID não existe";
        }

    }
}
