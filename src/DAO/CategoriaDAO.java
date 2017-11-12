package DAO;

import Modelo.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
}
