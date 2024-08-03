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
import sae.FrmUsuario;
import Classes.claFuncoes;
import Classes.CellTableZebra;
import Classes.SelectAllTheThings;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TabExpander;
import javax.swing.table.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.net.*;




/**
 *
 * @author Thiago
 */
public class FpesqProduto extends javax.swing.JDialog {
    claVariaveis variaveis = new claVariaveis();
    claAcessoDados AcessoDados = new claAcessoDados();
    claFuncoes funcoes = new claFuncoes();
    private ResultSet rs;
    private String retorno;
    public String getRetorno () { return retorno; }


    /** Creates new form FrmUsuario */
    public FpesqProduto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        funcoes.F_AtribuirClasse(rootPane);
        btnPesquisaCodigo.setFocusTraversalKeysEnabled(false);
        btnPesquisaDescricao.setFocusTraversalKeysEnabled(false);
        btnPesquisaGenerica.setFocusTraversalKeysEnabled(false);
        btnPesquisaEAN.setFocusTraversalKeysEnabled(false);
        
    }
   
   
    public void RetornaDadosGrid()
    {
       
    }

    public void MontaTituloColunaGrid()
    {
        grDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
             new String [] {"Seq.","Código Produto","Descrição",""
            }) { boolean[]  canEdit = new boolean [] {
                false, false, false, true
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        grDados.getColumnModel().getColumn(0).setPreferredWidth(20);
        grDados.getColumnModel().getColumn(1).setPreferredWidth(40);
        grDados.getColumnModel().getColumn(2).setPreferredWidth(490);
        grDados.getColumnModel().getColumn(3).setPreferredWidth(20);
        
        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();

        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);

        ButtonColumn buttonColumn = new ButtonColumn(grDados, 3);

    }
    public void MontaGridCodigo()
    {

        String vmCampos = "*";
      
        String vmCondicao_Consulta = "";
        if (!txtCodigo.getText().equals(""))
            vmCondicao_Consulta = " where prod_codigo_produto like '" + txtCodigo.getText() + "%' order by prod_codigo_id";
        else
            vmCondicao_Consulta = " order by prod_codigo_id";
        
        try
        {
        rs = AcessoDados.Selecao("produtos", vmCampos, vmCondicao_Consulta);
        MontaTituloColunaGrid();
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
        while (rs.next())
        {
            dtm.addRow(new Object[]{rs.getString("prod_codigo_id"),rs.getString("prod_codigo_produto"),rs.getString("prod_descricao"),""});
        }
        rs.close();
        
           }catch (Exception e) {
           e.printStackTrace();
        }


    }
    public void MontaGridDescricao()
    {

        String vmCampos = "*";
        String vmCondicao_Consulta = "";
        if (!txtDescricao.getText().equals(""))
            vmCondicao_Consulta = " where prod_descricao like '" + txtDescricao.getText() + "%' order by prod_codigo_id";
        else
            vmCondicao_Consulta = " order by prod_codigo_id";

        try
        {
        rs = AcessoDados.Selecao("produtos", vmCampos, vmCondicao_Consulta);
        MontaTituloColunaGrid();
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
        while (rs.next())
        {
            dtm.addRow(new Object[]{rs.getString("prod_codigo_id"),rs.getString("prod_codigo_produto"),rs.getString("prod_descricao"),""});
        }
        rs.close();
        

           }catch (Exception e) {
           e.printStackTrace();
        }

    }
    
    public void MontaGridEAN()
    {

         String vmCampos = "*";

        String vmCondicao_Consulta = "";
        if (!txtEAN.getText().equals(""))
            vmCondicao_Consulta = " where prod_codigo_ean like '" + txtEAN.getText() + "%' order by prod_codigo_id";
        else
            vmCondicao_Consulta = " order by prod_codigo_id";

        try
        {
        rs = AcessoDados.Selecao("produtos", vmCampos, vmCondicao_Consulta);
        MontaTituloColunaGrid();
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
        while (rs.next())
        {
            dtm.addRow(new Object[]{rs.getString("prod_codigo_id"),rs.getString("prod_codigo_produto"),rs.getString("prod_descricao"),""});
        }
        rs.close();
    

           }catch (Exception e) {
           e.printStackTrace();
        }

    }

    public void MontaGridDescricaoGenerica()
    {

        String vmCampos = "*";
        String vmCondicao_Consulta = "";
        if (!txtDescricaoGenerica.getText().equals(""))
            vmCondicao_Consulta = " where prod_descricao_generica like '" + txtDescricaoGenerica.getText() + "%' order by prod_codigo_id";
        else
            vmCondicao_Consulta = " order by prod_codigo_id";

        try
        {
        rs = AcessoDados.Selecao("produtos", vmCampos, vmCondicao_Consulta);
        MontaTituloColunaGrid();
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
        while (rs.next())
        {
            dtm.addRow(new Object[]{rs.getString("prod_codigo_id"),rs.getString("prod_codigo_produto"),rs.getString("prod_descricao"),""});
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
        jPanel5 = new javax.swing.JPanel();
        txtDescricaoGenerica = new javax.swing.JTextField();
        btnPesquisaGenerica = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txtEAN = new javax.swing.JTextField();
        btnPesquisaEAN = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisa de Produtos");
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
        grDados.setToolTipText("");
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

        jTabbedPane1.setForeground(new java.awt.Color(102, 102, 102));
        jTabbedPane1.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N

        txtCodigo.setColumns(7);
        txtCodigo.setToolTipText("");
        txtCodigo.setName(""); // NOI18N
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
        });

        btnPesquisaCodigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Pesquisar.gif"))); // NOI18N
        btnPesquisaCodigo.setToolTipText("");
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
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPesquisaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(547, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnPesquisaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Código do Produto", jPanel1);

        txtDescricao.setColumns(60);
        txtDescricao.setToolTipText("");
        txtDescricao.setName(""); // NOI18N
        txtDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescricaoKeyPressed(evt);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPesquisaDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(btnPesquisaDescricao, javax.swing.GroupLayout.Alignment.TRAILING, 0, 20, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Descrição", jPanel3);

        txtDescricaoGenerica.setColumns(60);
        txtDescricaoGenerica.setToolTipText("");
        txtDescricaoGenerica.setName(""); // NOI18N
        txtDescricaoGenerica.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescricaoGenericaKeyPressed(evt);
            }
        });

        btnPesquisaGenerica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Pesquisar.gif"))); // NOI18N
        btnPesquisaGenerica.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPesquisaGenerica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaGenericaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDescricaoGenerica, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPesquisaGenerica, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(192, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescricaoGenerica, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisaGenerica, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Descrição Genérica", jPanel5);

        txtEAN.setColumns(60);
        txtEAN.setToolTipText("");
        txtEAN.setName(""); // NOI18N
        txtEAN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEANKeyPressed(evt);
            }
        });

        btnPesquisaEAN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Pesquisar.gif"))); // NOI18N
        btnPesquisaEAN.setToolTipText("");
        btnPesquisaEAN.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPesquisaEAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaEANActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtEAN, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPesquisaEAN, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtEAN, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisaEAN, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Código de Barras", jPanel4);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        RetornaDadosGrid();
    }//GEN-LAST:event_grDadosMouseClicked

    private void grDadosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grDadosKeyReleased
        // TODO add your handling code here:
         if ((evt.getKeyCode() ==  java.awt.event.KeyEvent.VK_UP)  || (evt.getKeyCode() ==  java.awt.event.KeyEvent.VK_DOWN)) {
              RetornaDadosGrid();
         };
    }//GEN-LAST:event_grDadosKeyReleased

    private void btnPesquisaCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaCodigoActionPerformed
        // TODO add your handling code here:
        MontaGridCodigo();
    }//GEN-LAST:event_btnPesquisaCodigoActionPerformed

    private void btnPesquisaDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaDescricaoActionPerformed
        // TODO add your handling code here:
        MontaGridDescricao();
    }//GEN-LAST:event_btnPesquisaDescricaoActionPerformed

    private void btnPesquisaEANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaEANActionPerformed
        // TODO add your handling code here:
        MontaGridEAN();
    }//GEN-LAST:event_btnPesquisaEANActionPerformed

    private void btnPesquisaGenericaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaGenericaActionPerformed
        // TODO add your handling code here:
        MontaGridDescricaoGenerica();
    }//GEN-LAST:event_btnPesquisaGenericaActionPerformed

    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
        // TODO add your handling code here:
         if (evt.getKeyCode()== 114) //tecla F3 para pesquisa
            btnPesquisaCodigoActionPerformed(null);
    }//GEN-LAST:event_txtCodigoKeyPressed

    private void txtDescricaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescricaoKeyPressed
        // TODO add your handling code here:
         if (evt.getKeyCode()== 114) //tecla F3 para pesquisa
            btnPesquisaDescricaoActionPerformed(null);
    }//GEN-LAST:event_txtDescricaoKeyPressed

    private void txtDescricaoGenericaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescricaoGenericaKeyPressed
        // TODO add your handling code here:
         if (evt.getKeyCode()== 114) //tecla F3 para pesquisa
            btnPesquisaGenericaActionPerformed(null);
    }//GEN-LAST:event_txtDescricaoGenericaKeyPressed

    private void txtEANKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEANKeyPressed
        // TODO add your handling code here:
         if (evt.getKeyCode()== 114) //tecla F3 para pesquisa
            btnPesquisaEANActionPerformed(null);
    }//GEN-LAST:event_txtEANKeyPressed

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
    private javax.swing.JButton btnPesquisaEAN;
    private javax.swing.JButton btnPesquisaGenerica;
    private javax.swing.JTable grDados;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtDescricaoGenerica;
    private javax.swing.JTextField txtEAN;
    // End of variables declaration//GEN-END:variables

}
