/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmAlmoxarifados.java
 *
 * Created on 17/01/2010, 20:05:03
 */

package sae;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import Classes.claAcessoDados;
import Classes.claVariaveis;
import Classes.claFuncoes;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import Classes.claVariaveis;
import Classes.claAcessoDados;
import javax.swing.table.DefaultTableCellRenderer;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.*;
/**
 *
 * @author Thiago
 */
public class FrmAlmoxarifados extends javax.swing.JDialog {
    claVariaveis variaveis = new claVariaveis();
    claAcessoDados AcessoDados = new claAcessoDados();
    claFuncoes funcoes = new claFuncoes();
    private ResultSet rs;

    /** Creates new form FrmAlmoxarifados */
    public FrmAlmoxarifados(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        funcoes.F_AtribuirClasse(rootPane);
        btnNovo.setFocusTraversalKeysEnabled(false);
        btnAlterar.setFocusTraversalKeysEnabled(false);
        btnSalvar.setFocusTraversalKeysEnabled(false);
       
       
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        lbStatus = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnPesquisar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lbID = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Almoxarifados");
        setResizable(false);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        lbStatus.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lbStatus.setForeground(new java.awt.Color(153, 153, 153));
        lbStatus.setText(" Registro ");
        lbStatus.setToolTipText("");
        jToolBar1.add(lbStatus);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        btnNovo.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/novo.gif"))); // NOI18N
        btnNovo.setToolTipText("Novo");
        btnNovo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnNovo.setBorderPainted(false);
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnAlterar.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/alterar.gif"))); // NOI18N
        btnAlterar.setToolTipText("Alterar");
        btnAlterar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAlterar.setBorderPainted(false);
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed1(evt);
            }
        });

        btnPesquisar.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Localizar.gif"))); // NOI18N
        btnPesquisar.setToolTipText("Pesquisar");
        btnPesquisar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPesquisar.setBorderPainted(false);
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        btnExcluir.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/excluir.gif"))); // NOI18N
        btnExcluir.setToolTipText("Excluir");
        btnExcluir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnExcluir.setBorderPainted(false);
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnSair.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Sair.gif"))); // NOI18N
        btnSair.setToolTipText("Voltar");
        btnSair.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSair.setBorderPainted(false);
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Imprimir.gif"))); // NOI18N
        jButton1.setToolTipText("Imprimir");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnSalvar.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/salvar.gif"))); // NOI18N
        btnSalvar.setToolTipText("Salvar");
        btnSalvar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSalvar.setBorderPainted(false);
        btnSalvar.setEnabled(false);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/cancelar.gif"))); // NOI18N
        btnCancelar.setToolTipText("Cancelar");
        btnCancelar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCancelar.setBorderPainted(false);
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnSair, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAlterar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNovo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setText("Código:");
        jLabel1.setToolTipText("");

        lbID.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lbID.setText("0");
        lbID.setToolTipText("ID");
        lbID.setName(""); // NOI18N

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setText("Descrição:");
        jLabel2.setToolTipText("");

        txtDescricao.setColumns(60);
        txtDescricao.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtDescricao.setToolTipText("");
        txtDescricao.setEnabled(false);
        txtDescricao.setName("almox_descricao"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbID)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbID)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:
        if(funcoes.Permissao("001", variaveis.usuario_id, variaveis.usuario_super, this.getTitle()))
        {
          funcoes.limparTodosCampos(rootPane);
          funcoes.HabilitaCampos(rootPane, true);
          lbStatus.setText(" Incluindo...");
          txtDescricao.requestFocus();
        }

    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // TODO add your handling code here:
        if (txtDescricao.getText().equals(null) || (txtDescricao.getText().equals("")))
        {
            JOptionPane.showMessageDialog(null,"Nenhum registro para alterar.");
            return;
        }
        funcoes.HabilitaCampos(rootPane, true);
        txtDescricao.requestFocus();
        lbStatus.setText(" Alterando");
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
        if (txtDescricao.getText().equals(null) || (txtDescricao.getText().equals("")))
        {
            JOptionPane.showMessageDialog(null,"Descrição não foi informada");
            txtDescricao.requestFocus();
            return;
        }
        funcoes.GravarDados(lbID.getText(), rootPane, "almoxarifados", "almox_codigo_id",true);
        lbStatus.setText(" Registro");
        btnNovo.requestFocus();
    
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        funcoes.limparTodosCampos(rootPane);
        funcoes.HabilitaCampos(rootPane, false);
        btnNovo.requestFocus();
        lbStatus.setText(" Registro");
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        if(funcoes.Permissao("003", variaveis.usuario_id, variaveis.usuario_super, this.getTitle()))
        {
        funcoes.ExcluirDados(lbID.getText(), rootPane, "almoxarifados", "almox_codigo_id");
        btnNovo.requestFocus();
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        // TODO add your handling code here:
       if(funcoes.Permissao("004", variaveis.usuario_id, variaveis.usuario_super, this.getTitle()))
        {

         variaveis.xColuna1 = "almox_codigo_id";
         variaveis.xColuna2 = "almox_descricao";
         variaveis.xColuna3 = "''";
         variaveis.xColuna4 = "''";
         variaveis.xColuna5 = "''";
         variaveis.xColuna6 = "''";
         variaveis.xColuna7 = "''";
         variaveis.xColuna8 = "''";
         variaveis.xColuna9 = "''";
         
         variaveis.xColunaNome1 = "Código";
         variaveis.xColunaNome2 = "Descrição";
         variaveis.xColunaNome3 = "";
         variaveis.xColunaNome4 = "";
         variaveis.xColunaNome5 = "";
         variaveis.xColunaNome6 = "";
         variaveis.xColunaNome7 = "";
         variaveis.xColunaNome8 = "";
         variaveis.xColunaNome9 = "";
         
         variaveis.xColunaTamanho1 = 60;
         variaveis.xColunaTamanho2 = 400;
         variaveis.xColunaTamanho3 = 0;
         variaveis.xColunaTamanho4 = 0;
         variaveis.xColunaTamanho5 = 0;
         variaveis.xColunaTamanho6 = 0;
         variaveis.xColunaTamanho7 = 0;
         variaveis.xColunaTamanho8 = 0;
         variaveis.xColunaTamanho9 = 0;
         
         variaveis.xColunaStart = "almox_descricao";
         variaveis.xColunaNomeStart = "Descrição";
         variaveis.xTabela = "Almoxarifados";
         variaveis.xSql = ""; 
         
         FpesqPesquisa md = new FpesqPesquisa(null, true);
         Dimension d = new Dimension();   
         d.setSize(470, 480); 
         md.setSize(d);
         
         md.setTitle("Pesquisa Almoxarifado - ENTER ou DUPLO CLICK no registro retorna dados.");

         md.setLocationRelativeTo(null);
         md.setVisible(true);
            
            
            
       if(!md.getRetorno().trim().equals("") ||!md.getRetorno().equals(null))
       {
        lbID.setText((String) md.getRetorno());
       }

       if(!lbID.getText().equals(""))
       {
          String vmCampos = "*";
          String vmCondicao_Consulta = " WHERE almox_codigo_id = " + lbID.getText();
          try
        {
        rs = AcessoDados.Selecao("almoxarifados", vmCampos, vmCondicao_Consulta);
        rs.next();
        txtDescricao.setText(rs.getString("almox_descricao"));
        rs.close();
          }catch (Exception e) {
           e.printStackTrace();
        }
       }
      }
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(funcoes.Permissao("005", variaveis.usuario_id, variaveis.usuario_super, this.getTitle()))
        {
        String vmCampos = "*";
        String vmCondicao_Consulta = "order by almox_codigo_id ";
        try
        {
        rs = AcessoDados.Selecao("Almoxarifados", vmCampos, vmCondicao_Consulta);
         }catch (Exception e) {
           e.printStackTrace();
        }

        HashMap map = new HashMap();
        try
        {

          JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getClassLoader().getResource("Relatorios/RelAlmoxarifado.jasper"));
          JRResultSetDataSource jrRS1 = new JRResultSetDataSource(rs);
          JasperPrint jp = JasperFillManager.fillReport(jr, map,jrRS1);
          JasperViewer jv = new JasperViewer(jp,false);
          JDialog viewer = new  JDialog(jv, true);
          viewer.setSize(1000,700);
          viewer.setLocationRelativeTo(null);
          viewer.getContentPane().add(jv.getContentPane());
          viewer.setVisible(true);

        }catch (Exception e) {
           e.printStackTrace();
        }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnAlterarActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed1
        // TODO add your handling code here:
        if (txtDescricao.getText().equals(null) || (txtDescricao.getText().equals("")))
        {
            JOptionPane.showMessageDialog(null,"Nenhum registro para alterar.");
            return;
        }
        if(funcoes.Permissao("002", variaveis.usuario_id, variaveis.usuario_super, this.getTitle()))
        {
        funcoes.HabilitaCampos(rootPane, true);
        txtDescricao.requestFocus();
        lbStatus.setText(" Alterando");
        }

    }//GEN-LAST:event_btnAlterarActionPerformed1

    public static void main(String args[]) {
       
                java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmAlmoxarifados dialog = new FrmAlmoxarifados(new javax.swing.JFrame(), true);
                dialog.setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbID;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JTextField txtDescricao;
    // End of variables declaration//GEN-END:variables

}
