package com.login.exemplo.dto;

import com.login.exemplo.entity.Produto;

public class ProdutoResponseDTO {

    private String name;

    private Double preco;

    private int quantidade;

    private double subtotal;

    public ProdutoResponseDTO(Produto prod) {
        this.name = prod.getName();
        this.preco = prod.getPreco();
        this.quantidade = prod.getQuantidade();
        this.subtotal = prod.getPreco()* prod.getQuantidade();

    }

    public String getName() {
        return name;
    }

    public Double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getSubtotal() {
        return subtotal;
    }
}
