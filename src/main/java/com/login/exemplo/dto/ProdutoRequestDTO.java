package com.login.exemplo.dto;

import jakarta.validation.constraints.NotBlank;

public class ProdutoRequestDTO {

    @NotBlank(message = "O nome não pode ser nulo")
    private String name;

    @NotBlank(message = "O preço não pode ser nulo")
    private Double preco;

    @NotBlank(message = "A quantidade não pode ser nula")
    private int quantidade;

    public ProdutoRequestDTO(){

    }

    public ProdutoRequestDTO(String name, Double preco, int quantidade) {
        this.name = name;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
