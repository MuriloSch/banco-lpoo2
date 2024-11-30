package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 *
 * @author Murilo Schrickte and Pietra Minatti
 */

public class TelaInicialView extends JFrame {
    private JButton btnClientes;
    private JButton btnContas;
    private JButton btnOperacoes;

    public TelaInicialView() {
        setTitle("Tela Inicial");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1, 10, 10));

        btnClientes = new JButton("Gerenciar Clientes");
        btnContas = new JButton("Gerenciar Contas");
        btnOperacoes = new JButton("Realizar Operações");

        add(btnClientes);
        add(btnContas);
        add(btnOperacoes);

        setLocationRelativeTo(null);
    }

    public void addClientesListener(ActionListener listener) {
        btnClientes.addActionListener(listener);
    }

    public void addContasListener(ActionListener listener) {
        btnContas.addActionListener(listener);
    }

    public void addOperacoesListener(ActionListener listener) {
        btnOperacoes.addActionListener(listener);
    }
}