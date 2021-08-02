package br.com.treinaweb.twbiblioteca.services;

import br.com.treinaweb.twbiblioteca.models.Emprestimo;

public interface EmailService {

    void enviarEmailDeAtraso(Emprestimo emprestimo);

}
