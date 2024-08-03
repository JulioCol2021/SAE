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
public class FrmRemessaRetorno extends javax.swing.JDialog {
    claVariaveis variaveis = new claVariaveis();
    claAcessoDados AcessoDados = new claAcessoDados();
    claFuncoes funcoes = new claFuncoes();
    private ResultSet rs;

    /** Creates new form FrmAlmoxarifados */
    public FrmRemessaRetorno(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        funcoes.F_AtribuirClasse(rootPane);
       
       
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jPanel1 = new javax.swing.JPanel();
        remBrasil = new javax.swing.JRadioButton();
        retBrasil = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        remBradesco = new javax.swing.JRadioButton();
        retBradesco = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        remItau = new javax.swing.JRadioButton();
        retItau = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Remessa / Retorno");
        setResizable(false);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Banco do Brasil", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 10))); // NOI18N

        remBrasil.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        remBrasil.setText("Remessa de Arquivo");
        remBrasil.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                remBrasilItemStateChanged(evt);
            }
        });
        remBrasil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remBrasilActionPerformed(evt);
            }
        });

        retBrasil.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        retBrasil.setText("Retorno de Remessa");
        retBrasil.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                retBrasilItemStateChanged(evt);
            }
        });
        retBrasil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retBrasilActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(remBrasil)
                    .addComponent(retBrasil))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(remBrasil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(retBrasil)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Banco Bradesco", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 10))); // NOI18N

        remBradesco.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        remBradesco.setText("Remessa de Arquivo");
        remBradesco.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                remBradescoItemStateChanged(evt);
            }
        });
        remBradesco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remBradescoActionPerformed(evt);
            }
        });

        retBradesco.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        retBradesco.setText("Retorno de Remessa");
        retBradesco.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                retBradescoItemStateChanged(evt);
            }
        });
        retBradesco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retBradescoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(remBradesco)
                    .addComponent(retBradesco))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(remBradesco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(retBradesco)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Banco Itaú", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 10))); // NOI18N

        remItau.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        remItau.setSelected(true);
        remItau.setText("Remessa de Arquivo");
        remItau.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                remItauItemStateChanged(evt);
            }
        });
        remItau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remItauActionPerformed(evt);
            }
        });

        retItau.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        retItau.setText("Retorno de Remessa");
        retItau.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                retItauItemStateChanged(evt);
            }
        });
        retItau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retItauActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(remItau)
                    .addComponent(retItau))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(remItau)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(retItau)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        btnNovo.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        btnNovo.setText("OK");
        btnNovo.setToolTipText("Novo");
        btnNovo.setBorderPainted(false);
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnSair.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Sair.gif"))); // NOI18N
        btnSair.setToolTipText("Voltar");
        btnSair.setBorderPainted(false);
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnNovo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnSair, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_btnNovoActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
        this.dispose();
}//GEN-LAST:event_btnSairActionPerformed

    private void remItauItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_remItauItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_remItauItemStateChanged

    private void retItauItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_retItauItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_retItauItemStateChanged

    private void remBrasilItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_remBrasilItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_remBrasilItemStateChanged

    private void retBrasilItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_retBrasilItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_retBrasilItemStateChanged

    private void remBradescoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_remBradescoItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_remBradescoItemStateChanged

    private void retBradescoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_retBradescoItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_retBradescoItemStateChanged

    private void retItauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retItauActionPerformed
        // TODO add your handling code here:
        remItau.setSelected(false);
        remBradesco.setSelected(false);
        retBradesco.setSelected(false);
        remBrasil.setSelected(false);
        retBrasil.setSelected(false);



    }//GEN-LAST:event_retItauActionPerformed

    private void remItauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remItauActionPerformed
        // TODO add your handling code here:
        retItau.setSelected(false);
        remBradesco.setSelected(false);
        retBradesco.setSelected(false);
        remBrasil.setSelected(false);
        retBrasil.setSelected(false);

    }//GEN-LAST:event_remItauActionPerformed

    private void remBrasilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remBrasilActionPerformed
        // TODO add your handling code here:
        retItau.setSelected(false);
        remItau.setSelected(false);
        remBradesco.setSelected(false);
        retBradesco.setSelected(false);
        retBrasil.setSelected(false);

    }//GEN-LAST:event_remBrasilActionPerformed

    private void retBrasilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retBrasilActionPerformed
        // TODO add your handling code here:
        retItau.setSelected(false);
        remItau.setSelected(false);
        remBradesco.setSelected(false);
        retBradesco.setSelected(false);
        remBrasil.setSelected(false);

    }//GEN-LAST:event_retBrasilActionPerformed

    private void remBradescoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remBradescoActionPerformed
        // TODO add your handling code here:
        retItau.setSelected(false);
        remItau.setSelected(false);
        retBradesco.setSelected(false);
        remBrasil.setSelected(false);
        retBrasil.setSelected(false);

    }//GEN-LAST:event_remBradescoActionPerformed

    private void retBradescoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retBradescoActionPerformed
        // TODO add your handling code here:
        retItau.setSelected(false);
        remItau.setSelected(false);
        remBradesco.setSelected(false);
        remBrasil.setSelected(false);
        retBrasil.setSelected(false);

    }//GEN-LAST:event_retBradescoActionPerformed

    public static void main(String args[]) {
       
                java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmRemessaRetorno dialog = new FrmRemessaRetorno(new javax.swing.JFrame(), true);
                dialog.setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSair;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JRadioButton remBradesco;
    private javax.swing.JRadioButton remBrasil;
    private javax.swing.JRadioButton remItau;
    private javax.swing.JRadioButton retBradesco;
    private javax.swing.JRadioButton retBrasil;
    private javax.swing.JRadioButton retItau;
    // End of variables declaration//GEN-END:variables

}
