package forms;

import javax.swing.*;
import java.awt.*;
import modelo.Passeio;
import util.BDVeiculos;

public class ConsultarExcluirPasseio extends JFrame {
    private JFrame parent;
    private BDVeiculos bd;

    private JTextField txtBuscaPlaca = new JTextField();
    private JTextField txtMarca = new JTextField();
    private JTextField txtModelo = new JTextField();
    private JTextField txtCor = new JTextField();
    private JTextField txtQtdRodas = new JTextField();
    private JTextField txtVelocMax = new JTextField();
    private JTextField txtQtdPist = new JTextField();
    private JTextField txtPotencia = new JTextField();
    private JTextField txtQtdPassageiros = new JTextField();

    public ConsultarExcluirPasseio(JFrame parent, BDVeiculos bd) {
        this.parent = parent;
        this.bd = bd;

        setTitle("Consultar / Excluir por Placa - Passeio");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelForm = new JPanel(new GridLayout(10, 2, 5, 5));
        panelForm.add(new JLabel("Informe a Placa:")); panelForm.add(txtBuscaPlaca);
        panelForm.add(new JLabel("Marca:")); panelForm.add(txtMarca);
        panelForm.add(new JLabel("Modelo:")); panelForm.add(txtModelo);
        panelForm.add(new JLabel("Cor:")); panelForm.add(txtCor);
        panelForm.add(new JLabel("Qtd. Rodas:")); panelForm.add(txtQtdRodas);
        panelForm.add(new JLabel("Velocidade Máx:")); panelForm.add(txtVelocMax);
        panelForm.add(new JLabel("Qtd. Pistões:")); panelForm.add(txtQtdPist);
        panelForm.add(new JLabel("Potência:")); panelForm.add(txtPotencia);
        panelForm.add(new JLabel("Qtd. Passageiros:")); panelForm.add(txtQtdPassageiros);

        txtMarca.setEditable(false); txtModelo.setEditable(false); txtCor.setEditable(false);
        txtQtdRodas.setEditable(false); txtVelocMax.setEditable(false); txtQtdPist.setEditable(false);
        txtPotencia.setEditable(false); txtQtdPassageiros.setEditable(false);

        JPanel panelBotoes = new JPanel(new FlowLayout());
        JButton btnConsultar = new JButton("Consultar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnSair = new JButton("Sair");

        btnConsultar.addActionListener(e -> consultar());
        btnExcluir.addActionListener(e -> excluir());
        btnSair.addActionListener(e -> {
            parent.setVisible(true);
            this.dispose();
        });

        panelBotoes.add(btnConsultar);
        panelBotoes.add(btnExcluir);
        panelBotoes.add(btnSair);

        add(panelForm, BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);
    }

    private void consultar() {
        Passeio p = bd.buscarPasseioPorPlaca(txtBuscaPlaca.getText());
        if (p != null) {
            txtMarca.setText(p.getMarca());
            txtModelo.setText(p.getModelo());
            txtCor.setText(p.getCor());
            txtQtdRodas.setText(String.valueOf(p.getQtdRodas()));
            txtVelocMax.setText(String.valueOf(p.getVelocMax()));
            txtQtdPist.setText(String.valueOf(p.getMotor().getQtdPist()));
            txtPotencia.setText(String.valueOf(p.getMotor().getPotencia()));
            txtQtdPassageiros.setText(String.valueOf(p.getQtdePassageiros()));
        } else {
            JOptionPane.showMessageDialog(this, "Aviso: Veículo não encontrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
            limparCampos();
        }
    }

    private void excluir() {
        boolean sucesso = bd.excluirPasseioPorPlaca(txtBuscaPlaca.getText());
        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Veículo de passeio excluído com sucesso!");
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Aviso: Veículo não encontrado para exclusão.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void limparCampos() {
        txtMarca.setText(""); txtModelo.setText(""); txtCor.setText("");
        txtQtdRodas.setText(""); txtVelocMax.setText(""); txtQtdPist.setText("");
        txtPotencia.setText(""); txtQtdPassageiros.setText("");
    }
}