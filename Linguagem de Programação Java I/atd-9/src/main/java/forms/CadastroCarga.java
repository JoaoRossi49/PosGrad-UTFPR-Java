package forms;

import javax.swing.*;
import java.awt.*;
import modelo.Carga;
import util.BDVeiculos;
import util.VeicExistException;
import util.VelocException;

public class CadastroCarga extends JFrame {
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
    private JTextField txtTara = new JTextField();
    private JTextField txtCargaMax = new JTextField();

    public CadastroCarga(JFrame parent, BDVeiculos bd) {
        this.parent = parent;
        this.bd = bd;

        setTitle("Cadastro de Carga");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelForm = new JPanel(new GridLayout(10, 2, 5, 5));
        panelForm.add(new JLabel("Placa:")); panelForm.add(txtPlaca);
        panelForm.add(new JLabel("Marca:")); panelForm.add(txtMarca);
        panelForm.add(new JLabel("Modelo:")); panelForm.add(txtModelo);
        panelForm.add(new JLabel("Cor:")); panelForm.add(txtCor);
        panelForm.add(new JLabel("Qtd. Rodas:")); panelForm.add(txtQtdRodas);
        panelForm.add(new JLabel("Velocidade Máx:")); panelForm.add(txtVelocMax);
        panelForm.add(new JLabel("Qtd. Pistões:")); panelForm.add(txtQtdPist);
        panelForm.add(new JLabel("Potência:")); panelForm.add(txtPotencia);
        panelForm.add(new JLabel("Tara:")); panelForm.add(txtTara);
        panelForm.add(new JLabel("Carga Máx:")); panelForm.add(txtCargaMax);

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
            Carga c = new Carga();
            c.setPlaca(txtPlaca.getText());
            c.setMarca(txtMarca.getText());
            c.setModelo(txtModelo.getText());
            c.setCor(txtCor.getText());
            c.setQtdRodas(Integer.parseInt(txtQtdRodas.getText()));
            
            float velMax = Float.parseFloat(txtVelocMax.getText());
            if (velMax < 80 || velMax > 110) {
                c.setVelocMax(90);
                throw new VelocException();
            } else {
                c.setVelocMax(velMax);
            }

            c.getMotor().setQtdPist(Integer.parseInt(txtQtdPist.getText()));
            c.getMotor().setPotencia(Integer.parseInt(txtPotencia.getText()));
            c.setTara(Integer.parseInt(txtTara.getText()));
            c.setCargaMax(Integer.parseInt(txtCargaMax.getText()));

            bd.cadastrarCarga(c);
            JOptionPane.showMessageDialog(this, "Veículo de carga cadastrado com sucesso!");
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
        txtQtdPist.setText(""); txtPotencia.setText(""); txtTara.setText(""); txtCargaMax.setText("");
    }
}