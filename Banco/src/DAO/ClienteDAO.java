package DAO;

import model.Cliente;
import util.DBConn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Murilo Schrickte
 */

public class ClienteDAO {
    
    //Inserir cliente
    public void inserir(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente (nome, sobrenome, rg, cpf, endereco) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConn.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getSobrenome());
            stmt.setString(3, cliente.getRg());
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getEndereco());
            stmt.executeUpdate();
        }
    }
    
     //Atualizar cliente
    public void atualizar(Cliente cliente) throws SQLException {
        String sql = "UPDATE cliente SET nome = ?, sobrenome = ?, rg = ?, cpf = ?, endereco = ? WHERE id = ?";
        try (Connection conn = DBConn.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getSobrenome());
            stmt.setString(3, cliente.getRg());
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getEndereco());
            stmt.setInt(6, cliente.getId());
            stmt.executeUpdate();
        }
    }
    
     //Excluir cliente
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM cliente WHERE id = ?";
        try (Connection conn = DBConn.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

      //Listar cliente - Sem filtros
    public List<Cliente> listar() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (Connection conn = DBConn.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        rs.getString("rg"),
                        rs.getString("cpf"),
                        rs.getString("endereco")
                );
                clientes.add(cliente);
            }
        }
        return clientes;
    }
    
     //Listar por nome
    public List<Cliente> buscarPorNome(String nome) throws SQLException {
        return buscarClientes("SELECT * FROM cliente WHERE nome LIKE ?", "%" + nome + "%");
    }
    
        public Cliente buscaPorId(int id) throws SQLException {
            String sql = "SELECT * FROM cliente WHERE id = ?";
            try (Connection conn = DBConn.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);  // Usa o ID para a busca exata (sem o LIKE e sem o '%')

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        // Cria um novo cliente com os dados do ResultSet
                        Cliente cliente = new Cliente(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("sobrenome"),
                            rs.getString("rg"),
                            rs.getString("cpf"),
                            rs.getString("endereco")
                        );
                        return cliente;  // Retorna o cliente encontrado
                    }
                }
            }
            return null;  // Retorna null se nenhum cliente for encontrado
        }
        
    //Listar por sobrenome
    public List<Cliente> buscarPorSobrenome(String sobrenome) throws SQLException {
        return buscarClientes("SELECT * FROM cliente WHERE sobrenome LIKE ?", "%" + sobrenome + "%");
    }
    
    //Listar por RG
    public List<Cliente> buscarPorRG(String rg) throws SQLException {
        return buscarClientes("SELECT * FROM cliente WHERE rg LIKE ?", "%" + rg + "%");
    }
    
    //Listar por CPF
    public List<Cliente> buscarPorCPF(String cpf) throws SQLException {
        return buscarClientes("SELECT * FROM cliente WHERE cpf LIKE ?", "%" + cpf + "%");
    }
    
    //Buscar clientes
    private List<Cliente> buscarClientes(String sql, String parametro) throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        try (Connection conn = DBConn.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, parametro);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Cliente cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        rs.getString("rg"),
                        rs.getString("cpf"),
                        rs.getString("endereco")
                    );
                    clientes.add(cliente);
                }
            }
        }
        return clientes;
    }
}