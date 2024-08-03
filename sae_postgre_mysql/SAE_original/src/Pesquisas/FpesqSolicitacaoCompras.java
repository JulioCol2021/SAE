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
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TabExpander;
import javax.swing.table.*;
import javax.swing.table.DefaultTableCellRenderer;


/**
 *
 * @author Thiago
 */
public class FpesqSolicitacaoCompras extends javax.swing.JDialog {
    claVariaveis variaveis = new claVariaveis();
    claAcessoDados AcessoDados = new claAcessoDados();
    claFuncoes funcoes = new claFuncoes();
    private ResultSet rs;
    private String retorno;
    public String getRetorno () { return retorno; }


    /** Creates new form FrmUsuario */
    public FpesqSolicitacaoCompras(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        funcoes.F_AtribuirClasse(rootPane);
        btnPesquisaCodigo.setFocusTraversalKeysEnabled(false);
        btnPesquisaDescricao.setFocusTraversalKeysEnabled(false);
    }
   
    public void MontaTituloColunaGrid()
    {
        grDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"Código","Empresa","Solicitante","Data" ,"Solicitação","Situação","Nº O.C",""
            }) { boolean[]  canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        grDados.getColumnModel().getColumn(0).setPreferredWidth(30);
        grDados.getColumnModel().getColumn(1).setPreferredWidth(220);
        grDados.getColumnModel().getColumn(2).setPreferredWidth(210);
        grDados.getColumnModel().getColumn(3).setPreferredWidth(65);
        grDados.getColumnModel().getColumn(4).setPreferredWidth(50);
        grDados.getColumnModel().getColumn(5).setPreferredWidth(120);
        grDados.getColumnModel().getColumn(6).setPreferredWidth(20);
        grDados.getColumnModel().getColumn(7).setPreferredWidth(10);


        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();

        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);


        ButtonColumn buttonColumn = new ButtonColumn(grDados, 7);

    }

    public void MontaGridCodigo()
    {
        String vmCampos = "com_codigo_id, emp_nome, func_nome, com_data, com_numero_solicitacao, com_situacao, COALESCE(cot_codigo_id,0) as cot_codigo_id";
      
        String vmCondicao_Consulta = "";
        if (!txtCodigo.getText().equals(""))
            vmCondicao_Consulta = " left join funcionarios on (func_codigo_id = com_func_codigo_id) left join cotacao on (cot_numero_solicitacao = com_numero_solicitacao)" +
                                  " left join empresas on (emp_codigo_id = com_emp_codigo_id) where com_codigo_id =  " + txtCodigo.getText() + " order by com_codigo_id";
        else
            vmCondicao_Consulta = " left join funcionarios on (func_codigo_id = com_func_codigo_id) left join cotacao on (cot_numero_solicitacao = com_numero_solicitacao)" +
                                  " left join empresas on (emp_codigo_id = com_emp_codigo_id) order by com_codigo_id";
        
        try
        {
        rs = AcessoDados.Selecao("solicitacao_compras", vmCampos, vmCondicao_Consulta);
        MontaTituloColunaGrid();
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
        while (rs.next())
        {
            dtm.addRow(new Object[]{rs.getString("com_codigo_id"),
                                    rs.getString("emp_nome"),rs.getString("func_nome"),
                                    funcoes.formataData(rs.getString("com_data")),
                                    rs.getString("com_numero_solicitacao"),
                                    rs.getString("com_situacao"),
                                    rs.getString("cot_codigo_id"),
                                    ""});
        }
        rs.close();
        
         }catch (Exception e) {
           e.printStackTrace();

        }

    }

    public void MontaGridDescricao()
    {

        String vmCampos = "com_codigo_id, emp_nome, func_nome, com_data, com_numero_solicitacao, com_situacao, COALESCE(cot_codigo_id,0) as cot_codigo_id";

        String vmCondicao_Consulta = "";
        if (!txtDescricao.getText().equals(""))
            vmCondicao_Consulta = " inner join funcionarios on (func_codigo_id = com_func_codigo_id) left join cotacao on (cot_numero_solicitacao = com_numero_solicitacao)" +
                                  " inner join empresas on (emp_codigo_id = com_emp_codigo_id) where emp_nome like '" + txtDescricao.getText() + "%' order by com_codigo_id";
        else
            vmCondicao_Consulta = " inner join funcionarios on (func_codigo_id = com_func_codigo_id) left join cotacao on (cot_numero_solicitacao = com_numero_solicitacao) " +
                                  " inner join empresas on (emp_codigo_id = com_emp_codigo_id) order by com_codigo_id";

        try
        {
        rs = AcessoDados.Selecao("solicitacao_compras", vmCampos, vmCondicao_Consulta);
        MontaTituloColunaGrid();
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
        while (rs.next())
        {
            dtm.addRow(new Object[]{rs.getString("com_codigo_id"),
                                    rs.getString("emp_nome"),rs.getString("func_nome"),
                                    funcoes.formataData(rs.getString("com_data")),
                                    rs.getString("com_numero_solicitacao"),
                                    rs.getString("com_situacao"),
                                    rs.getString("cot_codigo_id"),
                                    ""});
        }
        rs.close();

         }catch (Exception e) {
           e.printStackTrace();
        }

    }

   public void MontaGridControleInterno()
    {

        String vmCampos = "com_codigo_id, emp_nome, func_nome, com_data, com_numero_solicitacao, com_situacao, COALESCE(cot_codigo_id,0) as cot_codigo_id";

        String vmCondicao_Consulta = "";
        if (!txtControleInterno.getText().equals(""))
            vmCondicao_Consulta = " inner join funcionarios on (func_codigo_id = com_func_codigo_id) left join cotacao on (cot_numero_solicitacao = com_numero_solicitacao) " +
                                  " inner join empresas on (emp_codigo_id = com_emp_codigo_id) where com_numero_solicitacao = '" + txtControleInterno.getText() + "' order by com_codigo_id";
        else
            vmCondicao_Consulta = " inner join funcionarios on (func_codigo_id = com_func_codigo_id) left join cotacao on (cot_numero_solicitacao = com_numero_solicitacao)" +
                                  " inner join empresas on (emp_codigo_id = com_emp_codigo_id) order by com_codigo_id";

        try
        {
        rs = AcessoDados.Selecao("solicitacao_compras", vmCampos, vmCondicao_Consulta);
        MontaTituloColunaGrid();
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
        while (rs.next())
        {
         dtm.addRow(new Object[]{rs.getString("com_codigo_id"),
                                    rs.getString("emp_nome"),rs.getString("func_nome"),
                                    funcoes.formataData(rs.getString("com_data")),
                                    rs.getString("com_numero_solicitacao"),
                                    rs.getString("com_situacao"),
                                    rs.getString("cot_codigo_id"),
                                    ""});
        }
        rs.close();

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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtCodigo = new javax.swing.JTextField();
        btnPesquisaCodigo = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtDescricao = new javax.swing.JTextField();
        btnPesquisaDescricao = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txtControleInterno = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisa Solictação de Compras");
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

        jTabbedPane1.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N

        txtCodigo.setColumns(8);
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        btnPesquisaCodigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Pesquisar.gif"))); // NOI18N
        btnPesquisaCodigo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPesquisaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(766, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPesquisaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Código", jPanel1);

        txtDescricao.setColumns(30);
        txtDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescricaoKeyReleased(evt);
            }
        });

        btnPesquisaDescricao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Pesquisar.gif"))); // NOI18N
        btnPesquisaDescricao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPesquisaDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaDescricaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPesquisaDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(168, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPesquisaDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Empresa", jPanel3);

        txtControleInterno.setColumns(20);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Pesquisar.gif"))); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtControleInterno, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(647, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtControleInterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Nº Solicitação", jPanel4);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 918, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    }//GEN-LAST:event_grDadosMouseClicked

    private void grDadosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grDadosKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_grDadosKeyReleased

    private void btnPesquisaCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaCodigoActionPerformed
        // TODO add your handling code here:
        MontaGridCodigo();
    }//GEN-LAST:event_btnPesquisaCodigoActionPerformed

    private void btnPesquisaDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaDescricaoActionPerformed
        // TODO add your handling code here:
        MontaGridDescricao();
    }//GEN-LAST:event_btnPesquisaDescricaoActionPerformed

    private void txtDescricaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescricaoKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_txtDescricaoKeyReleased

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        MontaGridControleInterno();
    }//GEN-LAST:event_jButton1ActionPerformed

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

   class ButtonColumn extends AbstractCellEditor
        implements TableCellRenderer, TableCellEditor, ActionListener
    {
        JTable table;
        JButton renderButton;
        JButton renderButton1;
        JButton editButton;
        Icon imgExcluir;
        String text;

        public ButtonColumn(JTable table, int column)
        {
            super();
            //btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Localizar.gif")));;;
            this.table = table;


            imgExcluir = new ImageIcon("..MTDS/Imagens/confirma.gif");
            renderButton = new JButton(imgExcluir);
            renderButton.setBorderPainted(false);
            renderButton.setToolTipText("Retornar Pesquisa");
            renderButton.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
            renderButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/confirma.gif")));

            editButton = new JButton();
            editButton.setFocusPainted( false );
            editButton.addActionListener( this );
            editButton.setBorderPainted(false);
            editButton.setToolTipText("Retornar Pesquisa");
            editButton.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
            editButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/confirma.gif")));


            TableColumnModel columnModel = table.getColumnModel();
            columnModel.getColumn(column).setCellRenderer( this );
            columnModel.getColumn(column).setCellEditor( this );
        }

        public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            if (hasFocus)
            {
                renderButton.setForeground(table.getForeground());
                renderButton.setBackground(UIManager.getColor("Button.background"));
            }
            else if (isSelected)
            {
                renderButton.setForeground(table.getSelectionForeground());
                 renderButton.setBackground(table.getSelectionBackground());
            }
            else
            {
                renderButton.setForeground(table.getForeground());
                renderButton.setBackground(UIManager.getColor("Button.background"));
            }

            renderButton.setText( (value == "") ? "" : value.toString() );
            return renderButton;
        }

        public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column)
        {
            text = (value == "") ? "" : value.toString();
            editButton.setText( text );
            return editButton;
        }

        public Object getCellEditorValue()
        {
            return text;
        }

        public void actionPerformed(ActionEvent e)
        {
            fireEditingStopped();
            System.out.println( e.getActionCommand() + " : " + table.getSelectedRow());
            retorno = grDados.getValueAt(grDados.getSelectedRow(), 0).toString();
            dispose();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPesquisaCodigo;
    private javax.swing.JButton btnPesquisaDescricao;
    private javax.swing.JTable grDados;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtControleInterno;
    private javax.swing.JTextField txtDescricao;
    // End of variables declaration//GEN-END:variables

}
