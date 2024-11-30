package controller;

import DAO.ContaOperacaoDAO;
import model.Conta;
import view.ContaOperacaoView;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ContaCorrente;
import model.ContaInvestimento;
import view.TelaInicialView;

/**
 *
 * @author Murilo Schrickte and Pietra Minatti
 */

public class ContaOperacaoController {
    private final ContaOperacaoView contaOperacaoView;
    private final ContaOperacaoDAO contaOperacaoDAO;
    private final TelaInicialView telaInicialView;

    public ContaOperacaoController(ContaOperacaoView contaOperacaoView, ContaOperacaoDAO contaOperacaoDAO, TelaInicialView telaInicialView) {
        this.contaOperacaoView = contaOperacaoView;
        this.contaOperacaoDAO = contaOperacaoDAO;
        this.telaInicialView = telaInicialView;

        // Ações para os botões
        contaOperacaoView.addDepositarListener(e -> depositar());
        contaOperacaoView.addVerSaldoListener(e -> verSaldo());
        contaOperacaoView.addRemunerarListener(e -> remunerar());
        contaOperacaoView.addSacarListener(e -> sacar());
        contaOperacaoView.addValidarCpfListener(e -> validarCpf());
        contaOperacaoView.addVoltarListener(e -> voltar());
    }

    private void depositar() {
        try {
            Conta conta = obterContaPorCpf();
            if (conta != null) {
                double valor = contaOperacaoView.getValorOperacao();
                if (contaOperacaoDAO.depositar(conta, valor)) {
                    contaOperacaoView.atualizarSaldo(conta.getSaldo());
                    contaOperacaoView.exibirMensagemSucesso("Depósito efetuado com sucesso!");
                } else {
                    contaOperacaoView.exibirMensagemErro("Valor inválido para depósito.");
                }
            }
        } catch (SQLException e) {
            contaOperacaoView.exibirMensagemErro("Erro ao depositar: " + e.getMessage());
        }
    }

    private void verSaldo() {
        try {
            Conta conta = obterContaPorCpf(); // Recupera a conta associada ao CPF
            if (conta != null) {
                // Atualiza a label de saldo na interface
                contaOperacaoView.atualizarSaldo(conta.getSaldo());
                contaOperacaoView.mostrarSaldo(); // Torna o saldo visível
            }
        } catch (SQLException e) {
            contaOperacaoView.exibirMensagemErro("Erro ao mostrar saldo: " + e.getMessage());
        }
    }

    private void remunerar() {
        try {
            Conta conta = obterContaPorCpf();
            if (conta != null) {
                contaOperacaoDAO.remunerar(conta);
                contaOperacaoView.atualizarSaldo(conta.getSaldo());
                contaOperacaoView.exibirMensagemSucesso("Remuneração aplicada com sucesso!");
            }
        } catch (SQLException e) {
            contaOperacaoView.exibirMensagemErro("Erro ao aplicar remuneração: " + e.getMessage());
        }
    }

    private void sacar() {
        try {
            Conta conta = obterContaPorCpf();
            if (conta != null) {
                double valor = contaOperacaoView.getValorOperacao();
                if (contaOperacaoDAO.sacar(conta, valor)) {
                    contaOperacaoView.atualizarSaldo(conta.getSaldo());
                    contaOperacaoView.exibirMensagemSucesso("Saque realizado com sucesso!");
                } else {
                    contaOperacaoView.exibirMensagemErro("Saldo insuficiente ou valor inválido.");
                }
            }
        } catch (SQLException e) {
            contaOperacaoView.exibirMensagemErro("Erro ao realizar saque: " + e.getMessage());
        }
    }

    private void validarCpf() {
        try {
            Conta conta = obterContaPorCpf(); // Recupera a conta associada ao CPF
            if (conta != null) {
                // Carregar as contas disponíveis para aquele CPF
                List<String> contas = obterContasDisponiveis(conta);
                contaOperacaoView.habilitarOperacoes();
                contaOperacaoView.exibirMensagemSucesso("CPF validado com sucesso!");
            }
        } catch (SQLException e) {
            contaOperacaoView.exibirMensagemErro("Erro ao validar CPF: " + e.getMessage());
        }
    }
    
    private List<String> obterContasDisponiveis(Conta conta) {
        List<String> contas = new ArrayList<>();
        if (conta instanceof ContaCorrente) {
            contas.add("Conta Corrente");
        }
        if (conta instanceof ContaInvestimento) {
            contas.add("Conta Investimento");
        }
        return contas;
    }

    private Conta obterContaPorCpf() throws SQLException {
        String cpf = contaOperacaoView.getCpfSelecionado();
        Conta conta = contaOperacaoDAO.buscarPorCpf(cpf);
        if (conta == null) {
            contaOperacaoView.exibirMensagemErro("Conta não encontrada para o CPF informado.");
        }
        return conta;
    }

    private void voltar() {
        contaOperacaoView.dispose();
        telaInicialView.setVisible(true);
    }
}