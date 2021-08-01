package br.com.treinaweb.twbiblioteca.models;

import java.time.LocalDate;

public class Cliente extends Pessoa {

    private String cpf;

    public Cliente() {}

    public Cliente(String nome, LocalDate dataNascimento, String cpf) {
        super(nome, dataNascimento);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
