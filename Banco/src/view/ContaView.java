package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 *
 * @author Murilo Schrickte
 */

public class ContaView extends JFrame {
    private JComboBox<String> comboClientes;
    private JComboBox<String> comboTiposConta;
    private JTextField txtDepositoInicialCorrente;  // Depósito inicial para Conta Corrente
    private JTextField txtDepositoInicialInvestimento;  // Depósito inicial para Conta Investimento
    private JTextField txtLimite;
    private JTextField txtMontanteMinimo;
    private JTextField txtDepositoMinimo;
    private JButton btnCriarConta;
    private JPanel panelCampos;
    private CardLayout cardLayout;
    private JButton btnVoltar;
    
    public ContaView() {
        initComponents();
        setLocationRelativeTo(null); // Centraliza a janela na tela
    }
    
    private void initComponents() {
        setTitle("Vincular Cliente a Conta");
        setSize(600, 500);  // Aumenta um pouco o tamanho para maior conforto
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));  // Espaçamento entre os componentes

        // Painel para seleção do cliente e tipo de conta
        JPanel panelSelecao = new JPanel();
        panelSelecao.setLayout(new GridLayout(2, 2, 10, 10));  // Espaçamento interno entre os componentes
        panelSelecao.add(new JLabel("Selecione o Cliente:"));
        comboClientes = new JComboBox<>();
        panelSelecao.add(comboClientes);

        panelSelecao.add(new JLabel("Tipo de Conta:"));
        comboTiposConta = new JComboBox<>(new String[]{"Conta Corrente", "Conta Investimento"});
        panelSelecao.add(comboTiposConta);

        // Painel para os campos de entrada
        panelCampos = new JPanel();
        cardLayout = new CardLayout(10, 10);  // Inicializa o CardLayout com espaçamento
        panelCampos.setLayout(cardLayout);  // Define o layout do painel como CardLayout

        // Painel para Conta Corrente
        JPanel panelContaCorrente = new JPanel(new GridLayout(3, 2, 10, 10));
        panelContaCorrente.add(new JLabel("Depósito Inicial (R$):"));
        txtDepositoInicialCorrente = new JTextField();
        panelContaCorrente.add(txtDepositoInicialCorrente);

        panelContaCorrente.add(new JLabel("Limite (R$):"));
        txtLimite = new JTextField();
        panelContaCorrente.add(txtLimite);

        panelCampos.add(panelContaCorrente, "Conta Corrente");  // Adiciona o painel para Conta Corrente

        // Painel para Conta Investimento
        JPanel panelContaInvestimento = new JPanel(new GridLayout(3, 2, 10, 10));
        panelContaInvestimento.add(new JLabel("Depósito Inicial (R$):"));
        txtDepositoInicialInvestimento = new JTextField();
        panelContaInvestimento.add(txtDepositoInicialInvestimento);

        panelContaInvestimento.add(new JLabel("Montante Mínimo (R$):"));
        txtMontanteMinimo = new JTextField();
        panelContaInvestimento.add(txtMontanteMinimo);

        panelContaInvestimento.add(new JLabel("Depósito Mínimo (R$):"));
        txtDepositoMinimo = new JTextField();
        panelContaInvestimento.add(txtDepositoMinimo);

        panelCampos.add(panelContaInvestimento, "Conta Investimento");  // Adiciona o painel para Conta Investimento

        add(panelSelecao, BorderLayout.NORTH);  // Painel de seleção fica no topo
        add(panelCampos, BorderLayout.CENTER);  // Campos de entrada ficam no centro
        
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBackground(new Color(240, 240, 240));  // Cor de fundo mais suave
        add(panelInferior, BorderLayout.SOUTH);
        
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotoes.setBackground(new Color(240, 240, 240));
        
        // Botão para criar a conta
        btnCriarConta = new JButton("Criar Conta");
        btnCriarConta.setFont(new Font("Arial", Font.BOLD, 14));  // Altera o estilo da fonte do botão
        btnCriarConta.setBackground(new Color(0, 123, 255));  // Cor de fundo mais atraente
        btnCriarConta.setForeground(Color.WHITE);  // Cor do texto no botão
        btnCriarConta.setFocusPainted(false);  // Remove o contorno padrão do botão ao clicar
        panelBotoes.add(btnCriarConta);
        
        btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 14)); // Estilo do botão
        btnVoltar.setBackground(new Color(220, 53, 69));  // Cor do botão "Voltar"
        btnVoltar.setForeground(Color.WHITE);  // Cor do texto
        btnVoltar.setFocusPainted(false);  // Remove o contorno padrão do botão ao clicar
        panelBotoes.add(btnVoltar);
        
        panelInferior.add(panelBotoes, BorderLayout.SOUTH);

        // Atualiza a visibilidade dos campos ao alterar o tipo de conta
        comboTiposConta.addActionListener(e -> atualizarCamposTipoConta());

        // Inicializa a tela com os campos corretos baseados no tipo de conta
        atualizarCamposTipoConta();
    }

    // Método para atualizar os campos dependendo do tipo de conta
    private void atualizarCamposTipoConta() {
        String tipoConta = (String) comboTiposConta.getSelectedItem();

        // Alterna os painéis do CardLayout com base no tipo de conta selecionado
        if ("Conta Corrente".equals(tipoConta)) {
            cardLayout.show(panelCampos, "Conta Corrente");  // Mostra o painel para Conta Corrente
        } else if ("Conta Investimento".equals(tipoConta)) {
            cardLayout.show(panelCampos, "Conta Investimento");  // Mostra o painel para Conta Investimento
        }
    }

    public String getClienteSelecionado() {
        return (String) comboClientes.getSelectedItem();
    }

    public String getTipoContaSelecionado() {
        return (String) comboTiposConta.getSelectedItem();
    }

    public String getDepositoInicial() {
        String tipoConta = (String) comboTiposConta.getSelectedItem();
        if ("Conta Corrente".equals(tipoConta)) {
            return txtDepositoInicialCorrente.getText();  // Retorna o valor para Conta Corrente
        } else if ("Conta Investimento".equals(tipoConta)) {
            return txtDepositoInicialInvestimento.getText();  // Retorna o valor para Conta Investimento
        }
        return "";  // Retorna uma string vazia se não houver tipo de conta válido
    }

    public String getLimite() {
        return txtLimite.getText();
    }

    public String getMontanteMinimo() {
        return txtMontanteMinimo.getText();
    }

    public String getDepositoMinimo() {
        return txtDepositoMinimo.getText();
    }

    public void setClientes(String[] clientes) {
        comboClientes.setModel(new DefaultComboBoxModel<>(clientes));
    }

    public void exibirMensagemErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void exibirMensagemSucesso(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    public void addCriarContaListener(ActionListener listener) {
        btnCriarConta.addActionListener(listener);
    }

    public void voltarIni (ActionListener listener) {
        btnVoltar.addActionListener(listener);
    }
}