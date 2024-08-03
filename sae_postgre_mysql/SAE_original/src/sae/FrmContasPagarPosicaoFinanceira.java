/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmContasPagarPosicaoFinanceira.java
 *
 * Created on 20/02/2010, 16:13:01
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
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Thiago
 */
public class FrmContasPagarPosicaoFinanceira extends javax.swing.JDialog {
    claVariaveis variaveis = new claVariaveis();
    claAcessoDados AcessoDados = new claAcessoDados();
    claFuncoes funcoes = new claFuncoes();
    private ResultSet rs;

    /** Creates new form FrmContasPagarPosicaoFinanceira */
    public FrmContasPagarPosicaoFinanceira(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        HashSet conj = new HashSet(this.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        this.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
        this.setTitle(variaveis.vg_PosicaoFinanceira);
        }


    
    public void MontaGridPagos()
    {
        String vmCampos = "cp_documento,cp_parcela,cp_data_emissao,cp_data_vencimento,cp_data_pagamento,cp_valor_documento";
        String vmCondicao_Consulta = " where not cp_data_pagamento is null";
        if(variaveis.vm_ID != 0)
        {
          vmCondicao_Consulta += " and cp_par_codigo_id =  " + Integer.toString(variaveis.vm_ID) ;
        }
        vmCondicao_Consulta += " order by cp_data_emissao";

        try
        {
        rs = AcessoDados.Selecao("contas_pagar", vmCampos, vmCondicao_Consulta);
        grDados.setModel(new javax.swing.table.DefaultTableModel(
                      new Object [][] { },
                      new String [] {"Documento ","Parcelas","Data de Emissão","Data de Vencimento","Data de Pagamento","Valor"})
                      {
                      public boolean isCellEditable(int row, int col) {
                      return false;
                      }});
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
        while (rs.next())
        {
            //dtm.addRow(new Object[]{rs.getString("cp_documento"),rs.getString("cp_parcela"),rs.getString("cp_data_emissao"),rs.getString("cp_data_vencimento"),rs.getString("cp_data_pagamento"),rs.getString("cp_valor_documento")});
          dtm.addRow(new Object[]{rs.getString("cp_documento"),rs.getString("cp_parcela"),funcoes.formataData(rs.getString("cp_data_emissao")),funcoes.formataData(rs.getString("cp_data_vencimento")),funcoes.formataData(rs.getString("cp_data_pagamento")),funcoes.formataMoeda(rs.getString("cp_valor_documento"),"BD")});
        }
        rs.close();
        grDados.getColumnModel().getColumn(0).setPreferredWidth(40);
        grDados.getColumnModel().getColumn(1).setPreferredWidth(10);
        grDados.getColumnModel().getColumn(2).setPreferredWidth(60);
        grDados.getColumnModel().getColumn(3).setPreferredWidth(60);
        grDados.getColumnModel().getColumn(4).setPreferredWidth(60);
        grDados.getColumnModel().getColumn(5).setPreferredWidth(80);
        

        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();

        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);

        grDados.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        grDados.getColumnModel().getColumn(1).setCellRenderer(centralizado);
        grDados.getColumnModel().getColumn(2).setCellRenderer(centralizado);
        grDados.getColumnModel().getColumn(3).setCellRenderer(centralizado);
        grDados.getColumnModel().getColumn(4).setCellRenderer(centralizado);
        grDados.getColumnModel().getColumn(5).setCellRenderer(direita);
        
         }catch (Exception e) {
           e.printStackTrace();
        }
        String vmCampos1 = " sum(cp_valor_documento) as total ";
        String vmCondicao_Consulta1 = " where not cp_data_pagamento is null " ;
        if(variaveis.vm_ID != 0)
        {
            vmCondicao_Consulta1 += " and cp_par_codigo_id =  " + Integer.toString(variaveis.vm_ID);
        }


        try
        {
        rs = AcessoDados.Selecao("contas_pagar", vmCampos1, vmCondicao_Consulta1);
        rs.next();
        lbTotalTitulos.setText(funcoes.formataMoeda(rs.getString("total"),"BD"));
        rs.close();
        }catch (Exception e) {
           e.printStackTrace();
        }
       }


