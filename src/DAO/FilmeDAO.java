package DAO;

import Modelo.Filme;
import Modelo.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class FilmeDAO {

    public void cadastrarFilme(Filme filme) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO filme VALUES(0, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, filme.getTitulo());
            stmt.setInt(2, filme.getAno());
            stmt.setString(3, filme.getDuracao());
            stmt.setInt(4, filme.getIdCategoria());
            stmt.setInt(5, filme.getIdClassificacao());
            stmt.setString(6, filme.getCapa());

            if (stmt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso", "PTQX Locadora",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao efetuar o cadastro", "PTQX Locadora",
                        JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException ex) {
            System.err.println("FilmeDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public int getUltimoCadastro() {
        int codigo = 0;
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            //idFuncionario padrão
            stmt = con.prepareStatement("SELECT MAX(idfilme) FROM filme");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario func = new Funcionario();
                codigo = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("Filme DAO: " + ex);
        }

        return (codigo + 1);
    }

    public List<Filme> mostrarTodosFilmes() {
        List<Filme> lista = new ArrayList<>();
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean achou = false;

        try {
            stmt = con.prepareStatement("SELECT f.idfilme, f.titulo, f.ano, "
                    + "f.duracao, cat.nome, cla.nome "
                    + "FROM filme AS f "
                    + "JOIN categoria AS cat "
                    + "JOIN classificacao AS cla;");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Filme filme = new Filme();
                filme.setIdFilme(rs.getInt("f.idfilme"));
                filme.setTitulo(rs.getString("f.titulo"));
                filme.setAno(rs.getInt("f.ano"));
                filme.setDuracao(rs.getString("f.duracao"));
                filme.setNomeCategoria(rs.getString("cat.nome"));
                filme.setNomeClassificacao(rs.getString("cla.nome"));
                lista.add(filme);
                achou = true;
            }

            if (!achou) {
                JOptionPane.showMessageDialog(null, "Nenhum Filme Encontrado",
                        "PTQX Locadora", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException ex) {
            System.err.println("FilmeDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return lista;
    }

    public List<Filme> buscarPorId(int idFilme) {
        List<Filme> lista = new ArrayList<>();
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean achou = false;

        try {
            stmt = con.prepareStatement("SELECT f.idfilme, f.titulo, f.ano, "
                    + "f.duracao, cat.nome, cla.nome "
                    + "FROM filme AS f "
                    + "JOIN categoria AS cat "
                    + "JOIN classificacao AS cla WHERE f.idfilme = ?");
            stmt.setInt(1, idFilme);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Filme filme = new Filme();
                filme.setIdFilme(rs.getInt("f.idfilme"));
                filme.setTitulo(rs.getString("f.titulo"));
                filme.setAno(rs.getInt("f.ano"));
                filme.setDuracao(rs.getString("f.duracao"));
                filme.setNomeCategoria(rs.getString("cat.nome"));
                filme.setNomeClassificacao(rs.getString("cla.nome"));
                lista.add(filme);
                achou = true;
            }

            if (!achou) {
                JOptionPane.showMessageDialog(null, "Nenhum Filme Encontrado",
                        "PTQX Locadora", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException ex) {
            System.err.println("FilmeDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return lista;
    }

    public List<Filme> buscarPorTitulo(String tituloFilme) {
        List<Filme> lista = new ArrayList<>();
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean achou = false;

        try {
            stmt = con.prepareStatement("SELECT f.idfilme, f.titulo, f.ano, "
                    + "f.duracao, cat.nome, cla.nome "
                    + "FROM filme AS f "
                    + "JOIN categoria AS cat "
                    + "JOIN classificacao AS cla WHERE f.titulo LIKE ?");
            stmt.setString(1, "%" + tituloFilme + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {
                Filme filme = new Filme();
                filme.setIdFilme(rs.getInt("f.idfilme"));
                filme.setTitulo(rs.getString("f.titulo"));
                filme.setAno(rs.getInt("f.ano"));
                filme.setDuracao(rs.getString("f.duracao"));
                filme.setNomeCategoria(rs.getString("cat.nome"));
                filme.setNomeClassificacao(rs.getString("cla.nome"));
                lista.add(filme);
                achou = true;
            }

            if (!achou) {
                JOptionPane.showMessageDialog(null, "Nenhum Filme Encontrado",
                        "PTQX Locadora", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException ex) {
            System.err.println("FilmeDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return lista;
    }

    public List<Filme> listarIdFilmes() {
        List<Filme> lista = new ArrayList<>();

        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT idfilme FROM filme");
            rs = stmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    Filme filme = new Filme();

                    filme.setIdFilme(rs.getInt("idfilme"));

                    lista.add(filme);
                }

            }
        } catch (SQLException ex) {
            System.err.println("ClienteDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return lista;
    }

    public List<Filme> resgatarDadosFilme(int idFilme) {
        List<Filme> lista = new ArrayList<>();
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT f.idfilme, f.titulo, f.ano, "
                    + "f.duracao, cat.nome, cla.nome, f.capa, f.idcategoria, f.idclassificacao "
                    + "FROM filme AS f "
                    + "JOIN categoria AS cat "
                    + "JOIN classificacao AS cla WHERE f.idfilme = ?");
            stmt.setInt(1, idFilme);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Filme filme = new Filme();
                filme.setIdFilme(rs.getInt("f.idfilme"));
                filme.setTitulo(rs.getString("f.titulo"));
                filme.setAno(rs.getInt("f.ano"));
                filme.setDuracao(rs.getString("f.duracao"));
                filme.setCapa(rs.getString("f.capa"));
                filme.setNomeCategoria(rs.getString("cat.nome"));
                filme.setNomeClassificacao(rs.getString("cla.nome"));
                filme.setIdCategoria(rs.getInt("f.idcategoria"));
                filme.setIdClassificacao(rs.getInt("f.idclassificacao"));
                lista.add(filme);
            }
        } catch (SQLException ex) {
            System.err.println("ClienteDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return lista;
    }

    public void alterarDadosFilme(Filme filme){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("UPDATE filme SET titulo = ?, ano = ?, duracao = ?, idcategoria = ?,"
                    + "idclassificacao = ?, capa = ? WHERE idfilme = ?");
            stmt.setString(1, filme.getTitulo());
            stmt.setInt(2, filme.getAno());
            stmt.setString(3, filme.getDuracao());
            stmt.setInt(4, filme.getIdCategoria());
            stmt.setInt(5, filme.getIdClassificacao());
            stmt.setString(6, filme.getCapa());
            stmt.setInt(7, filme.getIdFilme());
            
            if (stmt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Dados alterados com sucesso", "PTQX Locadora",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao alterar os dados", "PTQX Locadora",
                        JOptionPane.WARNING_MESSAGE);
            }
            
            
        }catch(SQLException ex){
            System.err.println("FilmeDAO: " + ex);
        }finally{
            Conexao.closeConnection(con, stmt);
        }
    }
}
