package controller;

import view.TelaInicialView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.ClienteView;
import view.ContaView;
import view.ContaOperacaoView;
import controller.ClienteController;
import controller.ContaController;
import DAO.ClienteDAO;
import DAO.ContaDAO;
import DAO.ContaOperacaoDAO;

/**
 *
 * @author Murilo Schrickte
 */

public class TelaInicialController {
    private final TelaInicialView telaInicialView;

    public TelaInicialController(TelaInicialView telaInicialView) {
        this.telaInicialView = telaInicialView;
        inicializarAcoes();
    }

   private void inicializarAcoes() {
        telaInicialView.addClientesListener(e -> abrirTelaClientes());
        telaInicialView.addContasListener(e -> abrirTelaContas());
        telaInicialView.addOperacoesListener(e -> abrirTelaOperacoes());
    }

    private void abrirTelaClientes() {
        telaInicialView.dispose();  // Fecha a tela atual (ContaView)
        ClienteView clienteView = new ClienteView();
        ClienteDAO clienteDAO = new ClienteDAO();
        ClienteController clienteController = new ClienteController(clienteView, clienteDAO, telaInicialView);
        clienteView.setVisible(true);
    }

    private void abrirTelaContas() {
        telaInicialView.dispose(); 
        ContaView contaView = new ContaView();
        ContaDAO contaDAO = new ContaDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        ContaController contaController = new ContaController(contaDAO, clienteDAO, contaView, telaInicialView);
        contaView.setVisible(true);
    }

    private void abrirTelaOperacoes() {
         telaInicialView.dispose(); 
         ContaOperacaoView contaOperacaoView = new ContaOperacaoView();
         ContaOperacaoDAO contaOperacaoDAO = new ContaOperacaoDAO();
         ContaOperacaoController contaOperacaoController = new ContaOperacaoController(contaOperacaoView, contaOperacaoDAO, telaInicialView);
         contaOperacaoView.setVisible(true);
    }

    public void exibir() {
        telaInicialView.setVisible(true);
    }
}
