package local.redes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// @author JOÃO VITOR DE ROSSI FIGUEIREDO

public class Servidor {

    private static ServerSocket servidor;

    public static int calcularCpf(String cpf, int qtd, int mult) {
        int total = 0;

        for (int i = 0; i < qtd; i++) {
            char c = cpf.charAt(i);
            int numero = (c - '0') * mult;

            total += numero;
            mult--;
        }

        return total;
    }

    public static void processarCpf(Socket conexao) {
        try {
            //receber dados do socket
            DataInputStream entrada = new DataInputStream(conexao.getInputStream());
            String cpf = entrada.readUTF();

            boolean cpfValido = false;

            //realizar verificação de zeros
            if (cpf.length() == 11 && !cpf.equals("00000000000")) {

                //Primeiro digito
                int totalPrimeiroDigito =
                        calcularCpf(cpf, 9, 10);

                int resto = totalPrimeiroDigito % 11;

                int primeiroDigito;

                if (resto < 2) {
                    primeiroDigito = 0;
                } else {
                    primeiroDigito = 11 - resto;
                }

                System.out.println("O primeiro dígito deve ser "+ primeiroDigito);

                //Segundo digito
                int totalSegundoDigito = 0;

                for (int i = 0; i < 9; i++) {
                    char c = cpf.charAt(i);
                    int numero = c - '0';

                    totalSegundoDigito += numero * (11 - i);
                }

                totalSegundoDigito += primeiroDigito * 2;

                resto = totalSegundoDigito % 11;

                int segundoDigito;

                if (resto < 2) {
                    segundoDigito = 0;
                } else {
                    segundoDigito = 11 - resto;
                }

                System.out.println("O segundo dígito deve ser "+ segundoDigito);

                int primeiroInformado = cpf.charAt(9) - '0';
                int segundoInformado = cpf.charAt(10) - '0';

                if (primeiroDigito == primeiroInformado
                        && segundoDigito == segundoInformado) {

                    cpfValido = true;
                }
            }

            String resultado = "";

            if (cpfValido) {
                resultado = "Este CPF é válido";
            } else {
                resultado = "Este CPF é inválido";
            }

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

                System.out.println("Iniciando processamento");
                Thread thread = new Thread(() -> {
                        processarCpf(conexao);
                });

                thread.start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

