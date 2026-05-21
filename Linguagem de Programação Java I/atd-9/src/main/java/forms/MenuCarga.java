package forms;

import javax.swing.*;
import java.awt.*;
import util.BDVeiculos;

public class MenuCarga extends JFrame {
    private JFrame parent;
    private BDVeiculos bd;

    public MenuCarga(JFrame parent, BDVeiculos bd) {
        this.parent = parent;
        this.bd = bd;
        
        setTitle("Veículos de Carga");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 5, 5));

        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnConsultar = new JButton("Consultar / Excluir pela placa");
        JButton btnImprimirTodos = new JButton("Imprimir / Excluir todos");
        JButton btnSair = new JButton("Sair");

        btnCadastrar.addActionListener(e -> {
            new CadastroCarga(this, bd).setVisible(true);
            this.setVisible(false);
        });

        btnConsultar.addActionListener(e -> {
            new ConsultarExcluirCarga(this, bd).setVisible(true);
            this.setVisible(false);
        });

        btnImprimirTodos.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Funcionalidade Opcional/Futura");
        });

        btnSair.addActionListener(e -> {
            parent.setVisible(true);
            this.dispose();
        });

        add(btnCadastrar);
        add(btnConsultar);
        add(btnImprimirTodos);
        add(btnSair);
    }
}