package DAO;

import Modelo.DVD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DVDDAO {

    public void cadastrarDVD(DVD dvd) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO dvd VALUES(0, ?, ?, ?, ?)");
            stmt.setInt(1, dvd.getIdFilme());
            stmt.setDouble(2, dvd.getPreco_compra());
            stmt.setString(3, dvd.getData_compra());
            stmt.setString(4, dvd.getSituacao());

            if (stmt.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "DVD cadastrado com sucesso.", "PTQX Locadora",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao tentar cadastrar o DVD", "PTQX Locadora",
                        JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException ex) {
            System.err.println("DVDDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }

    }

    public int getUltimoCadastro() {
        int id = 0;
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT MAX(iddvd) FROM dvd");
            rs = stmt.executeQuery();

            while (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.err.println("DVDDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return id + 1;
    }

    public List<DVD> mostrarTodosDVD() {
        List<DVD> lista = new ArrayList<>();
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean achou = false;

        try {
            stmt = con.prepareStatement("SELECT d.iddvd,d.preco_compra, d.data_compra, d.situacao, f.titulo\n"
                    + "FROM dvd as d\n"
                    + "JOIN filme as f\n"
                    + "ON d.idfilme = f.idfilme;");
            rs = stmt.executeQuery();

            while (rs.next()) {
                DVD dvd = new DVD();
                dvd.setIddvd(rs.getInt("d.iddvd"));
                dvd.setPreco_compra(rs.getDouble("d.preco_compra"));
                dvd.setSituacao(rs.getString("d.situacao"));
                dvd.setData_compra(rs.getString("d.data_compra"));
                dvd.setTituloFilme(rs.getString("f.titulo"));
                lista.add(dvd);
                achou = true;
            }

            if (!achou) {
                JOptionPane.showMessageDialog(null, "Nenhum DVD cadastrado", "PTQX Locadora",
                        JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException ex) {
            System.err.println("DVDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return lista;
    }

    public List<DVD> buscarPorCodigoDVD(int idDVD) {
        List<DVD> lista = new ArrayList<>();
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean achou = false;

        try {
            stmt = con.prepareStatement("SELECT d.iddvd,d.preco_compra, d.data_compra, d.situacao, f.titulo\n"
                    + "FROM dvd as d\n"
                    + "JOIN filme as f\n"
                    + "ON d.idfilme = f.idfilme AND d.iddvd = ?;");
            stmt.setInt(1, idDVD);
            rs = stmt.executeQuery();

            while (rs.next()) {
                DVD dvd = new DVD();
                dvd.setIddvd(rs.getInt("d.iddvd"));
                dvd.setPreco_compra(rs.getDouble("d.preco_compra"));
                dvd.setSituacao(rs.getString("d.situacao"));
                dvd.setData_compra(rs.getString("d.data_compra"));
                dvd.setTituloFilme(rs.getString("f.titulo"));
                lista.add(dvd);
                achou = true;
            }

            if (!achou) {
                JOptionPane.showMessageDialog(null, "Nenhum DVD com esse código encontrado", "PTQX Locadora",
                        JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException ex) {
            System.err.println("DVDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return lista;
    }

    public List<DVD> buscarPorCodigoFilme(int idFilme) {
        List<DVD> lista = new ArrayList<>();
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean achou = false;

        try {
            stmt = con.prepareStatement("SELECT d.iddvd,d.preco_compra, d.data_compra, d.situacao, f.titulo\n"
                    + "FROM dvd as d\n"
                    + "JOIN filme as f\n"
                    + "ON d.idfilme = f.idfilme AND d.idfilme = ?;");
            stmt.setInt(1, idFilme);
            rs = stmt.executeQuery();

            while (rs.next()) {
                DVD dvd = new DVD();
                dvd.setIddvd(rs.getInt("d.iddvd"));
                dvd.setPreco_compra(rs.getDouble("d.preco_compra"));
                dvd.setSituacao(rs.getString("d.situacao"));
                dvd.setData_compra(rs.getString("d.data_compra"));
                dvd.setTituloFilme(rs.getString("f.titulo"));
                lista.add(dvd);
                achou = true;
            }

            if (!achou) {
                JOptionPane.showMessageDialog(null, "Nenhum filme cadastrado com esse código em um DVD",
                        "PTQX Locadora", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException ex) {
            System.err.println("DVDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return lista;
    }

    public List<DVD> listaIdDVD() {
        List<DVD> lista = new ArrayList<>();
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT iddvd FROM dvd");
            rs = stmt.executeQuery();

            while (rs.next()) {
                DVD d = new DVD();

                d.setIddvd(rs.getInt("iddvd"));

                lista.add(d);

            }

        } catch (SQLException ex) {
            System.err.println("DVDDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return lista;
    }

    public List<DVD> resgatarDadosDvd(int idDvd) {
        List<DVD> lista = new ArrayList<>();
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT d.iddvd, d.situacao, d.preco_compra, d.data_compra, d.situacao, f.titulo\n"
                    + "FROM dvd as d\n"
                    + "JOIN filme as f\n"
                    + "ON d.idfilme = f.idfilme AND d.iddvd = ?");
            stmt.setInt(1, idDvd);
            rs = stmt.executeQuery();

            while (rs.next()) {
                DVD dvd = new DVD();

                dvd.setIddvd(rs.getInt("d.iddvd"));
                dvd.setPreco_compra(rs.getDouble("d.preco_compra"));
                dvd.setSituacao(rs.getString("d.situacao"));
                dvd.setData_compra(rs.getString("d.data_compra"));
                dvd.setTituloFilme(rs.getString("f.titulo"));
                dvd.setSituacao(rs.getString("d.situacao"));

                lista.add(dvd);

            }

        } catch (SQLException ex) {
            System.err.println("DVDDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return lista;

    }

    public List<DVD> alterarDadosDvd(DVD dvd) {
        List<DVD> lista = new ArrayList<>();
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("UPDATE dvd SET preco_compra = ?, data_compra = ?, situacao = ? WHERE iddvd = ?");

            stmt.setDouble(1, dvd.getPreco_compra());
            stmt.setString(2, dvd.getData_compra());
            stmt.setString(3, dvd.getSituacao());
            stmt.setInt(4, dvd.getIddvd());

            if (stmt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Os dados foram alterados com sucesso.", "PTQX Locadora",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao tentar salvar as alterações", "PTQX Locadora",
                        JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException ex) {
            System.err.println("DVDDAO:" + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return lista;
    }

    public String resgatarTituloFilme(int idDvd) {
        String titulo = "";
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT f.titulo\n"
                    + "FROM dvd as d\n"
                    + "JOIN filme as f\n"
                    + "ON d.idfilme = f.idfilme AND d.iddvd = ?");
            stmt.setInt(1, idDvd);
            rs = stmt.executeQuery();

            while (rs.next()) {
                titulo = rs.getString("f.titulo");
            }

        } catch (SQLException ex) {
            System.err.println("DVDDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return titulo;
    }

    public void excluirDvd(int idDvd) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM dvd WHERE iddvd = ?");
            stmt.setInt(1, idDvd);

            if (stmt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "DVD deletado com sucesso", "PTQX Locadora",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao tentar excluir o DVD, tente novamente.",
                        "PTQX Locadora", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Esse DVD está alugado, logo não é possível exclui-lo",
                    "PTQX Locadora", JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexao.closeConnection(con, stmt);
        }

    }

    public boolean testarDvd(int codDvd) {
        boolean existe = false;

        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT iddvd FROM dvd WHERE iddvd = ?");
            stmt.setInt(1, codDvd);
            rs = stmt.executeQuery();

            while (rs.next()) {
                existe = true;
            }

        } catch (SQLException ex) {
            System.err.println("DVDDAO:" + ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return existe;
    }

    public void mudarSituacao(int idDvd, String status) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE dvd SET situacao = ? WHERE iddvd = ?");
            stmt.setString(1, status);
            stmt.setInt(2, idDvd);

            if (stmt.executeUpdate() > 0) {
                System.out.println("Ok");
            } else {
                System.err.println("Erro");
            }

        } catch (SQLException ex) {
            System.err.println("FilmeDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public boolean verificarSituacao(int idDvd) {
        boolean disponivel = false;

        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM dvd WHERE iddvd = ?");
            stmt.setInt(1, idDvd);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                if(rs.getString("situacao").equals("Disponível")){
                    disponivel = true;
                }
            }
            
        } catch (SQLException ex) {
            System.err.println("FilmeDAO: " + ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }

        return disponivel;
    }

}
