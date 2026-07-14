package br.edu.utfpr;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Stream;

public class CotadorDeMoedas {

    public Path verificarEntrada() {
        // TODO
        final Path arquivo = Path.of("entrada", "moedas.txt");

        return arquivo;
    }

    public Path verificarSaida() {
        // TODO
        try {
            final Path arquivo = Path.of("saida", "cotacoes.csv");
            if (!Files.exists(arquivo)) {
                Files.createDirectories(arquivo.getParent());
            }
            return arquivo;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> lerArquivoDeMoedas(Path entrada) {
        // TODO

        List<String> retorno = new ArrayList<>();

        try (Stream<String> linhas = Files.lines(entrada)) {

            linhas.map(String::trim)
                    .filter(s -> !s.isBlank())
                    .map(String::toUpperCase)
                    .filter(s -> s.length() == 3) // codigo ISO de moeda
                    .forEach(retorno::add);

            return retorno;

        } catch (Exception e) {
            return List.of();
        }
    }

    public void cotarERegistrar(Path saida, List<String> moedas, ClienteCambio cliente) {

        //Se a lista de moedas é vazia, não fazemos nada no método cotarERegistrar
        if(moedas.isEmpty()) {
            return;
        }

        var <Optional<Cotacao>>

        for(String moeda : moedas){
            cliente.consultar(moeda);
        }

        // TODO

        gravarEmCSV(saida, List.of());
    }

    private void gravarEmCSV(Path saida, List<Cotacao> cotacoes) {
        // TODO
        final Path csv = saida.resolve("cotacoes.csv");

        final String conteudo = "moeda,valor\n" + cotacoes.stream()
                .map(c -> c.moeda() + "," + c.valor())
                .reduce("", (a, b) -> a.isEmpty() ? b : a + "\n" + b);
        try {
            Files.writeString(csv, conteudo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
