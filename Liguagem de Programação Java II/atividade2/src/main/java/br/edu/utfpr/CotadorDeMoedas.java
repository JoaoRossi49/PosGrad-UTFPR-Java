package br.edu.utfpr;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CotadorDeMoedas {

    public Path verificarEntrada() {
        // TODO
        //Ao ler o arquivo de moedas, valide se as moedas são apenas 3 letras, maiúsculas. Caso não atenda a regra, descarte
        return Path.of("");
    }

    public Path verificarSaida() {
        // TODO
        return Path.of("");
    }

    public List<String> lerArquivoDeMoedas(Path entrada) {
        // TODO
        //Se o arquivo não existir, retorna nulo
        if(!Files.exists(entrada)){
            return List.of();
        }

        try {

            //Lê o arquivo
            final List<String> todas = Files.readAllLines(entrada);
            return todas;

        } catch (Exception e){
            return List.of();
        }
    }

    public void cotarERegistrar(Path saida, List<String> moedas, ClienteCambio cliente) {

        //Se a lista de moedas é vazia, não fazemos nada no método cotarERegistrar
        if(moedas.isEmpty()) {
            return;
        }
        // TODO

        gravarEmCSV(saida, List.of());
    }

    private void gravarEmCSV(Path saida, List<Cotacao> cotacoes) {
        // TODO
    }
}
