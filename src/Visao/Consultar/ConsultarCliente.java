/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.Consultar;

import DAO.ClienteDAO;
import Modelo.Cliente;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Familia
 */
public class ConsultarCliente extends javax.swing.JFrame {

    ClienteDAO dao = new ClienteDAO();

    public ConsultarCliente() {
        initComponents();
        DefaultTableModel tabelaConsulta = (DefaultTableModel) jtTabelaClientes.getModel();
        jtTabelaClientes.setRowSorter(new TableRowSorter(tabelaConsulta));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtBuscaNome = new javax.swing.JTextField();
        btnBuscarNome = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtBuscaId = new javax.swing.JTextField();
        btnBuscarId = new javax.swing.JButton();
        btnBuscarTodos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtTabelaClientes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PTQX Locadora - Consulta de Clientes");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/Icons/if_video_1054941.png")).getImage());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 102, 255));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Consulta de Clientes");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jLabel2.setText("Pesquisa por nome");

        txtBuscaNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscaNomeKeyPressed(evt);
            }
        });

        btnBuscarNome.setBackground(new java.awt.Color(0, 102, 255));
        btnBuscarNome.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarNome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/magnifier.png"))); // NOI18N
        btnBuscarNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarNomeActionPerformed(evt);
            }
        });

        jLabel3.setText("Pesquisa por código");

        txtBuscaId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscaIdKeyPressed(evt);
            }
        });

        btnBuscarId.setBackground(new java.awt.Color(0, 102, 255));
        btnBuscarId.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarId.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/magnifier.png"))); // NOI18N
        btnBuscarId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarIdActionPerformed(evt);
            }
        });

        btnBuscarTodos.setBackground(new java.awt.Color(0, 102, 255));
        btnBuscarTodos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnBuscarTodos.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarTodos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/magnifier.png"))); // NOI18N
        btnBuscarTodos.setText("Todos");
        btnBuscarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarTodosActionPerformed(evt);
            }
        });

        jtTabelaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Cliente", "RG", "CPF", "Telefone", "Email"
            }
        ));
        jScrollPane1.setViewportView(jtTabelaClientes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarNome, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscaId, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscarId, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                        .addComponent(btnBuscarTodos)
                        .addGap(12, 12, 12))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnBuscarNome, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtBuscaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBuscaId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addComponent(btnBuscarId)
                    .addComponent(btnBuscarTodos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarIdActionPerformed
        buscaPorId();
         txtBuscaId.setText("");
    }//GEN-LAST:event_btnBuscarIdActionPerformed

    private void btnBuscarNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarNomeActionPerformed
        buscaPorNome();
        txtBuscaNome.setText("");
    }//GEN-LAST:event_btnBuscarNomeActionPerformed

    private void btnBuscarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarTodosActionPerformed
        mostrarTodosClientes();
    }//GEN-LAST:event_btnBuscarTodosActionPerformed

    private void txtBuscaNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaNomeKeyPressed
        if (evt.getKeyCode() == 10) {
            buscaPorNome();
            txtBuscaNome.setText("");
        }
    }//GEN-LAST:event_txtBuscaNomeKeyPressed

    private void txtBuscaIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaIdKeyPressed
        if (evt.getKeyCode() == 10) {
            buscaPorId();
            txtBuscaId.setText("");
        }
    }//GEN-LAST:event_txtBuscaIdKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConsultarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultarCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarId;
    private javax.swing.JButton btnBuscarNome;
    private javax.swing.JButton btnBuscarTodos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtTabelaClientes;
    private javax.swing.JTextField txtBuscaId;
    private javax.swing.JTextField txtBuscaNome;
    // End of variables declaration//GEN-END:variables

    public void buscaPorId() {
        DefaultTableModel tabelaConsulta = (DefaultTableModel) jtTabelaClientes.getModel();
        tabelaConsulta.setNumRows(0);
        int id = Integer.parseInt(txtBuscaId.getText());

        for (Cliente c : dao.buscaPorId(id)) {
            tabelaConsulta.addRow(new Object[]{
                c.getId(),
                c.getNome(),
                c.getRg(),
                c.getCpf(),
                c.getTelefone(),
                c.getEmail()
            });
        }
    }

    public void buscaPorNome() {
        DefaultTableModel tabelaConsulta = (DefaultTableModel) jtTabelaClientes.getModel();
        tabelaConsulta.setNumRows(0);
        String nomeBusca = txtBuscaNome.getText();

        for (Cliente c : dao.buscaPorNome(nomeBusca)) {
            tabelaConsulta.addRow(new Object[]{
                c.getId(),
                c.getNome(),
                c.getRg(),
                c.getCpf(),
                c.getTelefone(),
                c.getEmail()
            });
        }
    }

    public void mostrarTodosClientes() {
        DefaultTableModel tabelaConsulta = (DefaultTableModel) jtTabelaClientes.getModel();
        tabelaConsulta.setNumRows(0);

        for (Cliente c : dao.mostrarTodosClientes()) {
            tabelaConsulta.addRow(new Object[]{
                c.getId(),
                c.getNome(),
                c.getRg(),
                c.getCpf(),
                c.getTelefone(),
                c.getEmail()
            });
        }
    }

}
