package DAO;

import Modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteDAO {

    public void cadastrarCliente(Cliente cliente) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO cliente VALUES(0,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getDt_nasc());
            stmt.setString(3, cliente.getRg());
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getEmail());
            stmt.setString(6, cliente.getTelefone());
            stmt.setString(7, cliente.getBairro());
            stmt.setString(8, cliente.getRua());
            stmt.setInt(9, cliente.getNumero());
            stmt.setString(10, cliente.getCep());

            if (stmt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cliente Cadastrado com sucesso", "PTQX Locadora",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar o cliente, tente novamente.", "PTQX Locadora",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            System.err.println("ClienteDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public int getUltimoIdCadastro() {
        int id = 0;

        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT MAX(idCliente) FROM cliente");
            rs = stmt.executeQuery();

            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.err.println("ClienteDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return id + 1;
    }

    public List<Cliente> listarIdCliente() {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cliente> lista = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT idCliente FROM cliente");
            rs = stmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    Cliente cliente = new Cliente();

                    cliente.setId(rs.getInt("idCliente"));

                    lista.add(cliente);
                }

            }
        } catch (SQLException ex) {
            System.err.println("ClienteDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return lista;

    }

    public List<Cliente> resgatarDadosCliente(int idCliente) {
        List<Cliente> lista = new ArrayList<>();
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE idCliente = ?");
            stmt.setInt(1, idCliente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();

                cliente.setNome(rs.getString("nome"));
                cliente.setDt_nasc(rs.getString("data_nasc"));
                cliente.setRg(rs.getString("rg"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setRua(rs.getString("rua"));
                cliente.setNumero(rs.getInt("numero"));
                cliente.setCep(rs.getString("cep"));

                lista.add(cliente);
            }

        } catch (SQLException ex) {
            System.err.println("ClienteDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return lista;
    }

    public void alterarDadosCliente(Cliente cliente) {
        List<Cliente> lista = new ArrayList<>();
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE cliente SET nome = ?, data_nasc = ?, rg = ?, cpf = ?, email = ?,"
                    + "telefone = ?, bairro = ?, rua = ?, numero = ?, cep = ? WHERE idCliente = ?");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getDt_nasc());
            stmt.setString(3, cliente.getRg());
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getEmail());
            stmt.setString(6, cliente.getTelefone());
            stmt.setString(7, cliente.getBairro());
            stmt.setString(8, cliente.getRua());
            stmt.setInt(9, cliente.getNumero());
            stmt.setString(10, cliente.getCep());
            stmt.setInt(11, cliente.getId());

            if (stmt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Os dados foram alterados com sucesso.", "PTQX Locadora",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao tentar salvar as alterações", "PTQX Locadora",
                        JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException ex) {
            System.err.println("ClienteDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }

    }

    public List<Cliente> buscaPorId(int id) {
        List<Cliente> lista = new ArrayList<>();
        boolean achou = false;
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT idCliente, nome, rg, cpf, email, telefone FROM cliente WHERE idCliente = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("idCliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setRg(rs.getString("rg"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                achou = true;

                lista.add(cliente);
            }

            if (!achou) {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado", "PTQX Locadora",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.err.println("ClienteDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return lista;
    }

    public List<Cliente> buscaPorNome(String nomeCliente) {
        List<Cliente> lista = new ArrayList<>();
        boolean achou = false;
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT idCliente, nome, rg, cpf, email, telefone FROM cliente WHERE nome LIKE ?");
            stmt.setString(1, "%" + nomeCliente + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("idCliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setRg(rs.getString("rg"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                achou = true;
                lista.add(cliente);

            }

            if (!achou) {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado", "PTQX Locadora",
                        JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException ex) {
            System.err.println("ClienteDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return lista;
    }

    public List<Cliente> mostrarTodosClientes() {
        List<Cliente> lista = new ArrayList<>();
        boolean achou = false;
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT idCliente, nome, rg, cpf, email, telefone FROM cliente ORDER BY nome ASC");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("idCliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setRg(rs.getString("rg"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                achou = true;
                
                lista.add(cliente);
            }
            
            if(!achou){
                JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado", "PTQX Locadora",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.err.println("ClienteDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return lista;
    }

}
