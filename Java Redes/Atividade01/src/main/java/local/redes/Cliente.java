package local.redes;

import java.io.*;
import java.net.Socket;

// @author JOÃO VITOR DE ROSSI FIGUEIREDO

public class Cliente {
    private static Socket conexao;
    private static DataInputStream entrada;
    private static DataOutputStream saida;

    public static void enviaCpf(String cpf){
        try {
            //conectar ao servidor
            conexao = new Socket("127.0.0.1", 50000);

            //enviar um cpf
            saida = new DataOutputStream(conexao.getOutputStream());
            saida.writeUTF(cpf);

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
    static void main(String[] args) {
        while (true){
            try {
                //Recebe do usuário algum valor
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Digite algum cpf: ");
                enviaCpf(br.readLine());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
