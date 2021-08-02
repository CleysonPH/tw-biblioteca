package br.com.treinaweb.twbiblioteca.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.treinaweb.twbiblioteca.builders.ClienteBuilder;
import br.com.treinaweb.twbiblioteca.builders.EmprestimoBuilder;
import br.com.treinaweb.twbiblioteca.builders.LivroBuilder;
import br.com.treinaweb.twbiblioteca.daos.EmprestimoDao;
import br.com.treinaweb.twbiblioteca.enums.Reputacao;

@ExtendWith(MockitoExtension.class)
public class EmprestimoServiceTest {

    @Mock
    private EmprestimoDao emprestimoDao;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private EmprestimoService emprestimoService;

    @Test
    void quandoMetodoNovoForChamadoDeveRetornarUmEmprestimo() {
        var emprestimoEsperado = EmprestimoBuilder.builder().id(1L).build();
        when(emprestimoDao.salvar(any())).thenReturn(emprestimoEsperado);

        var emprestimo = emprestimoService.novo(emprestimoEsperado.getCliente(), emprestimoEsperado.getLivros());

        // Assertions.assertEquals(emprestimoEsperado, emprestimo);
        assertThat(emprestimo, is(equalTo(emprestimoEsperado)));
    }

    @Test
    void quandoMetodoNovoForChamadoDeveLancarUmaExcecaoDoTipoIllegalArgumentExceptionCasoOClienteSejaNulo() {
        var livros = List.of(LivroBuilder.builder().build());
        var mensagemEsperada = "Cliente não pode ser nulo";

        var excecao = assertThrows(IllegalArgumentException.class,
            () -> emprestimoService.novo(null, livros));
        assertThat(excecao.getMessage(), is(equalTo(mensagemEsperada)));
    }

    @Test
    void quandoMetodoNovoForChamadoDeveLancarUmaExcecaoDoTipoIllegalArgumentExceptionCasoOsLivrosSejamNulos() {
        var cliente = ClienteBuilder.builder().build();
        var mensagemEsperada = "Livros não pode ser nulo";

        var excecao = assertThrows(IllegalArgumentException.class,
            () -> emprestimoService.novo(cliente, null));
        assertEquals(mensagemEsperada, excecao.getMessage());
    }

    @Test
    void quandoMetodoEstaAtrasadoForChamdoDeveRetornarTrueCasoDataDevolucaoSejaMenorQueHoje() {
        var emprestimo = EmprestimoBuilder.builder()
            .dataEmprestimo(LocalDate.now().minusDays(6))
            .dataDevolucao(LocalDate.now().minusDays(3))
            .build();

        var estaAtrasado = emprestimoService.estaAtrasado(emprestimo);

        assertTrue(estaAtrasado);
    }

    @Test
    void quandoMetodoEstaAtrasadoForChamdoDeveRetornarFalseCasoDataDevolucaoSejaMaiorQueHoje() {
        var emprestimo = EmprestimoBuilder.builder().build();

        var estaAtrasado = emprestimoService.estaAtrasado(emprestimo);

        assertFalse(estaAtrasado);
    }

    @Test
    void quandoMetodoEstaAtrasadoForChamdoDeveRetornarFalseCasoDataDevolucaoSejaIgualHoje() {
        var emprestimo = EmprestimoBuilder.builder()
            .dataEmprestimo(LocalDate.now().minusDays(3))
            .dataDevolucao(LocalDate.now())
            .build();

        var estaAtrasado = emprestimoService.estaAtrasado(emprestimo);

        assertFalse(estaAtrasado);
    }

    @Test
    void quandoMetodoCalcularMultaForChamadoDeveRetornarZeroCasoNaoEstejaEmAtraso() {
        var emprestimo = EmprestimoBuilder.builder().build();
        double multaEsperada = 0;

        var multa = emprestimoService.calcularMulta(emprestimo);

        assertEquals(multaEsperada, multa);
    }

    @Test
    void quandoMetodoCalcularMultaForChamadoDeveRetornarAMultaDeAcordoComOsDiasEmAtraso() {
        var emprestimo = EmprestimoBuilder.builder()
            .dataEmprestimo(LocalDate.now().minusDays(6))
            .dataDevolucao(LocalDate.now().minusDays(3))
            .build();
        double multaEsperada = 4.5;

        var multa = emprestimoService.calcularMulta(emprestimo);

        assertEquals(multaEsperada, multa);
    }

    @ParameterizedTest
    @MethodSource
    void quandoMetodoNovoForChamadoDeveDevolverEmprestimoComDataDevolucaoEquivalenteAReputacaoDoCliente(
        Reputacao reputacao, int diasParaDevoluacao
    ) {
        var cliente = ClienteBuilder.builder().reputacao(reputacao).build();
        var livros = List.of(LivroBuilder.builder().build());
        var dataDevolucao = LocalDate.now().plusDays(diasParaDevoluacao);
        var emprestimoEsperado = EmprestimoBuilder.builder().dataDevolucao(dataDevolucao).id(1L).build();

        when(emprestimoDao.salvar(any())).thenReturn(emprestimoEsperado);

        var emprestimo = emprestimoService.novo(cliente, livros);

        assertEquals(dataDevolucao, emprestimo.getDataDevolucao());
    }

    @Test
    void quandoMetodoNotificarAtrasadosForChamadoDeveEnviarEmailParaEmprestimosAtrasados() {
        var emprestimosAtrasados = List.of(
            EmprestimoBuilder.builder().id(1L).build(),
            EmprestimoBuilder.builder().id(2L).build(),
            EmprestimoBuilder.builder().id(3L).build()
        );

        when(emprestimoDao.buscarAtrasados()).thenReturn(emprestimosAtrasados);

        emprestimoService.notificarAtrasados();

        verify(emailService).enviarEmailDeAtraso(emprestimosAtrasados.get(0));
        verify(emailService).enviarEmailDeAtraso(emprestimosAtrasados.get(1));
        verify(emailService).enviarEmailDeAtraso(emprestimosAtrasados.get(2));
    }

    static Stream<Arguments> quandoMetodoNovoForChamadoDeveDevolverEmprestimoComDataDevolucaoEquivalenteAReputacaoDoCliente() {
        return Stream.of(
            Arguments.arguments(Reputacao.RUIM, 1),
            Arguments.arguments(Reputacao.REGULAR, 3),
            Arguments.arguments(Reputacao.BOA, 5)
        );
    }

}
