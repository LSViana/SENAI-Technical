package br.com.senai.meuslivros.model;

/**
 * Created by adminLocal on 11/04/2018.
 */

public class Livro {
    private String caminhoDaCapa;
    private String tituloDoLivro;
    private String autorDoLivro;
    private Long id;

    public String getCaminhoDaCapa() {
        return caminhoDaCapa;
    }

    public void setCaminhoDaCapa(String caminhoDaCapa) {
        this.caminhoDaCapa = caminhoDaCapa;
    }

    public String getTituloDoLivro() {
        return tituloDoLivro;
    }

    public void setTituloDoLivro(String tituloDoLivro) {
        this.tituloDoLivro = tituloDoLivro;
    }

    public String getAutorDoLivro() {
        return autorDoLivro;
    }

    public void setAutorDoLivro(String autorDoLivro) {
        this.autorDoLivro = autorDoLivro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
