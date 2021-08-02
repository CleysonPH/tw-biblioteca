package br.com.treinaweb.twbiblioteca.builders;

import java.time.LocalDate;
import java.util.List;

import br.com.treinaweb.twbiblioteca.models.Cliente;
import br.com.treinaweb.twbiblioteca.models.Emprestimo;

public class EmprestimoBuilder {

    private Emprestimo emprestimo;

    public static EmprestimoBuilder builder() {
        var builder = new EmprestimoBuilder();

        var cliente = ClienteBuilder.builder().build();
        var livros = List.of(LivroBuilder.builder().build());
        var dataEmprestimo = LocalDate.now();
        var dataDevolucao = dataEmprestimo.plusDays(3);
        var emprestimo = new Emprestimo(cliente, livros, dataEmprestimo, dataDevolucao);

        builder.emprestimo = emprestimo;

        return builder;
    }

    public EmprestimoBuilder id(Long id) {
        emprestimo.setId(id);

        return this;
    }

    public EmprestimoBuilder dataDevolucao(LocalDate dataDevolucao) {
        emprestimo.setDataDevolucao(dataDevolucao);

        return this;
    }

    public EmprestimoBuilder dataEmprestimo(LocalDate dataEmprestimo) {
        emprestimo.setDataEmprestimo(dataEmprestimo);

        return this;
    }

    public EmprestimoBuilder cliente(Cliente cliente) {
        emprestimo.setCliente(cliente);

        return this;
    }

    public Emprestimo build() {
        return emprestimo;
    }

}
