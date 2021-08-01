package br.com.treinaweb.twbiblioteca.models;

import br.com.treinaweb.twbiblioteca.enums.Tipo;

public class Livro {

    private String isbn;

    private String nome;

    private Integer qtdPaginas;

    private Tipo tipo;

    private Autor autor;

    public Livro() {}

    public Livro(String isbn, String nome, Integer qtdPaginas, Tipo tipo, Autor autor) {
        this.isbn = isbn;
        this.nome = nome;
        this.qtdPaginas = qtdPaginas;
        this.tipo = tipo;
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQtdPaginas() {
        return qtdPaginas;
    }

    public void setQtdPaginas(Integer qtdPaginas) {
        this.qtdPaginas = qtdPaginas;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

}
