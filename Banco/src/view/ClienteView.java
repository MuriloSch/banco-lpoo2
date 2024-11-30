/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Murilo Schrickte
 */

import javax.swing.*;
import java.awt.*;

public class ClienteView extends JFrame {

    // Declaração de componentes
    private JTable tabelaClientes;
    private JTextField txtBuscar;
    private JButton btnAdicionar;
    private JButton btnAtualizar;
    private JButton btnExcluir;
    private JButton btnBuscar;
    private JScrollPane scrollPane;
    private JComboBox<String> comboCriterio;
    private JComboBox<String> comboOrdenacao;

    // Campos de edição de cliente
    private JTextField txtNome;
    private JTextField txtSobrenome;
    private JTextField txtRG;
    private JTextField txtCPF;
    private JTextField txtEndereco;

    public ClienteView() {
        initComponents();
        setLocationRelativeTo(null); // Centraliza a janela na tela
    }

    private void initComponents() {
        setTitle("Gerenciamento de Clientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Layout Principal
        setLayout(new BorderLayout());

        // Painel Superior (Busca)
        JPanel panelBusca = new JPanel();
        txtBuscar = new JTextField(20);
        btnBuscar = new JButton("Buscar");
        comboCriterio = new JComboBox<>(new String[]{"Nome", "Sobrenome", "RG", "CPF"});
        comboOrdenacao = new JComboBox<>(new String[]{
            "Nome",
            "Sobrenome",
            "Salário",
            "RG",
            "CPF"
        });
        panelBusca.add(new JLabel("Buscar:"));
        panelBusca.add(txtBuscar);
        panelBusca.add(new JLabel("Critério de busca:"));
        panelBusca.add(comboCriterio);
        panelBusca.add(btnBuscar);
        panelBusca.add(new JLabel("Ordenação:"));
        panelBusca.add(comboOrdenacao);
        add(panelBusca, BorderLayout.NORTH);

        // Tabela de Clientes
        tabelaClientes = new JTable();
        scrollPane = new JScrollPane(tabelaClientes);
        add(scrollPane, BorderLayout.CENTER);

        // Painel Inferior (Edição e botões)
        JPanel panelInferior = new JPanel(new BorderLayout());
        add(panelInferior, BorderLayout.SOUTH);

        // Painel de edição
        JPanel panelEdicao = new JPanel(new GridLayout(5, 2, 5, 5));
        panelEdicao.setBorder(BorderFactory.createTitledBorder("Dados do Cliente"));

        txtNome = new JTextField();
        txtSobrenome = new JTextField();
        txtRG = new JTextField();
        txtCPF = new JTextField();
        txtEndereco = new JTextField();

        panelEdicao.add(new JLabel("Nome:"));
        panelEdicao.add(txtNome);
        panelEdicao.add(new JLabel("Sobrenome:"));
        panelEdicao.add(txtSobrenome);
        panelEdicao.add(new JLabel("RG:"));
        panelEdicao.add(txtRG);
        panelEdicao.add(new JLabel("CPF:"));
        panelEdicao.add(txtCPF);
        panelEdicao.add(new JLabel("Endereço:"));
        panelEdicao.add(txtEndereco);

        panelInferior.add(panelEdicao, BorderLayout.CENTER);

        // Painel de botões
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnAdicionar = new JButton("Adicionar");
        btnAtualizar = new JButton("Atualizar");
        btnExcluir = new JButton("Excluir");
        panelBotoes.add(btnAdicionar);
        panelBotoes.add(btnAtualizar);
        panelBotoes.add(btnExcluir);

        panelInferior.add(panelBotoes, BorderLayout.SOUTH);
    }

    // Getters para componentes
    public JTable getTabelaClientes() {
        return tabelaClientes;
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public JTextField getTxtNome() {
        return txtNome;
    }

    public JTextField getTxtSobrenome() {
        return txtSobrenome;
    }

    public JTextField getTxtRG() {
        return txtRG;
    }

    public JTextField getTxtCPF() {
        return txtCPF;
    }

    public JTextField getTxtEndereco() {
        return txtEndereco;
    }

    public JButton getBtnAdicionar() {
        return btnAdicionar;
    }

    public JButton getBtnAtualizar() {
        return btnAtualizar;
    }

    public JButton getBtnExcluir() {
        return btnExcluir;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }
    
    public JComboBox<String> getComboCriterio() {
        return comboCriterio;
    }

    public JComboBox<String> getComboOrdenacao() {
        return comboOrdenacao;
    }


    // Para definir o modelo da tabela
    public void setTableModel(ClienteTableModel model) {
        tabelaClientes.setModel(model);
    }

    // Limpa os campos de edição
    public void limparCampos() {
        txtNome.setText("");
        txtSobrenome.setText("");
        txtRG.setText("");
        txtCPF.setText("");
        txtEndereco.setText("");
    }

    // Preenche os campos com dados de um cliente
    public void preencherCampos(String nome, String sobrenome, String rg, String cpf, String endereco) {
        txtNome.setText(nome);
        txtSobrenome.setText(sobrenome);
        txtRG.setText(rg);
        txtCPF.setText(cpf);
        txtEndereco.setText(endereco);
    }
}