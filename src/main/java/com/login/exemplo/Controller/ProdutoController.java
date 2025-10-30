package com.login.exemplo.Controller;

import com.login.exemplo.Entity.Produto;
import com.login.exemplo.Entity.Usuario;
import com.login.exemplo.Repostories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("Produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping(value = "produto/listar")
    public List<Produto> listarUsuarios() {
        return produtoRepository.findAll();
    }

    @GetMapping(value = "{id}")
    public Optional<Produto> produtoId(@PathVariable int id) {
        return produtoRepository.findById(id);
    }




    @PostMapping(value = "cadastre")
    public ResponseEntity<?> saveProduto(@RequestBody Produto prod) {
        Produto produto = new Produto(prod.getName(), prod.getPreco(), prod.getQuantidade());
        produtoRepository.save(produto);
        return ResponseEntity.ok("Produto Cadastrado com sucesso");
    }




    @DeleteMapping("{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {

        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Excluído com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse ID não existe");
        }

    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(@PathVariable int id, @RequestBody Produto produto) {

        Optional<Produto> novo = produtoRepository.findById(id);

        if (novo.isPresent()) {
            Produto e = novo.get();
            e.setQuantidade(produto.getQuantidade());
            produtoRepository.save(e);
            return ResponseEntity.status(HttpStatus.OK).body(e);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse ID não existe");
        }

    }

}

