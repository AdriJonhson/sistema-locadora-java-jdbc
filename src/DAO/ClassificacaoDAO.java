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
        //ConsultaIdClassificacao ou nome?
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
   
    public int getUltimoIdCadastro() {
        int id = 0;

        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT MAX(idclassi) FROM classificacao");
            rs = stmt.executeQuery();

            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.err.println("ClassificacaoDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return id + 1;
    }      
     
     public void cadastrarClassi(Classificacao classi) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO classificacao VALUES(0,?,?)");
            stmt.setString(1, classi.getNome());
           stmt.setDouble(2,classi.getPreco());

            if (stmt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "classificação Cadastrado com sucesso", "PTQX Locadora",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar o classificação, tente novamente.", "PTQX Locadora",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            System.err.println("ClassificaçãoDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
     
    public List<Classificacao> buscaPorNome(String Classificacao) {
        List<Classificacao> lista = new ArrayList<>();
        boolean achou = false;
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT idclassi, nome FROM classificacao WHERE nome LIKE ?");
            stmt.setString(1, "%" + Classificacao + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Classificacao classificacao = new Classificacao();
                classificacao.setId(rs.getInt("idclassi"));
                classificacao.setNome(rs.getString("nome"));
              
                achou = true;
                lista.add(classificacao);

            }

            if (!achou) {
                JOptionPane.showMessageDialog(null, "Classificacao não encontrada", "PTQX Locadora",
                        JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException ex) {
            System.err.println("ClassificacaoDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return lista;
    }
     
    public List<Classificacao> buscaPorId(int id) {
        List<Classificacao> lista = new ArrayList<>();
        boolean achou = false;
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT idclassi, nome FROM classificacao WHERE idclassi = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Classificacao classificacao = new Classificacao();
                classificacao.setId(rs.getInt("idclassi"));
                classificacao.setNome(rs.getString("nome"));
               
                achou = true;

                lista.add(classificacao);
            }

            if (!achou) {
                JOptionPane.showMessageDialog(null, "Classificação não encontrada", "PTQX Locadora",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.err.println("ClassificacaoDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return lista;
    }
    
public List<Classificacao> mostrarTodasClassificacao() {
        List<Classificacao> lista = new ArrayList<>();
        boolean achou = false;
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT idClassi, nome FROM classificacao ORDER BY nome ASC");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Classificacao classificacao = new Classificacao();
                classificacao.setId(rs.getInt("idClassi"));
                classificacao.setNome(rs.getString("nome"));
             
                achou = true;

                lista.add(classificacao);
            }

            if (!achou) {
                JOptionPane.showMessageDialog(null, "Nenhuma classificacao cadastrada", "PTQX Locadora",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.err.println("ClassificacaoDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return lista;
    }

}
