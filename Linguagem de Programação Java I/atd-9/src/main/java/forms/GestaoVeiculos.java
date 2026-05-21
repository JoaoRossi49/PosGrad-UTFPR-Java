package forms;

import javax.swing.*;
import java.awt.*;
import util.BDVeiculos;

public class GestaoVeiculos extends JFrame {
    private BDVeiculos bd;

    public GestaoVeiculos(BDVeiculos bd) {
        this.bd = bd;
        setTitle("Gestão de Veículos");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JButton btnPasseio = new JButton("Passeio");
        JButton btnCarga = new JButton("Carga");

        btnPasseio.addActionListener(e -> {
            new MenuPasseio(this, bd).setVisible(true);
            this.setVisible(false);
        });
        
        btnCarga.addActionListener(e -> {
            new MenuCarga(this, bd).setVisible(true);
            this.setVisible(false);
        });

        add(btnPasseio);
        add(btnCarga);
    }
}