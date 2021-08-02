package br.com.treinaweb.twbiblioteca.builders;

import java.time.LocalDate;

import br.com.treinaweb.twbiblioteca.models.Autor;

public class AutorBuilder {

    private Autor autor;

    public static AutorBuilder builder() {
        var builder = new AutorBuilder();

        var autor = new Autor("Autor Teste", LocalDate.now(), null);
        builder.autor = autor;

        return builder;
    }

    public Autor build() {
        return autor;
    }

}
