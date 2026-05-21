package forms;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import modelo.Passeio;
import util.BDVeiculos;

public class ImprimirTodosPasseio extends JFrame {
    private JFrame parent;
    private BDVeiculos bd;
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public ImprimirTodosPasseio(JFrame parent, BDVeiculos bd) {
        this.parent = parent;
        this.bd = bd;

        setTitle("Imprimir / Excluir todos - Passeio");
        setSize(800, 300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] colunas = {"Placa", "Marca", "Modelo", "Cor", "Qtd. Rodas", "Veloc Máx", "Qtd Pist", "Potência", "Qtd Passag"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabela);

        JPanel panelBotoes = new JPanel(new FlowLayout());
        JButton btnImprimir = new JButton("Imprimir Todos");
        JButton btnExcluir = new JButton("Excluir Todos");
        JButton btnSair = new JButton("Sair");

        btnImprimir.addActionListener(e -> preencherTabela());
        btnExcluir.addActionListener(e -> {
            bd.excluirTodosPasseio();
            modeloTabela.setRowCount(0);
            JOptionPane.showMessageDialog(this, "Todos os registros foram excluídos.");
        });
        btnSair.addActionListener(e -> {
            parent.setVisible(true);
            this.dispose();
        });

        panelBotoes.add(btnImprimir);
        panelBotoes.add(btnExcluir);
        panelBotoes.add(btnSair);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);
    }

    private void preencherTabela() {
        modeloTabela.setRowCount(0);
        for (Passeio p : bd.getListaPasseio()) {
            Object[] linha = {
                p.getPlaca(), p.getMarca(), p.getModelo(), p.getCor(),
                p.getQtdRodas(), p.getVelocMax(), p.getMotor().getQtdPist(),
                p.getMotor().getPotencia(), p.getQtdePassageiros()
            };
            modeloTabela.addRow(linha);
        }
    }
}