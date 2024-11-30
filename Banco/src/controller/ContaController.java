package controller;

import DAO.ClienteDAO;
import DAO.ContaDAO;
import model.Cliente;
import model.Conta;
import model.ContaCorrente;
import model.ContaInvestimento;
import view.ContaView;
import view.TelaInicialView;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Murilo Schrickte
 */

public class ContaController {
    private final ContaDAO contaDAO;
    private final ClienteDAO clienteDAO;
    private final ContaView contaView;
    private final TelaInicialView telaInicialView;

    public ContaController(ContaDAO contaDAO, ClienteDAO clienteDAO, ContaView contaView, TelaInicialView telaInicialView) {
        this.contaDAO = contaDAO;
        this.clienteDAO = clienteDAO;
        this.contaView = contaView;
        this.telaInicialView = telaInicialView;

        carregarClientes();
        contaView.addCriarContaListener(e -> criarConta());
        contaView.voltarIni(e -> voltarIni());
    }

    // Método para carregar os clientes na combobox da view
    private void carregarClientes() {
        try {
            List<Cliente> clientes = clienteDAO.listar();
            String[] nomesClientes = clientes.stream()
                .map(c -> c.getId() + " - " + c.getNome() + " " + c.getSobrenome())
                .toArray(String[]::new);
            contaView.setClientes(nomesClientes);
        } catch (SQLException e) {
            contaView.exibirMensagemErro("Erro ao carregar clientes: " + e.getMessage());
        }
    }

    // Método para criar uma conta
    private void criarConta() {
        try {
            String clienteSelecionado = contaView.getClienteSelecionado();
            if (clienteSelecionado == null || clienteSelecionado.isEmpty()) {
                contaView.exibirMensagemErro("Selecione um cliente.");
                return;
            }

            // Verifica o formato da string do cliente
            String[] partes = clienteSelecionado.split(" - ");
            if (partes.length < 2) {
                contaView.exibirMensagemErro("Seleção inválida do cliente.");
                return;
            }

            int clienteId = Integer.parseInt(partes[0].trim());
            Cliente cliente = clienteDAO.buscaPorId(clienteId);

            if (cliente == null) {
                contaView.exibirMensagemErro("Cliente não encontrado.");
                return;
            }

            String tipoConta = contaView.getTipoContaSelecionado();
            double depositoInicial = Double.parseDouble(contaView.getDepositoInicial());
            Conta novaConta;

            if ("Conta Corrente".equals(tipoConta)) {
                double limite = Double.parseDouble(contaView.getLimite());
                novaConta = new ContaCorrente(cliente, depositoInicial, limite);
            } else if ("Conta Investimento".equals(tipoConta)) {
                double montanteMinimo = Double.parseDouble(contaView.getMontanteMinimo());
                double depositoMinimo = Double.parseDouble(contaView.getDepositoMinimo());
                novaConta = new ContaInvestimento(cliente, depositoInicial, montanteMinimo, depositoMinimo);
            } else {
                contaView.exibirMensagemErro("Tipo de conta inválido.");
                return;
            }

            // Utiliza o DAO para criar a conta (o número será gerado automaticamente)
            contaDAO.criarConta(novaConta);
            contaView.exibirMensagemSucesso("Conta criada com sucesso! Número: " + novaConta.getNumero());

        } catch (SQLException e) {
            contaView.exibirMensagemErro("Erro ao criar conta: " + e.getMessage());
        } catch (NumberFormatException e) {
            contaView.exibirMensagemErro("Preencha os campos corretamente.");
        }
    }
    private void voltarIni() {
        contaView.dispose();  // Fecha a tela atual (ContaView)
        telaInicialView.setVisible(true);  // Exibe a tela principal novamente
    }
}
