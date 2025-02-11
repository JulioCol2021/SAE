/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmBaixaContasPagar.java
 *
 * Created on 11/02/2010, 18:54:56
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
import java.text.DecimalFormat;

/**
 *
 * @author Thiago
 */
public class FrmContasPagarBaixa extends javax.swing.JDialog {
        claVariaveis variaveis = new claVariaveis();
        claAcessoDados AcessoDados = new claAcessoDados();
        claFuncoes funcoes = new claFuncoes();
        private ResultSet rs;
        public String vmData;

    /** Creates new form FrmBaixaContasPagar */
    public FrmContasPagarBaixa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        funcoes.F_AtribuirClasse(rootPane);
        btnOK.setFocusTraversalKeysEnabled(false);
        
    }

     public static int vm_ID;


     public void limpaCampos(){
        
        txtDataPagamento.setText(null);
        txtAcrescimo.setText("0");
        txtDesconto.setText("0");
        txtValorPago.setText("0");
        txtFavorecido.setText(null);
        txtObservacaoBaixa.setText(null);
                
        }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbID = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtDataPagamento = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        txtAcrescimo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDesconto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtValorPago = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        lbValor = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtFavorecido = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservacaoBaixa = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnFechar = new javax.swing.JButton();
        btnOK = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Baixa - Contas a Pagar");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setText("Codigo:");

        lbID.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lbID.setForeground(new java.awt.Color(0, 0, 102));
        lbID.setText("0");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setText("Data de Pagamento:");

        try {
            txtDataPagamento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataPagamento.setToolTipText("");
        txtDataPagamento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDataPagamentoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDataPagamentoFocusLost(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel3.setText("Acréscimo:");

        txtAcrescimo.setColumns(12);
        txtAcrescimo.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtAcrescimo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtAcrescimo.setToolTipText("Valor");
        txtAcrescimo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAcrescimoFocusLost(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setText("Desconto:");

        txtDesconto.setColumns(12);
        txtDesconto.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtDesconto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDesconto.setToolTipText("Valor");
        txtDesconto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDescontoFocusLost(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel5.setText("Valor Pago:");

        txtValorPago.setColumns(12);
        txtValorPago.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtValorPago.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtValorPago.setToolTipText("Valor");
        txtValorPago.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtValorPagoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtValorPagoFocusLost(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 102));
        jLabel6.setText("Valor Documento:");

        lbValor.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lbValor.setForeground(new java.awt.Color(0, 0, 51));
        lbValor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbValor.setText("0,00");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados Adicionais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel7.setText("Favorecido:");

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel8.setText("Observação:");

        txtFavorecido.setColumns(30);
        txtFavorecido.setToolTipText("");
        txtFavorecido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFavorecidoKeyReleased(evt);
            }
        });

        txtObservacaoBaixa.setColumns(60);
        txtObservacaoBaixa.setRows(5);
        txtObservacaoBaixa.setToolTipText("");
        txtObservacaoBaixa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtObservacaoBaixaFocusLost(evt);
            }
        });
        txtObservacaoBaixa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtObservacaoBaixaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtObservacaoBaixaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(txtObservacaoBaixa);

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/warning.gif"))); // NOI18N
        jLabel9.setText("Tecla Ctrl - Quebra de linha.");
        jLabel9.setToolTipText("");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtFavorecido, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel8))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFavorecido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbID)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbValor, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtAcrescimo, 0, 1, Short.MAX_VALUE)
                                    .addComponent(txtValorPago, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(txtDesconto, 0, 1, Short.MAX_VALUE)
                                    .addComponent(txtDataPagamento))
                                .addContainerGap(250, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbValor)
                    .addComponent(jLabel6)
                    .addComponent(lbID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAcrescimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Sair.gif"))); // NOI18N
        btnFechar.setToolTipText("Voltar");
        btnFechar.setBorderPainted(false);
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });

        btnOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Gravar.gif"))); // NOI18N
        btnOK.setToolTipText("Salvar");
        btnOK.setBorderPainted(false);
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
                .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 403, Short.MAX_VALUE)
                .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtObservacaoBaixaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtObservacaoBaixaFocusLost
        // TODO add your handling code here:
        txtObservacaoBaixa.setText(txtObservacaoBaixa.getText().trim());
    }//GEN-LAST:event_txtObservacaoBaixaFocusLost

    private void txtObservacaoBaixaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacaoBaixaKeyReleased
        // TODO add your handling code here:
        JTextArea tf = (JTextArea) evt.getComponent();
        if (evt.getKeyCode()!=32 & evt.getKeyCode()!=8 & evt.getKeyCode()!=37 & evt.getKeyCode()!=39)
        tf.setText(tf.getText().toUpperCase());
        txtObservacaoBaixa.setLineWrap(true);
        txtObservacaoBaixa.setWrapStyleWord(true);
         String str = txtObservacaoBaixa.getText();
        if (str.length()>255){
            String strCut = str.substring(0,255);
            txtObservacaoBaixa.setText(strCut);}
    }//GEN-LAST:event_txtObservacaoBaixaKeyReleased

    private void txtObservacaoBaixaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacaoBaixaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode()==10)
            btnOK.requestFocus();
    }//GEN-LAST:event_txtObservacaoBaixaKeyPressed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        // TODO add your handling code here:
       // vmData = txtDataPapamento.getText();
       if (txtDataPagamento.getText().equals("  /  /    "))
        {
            JOptionPane.showMessageDialog(null,"Data de pagamento inválida");
            txtDataPagamento.requestFocus();
            return;
        }

        vmData = "cp_data_pagamento = '"  + txtDataPagamento.getText() + "',";

        Object[] options = { " Sim ", " Não " };
        int confirmar = JOptionPane.showOptionDialog(null, "Confirma Baixa de Título", " Mensagem...",JOptionPane.DEFAULT_OPTION, JOptionPane. QUESTION_MESSAGE,null,options, options[0]);
        if(confirmar==1)
        {
            txtDataPagamento.requestFocus();
            return;
        }


        double vValor = Double.parseDouble(txtValorPago.getText().replace(".", "").replace(",", "."));
        double Pendente = variaveis.vm_valor_pendente - vValor;
        if(Pendente == 0)
        {
           try
            {

            String vmCampos2 = " cc_realizado = 'SIM'";
            AcessoDados.Alterar("contas_corrente", vmCampos2, " cc_cp_codigo_id = " + lbID.getText());

            }catch (Exception e) {
                e.printStackTrace();
            }
        }


            //Gerar Recebimento
            try
            {

            String vmCampos2 = " rcp_cp_codigo_id, " +
                               " rcp_data_pagamento, " +
                               " rcp_usuario, " +
                               " rcp_valor_pago, rcp_acrescimo, rcp_desconto";

            String vmParametros =  lbID.getText() + ", "  +
                                  "'" + txtDataPagamento.getText() + "','" +
                                  variaveis.nome_usuario + "'," +
                                  txtValorPago.getText().replace(".", "").replace(",", ".") + ", " +
                                  txtAcrescimo.getText().replace(".", "").replace(",", ".") + "," +
                                  txtDesconto.getText().replace(".", "").replace(",", ".");

            AcessoDados.Inserir("recebimento_contas_pagar", vmCampos2, vmParametros);

            }catch (Exception e) {
                e.printStackTrace();
            }

            this.dispose();

    }//GEN-LAST:event_btnOKActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
       // lbID.setText(variaveis.vm_ID);
       
    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        variaveis.status = "A";
        lbID.setText(Integer.toString(variaveis.vm_ID));

        /*  String vmCampos = "*";
          String vmCondicao_Consulta = " WHERE cp_codigo_id = " + lbID.getText();
          try
        {
        rs = AcessoDados.Selecao("contas_pagar", vmCampos, vmCondicao_Consulta);
        rs.next();


        //lbValor.setText(funcoes.formataMoeda(rs.getString("cp_valor_documento"),"BD"));
        lbValor.setText(funcoes.formataMoeda(Double.toString(variaveis.vm_valor_pendente),"BD"));
        txtAcrescimo.setText(funcoes.formataMoeda(rs.getString("cp_acrescimo"),"BD"));
        txtDesconto.setText(funcoes.formataMoeda(rs.getString("cp_desconto"),"BD"));
        txtValorPago.setText(funcoes.formataMoeda(rs.getString("cp_valor_pago"),"BD"));
        txtFavorecido.setText(rs.getString("cp_favorecido"));
        txtObservacaoBaixa.setText(rs.getString("cp_observacao_baixa"));
        Date data = rs.getDate("cp_data_pagamento");
        SimpleDateFormat formatarDate1 = new SimpleDateFormat("dd/MM/yyyy");
        txtDataPagamento.setText(formatarDate1.format(data));
       
        rs.close();
          }catch (Exception e) {
           e.printStackTrace();
        }*/
        lbValor.setText(funcoes.formataMoeda(Double.toString(variaveis.vm_valor_pendente),"BD"));
        txtValorPago.setText(funcoes.formataMoeda(Double.toString(variaveis.vm_valor_pendente),"BD"));
        txtDataPagamento.requestFocus();
      
    }//GEN-LAST:event_formWindowOpened

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void txtAcrescimoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAcrescimoFocusLost
        // TODO add your handling code here:
      if (txtAcrescimo.getText().equals(null) || (txtAcrescimo.getText().equals("")))
            {
                txtAcrescimo.setText("0");
                txtAcrescimo.setText(funcoes.formataMoeda(txtAcrescimo.getText(),"T"));
            }
                txtAcrescimo.setText(funcoes.formataMoeda(txtAcrescimo.getText(),"T"));
    }//GEN-LAST:event_txtAcrescimoFocusLost

    private void txtDescontoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDescontoFocusLost
        // TODO add your handling code here:
        if (txtDesconto.getText().equals(null) || (txtDesconto.getText().equals("")))
            {
                txtDesconto.setText("0");
                txtDesconto.setText(funcoes.formataMoeda(txtDesconto.getText(),"T"));
            }
                txtDesconto.setText(funcoes.formataMoeda(txtDesconto.getText(),"T"));
    }//GEN-LAST:event_txtDescontoFocusLost

    private void txtValorPagoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtValorPagoFocusLost
        // TODO add your handling code here:
       /*if (txtValorPago.getText().equals(null) || (txtValorPago.getText().equals("")))
            {
                //JOptionPane.showMessageDialog(null,"Valor pago não informado");
                txtValorPago.setText("0");
                //return;
            }
        txtValorPago.setText(funcoes.formataMoeda(txtValorPago.getText(),"T"));
        double vvalorPago =  Double.parseDouble(txtValorPago.getText().replace(".", "").replace(",", "."));
        double vvalorTitulo =  Double.parseDouble(lbValor.getText().replace(".", "").replace(",", "."));
        double vdiferenca = 0;
        DecimalFormat nf = new DecimalFormat("#,##0.00");

        txtAcrescimo.setText(nf.format(0));
        txtDesconto.setText(nf.format(0));

        if(vvalorPago > vvalorTitulo)
        {
            vdiferenca = vvalorPago - vvalorTitulo;
            txtAcrescimo.setText(nf.format(vdiferenca));
            txtDesconto.setText(nf.format(0));
        }
        if(vvalorPago < vvalorTitulo)
        {
            vdiferenca = vvalorTitulo - vvalorPago;
            txtAcrescimo.setText(nf.format(0));
            txtDesconto.setText(nf.format(vdiferenca));
        }*/


    }//GEN-LAST:event_txtValorPagoFocusLost

    private void txtFavorecidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFavorecidoKeyReleased
        // TODO add your handling code here:
        JTextField tf = (JTextField) evt.getComponent();
        if (evt.getKeyCode()!=32 & evt.getKeyCode()!=8 & evt.getKeyCode()!=37 & evt.getKeyCode()!=39)
        tf.setText(tf.getText().toUpperCase());
        String str = txtFavorecido.getText();
        if (str.length() > 100) {
            String strCut = str.substring(0, 100);
            txtFavorecido.setText(strCut);
        }

    }//GEN-LAST:event_txtFavorecidoKeyReleased

    private void txtDataPagamentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDataPagamentoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataPagamentoFocusLost

    private void txtValorPagoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtValorPagoFocusGained
        // TODO add your handling code here:
        JTextField tf = (JTextField) evt.getComponent();
        tf.selectAll();
    }//GEN-LAST:event_txtValorPagoFocusGained

    private void txtDataPagamentoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDataPagamentoFocusGained
        // TODO add your handling code here:
        JTextField tf = (JTextField) evt.getComponent();
        tf.selectAll();
    }//GEN-LAST:event_txtDataPagamentoFocusGained

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmContasPagarBaixa dialog = new FrmContasPagarBaixa(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnOK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbID;
    private javax.swing.JLabel lbValor;
    private javax.swing.JTextField txtAcrescimo;
    private javax.swing.JFormattedTextField txtDataPagamento;
    private javax.swing.JTextField txtDesconto;
    private javax.swing.JTextField txtFavorecido;
    private javax.swing.JTextArea txtObservacaoBaixa;
    private javax.swing.JTextField txtValorPago;
    // End of variables declaration//GEN-END:variables

}
