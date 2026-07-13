package br.edu.utfpr;

import java.net.http.HttpClient;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;

public class Main {

    static void main2() {

        final var cotador = new CotadorDeMoedas();

        final var entrada = cotador.verificarEntrada();
        final var saida = cotador.verificarSaida();

        final var moedas = cotador.lerArquivoDeMoedas(entrada);

        try (HttpClient client = HttpClient.newHttpClient()) {
            cotador.cotarERegistrar(saida, moedas, new ClienteCambio(client));
        }
    }

    static void main() {
        final Path arquivo = Path.of("entrada", "moedas.txt");

        final var ct = new CotadorDeMoedas();
        final List<String> lista = ct.lerArquivoDeMoedas(arquivo);

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .version(HttpClient.Version.HTTP_2)
                .build();

        final var cot = new ClienteCambio(client);

        for(String moeda : lista){
            cot.consultar(moeda);
        }
    }
}