import java.util.Scanner;

public class Teste {

        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            // Exibe o menu para o usuário
            System.out.println("\nSistema de Gestão de Veículos - Menu Inicial ");
            System.out.println("1. Cadastrar Veículo de Passeio");
            System.out.println("2. Cadastrar Veículo de Carga");
            System.out.println("3. Imprimir Todos os Veículos de Passeio");
            System.out.println("4. Imprimir Todos os Veículos de Carga");
            System.out.println("5. Imprimir Veículo de Passeio pela Placa");
            System.out.println("6. Imprimir Veículo de Carga pela Placa");
            System.out.println("7. Sair do Sistema");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    //cadastrarVeiculoPasseio();
                    break;

                case 2:
                    //cadastrarVeiculoCarga();
                    break;

                case 3:
                    //imprimirVeiculo("p");
                    break;
                 
                case 4:
                    //imprimirVeiculo("c");
                    break;
                    
                case 5:
                    //imprimirVeiculo("p", "placa");
                    break;   
                    
                case 6:
                    //imprimirVeiculo("c", "placa");
                    break;                     

                case 7:
                    System.out.println("Saindo do programa...");
                    break;

                default:
                    System.out.println("Opção inválida, tente novamente.");
                    break;
            }
        } while (opcao != 7);
    }
}