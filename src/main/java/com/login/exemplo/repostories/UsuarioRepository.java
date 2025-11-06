package com.login.exemplo.repostories;

import com.login.exemplo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByEmail(String email);
//    UsuarioRepository findByEmail(String email);
}
