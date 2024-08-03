/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmUsuario.java
 *
 * Created on 02/01/2010, 13:07:26
 */

package Pesquisas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import Classes.claAcessoDados;
import Classes.claVariaveis;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import Classes.claVariaveis;
import Classes.claAcessoDados;
import javax.swing.table.DefaultTableCellRenderer;
import sae.FrmUsuario;
import Classes.claFuncoes;



/**
 *
 * @author Thiago
 */
public class FpesqTributos extends javax.swing.JDialog {
    claVariaveis variaveis = new claVariaveis();
    claAcessoDados AcessoDados = new claAcessoDados();
    claFuncoes funcoes = new claFuncoes();
    private ResultSet rs;
    private String retorno;
    public String getRetorno () { return retorno; }


    /** Creates new form FrmUsuario */
    public FpesqTributos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        HashSet conj = new HashSet(this.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        this.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
        registraEnterNoBotao(btnPesquisaCodigo);
        registraEnterNoBotao(btnPesquisaDescricao);
        registraEnterNoBotao(btnPesquisaCST);
        btnPesquisaCodigo.setFocusTraversalKeysEnabled(false);
        btnPesquisaDescricao.setFocusTraversalKeysEnabled(false);
        btnPesquisaCST.setFocusTraversalKeysEnabled(false);
    }
   
