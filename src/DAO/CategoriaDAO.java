package DAO;

import Modelo.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CategoriaDAO {

    public List<Categoria> listarCategoria() {
        List<Categoria> lista = new ArrayList<>();
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM categoria");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setNome(rs.getString("nome"));

                lista.add(c);
            }

        } catch (SQLException ex) {
            System.err.println("CategoriaDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }

        return lista;
    }

    public List<Categoria> consultaIdCategoria(String nome) {
        List<Categoria> lista = new ArrayList<>();
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM categoria WHERE nome = ?");
            stmt.setString(1, nome);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setId(rs.getInt("idCategoria"));

                lista.add(c);
            }

        } catch (SQLException ex) {
            System.err.println("CategoriaDAO: " + ex);
        } finally {
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
                JOptionPane.showMessageDialog(null, "Categoria Cadastrado com sucesso", "PTQX Locadora",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar o categoria, tente novamente.", "PTQX Locadora",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            System.err.println("CategoriaDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public List<Categoria> buscaPorNome(String Categoria) {
        List<Categoria> lista = new ArrayList<>();
        boolean achou = false;
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT idCategoria, nome FROM categoria WHERE nome LIKE ?");
            stmt.setString(1, "%" + Categoria + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("idCategoria"));
                categoria.setNome(rs.getString("nome"));

                achou = true;
                lista.add(categoria);

            }

            if (!achou) {
                JOptionPane.showMessageDialog(null, "Categoria não encontrada", "PTQX Locadora",
                        JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException ex) {
            System.err.println("CategoriaDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return lista;
    }

    public List<Categoria> buscaPorId(int id) {
        List<Categoria> lista = new ArrayList<>();
        boolean achou = false;
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT idCategoria, nome FROM Categoria WHERE idCategoria = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("idCategoria"));
                categoria.setNome(rs.getString("nome"));

                achou = true;

                lista.add(categoria);
            }

            if (!achou) {
                JOptionPane.showMessageDialog(null, "Categoria não encontrada", "PTQX Locadora",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.err.println("CategoriaDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return lista;
    }

    public List<Categoria> mostrarTodasClassificacao() {
        List<Categoria> lista = new ArrayList<>();
        boolean achou = false;
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT idCategoria, nome FROM categoria ORDER BY nome ASC");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("idCategoria"));
                categoria.setNome(rs.getString("nome"));

                achou = true;

                lista.add(categoria);
            }

            if (!achou) {
                JOptionPane.showMessageDialog(null, "Nenhuma categoria cadastrada", "PTQX Locadora",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.err.println("CategoriaDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return lista;
    }

    public void alterarDadosCategoria(int idCategoria, String nome) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("UPDATE categoria SET nome = ? WHERE idCategoria = ?");
            stmt.setString(1, nome);
            stmt.setInt(2, idCategoria);

            if (stmt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Os dados foram alterados com sucesso.", "PTQX Locadora",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao tentar salvar as alterações", "PTQX Locadora",
                        JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException ex) {
            System.err.println("Erro CategoriaDAO: " + ex);
        }
    }

    public List<Categoria> resgatarDadosCateg(int idCategoria) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Categoria> lista = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM categoria WHERE idCategoria = ?");
            stmt.setInt(1, idCategoria);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Categoria categoria = new Categoria();

                categoria.setId(rs.getInt("idCategoria"));
                categoria.setNome(rs.getString("nome"));

                lista.add(categoria);

            }

        } catch (SQLException ex) {
            System.out.println("CategoriaDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return lista;
    }

    public List<Categoria> listarIdCategoria() {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Categoria> lista = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT idCategoria FROM categoria");
            rs = stmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    Categoria categoia = new Categoria();

                    categoia.setId(rs.getInt("idCategoria"));

                    lista.add(categoia);
                }

            }
        } catch (SQLException ex) {
            System.err.println("FuncionarioDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return lista;

    }

    public void excluirCategoria(int idCategoria) {

        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("DELETE FROM categoria WHERE idCategoria = ?");
            stmt.setInt(1, idCategoria);

            if (stmt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Categoria excluída com sucesso", "PTQX Locadora",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao tentar excluir a categoria", "PTQX Locadora",
                        JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não é possível excluir essa categoria. "
                    + "Pois exise um filme que está usando a mesma", "PTQX Locadora",
                    JOptionPane.WARNING_MESSAGE);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public String resgatarNomeCategoria(int idCat) {
        String titulo = "";
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT idCategoria, nome FROM categoria WHERE idCategoria = ?");
            stmt.setInt(1, idCat);

            rs = stmt.executeQuery();

            while (rs.next()) {
                titulo = rs.getString("nome");
            }

        } catch (SQLException ex) {
            System.err.println("CategoriaDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return titulo;
    }

    public boolean verificarCadastro(String nomeCategoria) {
        //VERIFICAR O NOME QUE VAI SER CADASTRADO
        boolean existe = false;

        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT nome FROM categoria WHERE nome LIKE ?");
            stmt.setString(1, "%" + nomeCategoria + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                existe = true;
            }

        } catch (SQLException ex) {
            System.err.println("CategoriaDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return existe;
    }

}
