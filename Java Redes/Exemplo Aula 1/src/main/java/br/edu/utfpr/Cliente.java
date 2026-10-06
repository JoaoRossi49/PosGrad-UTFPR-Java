package br.edu.utfpr;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Cliente {
    private static Socket conexao;
    private static DataInputStream entrada;
    private static DataOutputStream saida;

    static void main(String[] args) {
        try {
            //conectar ao servidor
            conexao = new Socket("127.0.0.1", 55000);

            //enviar um número
            saida = new DataOutputStream(conexao.getOutputStream());
            int numero = -1;
            saida.writeInt(numero);

            //receber resposta
            entrada = new DataInputStream(conexao.getInputStream());
            String resposta = entrada.readUTF();

            //exibe resposta
            System.out.println(resposta);

            //fechar conexão
            conexao.close();

        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
