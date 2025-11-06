package com.login.exemplo.dto;

import jakarta.persistence.Column;

public class UsuarioResponseDTO {

    private String name;

    @Column(unique = true)
    private String email;

    public UsuarioResponseDTO(){

    }
    public UsuarioResponseDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
