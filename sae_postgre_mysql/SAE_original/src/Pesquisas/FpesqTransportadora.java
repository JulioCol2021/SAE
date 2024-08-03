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
public class FpesqTransportadora extends javax.swing.JDialog {
    claVariaveis variaveis = new claVariaveis();
    claAcessoDados AcessoDados = new claAcessoDados();
    claFuncoes funcoes = new claFuncoes();
    private ResultSet rs;
    private String retorno;
    public String getRetorno () { return retorno; }


    /** Creates new form FrmUsuario */
    public FpesqTransportadora(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        btnPesquisaCodigo.setFocusTraversalKeysEnabled(false);
        btnPesquisaRazaoSocial.setFocusTraversalKeysEnabled(false);
        btnPesquisaNomeFantasia.setFocusTraversalKeysEnabled(false);
        btnPesqCPFCNPJ.setFocusTraversalKeysEnabled(false);
        funcoes.F_AtribuirClasse(rootPane);
    }

     public void MontaTituloColunaGrid()
    {
        grDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
             new String [] {"Código","Razão Social","Nome Fantasia", "CPF / CNPJ",""
            }) { boolean[]  canEdit = new boolean [] {
                false, false, false, false, true
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        grDados.getColumnModel().getColumn(0).setPreferredWidth(15);
        grDados.getColumnModel().getColumn(1).setPreferredWidth(230);
        grDados.getColumnModel().getColumn(2).setPreferredWidth(150);
        grDados.getColumnModel().getColumn(3).setPreferredWidth(60);
        grDados.getColumnModel().getColumn(4).setPreferredWidth(20);

        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();

        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);

        ButtonColumn buttonColumn = new ButtonColumn(grDados, 4);

    }
    
