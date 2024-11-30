package util;

import java.sql.*;

/**
 * Classe utilitária para gerar números de conta únicos.
 *
 * @author Murilo Schrickte
 */
public class GeradorNumeroConta {

    // Método para gerar o número da conta
    public static synchronized int gerarNumero() throws SQLException {
        int numeroAtual = obterUltimoNumero(); // Obtemos o último número de conta
        return numeroAtual + 1;  // Incrementa 1 para gerar o próximo número
    }

    // Método para obter o último número de conta do banco
    private static int obterUltimoNumero() throws SQLException {
        int ultimoNumero = 0;  // Valor inicial caso não haja contas
        String sql = "SELECT MAX(numero) AS numero FROM conta";  // Consulta para pegar o maior número de conta
        try (Connection conn = DBConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                ultimoNumero = rs.getInt("numero");  // Recupera o maior número de conta
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao obter o último número de conta: " + e.getMessage());
        }

        return ultimoNumero;
    }
}