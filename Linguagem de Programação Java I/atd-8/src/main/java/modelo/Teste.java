package modelo;

import interfaces.Calcular;
import interfaces.Veiculo;
import util.VeicExistException;
import util.VelocException;
import util.BDVeiculos;
import modelo.Leitura;

public class Teste {

    private static BDVeiculos bd = new BDVeiculos();
    private static int contP = 0;
    private static int contC = 0;
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
            System.out.println("7. Excluir Veículo de Passeio pela Placa");
            System.out.println("8. Excluir Veículo de Carga pela Placa");
            System.out.println("9. Sair do Sistema");

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
                    excluirPorPlaca("p");
                    break;
                case 8:
                    excluirPorPlaca("c");
                    break;           
                case 9:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 9);
    }

    private static void cadastrar(String tipo) {
        boolean continuar = true;
        while (continuar) {
            try {
                if (tipo.equals("p")) {
                    if (contP >= 5) {
                        System.out.println("Vetor de Passeio cheio!");
                        return;
                    }
                    String placa = leitura.entDados("Placa: ");
                    validarPlaca(placa);

                    Passeio p = new Passeio();
                    p.setPlaca(placa);
                    preencherComum(p);
                    p.setQtdePassageiros(Integer.parseInt(leitura.entDados("Qtd Passageiros: ")));
                    bd.getListaPasseio().add(p);
                    contP++;
                } else {
                    if (contC >= 5) {
                        System.out.println("Vetor de Carga cheio!");
                        return;
                    }
                    String placa = leitura.entDados("Placa: ");
                    validarPlaca(placa);

                    Carga c = new Carga();
                    c.setPlaca(placa);
                    preencherComum(c);
                    c.setTara(Integer.parseInt(leitura.entDados("Tara: ")));
                    c.setCargaMax(Integer.parseInt(leitura.entDados("Carga Max: ")));
                    bd.getListaCarga().add(c);
                    contC++;
                }

                String resp = leitura.entDados("Deseja cadastrar outro deste tipo? (S/N): ");
                if (resp.equalsIgnoreCase("n")) {
                    continuar = false;
                }
            } catch (VeicExistException e) {
                return;
            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada de dados deve ser numérica.");
                return;
            }
        }
    }

    private static void preencherComum(Veiculo v) {
        v.setMarca(leitura.entDados("Marca: "));
        v.setModelo(leitura.entDados("Modelo: "));
        v.setCor(leitura.entDados("Cor: "));
        v.setQtdRodas(Integer.parseInt(leitura.entDados("Qtd Rodas: ")));

        try {
            float vel = Float.parseFloat(leitura.entDados("Velocidade Max (Km/h): "));
            if (vel < 80 || vel > 110) {
                throw new VelocException();
            }
            v.setVelocMax(vel);
        } catch (VelocException e) {
            if (v instanceof Passeio) {
                v.setVelocMax(100);
            } else {
                v.setVelocMax(90);
            }
        }

        v.getMotor().setPotencia(Integer.parseInt(leitura.entDados("Potencia Motor: ")));
        v.getMotor().setQtdPist(Integer.parseInt(leitura.entDados("Qtd Pistões: ")));
    }

    private static void validarPlaca(String placa) throws VeicExistException {
        for (int i = 0; i < contP; i++) {
            if (bd.getListaPasseio().get(i).getPlaca().equalsIgnoreCase(placa)) {
                throw new VeicExistException();
            }
        }
        for (int i = 0; i < contC; i++) {
            if (bd.getListaCarga().get(i).getPlaca().equalsIgnoreCase(placa)) {
                throw new VeicExistException();
            }
        }
    }

    private static void imprimirPorPlaca(String tipo) {
        String p = leitura.entDados("Digite a placa para busca: ");
        if (tipo.equals("p")) {
            for (int i = 0; i < contP; i++) {
                if (bd.getListaPasseio().get(i).getPlaca().equalsIgnoreCase(p)) {
                    exibirDados(bd.getListaPasseio().get(i));
                    return;
                }
            }
        } else {
            for (int i = 0; i < contC; i++) {
                if (bd.getListaCarga().get(i).getPlaca().equalsIgnoreCase(p)) {
                    exibirDados(bd.getListaCarga().get(i));
                    return;
                }
            }
        }
        System.out.println("Aviso: Veículo não encontrado.");
    }
    
    private static void excluirPorPlaca(String tipo) {
    String p = leitura.entDados("Digite a placa para exclusão: ");
    boolean encontrado = false;

    if (tipo.equalsIgnoreCase("p")) {
        for (int i = 0; i < contP; i++) {
            if (bd.getListaPasseio().get(i).getPlaca().equalsIgnoreCase(p)) {
                bd.getListaPasseio().remove(i);
                contP--;
                encontrado = true;
                System.out.println("Veículo de passeio excluído com sucesso!");
                return; 
            }
        }
    } else {
        for (int i = 0; i < contC; i++) {
            if (bd.getListaCarga().get(i).getPlaca().equalsIgnoreCase(p)) {
                bd.getListaCarga().remove(i);
                contC--;
                encontrado = true;
                System.out.println("Veículo de carga excluído com sucesso!");
                return;
            }
        }
    }

    if (!encontrado) {
        System.out.println("Aviso: Veículo com placa " + p + " não encontrado.");
    }
}

    private static void imprimirTodos(String tipo) {
        if (tipo.equals("p")) {
            for (int i = 0; i < contP; i++) {
                exibirDados(bd.getListaPasseio().get(i));
            }
        } else {
            for (int i = 0; i < contC; i++) {
                exibirDados(bd.getListaCarga().get(i));
            }
        }
    }

    private static void exibirDados(Veiculo v) {
        System.out.println("\n--- Dados do Veículo ---");
        System.out.println("Placa: " + v.getPlaca());
        System.out.println("Marca: " + v.getMarca());
        System.out.println("Modelo: " + v.getModelo());
        System.out.println("Cor: " + v.getCor());
        System.out.println("Velocidade: " + v.getVelocMax() + " Km/h");
        System.out.println("Qtd Rodas: " + v.getQtdRodas());
        System.out.println("Motor - Potência: " + v.getMotor().getPotencia());
        System.out.println("Motor - Qtd Pistões: " + v.getMotor().getQtdPist());

        if (v instanceof Passeio) {
            System.out.println("Qtd Passageiros: " + ((Passeio) v).getQtdePassageiros());
        } else if (v instanceof Carga) {
            System.out.println("Tara: " + ((Carga) v).getTara());
            System.out.println("Carga Máxima: " + ((Carga) v).getCargaMax());
        }

        System.out.println("Resultado Cálculo: " + ((Calcular) v).calcular());
        System.out.println("Velocidade Convertida: " + v.calcVel(v.getVelocMax()));
    }
}