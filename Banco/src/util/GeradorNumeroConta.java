package util;

import util.DBConn;
import java.sql.*;

/**
 *
 * @author Murilo Schrickte
 */public class GeradorNumeroConta {

    // Método para gerar o número da conta
    public static synchronized int gerarNumero() {
        int numeroAtual = obterUltimoNumero();
        System.out.println(numeroAtual);// Obtemos o último número de conta
        int novoNumero = numeroAtual + 1;  // Incrementa 1 para gerar o próximo número
        return novoNumero;  // Retorna o novo número da conta
    }

    // Método para obter o último número de conta do banco
    private static int obterUltimoNumero() {
        int ultimoNumero = 1;  // Valor inicial caso não haja contas
        String sql = "SELECT MAX(numero) AS ultimo_id FROM conta";  // Consulta para pegar o maior ID (última conta criada)
        try (Connection conn = DBConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                ultimoNumero = rs.getInt("ultimo_id");  // Recupera o último id
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter o último número de conta: " + e.getMessage());
        }

        // Se não houver contas, retorna 0, caso contrário, retorna o último número de conta
        return ultimoNumero > 0 ? ultimoNumero : 0;
    }
}
