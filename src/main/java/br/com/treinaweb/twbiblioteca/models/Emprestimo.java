package br.com.treinaweb.twbiblioteca.models;

import java.time.LocalDate;

public class Emprestimo {

    private Cliente cliente;

    private Livro livro;

    private LocalDate dataEmprestimo;

    private LocalDate dataDevolucao;

    public Emprestimo() {}

    public Emprestimo(Cliente cliente, Livro livro, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.cliente = cliente;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

}
