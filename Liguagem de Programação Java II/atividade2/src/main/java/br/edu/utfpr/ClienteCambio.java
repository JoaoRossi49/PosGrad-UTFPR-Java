package br.edu.utfpr;

import java.net.http.HttpClient;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ClienteCambio {

    private static final String BASE_URL = "https://api.frankfurter.dev/v1/latest?base=USD&symbols=";

    private final HttpClient client;

    public ClienteCambio(HttpClient client) {
        this.client = client;
    }

    public CompletableFuture<Optional<Cotacao>> consultar(String moeda) {

        // TODO implementar aqui a chamada para a API
        try {

            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL+moeda))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();

            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            final Optional<Double> Opvalor = JsonParser.extrairTaxa(response.toString(), moeda);

            double valor = Opvalor.orElse(0.0);

            Cotacao cotacao = new Cotacao(moeda, valor, LocalDateTime.now());


            return CompletableFuture.completedFuture(Optional.of(cotacao));
        } catch (Exception e) {

            return CompletableFuture.completedFuture(Optional.empty());

        }
    }
}