package DAO;

import model.Conta;
import util.DBConn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Murilo Schrickte and Pietra Minatti
 */

public class ContaOperacaoDAO {

    // Método para realizar depósito
    public boolean depositar(Conta conta, double valor) throws SQLException {
        if (valor <= 0) {
            return false; // Valor inválido
        }

        // Realiza o depósito na conta (em memória)
        conta.deposita(valor);

        // Atualiza imediatamente o saldo no banco de dados
        return atualizarSaldoNoBanco(conta);
    }

    // Método para realizar saque
    public boolean sacar(Conta conta, double valor) throws SQLException {
        System.out.println(valor);
        System.out.println(conta.getSaldo());
        if (valor <= 0 || conta.getSaldo() < valor) {
            return false; // Valor inválido ou saldo insuficiente
        }
        // Realiza o saque na conta (em memória)
        conta.saca(valor);

        // Atualiza imediatamente o saldo no banco de dados
        return atualizarSaldoNoBanco(conta);
    }

    // Método para aplicar remuneração
    public boolean remunerar(Conta conta) throws SQLException {
        // Aplica a remuneração na conta (em memória)
        conta.remunera();

        // Atualiza imediatamente o saldo no banco de dados
        return atualizarSaldoNoBanco(conta);
    }

    // Método para buscar conta pelo CPF
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
                    return ContaDAOUtil.construirConta(rs);
                }
            }
        }
        return null;
    }

    // Método auxiliar para atualizar o saldo da conta no banco
    private boolean atualizarSaldoNoBanco(Conta conta) throws SQLException {
        String query = "UPDATE conta SET saldo = ? WHERE numero = ?";
        try (Connection conn = DBConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDouble(1, conta.getSaldo());
            stmt.setInt(2, conta.getNumero());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Se a atualização foi bem-sucedida
        }
    }
}