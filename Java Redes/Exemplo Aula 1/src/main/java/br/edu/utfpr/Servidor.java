package br.edu.utfpr;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    private static ServerSocket servidor;
    private static Socket conexao;
    private static DataInputStream entrada;
    private static DataOutputStream saida;

    public static void main(String[] args){
        //Definir porta e aguardar conexão
        try {
            System.out.println("Aguardando conexão");
            servidor = new ServerSocket(55000);
            //accept é uma função bloqueante, vai ficar aqui aguardando
            conexao = servidor.accept();

            System.out.println("Conexão aceita, iniciando processamento");
            //receber dados cliente
            entrada = new DataInputStream(conexao.getInputStream());
            int valor = entrada.readInt();

            //realizar verificação
            String resultado = "";

            if(valor > 0)
                resultado = "O valor é maior que zero.";
            else
                resultado = "O valor é menor ou igual a zero";

            //retornar dados ao cliente
            saida = new DataOutputStream(conexao.getOutputStream());
            saida.writeUTF(resultado);

            System.out.println("Processamento concluído");
            //fechar conexão
            conexao.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
