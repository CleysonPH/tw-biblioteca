package br.com.treinaweb.twbiblioteca.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import br.com.treinaweb.twbiblioteca.daos.EmprestimoDao;
import br.com.treinaweb.twbiblioteca.models.Cliente;
import br.com.treinaweb.twbiblioteca.models.Emprestimo;
import br.com.treinaweb.twbiblioteca.models.Livro;

public class EmprestimoService {

    private static final double MULTA_POR_DIA = 1.5;

    private EmprestimoDao emprestimoDao;

    private EmailService emailService;

    public EmprestimoService(EmprestimoDao emprestimoDao, EmailService emailService) {
        this.emprestimoDao = emprestimoDao;
        this.emailService = emailService;
    }

    public Emprestimo novo(Cliente cliente, List<Livro> livros) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo");
        }

        if (livros == null) {
            throw new IllegalArgumentException("Livros não pode ser nulo");
        }

        int diasParaDevolucao = cliente.getReputacao().obterDiasParaDevolucao();

        var dataEmprestimo = LocalDate.now();
        var dataDevolucao = dataEmprestimo.plusDays(diasParaDevolucao);

        var emprestimo = new Emprestimo(cliente, livros, dataEmprestimo, dataDevolucao);

        return emprestimoDao.salvar(emprestimo);
    }

    public boolean estaAtrasado(Emprestimo emprestimo) {
        var hoje = LocalDate.now();

        return emprestimo.getDataDevolucao().isBefore(hoje);
    }

    public double calcularMulta(Emprestimo emprestimo) {
        if (!estaAtrasado(emprestimo)) {
            return 0;
        }

        var diasEmAtraso = calcularDiasEmAtraso(emprestimo);

        return diasEmAtraso * MULTA_POR_DIA;
    }

    public void notificarAtrasados() {
        var emprestimosAtrasados = emprestimoDao.buscarAtrasados();

        for (Emprestimo emprestimo : emprestimosAtrasados) {
            emailService.enviarEmailDeAtraso(emprestimo);
        }
    }

    private long calcularDiasEmAtraso(Emprestimo emprestimo) {
        var hoje = LocalDate.now();
        return ChronoUnit.DAYS.between(emprestimo.getDataDevolucao(), hoje);
    }

}
