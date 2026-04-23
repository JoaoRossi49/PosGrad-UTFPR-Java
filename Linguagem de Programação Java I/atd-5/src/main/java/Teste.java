import java.util.Scanner;

public class Teste {
    private static int contador = 0;
    private static Carga[] veiculosCarga = new Carga[10];
    private static Passeio[] veiculosPasseio = new Passeio[10];    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        String placaPesquisada = "";

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
                    buscarVeiculo("p", null);
                    break;
                 
                case 4:
                    buscarVeiculo("c", null);
                    break;
                    
                case 5:
                    placaPesquisada = scanner.nextLine();
                    buscarVeiculo("p", placaPesquisada);
                    break;   
                    
                case 6:
                    placaPesquisada = scanner.nextLine();
                    buscarVeiculo("c", placaPesquisada);
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
    
    //Método para buscar um aluno pela matrícula
    private static void buscarVeiculo(String tipoVeiculo, String placa) {
        Veiculo[] veiculos;

        if (tipoVeiculo.equalsIgnoreCase("c")) {
            veiculos = veiculosCarga;
        } else {
            veiculos = veiculosPasseio;
        } 
        
        boolean encontrado = false;
        
        if(placa != null){
          for (int i = 0; i < contador; i++) {
            if (veiculos[i].getPlaca().equals(placa)) {
                System.out.println("\nVeículo encontrado:");
                System.out.println(veiculos[i]);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Veículo com a placa " + placa + " não encontrado.");
        }
            
        }

    }
}