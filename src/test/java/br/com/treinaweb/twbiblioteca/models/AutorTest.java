package br.com.treinaweb.twbiblioteca.models;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AutorTest {

    @Test
    void quandoMetodoEstaVivoForChamadoDeveRetornarTrueCasoDataFalecimentoEstajaNula() {
        var autor = new Autor();

        var estaVivo = autor.estaVivo();

        Assertions.assertTrue(estaVivo);
    }

    @Test
    void quandoMetodoEstaVivoForChamadoDeveRetornarFalseCasoDataFalecimentoNaoEstajaNula() {
        var autor = new Autor();
        autor.setDataFalecimento(LocalDate.now());

        var estaVivo = autor.estaVivo();

        Assertions.assertFalse(estaVivo);
    }

}
