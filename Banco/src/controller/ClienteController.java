package controller;

import DAO.ClienteDAO;
import model.Cliente;
import view.ClienteTableModel;
import view.ClienteView;
import view.TelaInicialView;
import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Murilo Schrickte and Pietra Minatti
 */

public class ClienteController {
    private ClienteView view;
    private ClienteDAO dao;
    private final TelaInicialView telaIni;

    public ClienteController(ClienteView view, ClienteDAO dao, TelaInicialView telaIni) {
        this.view = view;
        this.dao = dao;
        this.telaIni = telaIni;
        configurarAcoes();
        atualizarTabela();
    }
    
    //Listeners e direcionamentos
    private void configurarAcoes() {
        view.getBtnAdicionar().addActionListener(e -> adicionarCliente());
        view.getBtnAtualizar().addActionListener(e -> atualizarCliente());
        view.getBtnExcluir().addActionListener(e -> excluirCliente());
        view.getBtnBuscar().addActionListener(e -> buscarClientes());
        view.getComboOrdenacao().addActionListener(e -> ordenarClientes());
        view.voltarIni(e -> voltarIni());
    }
    //Att tabela
    private void atualizarTabela() {
        try {
            List<Cliente> clientes = dao.listar();
            ClienteTableModel model = new ClienteTableModel(clientes);
            view.setTableModel(model);
        } catch (SQLException e) {
            mostrarErro("Erro ao listar clientes: " + e.getMessage());
        }
    }
    
    //Add cliente
    private void adicionarCliente() {
        String nome = view.getTxtNome().getText();
        String sobrenome = view.getTxtSobrenome().getText();
        String rg = view.getTxtRG().getText();
        String cpf = view.getTxtCPF().getText();
        String endereco = view.getTxtEndereco().getText();

        try {
            Cliente cliente = new Cliente(0, nome, sobrenome, rg, cpf, endereco);
            dao.inserir(cliente);
            atualizarTabela();
            view.limparCampos();
        } catch (SQLException e) {
            mostrarErro("Erro ao adicionar cliente: " + e.getMessage());
        }
    }

    //Att cliente
    private void atualizarCliente() {
        int selectedRow = view.getTabelaClientes().getSelectedRow();
        if (selectedRow == -1) {
            mostrarErro("Selecione um cliente para atualizar.");
            return;
        }

        Cliente cliente = ((ClienteTableModel) view.getTabelaClientes().getModel()).getClienteAt(selectedRow);

        cliente.setNome(view.getTxtNome().getText());
        cliente.setSobrenome(view.getTxtSobrenome().getText());
        cliente.setRg(view.getTxtRG().getText());
        cliente.setCpf(view.getTxtCPF().getText());
        cliente.setEndereco(view.getTxtEndereco().getText());

        try {
            dao.atualizar(cliente);
            atualizarTabela();
            view.limparCampos();
        } catch (SQLException e) {
            mostrarErro("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    //Exclui cliente
    private void excluirCliente() {
        int selectedRow = view.getTabelaClientes().getSelectedRow();
        if (selectedRow == -1) {
            mostrarErro("Selecione um cliente para excluir.");
            return;
        }

        Cliente cliente = ((ClienteTableModel) view.getTabelaClientes().getModel()).getClienteAt(selectedRow);

        int confirm = JOptionPane.showConfirmDialog(view,
                "Deseja realmente excluir o cliente? Todas as contas vinculadas serão apagadas.",
                "Confirmação",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                dao.excluir(cliente.getId());
                atualizarTabela();
                view.limparCampos();
            } catch (SQLException e) {
                mostrarErro("Erro ao excluir cliente: " + e.getMessage());
            }
        }
    }

    //Busca cliente
    private void buscarClientes() {
        String termo = view.getTxtBuscar().getText();
        String criterio = view.getComboCriterio().getSelectedItem().toString();
        List<Cliente> clientes;

        try {
            switch (criterio) {
                case "Nome" -> clientes = dao.buscarPorNome(termo);
                case "Sobrenome" -> clientes = dao.buscarPorSobrenome(termo);
                case "RG" -> clientes = dao.buscarPorRG(termo);
                case "CPF" -> clientes = dao.buscarPorCPF(termo);
                default -> throw new IllegalArgumentException("Critério inválido!");
            }
            view.setTableModel(new ClienteTableModel(clientes));
        } catch (SQLException e) {
            mostrarErro("Erro ao buscar clientes: " + e.getMessage());
        }
    }

    //Ordena cliente
    private void ordenarClientes() {
        String ordenacao = view.getComboOrdenacao().getSelectedItem().toString();
        ClienteTableModel model = (ClienteTableModel) view.getTabelaClientes().getModel();
        List<Cliente> clientes = model.getClientes();
        clientes.sort(Cliente.compararPorCampo(ordenacao));
        model.fireTableDataChanged();
    }

    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(view, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }
    
    private void voltarIni() {
        view.dispose();  // Fecha a tela atual (ContaView)
        telaIni.setVisible(true);  // Exibe a tela principal novamente
    }
}
