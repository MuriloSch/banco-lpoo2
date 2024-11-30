package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 *
 * @author Murilo Schrickte
 */

public class ClienteView extends JFrame {
    private JTable tabelaClientes;
    private JTextField txtBuscar;
    private JButton btnAdicionar;
    private JButton btnAtualizar;
    private JButton btnExcluir;
    private JButton btnBuscar;
    private JScrollPane scrollPane;
    private JComboBox<String> comboCriterio;
    private JComboBox<String> comboOrdenacao;
    private JTextField txtNome;
    private JTextField txtSobrenome;
    private JTextField txtRG;
    private JTextField txtCPF;
    private JTextField txtEndereco;
    private JButton btnVoltar;

    public ClienteView() {
        initComponents();
        setLocationRelativeTo(null); // Centraliza a janela na tela
    }

    private void initComponents() {
       setTitle("Gerenciamento de Clientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout(10, 10));  // Ajusta o espaçamento entre os componentes

        // Parte superior - Busca e filtros
        JPanel panelBusca = new JPanel(new GridLayout(1, 6, 10, 10));  // Ajustando a disposição para maior clareza
        panelBusca.setBackground(new Color(240, 240, 240));  // Cor de fundo mais suave
        panelBusca.add(new JLabel("Buscar:"));
        txtBuscar = new JTextField(20);
        panelBusca.add(txtBuscar);

        panelBusca.add(new JLabel("Critério de busca:"));
        comboCriterio = new JComboBox<>(new String[]{"Nome", "Sobrenome", "RG", "CPF"});
        panelBusca.add(comboCriterio);

        panelBusca.add(btnBuscar = new JButton("Buscar"));
        panelBusca.add(new JLabel("Ordenação:"));
        comboOrdenacao = new JComboBox<>(new String[]{
            "Nome", "Sobrenome", "Salário", "RG", "CPF"
        });
        panelBusca.add(comboOrdenacao);
        add(panelBusca, BorderLayout.NORTH);

        // Tabela de clientes
        tabelaClientes = new JTable();
        scrollPane = new JScrollPane(tabelaClientes);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));  // Borda mais suave
        add(scrollPane, BorderLayout.CENTER);

        // Parte inferior - Edição de dados do cliente
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBackground(new Color(240, 240, 240));  // Cor de fundo mais suave
        add(panelInferior, BorderLayout.SOUTH);

        JPanel panelEdicao = new JPanel(new GridLayout(6, 2, 10, 10));  // Ajustando a quantidade de campos
        panelEdicao.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)),
        "Dados do Cliente", 0, 0, new Font("Arial", Font.BOLD, 14), new Color(0, 123, 255))); // Título em azul
        panelEdicao.setBackground(new Color(255, 255, 255));  // Fundo branco para a área de edição
        panelEdicao.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        panelEdicao.add(txtNome);

        panelEdicao.add(new JLabel("Sobrenome:"));
        txtSobrenome = new JTextField();
        panelEdicao.add(txtSobrenome);

        panelEdicao.add(new JLabel("RG:"));
        txtRG = new JTextField();
        panelEdicao.add(txtRG);

        panelEdicao.add(new JLabel("CPF:"));
        txtCPF = new JTextField();
        panelEdicao.add(txtCPF);

        panelEdicao.add(new JLabel("Endereço:"));
        txtEndereco = new JTextField();
        panelEdicao.add(txtEndereco);

        panelInferior.add(panelEdicao, BorderLayout.CENTER);

        // Parte inferior - Botões de Ação
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotoes.setBackground(new Color(240, 240, 240));
        
        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setFont(new Font("Arial", Font.BOLD, 14));
        btnAdicionar.setBackground(new Color(0, 123, 255));  // Cor de fundo do botão
        btnAdicionar.setForeground(Color.WHITE);  // Cor do texto do botão
        btnAdicionar.setFocusPainted(false);
        panelBotoes.add(btnAdicionar);

        btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setFont(new Font("Arial", Font.BOLD, 14));
        btnAtualizar.setBackground(new Color(0, 123, 255));
        btnAtualizar.setForeground(Color.WHITE);
        btnAtualizar.setFocusPainted(false);
        panelBotoes.add(btnAtualizar);

        btnExcluir = new JButton("Excluir");
        btnExcluir.setFont(new Font("Arial", Font.BOLD, 14));
        btnExcluir.setBackground(new Color(220, 53, 69));  // Cor de fundo do botão de excluir (vermelho)
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.setFocusPainted(false);
        panelBotoes.add(btnExcluir);
        
        btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 14)); // Estilo do botão
        btnVoltar.setBackground(new Color(220, 53, 69));  // Cor do botão "Voltar"
        btnVoltar.setForeground(Color.WHITE);  // Cor do texto
        btnVoltar.setFocusPainted(false);  // Remove o contorno padrão do botão ao clicar
        panelBotoes.add(btnVoltar);

        panelInferior.add(panelBotoes, BorderLayout.SOUTH);
    }

    //Gets
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


    //Aplicação do TableModel
    public void setTableModel(ClienteTableModel model) {
        tabelaClientes.setModel(model);
    }

    //Limpa campos
    public void limparCampos() {
        txtNome.setText("");
        txtSobrenome.setText("");
        txtRG.setText("");
        txtCPF.setText("");
        txtEndereco.setText("");
    }

    //Preenche campos
    public void preencherCampos(String nome, String sobrenome, String rg, String cpf, String endereco) {
        txtNome.setText(nome);
        txtSobrenome.setText(sobrenome);
        txtRG.setText(rg);
        txtCPF.setText(cpf);
        txtEndereco.setText(endereco);
    }

    public void voltarIni (ActionListener listener) {
        btnVoltar.addActionListener(listener);
    }
}