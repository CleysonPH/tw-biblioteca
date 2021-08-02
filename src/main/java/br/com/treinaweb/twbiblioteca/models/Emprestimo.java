package br.com.treinaweb.twbiblioteca.models;

import java.time.LocalDate;
import java.util.List;

public class Emprestimo {

    private Long id;

    private Cliente cliente;

    private List<Livro> livros;

    private LocalDate dataEmprestimo;

    private LocalDate dataDevolucao;

    public Emprestimo() {}

    public Emprestimo(Cliente cliente, List<Livro> livros, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.cliente = cliente;
        this.livros = livros;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public Emprestimo(Long id, Cliente cliente, List<Livro> livros, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.id = id;
        this.cliente = cliente;
        this.livros = livros;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
        result = prime * result + ((dataDevolucao == null) ? 0 : dataDevolucao.hashCode());
        result = prime * result + ((dataEmprestimo == null) ? 0 : dataEmprestimo.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((livros == null) ? 0 : livros.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Emprestimo other = (Emprestimo) obj;
        if (cliente == null) {
            if (other.cliente != null)
                return false;
        } else if (!cliente.equals(other.cliente))
            return false;
        if (dataDevolucao == null) {
            if (other.dataDevolucao != null)
                return false;
        } else if (!dataDevolucao.equals(other.dataDevolucao))
            return false;
        if (dataEmprestimo == null) {
            if (other.dataEmprestimo != null)
                return false;
        } else if (!dataEmprestimo.equals(other.dataEmprestimo))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (livros == null) {
            if (other.livros != null)
                return false;
        } else if (!livros.equals(other.livros))
            return false;
        return true;
    }

}