     public void MontaGridAVencer()
    {

        String vmCampos = "cp_documento,cp_parcela,cp_data_emissao,cp_data_vencimento,cp_data_pagamento,cp_valor_documento";
        String vmCondicao_Consulta = " where cp_data_pagamento is null";
        if(variaveis.vm_ID != 0)
        {
          vmCondicao_Consulta += " and cp_par_codigo_id =  " + Integer.toString(variaveis.vm_ID) ;
        }
        vmCondicao_Consulta += " and cp_data_vencimento >= current_date order by cp_data_vencimento";

        
        try
        {
        rs = AcessoDados.Selecao("contas_pagar", vmCampos, vmCondicao_Consulta);
        grDados.setModel(new javax.swing.table.DefaultTableModel(
                      new Object [][] { },
                      new String [] {"Documento ","Parcelas","Data de Emissão","Data de Vencimento","Data de Pagamento","Valor"})
                      {
                      public boolean isCellEditable(int row, int col) {
                      return false;
                      }});
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
        while (rs.next())
        {
            //dtm.addRow(new Object[]{rs.getString("cp_documento"),rs.getString("cp_parcela"),rs.getString("cp_data_emissao"),rs.getString("cp_data_vencimento"),rs.getString("cp_data_pagamento"),rs.getString("cp_valor_documento")});
            dtm.addRow(new Object[]{rs.getString("cp_documento"),rs.getString("cp_parcela"),funcoes.formataData(rs.getString("cp_data_emissao")),funcoes.formataData(rs.getString("cp_data_vencimento")),rs.getString("cp_data_pagamento"),funcoes.formataMoeda(rs.getString("cp_valor_documento"),"BD")});
        }
        rs.close();
        grDados.getColumnModel().getColumn(0).setPreferredWidth(40);
        grDados.getColumnModel().getColumn(1).setPreferredWidth(10);
        grDados.getColumnModel().getColumn(2).setPreferredWidth(60);
        grDados.getColumnModel().getColumn(3).setPreferredWidth(60);
        grDados.getColumnModel().getColumn(4).setPreferredWidth(60);
        grDados.getColumnModel().getColumn(5).setPreferredWidth(80);


        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();

        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);

        grDados.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        grDados.getColumnModel().getColumn(1).setCellRenderer(centralizado);
        grDados.getColumnModel().getColumn(2).setCellRenderer(centralizado);
        grDados.getColumnModel().getColumn(3).setCellRenderer(centralizado);
        grDados.getColumnModel().getColumn(4).setCellRenderer(centralizado);
        grDados.getColumnModel().getColumn(5).setCellRenderer(direita);

         }catch (Exception e) {
           e.printStackTrace();
          }
        String vmCampos1 = " sum(cp_valor_documento) as total ";
        String vmCondicao_Consulta1 = " where cp_data_pagamento is null and cp_data_vencimento >= current_date";
        if(variaveis.vm_ID != 0)
        {
            vmCondicao_Consulta1 += " and cp_par_codigo_id =  " + Integer.toString(variaveis.vm_ID);
        }

