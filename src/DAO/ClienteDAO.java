package DAO;

import Modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public int getUltimoIdCadastro(){
        int id = 0;
        
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = con.prepareStatement("SELECT MAX(idCliente) FROM cliente");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                id = rs.getInt(1);
            }
        }catch(SQLException ex){
            System.err.println("ClienteDAO: " + ex);
        }finally{
            Conexao.closeConnection(con, stmt, rs);
        }
        return id + 1;
    }
}
