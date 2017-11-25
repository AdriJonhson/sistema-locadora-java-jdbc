package DAO;

import Modelo.Aluguel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class AluguelDAO {

    public void cadastrarNovoAluguel(Aluguel aluguel) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO aluguel VALUES(0, ?, ?, ?, ?, ?)");
            stmt.setInt(1, aluguel.getIdDvd());
            stmt.setInt(2, aluguel.getIdCliente());
            stmt.setString(3, aluguel.getHora());
            stmt.setString(4, aluguel.getDataAluguel());
            stmt.setString(5, aluguel.getDataDevolucao());

            if (stmt.executeUpdate() >= 1) {
                JOptionPane.showMessageDialog(null, "Aluguel cadastrado com sucesso", "PTQX Locadora",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao tentar cadastrar o aluguel", "PTQX Locadora",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException ex) {
            System.err.println("AlguelDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public List<Aluguel> mostrarTodosAlugueis() {
        List<Aluguel> lista = new ArrayList<>();
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM aluguel");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Aluguel aluguel = new Aluguel();
                aluguel.setIdAluguel(rs.getInt("idaluguel"));
                aluguel.setIdDvd(rs.getInt("iddvd"));
                aluguel.setIdCliente(rs.getInt("idcliente"));
                aluguel.setHora(rs.getString("hora_aluguel"));
                aluguel.setDataAluguel(rs.getString("data_aluguel"));
                aluguel.setDataDevolucao(rs.getString("data_devolucao"));

                lista.add(aluguel);
            }
        } catch (SQLException ex) {
            System.err.println("AluguelDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return lista;
    }

    public List<Aluguel> buscarPorIdAluguel(int idAluguel) {
        List<Aluguel> lista = new ArrayList<>();
        boolean existe = false;
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM aluguel WHERE idaluguel = ?");
            stmt.setInt(1, idAluguel);
            
            rs = stmt.executeQuery();
            while (rs.next()) {
                Aluguel aluguel = new Aluguel();
                aluguel.setIdAluguel(rs.getInt("idaluguel"));
                aluguel.setIdDvd(rs.getInt("iddvd"));
                aluguel.setIdCliente(rs.getInt("idcliente"));
                aluguel.setHora(rs.getString("hora_aluguel"));
                aluguel.setDataAluguel(rs.getString("data_aluguel"));
                aluguel.setDataDevolucao(rs.getString("data_devolucao"));
                
                lista.add(aluguel);
                existe = true;
            }
            
            if(!existe){
                JOptionPane.showMessageDialog(null, "Aluguel não encontrado", "PTQX Locadora", 
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.err.println("AluguelDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return lista;
    }

    public List<Aluguel> buscarPorIdDvd(int idDvd) {
        List<Aluguel> lista = new ArrayList<>();
        boolean existe = false;
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM aluguel WHERE iddvd = ?");
            stmt.setInt(1, idDvd);
            
            rs = stmt.executeQuery();
            while (rs.next()) {
                Aluguel aluguel = new Aluguel();
                aluguel.setIdAluguel(rs.getInt("idaluguel"));
                aluguel.setIdDvd(rs.getInt("iddvd"));
                aluguel.setIdCliente(rs.getInt("idcliente"));
                aluguel.setHora(rs.getString("hora_aluguel"));
                aluguel.setDataAluguel(rs.getString("data_aluguel"));
                aluguel.setDataDevolucao(rs.getString("data_devolucao"));
                
                lista.add(aluguel);
                existe = true;
            }
            
            if(!existe){
                JOptionPane.showMessageDialog(null, "Aluguel não encontrado", "PTQX Locadora", 
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.err.println("AluguelDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return lista;
    }

    public List<Aluguel> buscarPorIdCliente(int idCliente) {
        List<Aluguel> lista = new ArrayList<>();
        boolean existe = false;
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM aluguel WHERE idcliente = ?");
            stmt.setInt(1, idCliente);
            
            rs = stmt.executeQuery();
            while (rs.next()) {
                Aluguel aluguel = new Aluguel();
                aluguel.setIdAluguel(rs.getInt("idaluguel"));
                aluguel.setIdDvd(rs.getInt("iddvd"));
                aluguel.setIdCliente(rs.getInt("idcliente"));
                aluguel.setHora(rs.getString("hora_aluguel"));
                aluguel.setDataAluguel(rs.getString("data_aluguel"));
                aluguel.setDataDevolucao(rs.getString("data_devolucao"));
                
                lista.add(aluguel);
                existe = true;
            }
            
            if(!existe){
                JOptionPane.showMessageDialog(null, "Aluguel não encontrado", "PTQX Locadora", 
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.err.println("AluguelDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return lista;
    }

    


}
