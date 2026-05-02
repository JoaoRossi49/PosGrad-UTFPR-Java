
public class Teste {

    private static Carga[] veiculosCarga = new Carga[5];
    private static Passeio[] veiculosPasseio = new Passeio[5];
    private static int contPasseio = 0;
    private static int contCarga = 0;
    private static Leitura leitura = new Leitura();

    public static void main(String[] args) {
        int opcao = 0;
        do {
            System.out.println("\nSistema de Gestão de Veículos - Menu Inicial");
            System.out.println("1. Cadastrar Veículo de Passeio");
            System.out.println("2. Cadastrar Veículo de Carga");
            System.out.println("3. Imprimir Todos os Veículos de Passeio");
            System.out.println("4. Imprimir Todos os Veículos de Carga");
            System.out.println("5. Imprimir Veículo de Passeio pela Placa");
            System.out.println("6. Imprimir Veículo de Carga pela Placa");
            System.out.println("7. Sair do Sistema");

            try {
                opcao = Integer.parseInt(leitura.entDados("Escolha uma opção: "));
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida.");
                continue;
            }

            switch (opcao) {
                case 1:
                    cadastrar("p");
                    break;
                case 2:
                    cadastrar("c");
                    break;
                case 3:
                    imprimirTodos("p");
                    break;
                case 4:
                    imprimirTodos("c");
                    break;
                case 5:
                    imprimirPorPlaca("p");
                    break;
                case 6:
                    imprimirPorPlaca("c");
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 7);
    }

    private static void cadastrar(String tipo) {
        boolean continuar = true;
        while (continuar) {
            if (tipo.equals("p") && contPasseio >= 5 || tipo.equals("c") && contCarga >= 5) {
                System.out.println("Vetor cheio!");
                return;
            }

            String placa = leitura.entDados("Placa: ");
            if (existePlaca(placa)) {
                System.out.println("Erro: Placa já cadastrada!");
                return;
            }

            if (tipo.equals("p")) {
                Passeio p = new Passeio();
                p.setPlaca(placa);
                preencherVeiculo(p);
                p.setQtdePassageiros(Integer.parseInt(leitura.entDados("Qtd Passageiros: ")));
                veiculosPasseio[contPasseio++] = p;
            } else {
                Carga c = new Carga();
                c.setPlaca(placa);
                preencherVeiculo(c);
                c.setTara(Integer.parseInt(leitura.entDados("Tara: ")));
                c.setCargaMax(Integer.parseInt(leitura.entDados("Carga Max: ")));
                veiculosCarga[contCarga++] = c;
            }

            String resp = leitura.entDados("Deseja cadastrar outro deste tipo? (S/N): ");
            if (resp.equalsIgnoreCase("n")) {
                continuar = false;
            }
        }
    }

    private static void preencherVeiculo(Veiculo v) {
        v.setMarca(leitura.entDados("Marca: "));
        v.setModelo(leitura.entDados("Modelo: "));
        v.setCor(leitura.entDados("Cor: "));
        v.setQtdRodas(Integer.parseInt(leitura.entDados("Qtd Rodas: ")));
        v.setVelocMax(Float.parseFloat(leitura.entDados("Velocidade Max (Km/h): ")));
        v.getMotor().setPotencia(Integer.parseInt(leitura.entDados("Potencia Motor: ")));
        v.getMotor().setQtdPist(Integer.parseInt(leitura.entDados("Qtd Pistões: ")));
    }

    private static boolean existePlaca(String placa) {
        for (int i = 0; i < contPasseio; i++) {
            if (veiculosPasseio[i].getPlaca().equals(placa)) {
                return true;
            }
        }
        for (int i = 0; i < contCarga; i++) {
            if (veiculosCarga[i].getPlaca().equals(placa)) {
                return true;
            }
        }
        return false;
    }

    private static void imprimirPorPlaca(String tipo) {
        String p = leitura.entDados("Digite a placa para busca: ");
        if (tipo.equals("p")) {
            for (int i = 0; i < contPasseio; i++) {
                if (veiculosPasseio[i].getPlaca().equals(p)) {
                    exibirDados(veiculosPasseio[i]);
                    return;
                }
            }
        } else {
            for (int i = 0; i < contCarga; i++) {
                if (veiculosCarga[i].getPlaca().equals(p)) {
                    exibirDados(veiculosCarga[i]);
                    return;
                }
            }
        }
        System.out.println("Veículo não encontrado.");
    }

    private static void imprimirTodos(String tipo) {
        if (tipo.equals("p")) {
            for (int i = 0; i < contPasseio; i++) {
                exibirDados(veiculosPasseio[i]);
            }
        } else {
            for (int i = 0; i < contCarga; i++) {
                exibirDados(veiculosCarga[i]);
            }
        }
    }

    private static void exibirDados(Veiculo v) {
        System.out.println("\n--- Dados do Veículo ---");
        System.out.println("Placa: " + v.getPlaca() + " | Marca: " + v.getMarca() + " | Modelo: " + v.getModelo());
        System.out.println("Velocidade convertida: " + v.calcVel(v.getVelocMax()));
        System.out.println("Resultado do Cálculo (Interface): " + ((Calcular) v).calcular());
    }
}
