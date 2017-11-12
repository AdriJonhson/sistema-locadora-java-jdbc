package DAO;

import Modelo.Filme;
import Modelo.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class FilmeDAO {

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
}
