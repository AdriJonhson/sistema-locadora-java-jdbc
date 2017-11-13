package DAO;

import Modelo.Categoria;
import Modelo.Classificacao;
import Modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CategoriaDAO {
    
    public List<Categoria> listarCategoria(){
        List<Categoria> lista = new ArrayList<>();
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs =null;
        
        try{
            stmt = con.prepareStatement("SELECT * FROM categoria");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Categoria c = new Categoria();
                c.setNome(rs.getString("nome"));
                
                lista.add(c);
            }
            
        }catch(SQLException ex){
            System.err.println("CategoriaDAO: " + ex);
        }finally{
            Conexao.closeConnection(con, stmt);
        }
        
        return lista;
    }
    
    public List<Categoria> consultaIdCategoria(String nome){
        List<Categoria> lista = new ArrayList<>();
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs =null;
        
        try{
            stmt = con.prepareStatement("SELECT * FROM categoria WHERE nome = ?");
            stmt.setString(1, nome);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Categoria c = new Categoria();
                c.setId(rs.getInt("idCategoria"));
                
                lista.add(c);
            }
            
        }catch(SQLException ex){
            System.err.println("CategoriaDAO: " + ex);
        }finally{
            Conexao.closeConnection(con, stmt);
        }
        
        return lista;
    }
    
    public int getUltimoIdCadastro() {
        int id = 0;

        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT MAX(idCategoria) FROM categoria");
            rs = stmt.executeQuery();

            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.err.println("CategoriaDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return id + 1;
    }      
    
     public void cadastrarCategoria(Categoria categoria) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO categoria VALUES(0,?)");
            stmt.setString(1, categoria.getNome());
           
            if (stmt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cliente Cadastrado com sucesso", "PTQX Locadora",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar o cliente, tente novamente.", "PTQX Locadora",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            System.err.println("CategoriaDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
     
}