        try
        {
        rs = AcessoDados.Selecao("contas_pagar", vmCampos1, vmCondicao_Consulta1);
        rs.next();
        lbTotalTitulos.setText(funcoes.formataMoeda(rs.getString("total"),"BD"));
        rs.close();
        }catch (Exception e) {
           e.printStackTrace();
        }
       }

      public void MontaGridAVencidos()
    {
        String vmCampos = "cp_documento,cp_parcela,cp_data_emissao,cp_data_vencimento,cp_data_pagamento,cp_valor_documento";
        String vmCondicao_Consulta = " where cp_data_pagamento is null  ";
        if(variaveis.vm_ID != 0)
        {
          vmCondicao_Consulta += " and cp_par_codigo_id =  " + Integer.toString(variaveis.vm_ID) ;
        }
        vmCondicao_Consulta += " and cp_data_vencimento < current_date order by cp_data_vencimento";


        try
        {
        rs = AcessoDados.Selecao("contas_pagar", vmCampos, vmCondicao_Consulta);
        grDados.setModel(new javax.swing.table.DefaultTableModel(
                      new Object [][] { },
                      new String [] {"Documento ","Parcelas","Data de Emissão","Data de Vencimento","Data de Pagamento","Valor"})
                      {
                      public boolean isCellEditable(int row, int col) {
                      return false;
                      }});
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
        while (rs.next())
        {
            //dtm.addRow(new Object[]{rs.getString("cp_documento"),rs.getString("cp_parcela"),rs.getString("cp_data_emissao"),rs.getString("cp_data_vencimento"),rs.getString("cp_data_pagamento"),rs.getString("cp_valor_documento")});
            dtm.addRow(new Object[]{rs.getString("cp_documento"),rs.getString("cp_parcela"),funcoes.formataData(rs.getString("cp_data_emissao")),funcoes.formataData(rs.getString("cp_data_vencimento")),rs.getString("cp_data_pagamento"),funcoes.formataMoeda(rs.getString("cp_valor_documento"),"BD")});
        }
        rs.close();
        grDados.getColumnModel().getColumn(0).setPreferredWidth(40);
        grDados.getColumnModel().getColumn(1).setPreferredWidth(10);
        grDados.getColumnModel().getColumn(2).setPreferredWidth(60);
        grDados.getColumnModel().getColumn(3).setPreferredWidth(60);
        grDados.getColumnModel().getColumn(4).setPreferredWidth(60);
        grDados.getColumnModel().getColumn(5).setPreferredWidth(80);


        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();

        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);

        grDados.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        grDados.getColumnModel().getColumn(1).setCellRenderer(centralizado);
        grDados.getColumnModel().getColumn(2).setCellRenderer(centralizado);
        grDados.getColumnModel().getColumn(3).setCellRenderer(centralizado);
        grDados.getColumnModel().getColumn(4).setCellRenderer(centralizado);
        grDados.getColumnModel().getColumn(5).setCellRenderer(direita);

         }catch (Exception e) {
           e.printStackTrace();
        }
        String vmCampos1 = " sum(cp_valor_documento) as total ";
        String vmCondicao_Consulta1 = " where cp_data_pagamento is null and cp_data_vencimento < current_date";
       if(variaveis.vm_ID != 0)
        {
            vmCondicao_Consulta1 += " and cp_par_codigo_id =  " + Integer.toString(variaveis.vm_ID);
        }

        try
        {
        rs = AcessoDados.Selecao("contas_pagar", vmCampos1, vmCondicao_Consulta1);
        rs.next();
        lbTotalTitulos.setText(funcoes.formataMoeda(rs.getString("total"),"BD"));
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
        jPanel1 = new javax.swing.JPanel();
        btnOK = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lbTotalTitulos = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Posição Financeira");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
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
        ));
        jScrollPane1.setViewportView(grDados);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnOK.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(btnOK)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Total de Titulos :");

        lbTotalTitulos.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lbTotalTitulos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbTotalTitulos.setText("0,00");

        jToolBar1.setRollover(true);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("TTC Software.");
        jToolBar1.add(jLabel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lbTotalTitulos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTotalTitulos)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        if (variaveis.vg_PosicaoFinanceira.equals("Títulos Pagos"))
            MontaGridPagos();
        else if (variaveis.vg_PosicaoFinanceira.equals("Títulos a Vencer"))
            MontaGridAVencer();
        else if (variaveis.vg_PosicaoFinanceira.equals("Títulos Vencidos"))
            MontaGridAVencidos();
        lbTotalTitulos.setText(funcoes.formataMoeda(lbTotalTitulos.getText(),"T"));
    }//GEN-LAST:event_formWindowOpened

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        // TODO add your handling code here:
        variaveis.vg_PosicaoFinanceira = "";
        this.dispose();

    }//GEN-LAST:event_btnOKActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmContasReceberPosicaoFinanceira dialog = new FrmContasReceberPosicaoFinanceira(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnOK;
    private javax.swing.JTable grDados;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbTotalTitulos;
    // End of variables declaration//GEN-END:variables

}
