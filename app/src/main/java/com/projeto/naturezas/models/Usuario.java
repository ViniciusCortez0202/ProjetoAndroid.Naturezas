package com.projeto.naturezas.models;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nome;
    private String email;
    private int pontuacao;
    private int id;
    private String numero;
    private String senha;
    private String foto;

    public Usuario(String nome, String email, int pontuacao, int id, String numero, String senha, String foto) {
        this.nome = nome;
        this.email = email;
        this.pontuacao = pontuacao;
        this.id = id;
        this.numero = numero;
        this.senha = senha;
        this.foto = foto
    }

    public Usuario(String nome, String email, String numero, String senha, String foto) {
        this.nome = nome;
        this.email = email;
        this.numero = numero;
        this.senha = senha;
        this.foto = foto;
    }

    public Usuario() {
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
