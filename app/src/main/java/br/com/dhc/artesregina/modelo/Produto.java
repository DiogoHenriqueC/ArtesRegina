package br.com.dhc.artesregina.modelo;

import android.media.Image;

import java.io.Serializable;

public class Produto implements Serializable {

    private Long id;
    private String nome;
    private String descricao;
    private String categoria;
    private String autor;
    private String price;

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    private byte[] imagem;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return getId() + " - " + getNome() + " \n " + getAutor() + " - " + getPrice() + " \n " + getDescricao();
    }

}
