package DAO;

import Modelo.Classificacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassificacaoDAO {
    
    public List<Classificacao> listarClassificacao(){
        List<Classificacao> lista = new ArrayList<>();
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs =null;
        
        try{
            stmt = con.prepareStatement("SELECT * FROM classificacao");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Classificacao c = new Classificacao();
                c.setId(rs.getInt("idclassi"));
                c.setNome(rs.getString("nome"));
                
                lista.add(c);
            }
            
        }catch(SQLException ex){
            System.err.println("ClassificacaoDAO: " + ex);
        }finally{
            Conexao.closeConnection(con, stmt);
        }
        
        return lista;
    }

    public List<Classificacao> consultaIdClassificacao(String nome){
        List<Classificacao> lista = new ArrayList<>();
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs =null;
        
        try{
            stmt = con.prepareStatement("SELECT * FROM classificacao WHERE nome = ?");
            stmt.setString(1, nome);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Classificacao c = new Classificacao();
                c.setId(rs.getInt("idclassi"));
                
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
