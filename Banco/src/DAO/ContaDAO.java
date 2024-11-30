package DAO;

import model.Cliente;
import model.Conta;
import model.ContaCorrente;
import model.ContaInvestimento;
import util.DBConn;
import util.GeradorNumeroConta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Murilo Schrickte and Pietra Minatti
 */

public class ContaDAO {

    // Método para criar uma conta
        public void criarConta(Conta conta) throws SQLException {
        String sql = "INSERT INTO conta (numero, tipo, cliente_id, saldo, limite, montante_minimo, deposito_minimo) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Gera o número único da conta
            int numeroConta = GeradorNumeroConta.gerarNumero();

            stmt.setInt(1, numeroConta);
            stmt.setString(2, conta.getClass().getSimpleName()); // Identifica o tipo da conta
            stmt.setInt(3, conta.getDono().getId());
            stmt.setDouble(4, conta.getSaldo());

            if (conta instanceof ContaCorrente) {
                stmt.setDouble(5, ((ContaCorrente) conta).getLimite());
                stmt.setNull(6, Types.DOUBLE); // Montante mínimo
                stmt.setNull(7, Types.DOUBLE); // Depósito mínimo
            } else if (conta instanceof ContaInvestimento) {
                stmt.setNull(5, Types.DOUBLE); // Limite
                stmt.setDouble(6, ((ContaInvestimento) conta).getMontanteMinimo());
                stmt.setDouble(7, ((ContaInvestimento) conta).getDepositoMinimo());
            }
            stmt.executeUpdate();

            // Atualiza o número gerado na instância da conta
            conta.setNumero(numeroConta);
        }
    }

    // Método para buscar contas de um cliente
    public List<Conta> buscarContasPorCliente(Cliente cliente) throws SQLException {
        String sql = "SELECT * FROM conta WHERE cliente_id = ?";
        List<Conta> contas = new ArrayList<>();

        try (Connection conn = DBConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cliente.getId());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Conta conta = ContaDAOUtil.construirConta(rs);
                    contas.add(conta);
                }
            }
        }
        return contas;
    }

    // Método para atualizar uma conta
    public void atualizarConta(Conta conta) throws SQLException {
        String query = "UPDATE conta SET saldo = ? WHERE numero = ?";
        try (Connection conn = DBConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDouble(1, conta.getSaldo());
            stmt.setInt(2, conta.getNumero());
            stmt.executeUpdate();
        }
    }

    // Método para buscar uma conta pelo CPF do cliente
    public Conta buscarPorCpf(String cpf) throws SQLException {
        String query = "SELECT c.*, cl.cpf, cl.nome, cl.sobrenome, cl.endereco, cl.id, cl.rg " +
                       "FROM conta c " +
                       "JOIN cliente cl ON c.cliente_id = cl.id " +
                       "WHERE cl.cpf = ?";
        try (Connection conn = DBConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return construirContaComCliente(rs);
                }
            }
        }
        return null;
    }

    // Método auxiliar para construir uma conta com dados do cliente a partir do ResultSet
    public Conta construirContaComCliente(ResultSet rs) throws SQLException {
        int numero = rs.getInt("numero");
        double saldo = rs.getDouble("saldo");
        String tipo = rs.getString("tipo");

        // Dados do cliente
        int clienteId = rs.getInt("id");
        String nome = rs.getString("nome");
        String sobrenome = rs.getString("sobrenome");
        String rg = rs.getString("rg");
        String cpf = rs.getString("cpf");
        String endereco = rs.getString("endereco");
        Cliente cliente = new Cliente(clienteId, nome, sobrenome, rg, cpf, endereco);

        // Construção da conta com base no tipo
        if ("ContaCorrente".equals(tipo)) {
            double limite = rs.getDouble("limite");
            return new ContaCorrente(cliente, saldo, limite);
        } else if ("ContaInvestimento".equals(tipo)) {
            double montanteMinimo = rs.getDouble("montante_minimo");
            double depositoMinimo = rs.getDouble("deposito_minimo");
            return new ContaInvestimento(cliente, saldo, montanteMinimo, depositoMinimo);
        }
        throw new SQLException("Tipo de conta desconhecido: " + tipo);
    }
}