package DAO;

import model.Conta;
import model.ContaCorrente;
import model.ContaInvestimento;
import model.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Murilo Schrickte and Pietra Minatti
 */

public class ContaDAOUtil {

    // Método para construir uma única conta a partir do ResultSet
    public static Conta construirConta(ResultSet rs) throws SQLException {
        System.out.println(rs);
        int numero = rs.getInt("numero");
        String tipo = rs.getString("tipo");
        double saldo = rs.getDouble("saldo");
        int clienteId = rs.getInt("cliente_id");
        String nome = rs.getString("nome");
        String sobrenome = rs.getString("sobrenome");
        String rg = rs.getString("rg");
        String cpf = rs.getString("cpf");
        String end = rs.getString("endereco");

        Cliente cliente = new Cliente(clienteId, nome, sobrenome, rg, cpf, end);

        // Constrói o objeto Conta com base no tipo
        if ("ContaCorrente".equals(tipo)) {
            double limite = rs.getDouble("limite");
            ContaCorrente contaCorrente = new ContaCorrente(cliente, saldo, limite);
            contaCorrente.setNumero(numero);  // Atribuindo o número da conta
            return contaCorrente;
        } else if ("ContaInvestimento".equals(tipo)) {
            double montanteMinimo = rs.getDouble("montante_minimo");
            double depositoMinimo = rs.getDouble("deposito_minimo");
            ContaInvestimento contaInvestimento = new ContaInvestimento(cliente, saldo, montanteMinimo, depositoMinimo);
            contaInvestimento.setNumero(numero);  // Atribuindo o número da conta
            return contaInvestimento;
        }
        throw new SQLException("Tipo de conta desconhecido: " + tipo);
    }

    // Método para construir uma lista de contas a partir do ResultSet
    public static List<Conta> construirContas(ResultSet rs) throws SQLException {
        List<Conta> contas = new ArrayList<>();
        
        // Percorre o ResultSet para construir todas as contas
        while (rs.next()) {
            Conta conta = construirConta(rs);
            contas.add(conta); // Adiciona a conta à lista
        }
        return contas; // Retorna a lista de contas
    }
}