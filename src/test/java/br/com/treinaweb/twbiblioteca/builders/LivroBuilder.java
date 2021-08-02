package br.com.treinaweb.twbiblioteca.builders;

import br.com.treinaweb.twbiblioteca.enums.Tipo;
import br.com.treinaweb.twbiblioteca.models.Livro;

public class LivroBuilder {

    private Livro livro;

    public static LivroBuilder builder() {
        var builder = new LivroBuilder();

        var autor = AutorBuilder.builder().build();
        var livro = new Livro("Livro Teste", 10, Tipo.LIVRO, autor);
        builder.livro = livro;

        return builder;
    }

    public Livro build() {
        return livro;
    }

}
