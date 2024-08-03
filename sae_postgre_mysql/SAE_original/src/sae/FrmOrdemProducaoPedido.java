/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmUnidadeMedida.java
 *
 * Created on 21/01/2010, 16:49:08
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
public class FrmOrdemProducaoPedido extends javax.swing.JDialog {
    claVariaveis variaveis = new claVariaveis();
    claAcessoDados AcessoDados = new claAcessoDados();
    claFuncoes funcoes = new claFuncoes();
    private ResultSet rs;

    /** Creates new form FrmUnidadeMedida */
    public FrmOrdemProducaoPedido(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        funcoes.F_AtribuirClasse(rootPane);
        btnNovo.setFocusTraversalKeysEnabled(false);
        btnSalvar.setFocusTraversalKeysEnabled(false);
        btnAlterar.setFocusTraversalKeysEnabled(false);
        txtIDProduto.setVisible(false);
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        lbStatus = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lbID = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUnidade = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDescricao1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtDescricao4 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtDescricao5 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtDescricao6 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtDescricao7 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtDescricao12 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCodProduto = new javax.swing.JTextField();
        btnPesquisaProduto = new javax.swing.JButton();
        txtDescricaoProduto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtUN = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnPesquisa = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        txtIDProduto = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ordem de Produção por Pedido");
        setResizable(false);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        lbStatus.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lbStatus.setForeground(new java.awt.Color(153, 153, 153));
        lbStatus.setText(" Registro ");
        lbStatus.setToolTipText("");
        jToolBar1.add(lbStatus);

        txtDescricao.setColumns(60);
        txtDescricao.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtDescricao.setToolTipText("");
        txtDescricao.setEnabled(false);
        txtDescricao.setName("unid_descricao"); // NOI18N

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabel3.setText("Lote :");
        jLabel3.setToolTipText("");

        lbID.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lbID.setText("0");
        lbID.setToolTipText("ID");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabel2.setText("Nº Ordem de Produção :");
        jLabel2.setToolTipText("");

        txtUnidade.setColumns(3);
        txtUnidade.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtUnidade.setToolTipText("");
        txtUnidade.setEnabled(false);
        txtUnidade.setName("unid_unidade"); // NOI18N

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabel6.setText("Quantidade a produzir :");
        jLabel6.setToolTipText("");

        txtDescricao1.setColumns(60);
        txtDescricao1.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtDescricao1.setToolTipText("");
        txtDescricao1.setEnabled(false);
        txtDescricao1.setName("unid_descricao"); // NOI18N

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Data de Entrada :");
        jLabel9.setToolTipText("");

        txtDescricao4.setColumns(60);
        txtDescricao4.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtDescricao4.setToolTipText("");
        txtDescricao4.setEnabled(false);
        txtDescricao4.setName("unid_descricao"); // NOI18N

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabel10.setText("Hora Entrada :");
        jLabel10.setToolTipText("");

        txtDescricao5.setColumns(60);
        txtDescricao5.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtDescricao5.setToolTipText("");
        txtDescricao5.setEnabled(false);
        txtDescricao5.setName("unid_descricao"); // NOI18N

        jLabel11.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Data de Saída :");
        jLabel11.setToolTipText("");

        txtDescricao6.setColumns(60);
        txtDescricao6.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtDescricao6.setToolTipText("");
        txtDescricao6.setEnabled(false);
        txtDescricao6.setName("unid_descricao"); // NOI18N

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabel12.setText("Hora Saída :");
        jLabel12.setToolTipText("");

        txtDescricao7.setColumns(60);
        txtDescricao7.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtDescricao7.setToolTipText("");
        txtDescricao7.setEnabled(false);
        txtDescricao7.setName("unid_descricao"); // NOI18N

        jLabel18.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Máquina :");
        jLabel18.setToolTipText("");

        txtDescricao12.setColumns(60);
        txtDescricao12.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtDescricao12.setToolTipText("");
        txtDescricao12.setEnabled(false);
        txtDescricao12.setName("unid_descricao"); // NOI18N

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Produto :");
        jLabel7.setToolTipText("");

        txtCodProduto.setColumns(15);
        txtCodProduto.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtCodProduto.setToolTipText("");
        txtCodProduto.setEnabled(false);
        txtCodProduto.setName("");
        txtCodProduto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodProdutoFocusLost(evt);
            }
        });
        txtCodProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodProdutoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodProdutoKeyReleased(evt);
            }
        });

        btnPesquisaProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Pesquisar.gif"))); // NOI18N
        btnPesquisaProduto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPesquisaProduto.setEnabled(false);
        btnPesquisaProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaProdutoActionPerformed(evt);
            }
        });

        txtDescricaoProduto.setEditable(false);
        txtDescricaoProduto.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtDescricaoProduto.setForeground(new java.awt.Color(102, 102, 255));
        txtDescricaoProduto.setToolTipText("");
        txtDescricaoProduto.setFocusable(false);
        txtDescricaoProduto.setName("");
        txtDescricaoProduto.setRequestFocusEnabled(false);

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabel8.setText("UN :");
        jLabel8.setToolTipText("");

        txtUN.setBackground(new java.awt.Color(238, 238, 238));
        txtUN.setEditable(false);
        txtUN.setForeground(new java.awt.Color(102, 102, 255));
        txtUN.setToolTipText("");
        txtUN.setFocusable(false);
        txtUN.setName("");
        txtUN.setRequestFocusEnabled(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Observação", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 10))); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel13.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/warning.gif"))); // NOI18N
        jLabel13.setText("Tecla Ctrl - Quebra de linha.");
        jLabel13.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13))
        );

        jLabel14.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Código :");
        jLabel14.setToolTipText("");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/novo.gif"))); // NOI18N
        btnNovo.setToolTipText("Novo");
        btnNovo.setBorderPainted(false);
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Localizar.gif"))); // NOI18N
        btnPesquisa.setToolTipText("Pesquisar");
        btnPesquisa.setBorderPainted(false);
        btnPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaActionPerformed(evt);
            }
        });

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Sair.gif"))); // NOI18N
        btnSair.setToolTipText("Voltar");
        btnSair.setBorderPainted(false);
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/salvar.gif"))); // NOI18N
        btnSalvar.setToolTipText("Salvar");
        btnSalvar.setBorderPainted(false);
        btnSalvar.setEnabled(false);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/cancelar.gif"))); // NOI18N
        btnCancelar.setToolTipText("Cancelar");
        btnCancelar.setBorderPainted(false);
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
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

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Engrenagem.png"))); // NOI18N
        jButton1.setToolTipText("Produção");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtIDProduto.setToolTipText("Código");
        txtIDProduto.setName("eng_prod_codigo_id");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 239, Short.MAX_VALUE)
                .addComponent(txtIDProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnPesquisa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnSair, javax.swing.GroupLayout.Alignment.LEADING, 0, 20, Short.MAX_VALUE)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
                        .addComponent(btnAlterar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtIDProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCodProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtDescricao6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(txtDescricao7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(txtUnidade, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDescricaoProduto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUN, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbID))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtDescricao4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtDescricao5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtDescricao12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtDescricao1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lbID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCodProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDescricaoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(txtUN, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnPesquisaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDescricao1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtDescricao4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDescricao5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDescricao6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtDescricao7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtDescricao12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodProdutoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodProdutoFocusLost
        // TODO add your handling code here:
        if (!txtCodProduto.getText().equals("")) {
            String vmCampos = "*";
            String vmCondicao_Consulta = " INNER JOIN unidade_de_medida on (unid_codigo_id=prod_unid_codigo_id) WHERE prod_codigo_produto ='" + txtCodProduto.getText() + "'";
            try {
                rs = AcessoDados.Selecao("produtos", vmCampos, vmCondicao_Consulta);
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "Produto não cadastrado no sistema");
                    txtCodProduto.setText(null);
                    txtCodProduto.requestFocus();
                    return;
                }
                txtDescricaoProduto.setText(rs.getString("prod_descricao"));
                txtUN.setText(rs.getString("unid_unidade"));
                txtIDProduto.setText(rs.getString("prod_codigo_id"));
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_txtCodProdutoFocusLost

    private void txtCodProdutoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProdutoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 114) //tecla F3 para pesquisa
        {
            btnPesquisaProdutoActionPerformed(null);
        }
    }//GEN-LAST:event_txtCodProdutoKeyPressed

    private void txtCodProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProdutoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodProdutoKeyReleased

    private void btnPesquisaProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaProdutoActionPerformed
        // TODO add your handling code here:
        variaveis.xColuna1 = "prod_codigo_id";
        variaveis.xColuna2 = "prod_codigo_produto";
        variaveis.xColuna3 = "prod_descricao";
        variaveis.xColuna4 = "prod_descricao_generica";
        variaveis.xColuna5 = "''";
        variaveis.xColuna6 = "''";
        variaveis.xColuna7 = "''";
        variaveis.xColuna8 = "''";
        variaveis.xColuna9 = "''";

        variaveis.xColunaNome1 = "Código";
        variaveis.xColunaNome2 = "Cód Produto";
        variaveis.xColunaNome3 = "Descrição";
        variaveis.xColunaNome4 = "Descrição Genérica";
        variaveis.xColunaNome5 = "";
        variaveis.xColunaNome6 = "";
        variaveis.xColunaNome7 = "";
        variaveis.xColunaNome8 = "";
        variaveis.xColunaNome9 = "";

        variaveis.xColunaTamanho1 = 60;
        variaveis.xColunaTamanho2 = 80;
        variaveis.xColunaTamanho3 = 400;
        variaveis.xColunaTamanho4 = 300;
        variaveis.xColunaTamanho5 = 0;
        variaveis.xColunaTamanho6 = 0;
        variaveis.xColunaTamanho7 = 0;
        variaveis.xColunaTamanho8 = 0;
        variaveis.xColunaTamanho9 = 0;

        variaveis.xColunaStart = "prod_descricao";
        variaveis.xColunaNomeStart = "Descrição";
        variaveis.xTabela = "produtos";
        variaveis.xSql = "";

        FpesqPesquisa md = new FpesqPesquisa(null, true);
        Dimension d = new Dimension();
        d.setSize(850, 480);
        md.setSize(d);

        md.setTitle("Pesquisa Produto - ENTER ou DUPLO CLICK no registro retorna dados.");

        md.setLocationRelativeTo(null);
        md.setVisible(true);
        txtIDProduto.setText((String) md.getRetorno());
        if (!txtIDProduto.getText().equals("")) {
            String vmCampos = "*";
            String vmCondicao_Consulta = " INNER JOIN unidade_de_medida on (unid_codigo_id=prod_unid_codigo_id) WHERE prod_codigo_id = " + txtIDProduto.getText();
            try {
                rs = AcessoDados.Selecao("produtos", vmCampos, vmCondicao_Consulta);
                rs.next();
                txtCodProduto.setText(rs.getString("prod_codigo_produto"));
                txtDescricaoProduto.setText(rs.getString("prod_descricao"));
                txtUN.setText(rs.getString("unid_unidade"));
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }//GEN-LAST:event_btnPesquisaProdutoActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:
        if (funcoes.Permissao("001", variaveis.usuario_id, variaveis.usuario_super, this.getTitle())) {
        }
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaActionPerformed
        // TODO add your handling code here:
        if (funcoes.Permissao("004", variaveis.usuario_id, variaveis.usuario_super, this.getTitle())) {
            variaveis.xColuna1 = "eng_codigo_id";
            variaveis.xColuna2 = "prod_descricao";
            variaveis.xColuna3 = "func_nome";
            variaveis.xColuna4 = "to_char(eng_data,'DD/MM/YYYY')";
            variaveis.xColuna5 = "''";
            variaveis.xColuna6 = "''";
            variaveis.xColuna7 = "''";
            variaveis.xColuna8 = "''";
            variaveis.xColuna9 = "''";

            variaveis.xColunaNome1 = "Código";
            variaveis.xColunaNome2 = "Produto";
            variaveis.xColunaNome3 = "Solicitante";
            variaveis.xColunaNome4 = "Data";
            variaveis.xColunaNome5 = "";
            variaveis.xColunaNome6 = "";
            variaveis.xColunaNome7 = "";
            variaveis.xColunaNome8 = "";
            variaveis.xColunaNome9 = "";

            variaveis.xColunaTamanho1 = 60;
            variaveis.xColunaTamanho2 = 400;
            variaveis.xColunaTamanho3 = 300;
            variaveis.xColunaTamanho4 = 80;
            variaveis.xColunaTamanho5 = 0;
            variaveis.xColunaTamanho6 = 0;
            variaveis.xColunaTamanho7 = 0;
            variaveis.xColunaTamanho8 = 0;
            variaveis.xColunaTamanho9 = 0;

            variaveis.xColunaStart = "eng_codigo_id";
            variaveis.xColunaNomeStart = "Código";
            variaveis.xTabela = "engenharia";
            variaveis.xSql = "INNER JOIN produtos on(prod_codigo_id=eng_prod_codigo_id)"
                    + " INNER JOIN funcionarios on (func_codigo_id=eng_func_codigo_id)";

            FpesqPesquisa md = new FpesqPesquisa(null, true);
            Dimension d = new Dimension();
            d.setSize(850, 480);
            md.setSize(d);

            md.setTitle("Pesquisa Engenharia - ENTER ou DUPLO CLICK no registro retorna dados.");

            md.setLocationRelativeTo(null);
            md.setVisible(true);
            lbID.setText((String) md.getRetorno());
            if (!lbID.getText().equals("")) {
                String vmCampos = "*";
                String vmCondicao_Consulta = " WHERE eng_codigo_id = " + lbID.getText();
                funcoes.RetornarSelecao(rootPane, "engenharia", vmCampos, vmCondicao_Consulta);
                try {
                    rs = AcessoDados.Selecao("produtos", "prod_codigo_produto", "where prod_codigo_id=" + txtIDProduto.getText());
                    rs.next();
                    txtCodProduto.setText(rs.getString("prod_codigo_produto"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        txtCodProdutoFocusLost(null);
    }//GEN-LAST:event_btnPesquisaActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        funcoes.limparTodosCampos(rootPane);
        funcoes.HabilitaCampos(rootPane, false);
        btnNovo.requestFocus();

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAlterarActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed1
        // TODO add your handling code here:

    }//GEN-LAST:event_btnAlterarActionPerformed1

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        if (funcoes.Permissao("003", variaveis.usuario_id, variaveis.usuario_super, this.getTitle())) {
            {
                if (lbID.getText().equals("0")) {
                    JOptionPane.showMessageDialog(null, "Selecione um registro antes de excluir");
                    return;
                }

                Object[] options = {" Sim ", " Não "};
                int confirmar = JOptionPane.showOptionDialog(null, "Confirma Exclusão", " Mensagem...", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (confirmar == 1) {
                    //txtDescricao.requestFocus();
                    return;
                }
                try {
                    String vmCondicao = "eng_codigo_id = " + lbID.getText();

                    AcessoDados.Excluir("engenharia", vmCondicao);
                    //AcessoDados.Excluir("engenharia_item", " cc_cp_codigo_id = " + lbID.getText());

                } catch (Exception e) {
                    e.printStackTrace();
                }

                btnCancelarActionPerformed(null);
                funcoes.HabilitaCampos(rootPane, false);
                variaveis.vm_ID = 0;
            }
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
      FrmProducao md = new FrmProducao(null, true);
      md.setLocationRelativeTo(null);
      md.setVisible(true);
        
   }//GEN-LAST:event_jButton1ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmUnidadeMedida dialog = new FrmUnidadeMedida(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisa;
    private javax.swing.JButton btnPesquisaProduto;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbID;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JTextField txtCodProduto;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtDescricao1;
    private javax.swing.JTextField txtDescricao12;
    private javax.swing.JTextField txtDescricao4;
    private javax.swing.JTextField txtDescricao5;
    private javax.swing.JTextField txtDescricao6;
    private javax.swing.JTextField txtDescricao7;
    private javax.swing.JTextField txtDescricaoProduto;
    private javax.swing.JTextField txtIDProduto;
    private javax.swing.JTextField txtUN;
    private javax.swing.JTextField txtUnidade;
    // End of variables declaration//GEN-END:variables

}