    private void registraEnterNoBotao(JButton b) {
        b.registerKeyboardAction(
                b.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                JComponent.WHEN_FOCUSED);

        b.registerKeyboardAction(
                b.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                JComponent.WHEN_FOCUSED);
    }
    public void RetornaDadosGrid()
    {
       
    }
    public void MontaGridCodigo()
    {

        String vmCampos = "*";
      
        String vmCondicao_Consulta = "";
        if (!txtCodigo.getText().equals(""))
            vmCondicao_Consulta = " where trib_codigo_id = " + txtCodigo.getText() + " order by trib_codigo_id";
        else
            vmCondicao_Consulta = " order by trib_codigo_id";
        
        try
        {
        rs = AcessoDados.Selecao("tributos", vmCampos, vmCondicao_Consulta);
        grDados.setModel(new javax.swing.table.DefaultTableModel(
                      new Object [][] { },
                      new String [] {"Código ","Descrição","Classificação","Cód. Fiscal","CST"}));
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
        while (rs.next())
        {
            dtm.addRow(new Object[]{rs.getString("trib_codigo_id"),rs.getString("trib_descricao"),rs.getString("trib_classificacao_fiscal"),rs.getString("trib_codigo_fiscal"),rs.getString("trib_cst")});
        }
        rs.close();
        grDados.getColumnModel().getColumn(0).setPreferredWidth(10);
        grDados.getColumnModel().getColumn(1).setPreferredWidth(250);
        grDados.getColumnModel().getColumn(2).setPreferredWidth(50);
        grDados.getColumnModel().getColumn(3).setPreferredWidth(15);
        grDados.getColumnModel().getColumn(4).setPreferredWidth(10);

        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();

        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);

        grDados.getColumnModel().getColumn(2).setCellRenderer(centralizado);
        grDados.getColumnModel().getColumn(3).setCellRenderer(centralizado);
        grDados.getColumnModel().getColumn(4).setCellRenderer(centralizado);

        }catch (Exception e) {
           e.printStackTrace();
        }


    }
    public void MontaGridDescricao()
    {

        String vmCampos = "*";
        String vmCondicao_Consulta = "";
        if (!txtDescricao.getText().equals(""))
            vmCondicao_Consulta = " where trib_descricao like '" + txtDescricao.getText() + "%' order by trib_codigo_id";
        else
            vmCondicao_Consulta = " order by trib_codigo_id";

        try
        {
        rs = AcessoDados.Selecao("tributos", vmCampos, vmCondicao_Consulta);
        grDados.setModel(new javax.swing.table.DefaultTableModel(
                      new Object [][] { },
                      new String [] {"Código ","Descrição","Classificação","Cód. Fiscal","CST"}));
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
        while (rs.next())
        {
            dtm.addRow(new Object[]{rs.getString("trib_codigo_id"),rs.getString("trib_descricao"),rs.getString("trib_classificacao_fiscal"),rs.getString("trib_codigo_fiscal"),rs.getString("trib_cst")});
        }
        rs.close();
        grDados.getColumnModel().getColumn(0).setPreferredWidth(10);
        grDados.getColumnModel().getColumn(1).setPreferredWidth(250);
        grDados.getColumnModel().getColumn(2).setPreferredWidth(50);
        grDados.getColumnModel().getColumn(3).setPreferredWidth(15);
        grDados.getColumnModel().getColumn(4).setPreferredWidth(10);

        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();

        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);

        grDados.getColumnModel().getColumn(2).setCellRenderer(centralizado);
        grDados.getColumnModel().getColumn(3).setCellRenderer(centralizado);
        grDados.getColumnModel().getColumn(4).setCellRenderer(centralizado);

        }catch (Exception e) {
           e.printStackTrace();
        }
    }
    public void MontaGridCST()
    {

        String vmCampos = "*";
        String vmCondicao_Consulta = "";
        if (!txtCST.getText().equals(""))
            vmCondicao_Consulta = " where trib_cst = '" + txtCST.getText() + "' order by trib_codigo_id";
        else
            vmCondicao_Consulta = " order by trib_codigo_id";

        try
        {
        rs = AcessoDados.Selecao("tributos", vmCampos, vmCondicao_Consulta);
        grDados.setModel(new javax.swing.table.DefaultTableModel(
                      new Object [][] { },
                      new String [] {"Código ","Descrição","Classificação","Cód. Fiscal","CST"}));
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
        while (rs.next())
        {
            dtm.addRow(new Object[]{rs.getString("trib_codigo_id"),rs.getString("trib_descricao"),rs.getString("trib_classificacao_fiscal"),rs.getString("trib_codigo_fiscal"),rs.getString("trib_cst")});
        }
        rs.close();
        grDados.getColumnModel().getColumn(0).setPreferredWidth(10);
        grDados.getColumnModel().getColumn(1).setPreferredWidth(250);
        grDados.getColumnModel().getColumn(2).setPreferredWidth(50);
        grDados.getColumnModel().getColumn(3).setPreferredWidth(15);
        grDados.getColumnModel().getColumn(4).setPreferredWidth(10);

        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();

        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);

        grDados.getColumnModel().getColumn(2).setCellRenderer(centralizado);
        grDados.getColumnModel().getColumn(3).setCellRenderer(centralizado);
        grDados.getColumnModel().getColumn(4).setCellRenderer(centralizado);

        }catch (Exception e) {
           e.printStackTrace();
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        grDados = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnOK = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtCodigo = new javax.swing.JTextField();
        btnPesquisaCodigo = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtDescricao = new javax.swing.JTextField();
        btnPesquisaDescricao = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txtCST = new javax.swing.JTextField();
        btnPesquisaCST = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("  (FpesqTributos) - Pesquisa de Tributos");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        grDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        grDados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grDadosMouseClicked(evt);
            }
        });
        grDados.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                grDadosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(grDados);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/cancelar.gif"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnOK, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnOK))
                .addContainerGap())
        );

        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        btnPesquisaCodigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Localizar.gif"))); // NOI18N
        btnPesquisaCodigo.setText("Pesquisar");
        btnPesquisaCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaCodigoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 556, Short.MAX_VALUE)
                .addComponent(btnPesquisaCodigo)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisaCodigo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Código", jPanel1);

        txtDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescricaoKeyReleased(evt);
            }
        });

        btnPesquisaDescricao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Localizar.gif"))); // NOI18N
        btnPesquisaDescricao.setText("Pesquisar");
        btnPesquisaDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaDescricaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnPesquisaDescricao)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisaDescricao))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Descrição", jPanel3);

        txtCST.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCSTKeyReleased(evt);
            }
        });

        btnPesquisaCST.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Localizar.gif"))); // NOI18N
        btnPesquisaCST.setText("Pesquisar");
        btnPesquisaCST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaCSTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(652, Short.MAX_VALUE)
                .addComponent(btnPesquisaCST)
                .addContainerGap())
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txtCST, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(693, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPesquisaCST)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(txtCST, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(15, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("CST", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void limpaCampos(){
         }
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        MontaGridCodigo();
        txtCodigo.requestFocus();
        
    }//GEN-LAST:event_formWindowActivated

    private void grDadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grDadosMouseClicked
        // TODO add your handling code here:
        RetornaDadosGrid();
    }//GEN-LAST:event_grDadosMouseClicked

    private void grDadosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grDadosKeyReleased
        // TODO add your handling code here:
         if ((evt.getKeyCode() ==  java.awt.event.KeyEvent.VK_UP)  || (evt.getKeyCode() ==  java.awt.event.KeyEvent.VK_DOWN)) {
              RetornaDadosGrid();
         };
    }//GEN-LAST:event_grDadosKeyReleased

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
}//GEN-LAST:event_btnCancelarActionPerformed

    private void btnPesquisaCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaCodigoActionPerformed
        // TODO add your handling code here:
        MontaGridCodigo();
    }//GEN-LAST:event_btnPesquisaCodigoActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        // TODO add your handling code here:
        retorno = grDados.getValueAt(grDados.getSelectedRow(), 0).toString();
        this.dispose();
    }//GEN-LAST:event_btnOKActionPerformed

    private void btnPesquisaDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaDescricaoActionPerformed
        // TODO add your handling code here:
        MontaGridDescricao();
    }//GEN-LAST:event_btnPesquisaDescricaoActionPerformed

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtDescricaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescricaoKeyReleased
        // TODO add your handling code here:
        JTextField tf = (JTextField) evt.getComponent();
        if (evt.getKeyCode()!=32 & evt.getKeyCode()!=8 & evt.getKeyCode()!=37 & evt.getKeyCode()!=39)
            tf.setText(tf.getText().toUpperCase());
    }//GEN-LAST:event_txtDescricaoKeyReleased

    private void txtCSTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCSTKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCSTKeyReleased

    private void btnPesquisaCSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaCSTActionPerformed
        // TODO add your handling code here:
        MontaGridCST();
    }//GEN-LAST:event_btnPesquisaCSTActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmUsuario dialog = new FrmUsuario(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnOK;
    private javax.swing.JButton btnPesquisaCST;
    private javax.swing.JButton btnPesquisaCodigo;
    private javax.swing.JButton btnPesquisaDescricao;
    private javax.swing.JTable grDados;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField txtCST;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescricao;
    // End of variables declaration//GEN-END:variables

}
