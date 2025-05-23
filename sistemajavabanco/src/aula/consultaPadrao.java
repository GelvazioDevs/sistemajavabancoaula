package aula;

import viewconsulta.*;
import model.Pessoa;
import model.Cliente;
import controller.ControllerPessoa;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author Gelvazio Camargo
 */
public class consultaPadrao extends javax.swing.JDialog {

    int BUSCAR_TODOS_CLIENTES = 0;

    
    /**
     * Creates new form consProduto
     */
    public consultaPadrao(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.filtraTodosRegistros(BUSCAR_TODOS_CLIENTES);
    }

    public void filtraTodosRegistros(int codigo) {
        // Seta os clientes do banco de dados na consulta
        ControllerPessoa controllerPessoa = new ControllerPessoa();
        String sqlPessoa = "select * from pessoa where codigo = " + codigo + " order by codigo";
        if (codigo == BUSCAR_TODOS_CLIENTES) {
            sqlPessoa = "select * from pessoa order by codigo";
        }

        DefaultTableModel model = (DefaultTableModel) tabelaConsultaClientes.getModel();
        // Limpa a tabela
        model.setRowCount(0);

        ArrayList<Pessoa> listaPessoas = controllerPessoa.getAll(sqlPessoa);
        for (Pessoa auxPessoa : listaPessoas) {
            model.addRow(new Object[]{
                auxPessoa.getCodigo(),
                auxPessoa.getNome(),
                auxPessoa.getCpf(),
                auxPessoa.getEndereco()
            });
        }
    }

    public void filtraClientesPorNome(String nome) {
        // Seta os clientes do banco de dados na consulta
        ControllerPessoa controllerPessoa = new ControllerPessoa();
        String sqlPessoa = "select * from pessoa where nome ilike '%" + nome + "%' order by codigo";

        DefaultTableModel model = (DefaultTableModel) tabelaConsultaClientes.getModel();
        // Limpa a tabela
        model.setRowCount(0);

        ArrayList<Pessoa> listaPessoas = controllerPessoa.getAll(sqlPessoa);
        for (Pessoa auxPessoa : listaPessoas) {
            model.addRow(new Object[]{
                auxPessoa.getCodigo(),
                auxPessoa.getNome(),
                auxPessoa.getCpf(),
                auxPessoa.getEndereco()
            });
        }
    }

    public int codigo;
    public String nome;

    Cliente cli = new Cliente();

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator8 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaConsultaClientes = new javax.swing.JTable();
        edtConsulta = new javax.swing.JTextField();
        botaoTodos = new javax.swing.JRadioButton();
        botaoNome = new javax.swing.JRadioButton();
        botaoCodigo = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 620, 10));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Consulta de Clientes");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 660, -1));

        tabelaConsultaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "CPF", "Endereço"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaConsultaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaConsultaClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaConsultaClientes);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 640, 340));

        edtConsulta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtConsultaKeyPressed(evt);
            }
        });
        getContentPane().add(edtConsulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 620, -1));

        botaoTodos.setSelected(true);
        botaoTodos.setText("Todos");
        botaoTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoTodosActionPerformed(evt);
            }
        });
        getContentPane().add(botaoTodos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        botaoNome.setText("Nome");
        botaoNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoNomeActionPerformed(evt);
            }
        });
        getContentPane().add(botaoNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, -1, -1));

        botaoCodigo.setText("Código");
        botaoCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCodigoActionPerformed(evt);
            }
        });
        getContentPane().add(botaoCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, -1, -1));

        jButton1.setText("Pesquisar");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabelaConsultaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaConsultaClientesMouseClicked
        codigo = Integer.parseInt(String.valueOf(tabelaConsultaClientes.getModel().getValueAt(tabelaConsultaClientes.getSelectedRow(), 0)));
        nome = String.valueOf(tabelaConsultaClientes.getModel().getValueAt(tabelaConsultaClientes.getSelectedRow(), 1));
        dispose();
    }//GEN-LAST:event_tabelaConsultaClientesMouseClicked

    private void edtConsultaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtConsultaKeyPressed

        JOptionPane.showMessageDialog(rootPane, "Consultando clientes....");

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            DefaultTableModel model = (DefaultTableModel) tabelaConsultaClientes.getModel();
            model.setNumRows(0);
            cli.setListaCli(cli.retornaClientes());
            if (botaoTodos.isSelected()) { //Todos
                filtraTodosRegistros(BUSCAR_TODOS_CLIENTES);
            } else if (botaoCodigo.isSelected()) { //Código
                // Pega o codigo da Consulta
                int codigoPesquisa = Integer.parseInt(edtConsulta.getText());
                filtraTodosRegistros(codigoPesquisa);
            } else if (botaoNome.isSelected()) { //Descrição
                String nomeFiltrado = edtConsulta.getText().toUpperCase();
                filtraClientesPorNome(nomeFiltrado);
            }
        }
    }//GEN-LAST:event_edtConsultaKeyPressed

    private void botaoTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoTodosActionPerformed

        if (botaoTodos.isSelected()) {
            botaoNome.setSelected(false);
            botaoCodigo.setSelected(false);
            filtraTodosRegistros(BUSCAR_TODOS_CLIENTES);
        } else {
            botaoNome.setSelected(true);
        }

        edtConsulta.requestFocus();
        edtConsulta.selectAll();
    }//GEN-LAST:event_botaoTodosActionPerformed

    private void botaoCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCodigoActionPerformed

        if (botaoCodigo.isSelected()) {
            botaoTodos.setSelected(false);
            botaoNome.setSelected(false);
        } else {
            botaoNome.setSelected(true);
        }

        edtConsulta.requestFocus();
        edtConsulta.selectAll();
    }//GEN-LAST:event_botaoCodigoActionPerformed

    private void botaoNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNomeActionPerformed

        if (botaoNome.isSelected()) {
            botaoTodos.setSelected(false);
            botaoCodigo.setSelected(false);
        } else {
            botaoTodos.setSelected(true);
        }

        edtConsulta.requestFocus();
        edtConsulta.selectAll();
    }//GEN-LAST:event_botaoNomeActionPerformed

    public void atualizaConsulta(String tipoConsulta){
        switch (tipoConsulta) {
            case "TIPO_CONSULTA_CODIGO":
                
                break;
            default:
                throw new AssertionError();
        }
    }
    
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
            java.util.logging.Logger.getLogger(consultaPadrao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(consultaPadrao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(consultaPadrao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(consultaPadrao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                consultaPadrao dialog = new consultaPadrao(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton botaoCodigo;
    private javax.swing.JRadioButton botaoNome;
    private javax.swing.JRadioButton botaoTodos;
    private javax.swing.JTextField edtConsulta;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTable tabelaConsultaClientes;
    // End of variables declaration//GEN-END:variables
}
