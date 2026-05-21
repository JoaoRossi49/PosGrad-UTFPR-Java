package forms;

import javax.swing.*;
import java.awt.*;
import modelo.Passeio;
import util.BDVeiculos;
import util.VeicExistException;
import util.VelocException;

public class CadastroPasseio extends JFrame {
    private JFrame parent;
    private BDVeiculos bd;

    private JTextField txtPlaca = new JTextField();
    private JTextField txtMarca = new JTextField();
    private JTextField txtModelo = new JTextField();
    private JTextField txtCor = new JTextField();
    private JTextField txtQtdRodas = new JTextField();
    private JTextField txtVelocMax = new JTextField();
    private JTextField txtQtdPist = new JTextField();
    private JTextField txtPotencia = new JTextField();
    private JTextField txtQtdPassageiros = new JTextField();

    public CadastroPasseio(JFrame parent, BDVeiculos bd) {
        this.parent = parent;
        this.bd = bd;

        setTitle("Cadastro de Passeio");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelForm = new JPanel(new GridLayout(9, 2, 5, 5));
        panelForm.add(new JLabel("Placa:")); panelForm.add(txtPlaca);
        panelForm.add(new JLabel("Marca:")); panelForm.add(txtMarca);
        panelForm.add(new JLabel("Modelo:")); panelForm.add(txtModelo);
        panelForm.add(new JLabel("Cor:")); panelForm.add(txtCor);
        panelForm.add(new JLabel("Qtd. Rodas:")); panelForm.add(txtQtdRodas);
        panelForm.add(new JLabel("Velocidade Máx:")); panelForm.add(txtVelocMax);
        panelForm.add(new JLabel("Qtd. Pistões:")); panelForm.add(txtQtdPist);
        panelForm.add(new JLabel("Potência:")); panelForm.add(txtPotencia);
        panelForm.add(new JLabel("Qtd. Passageiros:")); panelForm.add(txtQtdPassageiros);

        JPanel panelBotoes = new JPanel(new FlowLayout());
        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnLimpar = new JButton("Limpar");
        JButton btnSair = new JButton("Sair");

        btnCadastrar.addActionListener(e -> cadastrar());
        btnLimpar.addActionListener(e -> limparCampos());
        btnSair.addActionListener(e -> {
            parent.setVisible(true);
            this.dispose();
        });

        panelBotoes.add(btnCadastrar);
        panelBotoes.add(btnLimpar);
        panelBotoes.add(btnSair);

        add(panelForm, BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);
    }

    private void cadastrar() {
        try {
            Passeio p = new Passeio();
            p.setPlaca(txtPlaca.getText());
            p.setMarca(txtMarca.getText());
            p.setModelo(txtModelo.getText());
            p.setCor(txtCor.getText());
            p.setQtdRodas(Integer.parseInt(txtQtdRodas.getText()));
            
            float velMax = Float.parseFloat(txtVelocMax.getText());
            if (velMax < 80 || velMax > 110) {
                p.setVelocMax(100); // Regra de negócio do exercício anterior
                throw new VelocException();
            } else {
                p.setVelocMax(velMax);
            }

            p.getMotor().setQtdPist(Integer.parseInt(txtQtdPist.getText()));
            p.getMotor().setPotencia(Integer.parseInt(txtPotencia.getText()));
            p.setQtdePassageiros(Integer.parseInt(txtQtdPassageiros.getText()));

            bd.cadastrarPasseio(p);
            JOptionPane.showMessageDialog(this, "Veículo cadastrado com sucesso!");
            limparCampos();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro: Campos numéricos preenchidos incorretamente.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (VelocException | VeicExistException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void limparCampos() {
        txtPlaca.setText(""); txtMarca.setText(""); txtModelo.setText("");
        txtCor.setText(""); txtQtdRodas.setText(""); txtVelocMax.setText("");
        txtQtdPist.setText(""); txtPotencia.setText(""); txtQtdPassageiros.setText("");
    }
}