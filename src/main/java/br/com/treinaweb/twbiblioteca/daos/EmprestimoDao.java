package br.com.treinaweb.twbiblioteca.daos;

import java.util.List;

import br.com.treinaweb.twbiblioteca.models.Emprestimo;

public interface EmprestimoDao {

    List<Emprestimo> buscarTodos(Emprestimo emprestimo);

    Emprestimo salvar(Emprestimo emprestimo);

    Emprestimo buscarPorId(Long id);

    Emprestimo editar(Emprestimo emprestimo, Long id);

    void excluirPorId(Emprestimo emprestimo);

    List<Emprestimo> buscarAtrasados();

}