    public void RetornaDadosGrid()
    {
       
    }
    public void MontaGridCodigo()
    {

        String vmCampos = "*";
      
        String vmCondicao_Consulta = "";
        if (!txtCodigo.getText().equals(""))
            vmCondicao_Consulta = " WHERE tran_codigo_id = " + txtCodigo.getText() + " ORDER BY tran_codigo_id";
        else
            vmCondicao_Consulta = "  ORDER By tran_codigo_id";
        
        try
        {
        rs = AcessoDados.Selecao("transportadoras", vmCampos, vmCondicao_Consulta);
         MontaTituloColunaGrid();
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
        while (rs.next())
        {
            dtm.addRow(new Object[]{rs.getString("tran_codigo_id"),rs.getString("tran_nome_razao_social"),rs.getString("tran_nome_fantasia"),rs.getString("tran_cpf_cnpj"),""});
        }
        rs.close();
        
           }catch (Exception e) {
           e.printStackTrace();
        }


    }
    public void MontaGridRazaoSocial()
    {

        String vmCampos = "*";
        String vmCondicao_Consulta = "";
        if (!txtRazaoSocial.getText().equals(""))
            vmCondicao_Consulta = " WHERE tran_nome_razao_social LIKE '" + txtRazaoSocial.getText() + "%' ORDER BY tran_nome_razao_social";
        else
            vmCondicao_Consulta = "  order by tran_nome_razao_social";

        try
        {
        rs = AcessoDados.Selecao("transportadoras", vmCampos, vmCondicao_Consulta);
         MontaTituloColunaGrid();
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
        while (rs.next())
        {
            dtm.addRow(new Object[]{rs.getString("tran_codigo_id"),rs.getString("tran_nome_razao_social"),rs.getString("tran_nome_fantasia"),rs.getString("tran_cpf_cnpj"),""});
        }
        rs.close();
       
           }catch (Exception e) {
           e.printStackTrace();
        }

    }
    public void MontaGridNomeFantasia()
    {

        String vmCampos = "*";

        String vmCondicao_Consulta = "";
        if (!txtNomeFantasia.getText().equals(""))
            vmCondicao_Consulta = " WHERE tran_nome_fantasia LIKE '" + txtNomeFantasia.getText() + "%' ORDER BY tran_nome_fantasia";
        else
            vmCondicao_Consulta = " BY tran_nome_fantasia";

        try
        {
        rs = AcessoDados.Selecao("transportadoras", vmCampos, vmCondicao_Consulta);
         MontaTituloColunaGrid();
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
        while (rs.next())
        {
           dtm.addRow(new Object[]{rs.getString("tran_codigo_id"),rs.getString("tran_nome_razao_social"),rs.getString("tran_nome_fantasia"),rs.getString("tran_cpf_cnpj"),""});
        }
        rs.close();
       
           }catch (Exception e) {
           e.printStackTrace();
        }

    }
    public void MontaGridCPFCNPJ()
    {

        String vmCampos = "*";

        String vmCondicao_Consulta = "";
        if (!txtCPFCNPJ.getText().equals(""))
            vmCondicao_Consulta = " WHERE tran_cpf_cnpj LIKE '" + txtCPFCNPJ.getText() + "%' ORDER BY tran_cpf_cnpj";
        else
            vmCondicao_Consulta = " ORDER BY tran_cpf_cnpj";

        try
        {
        rs = AcessoDados.Selecao("transportadoras", vmCampos, vmCondicao_Consulta);
        MontaTituloColunaGrid();
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
        while (rs.next())
        {
            dtm.addRow(new Object[]{rs.getString("tran_codigo_id"),rs.getString("tran_nome_razao_social"),rs.getString("tran_nome_fantasia"),rs.getString("tran_cpf_cnpj"),""});
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
        abaRazaoSocial = new javax.swing.JPanel();
        txtRazaoSocial = new javax.swing.JTextField();
        btnPesquisaRazaoSocial = new javax.swing.JButton();
        abaNomeFantasia = new javax.swing.JPanel();
        txtNomeFantasia = new javax.swing.JTextField();
        btnPesquisaNomeFantasia = new javax.swing.JButton();
        abaCPFCNPJ = new javax.swing.JPanel();
        txtCPFCNPJ = new javax.swing.JTextField();
        btnPesqCPFCNPJ = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisa Transportadora");
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

        jTabbedPane1.setForeground(new java.awt.Color(102, 102, 102));
        jTabbedPane1.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N

        txtCodigo.setColumns(8);
        txtCodigo.setToolTipText("Número");
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
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPesquisaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(520, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPesquisaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Código", jPanel1);

        txtRazaoSocial.setColumns(60);
        txtRazaoSocial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRazaoSocialKeyReleased(evt);
            }
        });

        btnPesquisaRazaoSocial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Pesquisar.gif"))); // NOI18N
        btnPesquisaRazaoSocial.setToolTipText("");
        btnPesquisaRazaoSocial.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPesquisaRazaoSocial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaRazaoSocialActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout abaRazaoSocialLayout = new javax.swing.GroupLayout(abaRazaoSocial);
        abaRazaoSocial.setLayout(abaRazaoSocialLayout);
        abaRazaoSocialLayout.setHorizontalGroup(
            abaRazaoSocialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaRazaoSocialLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtRazaoSocial, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPesquisaRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );
        abaRazaoSocialLayout.setVerticalGroup(
            abaRazaoSocialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaRazaoSocialLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaRazaoSocialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPesquisaRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Razão Social", abaRazaoSocial);

        txtNomeFantasia.setColumns(60);
        txtNomeFantasia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomeFantasiaKeyReleased(evt);
            }
        });

        btnPesquisaNomeFantasia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Pesquisar.gif"))); // NOI18N
        btnPesquisaNomeFantasia.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPesquisaNomeFantasia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaNomeFantasiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout abaNomeFantasiaLayout = new javax.swing.GroupLayout(abaNomeFantasia);
        abaNomeFantasia.setLayout(abaNomeFantasiaLayout);
        abaNomeFantasiaLayout.setHorizontalGroup(
            abaNomeFantasiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaNomeFantasiaLayout.createSequentialGroup()
                .addContainerGap(641, Short.MAX_VALUE)
                .addComponent(btnPesquisaNomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(abaNomeFantasiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(abaNomeFantasiaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txtNomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(64, Short.MAX_VALUE)))
        );
        abaNomeFantasiaLayout.setVerticalGroup(
            abaNomeFantasiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaNomeFantasiaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPesquisaNomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(abaNomeFantasiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(abaNomeFantasiaLayout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(txtNomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Nome Fantasia", abaNomeFantasia);

        txtCPFCNPJ.setColumns(18);
        txtCPFCNPJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCPFCNPJKeyReleased(evt);
            }
        });

        btnPesqCPFCNPJ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Pesquisar.gif"))); // NOI18N
        btnPesqCPFCNPJ.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPesqCPFCNPJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesqCPFCNPJActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout abaCPFCNPJLayout = new javax.swing.GroupLayout(abaCPFCNPJ);
        abaCPFCNPJ.setLayout(abaCPFCNPJLayout);
        abaCPFCNPJLayout.setHorizontalGroup(
            abaCPFCNPJLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCPFCNPJLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtCPFCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPesqCPFCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(477, Short.MAX_VALUE))
        );
        abaCPFCNPJLayout.setVerticalGroup(
            abaCPFCNPJLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCPFCNPJLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaCPFCNPJLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCPFCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesqCPFCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("CPF / CNPJ", abaCPFCNPJ);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

    private void btnPesquisaRazaoSocialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaRazaoSocialActionPerformed
        // TODO add your handling code here:
       MontaGridRazaoSocial();
    }//GEN-LAST:event_btnPesquisaRazaoSocialActionPerformed

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtRazaoSocialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazaoSocialKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazaoSocialKeyReleased

    private void txtNomeFantasiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeFantasiaKeyReleased
        // TODO add your handling code here:
       
    }//GEN-LAST:event_txtNomeFantasiaKeyReleased

    private void btnPesquisaNomeFantasiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaNomeFantasiaActionPerformed
        // TODO add your handling code here:
        MontaGridNomeFantasia();
    }//GEN-LAST:event_btnPesquisaNomeFantasiaActionPerformed

    private void txtCPFCNPJKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCPFCNPJKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPFCNPJKeyReleased

    private void btnPesqCPFCNPJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesqCPFCNPJActionPerformed
        // TODO add your handling code here:
        MontaGridCPFCNPJ();
    }//GEN-LAST:event_btnPesqCPFCNPJActionPerformed

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
    private javax.swing.JPanel abaCPFCNPJ;
    private javax.swing.JPanel abaNomeFantasia;
    private javax.swing.JPanel abaRazaoSocial;
    private javax.swing.JButton btnPesqCPFCNPJ;
    private javax.swing.JButton btnPesquisaCodigo;
    private javax.swing.JButton btnPesquisaNomeFantasia;
    private javax.swing.JButton btnPesquisaRazaoSocial;
    private javax.swing.JTable grDados;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField txtCPFCNPJ;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNomeFantasia;
    private javax.swing.JTextField txtRazaoSocial;
    // End of variables declaration//GEN-END:variables

}
