/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Murilo Schrickte
 */
import DAO.ClienteDAO;
import model.Cliente;
import view.ClienteTableModel;
import view.ClienteView;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class ClienteController {
    private ClienteView view;
    private ClienteDAO dao;

    public ClienteController(ClienteView view, ClienteDAO dao) {
        this.view = view;
        this.dao = dao;

        configurarAcoes();
        atualizarTabela();
    }

    private void configurarAcoes() {
        view.getBtnAdicionar().addActionListener(e -> adicionarCliente());
        view.getBtnAtualizar().addActionListener(e -> atualizarCliente());
        view.getBtnExcluir().addActionListener(e -> excluirCliente());
        view.getBtnBuscar().addActionListener(e -> buscarClientes());
        view.getComboOrdenacao().addActionListener(e -> ordenarClientes());
    }

    private void atualizarTabela() {
        try {
            List<Cliente> clientes = dao.listar();
            ClienteTableModel model = new ClienteTableModel(clientes);
            view.setTableModel(model);
        } catch (SQLException e) {
            mostrarErro("Erro ao listar clientes: " + e.getMessage());
        }
    }

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

    private void ordenarClientes() {
        String ordenacao = view.getComboOrdenacao().getSelectedItem().toString();
        ClienteTableModel model = (ClienteTableModel) view.getTabelaClientes().getModel();
        List<Cliente> clientes = model.getClientes();

        // Usando o comparador global
        clientes.sort(Cliente.compararPorCampo(ordenacao));

        model.fireTableDataChanged();
    }

    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(view, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
