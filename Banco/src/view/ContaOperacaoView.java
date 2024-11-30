package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 *
 * @author Murilo Schrickte and Pietra Minatti
 */

public class ContaOperacaoView extends JFrame {
    private JTextField cpfField;
    private JTextField valorField;
    private JButton validarCpfButton; // Botão de validação de CPF
    private JButton sacarButton;
    private JButton depositarButton;
    private JButton verSaldoButton;
    private JButton remunerarButton;
    private JButton voltarButton;
    private JLabel mensagemLabel;
    private JLabel saldoLabel;

    private double saldoAtual;

    public ContaOperacaoView() {
        setTitle("Operações de Conta");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8, 2, 10, 10)); // 8 linhas para acomodar o layout

        // Primeira parte da tela (CPF do Cliente)
        JLabel cpfLabel = new JLabel("CPF do Cliente:");
        cpfField = new JTextField();
        validarCpfButton = new JButton("Validar CPF");

        // Campo para preencher o valor da operação
        JLabel valorLabel = new JLabel("Valor:");
        valorField = new JTextField();
        valorField.setEnabled(false); // Inicialmente desativado

        // Botões de operação
        sacarButton = new JButton("Sacar");
        depositarButton = new JButton("Depositar");
        verSaldoButton = new JButton("Ver Saldo");
        remunerarButton = new JButton("Remunerar");
        voltarButton = new JButton("Voltar");

        // Rótulos de mensagens e saldo
        mensagemLabel = new JLabel("", SwingConstants.CENTER);
        mensagemLabel.setForeground(Color.RED);
        saldoLabel = new JLabel("Saldo: R$ 0.00", SwingConstants.CENTER);
        saldoLabel.setForeground(Color.BLACK);
        saldoLabel.setVisible(false); // Inicialmente o saldo está oculto

        // Adicionando elementos ao layout
        add(cpfLabel);
        add(cpfField);
        add(validarCpfButton);
        add(new JLabel()); // Espaço vazio no layout
        add(valorLabel);
        add(valorField);
        add(sacarButton);
        add(depositarButton);
        add(verSaldoButton);
        add(remunerarButton);
        add(saldoLabel);
        add(mensagemLabel);
        add(voltarButton);

        // Inicialmente ocultar os botões de operação
        desabilitarOperacoes();
    }

    // Atualiza o saldo visível na interface
    public void atualizarSaldo(double saldo) {
        saldoAtual = saldo;
        saldoLabel.setText("Saldo: R$ " + String.format("%.2f", saldo));
    }

    // Exibe a interface de operações após validação do CPF
    public void habilitarOperacoes() {
        valorField.setEnabled(true);
        sacarButton.setEnabled(true);
        depositarButton.setEnabled(true);
        verSaldoButton.setEnabled(true);
        remunerarButton.setEnabled(true);
    }

    // Oculta os botões de operação
    public void desabilitarOperacoes() {
        valorField.setEnabled(false);
        sacarButton.setEnabled(false);
        depositarButton.setEnabled(false);
        verSaldoButton.setEnabled(false);
        remunerarButton.setEnabled(false);
    }

    public String getCpfSelecionado() {
        return cpfField.getText();
    }

    public double getValorOperacao() {
        try {
            return Double.parseDouble(valorField.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    // Listeners para os botões
    public void addValidarCpfListener(ActionListener listener) {
        validarCpfButton.addActionListener(listener);
    }

    public void addSacarListener(ActionListener listener) {
        sacarButton.addActionListener(listener);
    }

    public void addDepositarListener(ActionListener listener) {
        depositarButton.addActionListener(listener);
    }

    public void addVerSaldoListener(ActionListener listener) {
        verSaldoButton.addActionListener(listener);
    }

    public void addRemunerarListener(ActionListener listener) {
        remunerarButton.addActionListener(listener);
    }

    public void addVoltarListener(ActionListener listener) {
        voltarButton.addActionListener(listener);
    }

    public void exibirMensagemSucesso(String mensagem) {
        mensagemLabel.setText(mensagem);
        mensagemLabel.setForeground(Color.GREEN);
    }

    public void exibirMensagemErro(String mensagem) {
        mensagemLabel.setText(mensagem);
        mensagemLabel.setForeground(Color.RED);
    }

    // Método que torna o saldo visível ao clicar no botão "Ver Saldo"
    public void mostrarSaldo() {
        saldoLabel.setVisible(true); // Exibe o saldo
    }

    // Método que oculta o saldo ao clicar no botão "Ver Saldo" novamente
    public void ocultarSaldo() {
        saldoLabel.setVisible(false); // Oculta o saldo
    }
}
