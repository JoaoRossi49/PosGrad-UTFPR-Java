package local.redes;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

// @author JOÃO VITOR DE ROSSI FIGUEIREDO

public class Servidor {

    private static ServerSocket servidor;

    public static void processarObjeto(Socket conexao) {
        try {
            //receber objeto do socket
            ObjectInputStream  entrada = new ObjectInputStream (conexao.getInputStream());
            Pessoa pessoa = (Pessoa)entrada.readObject();
            
            System.out.println("Novo objeto pessoa recebido. Nome: "+ pessoa.getNome() + " Idade: " + pessoa.getIdade());
          
            String resultado = "Dados recebidos corretamente";

            //retornar dados ao cliente
            DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
            saida.writeUTF(resultado);

            //fechar conexão
            conexao.close();

        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("Aguardando conexão");
            servidor = new ServerSocket(50000);

            while (true) {

                Socket conexao = servidor.accept();

                Thread thread = new Thread(() -> {
                        processarObjeto(conexao);
                });

                thread.start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

