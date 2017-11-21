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

    public List<Classificacao> listarClassificacao() {
        List<Classificacao> lista = new ArrayList<>();
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM classificacao");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Classificacao c = new Classificacao();
                c.setId(rs.getInt("idclassi"));
                c.setNome(rs.getString("nome"));

                lista.add(c);
            }

        } catch (SQLException ex) {
            System.err.println("ClassificacaoDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }

        return lista;
    }

    public List<Classificacao> consultaIdClassificacao(String nome) {
        //ConsultaIdClassificacao ou nome?
        List<Classificacao> lista = new ArrayList<>();
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM classificacao WHERE nome = ?");
            stmt.setString(1, nome);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Classificacao c = new Classificacao();
                c.setId(rs.getInt("idclassi"));

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
            stmt.setDouble(2, classi.getPreco());

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

    public void alterarDadosClassificacao(int idclassi, String nome, Double preco) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("UPDATE classificacao SET nome = ?, preco = ? WHERE idclassi = ?");
            stmt.setString(1, nome);
            stmt.setInt(3, idclassi);
            stmt.setDouble(2, preco);

            if (stmt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Os dados foram alterados com sucesso.", "PTQX Locadora",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao tentar salvar as alterações", "PTQX Locadora",
                        JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ClassificacaoDAO: " + ex);
        }
    }

    public List<Classificacao> resgatarDadosClassi(int idclassi) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Classificacao> lista = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM classificacao WHERE idclassi = ?");
            stmt.setInt(1, idclassi);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Classificacao classificacao = new Classificacao();

                classificacao.setId(rs.getInt("idclassi"));
                classificacao.setNome(rs.getString("nome"));
                classificacao.setPreco(rs.getDouble("preco"));
                lista.add(classificacao);

            }

        } catch (SQLException ex) {
            System.out.println("ClassificacaoDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return lista;
    }

    public List<Classificacao> listarIdClassificacao() {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Classificacao> lista = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT idclassi FROM classificacao");
            rs = stmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    Classificacao classificacao = new Classificacao();

                    classificacao.setId(rs.getInt("idclassi"));

                    lista.add(classificacao);
                }

            }
        } catch (SQLException ex) {
            System.err.println("ClassificacaoDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return lista;

    }

    public void excluirClassi(int idClassi) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("DELETE FROM classificacao WHERE idclassi = ?");
            stmt.setInt(1, idClassi);

            if (stmt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Classificação Excluíao com sucesso", "PTQX Locadora",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao tentar excluir a classificação", "PTQX Locadora",
                        JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não é possível excluir essa classificação. "
                    + "Pois exise um filme está usando a mesma", "PTQX Locadora",
                    JOptionPane.WARNING_MESSAGE);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public String resgatarNomeClassi(int idClassi) {
        String titulo = "";
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT idclassi, nome FROM classificacao WHERE idclassi = ?");
            stmt.setInt(1, idClassi);

            rs = stmt.executeQuery();

            while (rs.next()) {
                titulo = rs.getString("nome");
            }

        } catch (SQLException ex) {
            System.err.println("ClassificacaoDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return titulo;
    }
}
