package DAO;

import model.Cliente;
import model.Conta;
import model.ContaCorrente;
import model.ContaInvestimento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DBConn;

/**
 *
 * @author Murilo Schrickte
 */

public class ContaDAO {

    // Método para criar uma conta
    public void criarConta(Conta conta) throws SQLException {
        String sql = "INSERT INTO conta (numero, tipo, cliente_id, saldo, limite, montante_minimo, deposito_minimo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConn.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, conta.getNumero());
            stmt.setString(2, conta.getTipoConta());
            stmt.setInt(3, conta.getCliente().getId());
            stmt.setDouble(4, conta.getSaldo());

            if (conta instanceof ContaCorrente corrente) {
                stmt.setDouble(5, corrente.getLimite());
                stmt.setNull(6, Types.DOUBLE); // Montante mínimo
                stmt.setNull(7, Types.DOUBLE); // Depósito mínimo
            } else if (conta instanceof ContaInvestimento investimento) {
                stmt.setNull(5, Types.DOUBLE); // Limite
                stmt.setDouble(6, investimento.getMontanteMinimo());
                stmt.setDouble(7, investimento.getDepositoMinimo());
            }
            stmt.executeUpdate();
        }
    }

    // Método para buscar contas de um cliente
    public List<Conta> buscarContasPorCliente(Cliente cliente) throws SQLException {
        String sql = "SELECT * FROM conta WHERE cliente_id = ?";
        List<Conta> contas = new ArrayList<>();

        try (Connection conn = DBConn.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cliente.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Conta conta = construirConta(rs, cliente);
                contas.add(conta);
            }
        }

        return contas;
    }

    // Método para atualizar uma conta
    public void atualizarConta(Conta conta) throws SQLException {
        String sql = "UPDATE conta SET saldo = ?, limite = ?, montante_minimo = ?, deposito_minimo = ? WHERE numero = ?";
        try (Connection conn = DBConn.getConnection();    
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, conta.getSaldo());

            if (conta instanceof ContaCorrente corrente) {
                stmt.setDouble(2, corrente.getLimite());
                stmt.setNull(3, Types.DOUBLE); // Montante mínimo
                stmt.setNull(4, Types.DOUBLE); // Depósito mínimo
            } else if (conta instanceof ContaInvestimento investimento) {
                stmt.setNull(2, Types.DOUBLE); // Limite
                stmt.setDouble(3, investimento.getMontanteMinimo());
                stmt.setDouble(4, investimento.getDepositoMinimo());
            }

            stmt.setInt(5, conta.getNumero());
            stmt.executeUpdate();
        }
    }

    // Método para excluir uma conta
    public void excluirConta(int numeroConta) throws SQLException {
        String sql = "DELETE FROM conta WHERE numero = ?";
        try (Connection conn = DBConn.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, numeroConta);
            stmt.executeUpdate();
        }
    }

    // Método auxiliar para construir uma conta a partir do ResultSet
    private Conta construirConta(ResultSet rs, Cliente cliente) throws SQLException {
        int numero = rs.getInt("numero");
        String tipo = rs.getString("tipo");
        double saldo = rs.getDouble("saldo");

        if ("Conta Corrente".equals(tipo)) {
            double limite = rs.getDouble("limite");
            return new ContaCorrente(cliente, saldo, limite);
        } else if ("Conta Investimento".equals(tipo)) {
            double montanteMinimo = rs.getDouble("montante_minimo");
            double depositoMinimo = rs.getDouble("deposito_minimo");
            return new ContaInvestimento(cliente, saldo, montanteMinimo, depositoMinimo);
        }

        throw new SQLException("Tipo de conta desconhecido: " + tipo);
    }
}
