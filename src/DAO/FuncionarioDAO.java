package DAO;

import Modelo.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class FuncionarioDAO {

    public boolean checkLogin(String login, String senha) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            stmt = con.prepareStatement("SELECT * FROM funcionario WHERE login = ?"
                    + " AND senha = ?");

            stmt.setString(1, login);
            stmt.setString(2, senha);

            rs = stmt.executeQuery();

            if (rs.next()) {
                check = true;
            }

        } catch (SQLException ex) {
            System.err.println("Erro FuncionarioDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return check;
    }

    public void cadastrarFuncionario(String nome, String login, String senha) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO funcionario VALUES(null, ?, ?, ?)");
            stmt.setString(1, nome);
            stmt.setString(2, login);
            stmt.setString(3, senha);

            if (stmt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso", "PTQX Locadora",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao efetuar o cadastro", "PTQX Locadora",
                        JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException ex) {
            System.err.println("Erro no cadastro FuncionarioDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }

    }

    public List<Funcionario> listarIdFuncionario() {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Funcionario> lista = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT idFuncionario FROM funcionario");
            rs = stmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    Funcionario funcionario = new Funcionario();

                    funcionario.setId(rs.getInt("idFuncionario"));

                    lista.add(funcionario);
                }

            }
        } catch (SQLException ex) {
            System.err.println("FuncionarioDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return lista;

    }

    public int getUltimoCadastro() {
        int codigo = 0;
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            //idFuncionario padrão
            stmt = con.prepareStatement("SELECT MAX(idFuncionario) FROM funcionario");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario func = new Funcionario();
                codigo = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("Funcionario DAO: " + ex);
        }

        return (codigo + 1);

    }

    public List<Funcionario> resgatarDadosFunc(int id) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Funcionario> lista = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM funcionario WHERE idFuncionario = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();

                funcionario.setNome(rs.getString("nome"));
                funcionario.setLogin(rs.getString("login"));
                funcionario.setSenha(rs.getString("senha"));

                lista.add(funcionario);

            }

        } catch (SQLException ex) {
            System.out.println("FuncionarioDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return lista;
    }

    public void alterarDadosFuncionario(int id, String nome, String login, String senha) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("UPDATE funcionario SET nome = ?, login = ?, senha = ? WHERE idFuncionario = ?");
            stmt.setString(1, nome);
            stmt.setString(2, login);
            stmt.setString(3, senha);
            stmt.setInt(4, id);

            if (stmt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Os dados foram alterados com sucesso.", "PTQX Locadora",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao tentar salvar as alterações", "PTQX Locadora",
                        JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException ex) {
            System.err.println("Erro FuncionarioDAO: " + ex);
        }
    }

    public String mostrarNomeFuncionario(int idFuncionario) {
        String nome = "";
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT nome FROM funcionario WHERE idFuncionario = ?");
            stmt.setInt(1, idFuncionario);
            rs = stmt.executeQuery();

            while (rs.next()) {
                nome = rs.getString("nome");
            }
        } catch (SQLException ex) {
            System.err.println("FuncionarioDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return nome;
    }

    public void excluirFuncionario(int idFuncionario) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM funcionario WHERE idFuncionario = ?");
            stmt.setInt(1, idFuncionario);

            if (stmt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Funcionario deletado com sucesso", "PTQX Locadora",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao tentar excluir o funcionario, tente novamente.",
                        "PTQX Locadora", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            System.err.println("FuncionarioDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public List<Funcionario> mostrarTodos() {
        List<Funcionario> lista = new ArrayList<>();
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT idFuncionario, nome FROM funcionario");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setNome(rs.getString("nome"));
                f.setId(rs.getInt("idFuncionario"));

                lista.add(f);
            }

        } catch (SQLException ex) {
            System.err.println("FuncionarioDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return lista;
    }

    public List<Funcionario> buscarId(int idFuncionario) {
        boolean achou = false;
        List<Funcionario> lista = new ArrayList<>();
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT idFuncionario, nome FROM funcionario WHERE idFuncionario = ?");
            stmt.setInt(1, idFuncionario);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setNome(rs.getString("nome"));
                f.setId(rs.getInt("idFuncionario"));
                achou = true;
                lista.add(f);
            }

        } catch (SQLException ex) {
            System.err.println("FuncionarioDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        
        if(!achou){
            JOptionPane.showMessageDialog(null, "Nenhum funcionário com esse código foi encontrado",
                    "PTQX Locadora", JOptionPane.WARNING_MESSAGE);
        }
        
        return lista;
    }
    
    public List<Funcionario> buscarNome(String nome) {
        boolean achou = false;
        List<Funcionario> lista = new ArrayList<>();
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT idFuncionario, nome FROM funcionario WHERE nome LIKE ?");
            stmt.setString(1, '%'+nome+'%');
            rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setNome(rs.getString("nome"));
                f.setId(rs.getInt("idFuncionario"));
                achou = true;
                lista.add(f);
            }

        } catch (SQLException ex) {
            System.err.println("FuncionarioDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        
        if(!achou){
            JOptionPane.showMessageDialog(null, "Nenhum funcionário com esse nome foi encontrado",
                    "PTQX Locadora", JOptionPane.WARNING_MESSAGE);
        }
        
        return lista;
    }
}
