package view;

import model.Cliente;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 *
 * @author Murilo Schrickte
 */

//Modelo de tabela
public class ClienteTableModel extends AbstractTableModel {
    private final String[] colunas = {"ID", "Nome", "Sobrenome", "RG", "CPF", "Endereço"};
    private List<Cliente> clientes;

    public ClienteTableModel(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = clientes.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> cliente.getId();
            case 1 -> cliente.getNome();
            case 2 -> cliente.getSobrenome();
            case 3 -> cliente.getRg();
            case 4 -> cliente.getCpf();
            case 5 -> cliente.getEndereco();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    public Cliente getClienteAt(int rowIndex) {
        return clientes.get(rowIndex);
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
        fireTableDataChanged();
    }
    
    public List<Cliente> getClientes() {
        return clientes;
    }
}