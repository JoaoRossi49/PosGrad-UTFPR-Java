package br.edu.utfpr;

import br.edu.utfpr.dominio.*;
import br.edu.utfpr.utilidades.*;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Joiner;
import java.util.concurrent.StructuredTaskScope.Subtask;

public class ProcessadorDePedidos {

    public Relatorio processarArquivo(Path arquivoEntrada) {

        List<Pedido> pedidos = LeitorDePedidos.ler(arquivoEntrada);

        List<PedidoAprovado> aprovados = new ArrayList<>();
        List<PedidoRejeitado> rejeitados = new ArrayList<>();

        try (var escopo = StructuredTaskScope.open(Joiner.<ResultadoPedido>allSuccessfulOrThrow())) {

            List<Subtask<ResultadoPedido>> tarefas = new ArrayList<>();

            for (Pedido pedido : pedidos) {
                tarefas.add(escopo.fork(() -> processarPedido(pedido)));
            }

            escopo.join();

            for (Subtask<ResultadoPedido> tarefa : tarefas) {
                ResultadoPedido resultado = tarefa.get();

                if (resultado instanceof PedidoAprovado aprovado) {
                    aprovados.add(aprovado);
                } else if (resultado instanceof PedidoRejeitado rejeitado) {
                    rejeitados.add(rejeitado);
                }
            }

            return new Relatorio(aprovados, rejeitados);

        } catch (InterruptedException excecao) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("O processamento foi interrompido", excecao);
        }
    }

    public ResultadoPedido processarPedido(Pedido pedido) {
        try (var escopo = StructuredTaskScope.open(Joiner.awaitAllSuccessfulOrThrow())) {

            Subtask<Estoque> tarefaEstoque = escopo.fork(() -> ServicosExternos.consultarEstoque(pedido.produto(), pedido.identificador()));
            Subtask<Preco> tarefaPreco = escopo.fork(() -> ServicosExternos.consultarPreco(pedido.produto(), pedido.identificador()));

            escopo.join();

            Estoque estoque = tarefaEstoque.get();
            Preco preco = tarefaPreco.get();

            if (estoque.quantidadeDisponivel() < pedido.quantidade()) {
                return new PedidoRejeitado(pedido.identificador(), "estoque insuficiente para o produto.");
            }

            CotacaoFrete frete = cotarFrete(pedido.produto());

            BigDecimal quantidade = BigDecimal.valueOf(pedido.quantidade());
            BigDecimal valorProdutos = preco.valorUnitario().multiply(quantidade);
            BigDecimal valorTotal = valorProdutos.add(frete.valor());

            return new PedidoAprovado(pedido.identificador(), valorTotal, frete);

        } catch (StructuredTaskScope.FailedException excecao) {
            return new PedidoRejeitado(pedido.identificador(), "Erro ao validar pedido: " + excecao.getCause().getMessage());
        } catch (InterruptedException excecao) {
            Thread.currentThread().interrupt();
            return new PedidoRejeitado(pedido.identificador(), "Processamento do pedido interrompido.");
        }
    }

    private CotacaoFrete cotarFrete(String produto) throws InterruptedException {
        try (var escopo = StructuredTaskScope.open(Joiner.<CotacaoFrete>anySuccessfulResultOrThrow())) {

            escopo.fork(() -> ServicosExternos.cotarFreteTransportadoraUm(produto));
            escopo.fork(() -> ServicosExternos.cotarFreteTransportadoraDois(produto));

            return escopo.join();
        }
    }
}