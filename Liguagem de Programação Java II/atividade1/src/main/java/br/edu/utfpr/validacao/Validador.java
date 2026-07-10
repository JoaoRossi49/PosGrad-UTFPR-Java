package br.edu.utfpr.validacao;

import br.edu.utfpr.anotacoes.NaoNulo;
import br.edu.utfpr.anotacoes.Positivo;
import br.edu.utfpr.anotacoes.Tamanho;
import br.edu.utfpr.dominio.ContaBancaria;
import br.edu.utfpr.dominio.ContaPessoaFisica;
import br.edu.utfpr.dominio.ContaPessoaJuridica;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Validador {

    private static final int IDADE_MINIMA = 18;
    private static final BigDecimal CAPITAL_SOCIAL_MINIMO = new BigDecimal("10000");

    public ResultadoValidacao validar(ContaBancaria conta) {
        final List<Violacao> violacoes = new ArrayList<>();

        //guard
        if (conta == null) {
            violacoes.add(new Violacao("(objeto)", "o objeto a validar e nulo"));
            return ResultadoValidacao.de(violacoes);
        }

        // TODO ver requisito 1, implementar aqui    ---FEITO
        violacoes.addAll(validarCampos(conta));
        violacoes.addAll(regrasDeNegocio(conta));

        return ResultadoValidacao.de(violacoes);
    }

    public List<Violacao> validarCampos(Object objeto) {

        final List<Violacao> violacoes = new ArrayList<>();

        // TODO ver requisito 2, implementar aqui
        final Field[] campos = objeto.getClass().getDeclaredFields();
        for (final Field campo : campos) {
            try {
                campo.setAccessible(true);
                final Object valor = campo.get(objeto);

                verificarNaoNulo(campo, valor, violacoes);
                verificarTamanho(campo, valor, violacoes);
                verificarPositivo(campo, valor, violacoes);

            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Campo indisponível " + campo.getName(), e);
            }
        }

        return violacoes;
    }

    private void verificarNaoNulo(Field campo, Object valor, List<Violacao> acc) {
        // TODO implementar regra de negocio de nao nulo
        final NaoNulo naoNulo = campo.getAnnotation(NaoNulo.class);

        if(naoNulo != null && valor == null){
            acc.add(new Violacao(campo.getName(), naoNulo.mensagem()));
        }
    }

    private void verificarTamanho(Field campo, Object valor, List<Violacao> acc) {
        // TODO implementar regra de negocio de tamanho
        final Tamanho tamanho = campo.getAnnotation(Tamanho.class);

        if(valor instanceof String txt && tamanho != null){
            if(txt.length() < tamanho.min() || txt.length() > tamanho.max())
                acc.add(new Violacao(campo.getName(), tamanho.mensagem()));
        }
    }

    private void verificarPositivo(Field campo, Object valor, List<Violacao> acc) {
        // TODO implementar regra de negocio de positivo
        final Positivo positivo =
                campo.getAnnotation(Positivo.class);

        final boolean invalido = switch (valor) {
            case null -> false;
            case Integer numero -> numero <= 0;
            case Double numero -> numero <= 0;
            case Long numero -> numero <= 0;
            case BigDecimal numero -> numero.compareTo(BigDecimal.ZERO) <= 0;
            default -> false;
        };

        if (positivo != null && invalido) {
            acc.add(new Violacao(campo.getName(), positivo.mensagem()));
        }
    }

    private List<Violacao> regrasDeNegocio(ContaBancaria conta) {
        // TODO implementar as regras de negocio por tipo
        return switch (conta) {
            case ContaPessoaFisica pf -> validarMaioridade(pf);
            case ContaPessoaJuridica(_, _, _, _, BigDecimal capital) ->
                    validarCapitalSocial(capital);
        };
    }

    private List<Violacao> validarMaioridade(ContaPessoaFisica pf) {
        // TODO implementar regra de negocio de maioridade
        final java.time.LocalDate dataNascimento = pf.dataNascimento();
        if (dataNascimento == null) {
            return List.of();
        }

        final int idade = java.time.Period.between(dataNascimento, java.time.LocalDate.now()).getYears();
        if (idade < IDADE_MINIMA) {
            return List.of(new Violacao(Violacao.DATA_NASCIMENTO_CAMPO, Violacao.DATA_NASCIMENTO_MENSAGEM));
        }
        return List.of();
    }

    private List<Violacao> validarCapitalSocial(BigDecimal capital) {
        // TODO implementar regra de negocio de capital social
        if (capital == null) {
            return List.of();
        }

        if (capital.compareTo(CAPITAL_SOCIAL_MINIMO) < 0) {
            return List.of(new Violacao(Violacao.CAPITAL_SOCIAL_CAMPO, Violacao.CAPITAL_SOCIAL_MENSAGEM));
        }

        return List.of();
    }
}
