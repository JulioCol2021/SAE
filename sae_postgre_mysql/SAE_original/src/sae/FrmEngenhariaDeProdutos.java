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
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutionException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.TabExpander;
import javax.swing.table.*;

/**
 *
 * @author Thiago
 */
public class FrmEngenhariaDeProdutos extends javax.swing.JDialog {
    claVariaveis variaveis = new claVariaveis();
    claAcessoDados AcessoDados = new claAcessoDados();
    claFuncoes funcoes = new claFuncoes();
    private ResultSet rs, rsLote;
    private DefaultTableModel dtm = new DefaultTableModel();
 

    /** Creates new form FrmAlmoxarifados */
    public FrmEngenhariaDeProdutos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        funcoes.F_AtribuirClasse(rootPane);
        btnNovo.setFocusTraversalKeysEnabled(false);
        btnSalvar.setFocusTraversalKeysEnabled(false);
        btnItens.setFocusTraversalKeysEnabled(false);
        TableEnterAction(grDados);
        //grDados.setDefaultRenderer(Object.class, new TabelaItemEntrada());
        MontaTituloGrid();
        txtIDProduto.setVisible(false);
       
       
    }
    public void MontaTituloGrid()
    {
       grDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"Código", "Descrição", "UM", "Lote", "QTDE", "", ""
            }) { boolean[]  canEdit = new boolean [] {
                true, false, false, true, true, false, true
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }

        });
        grDados.getColumnModel().getColumn(0).setPreferredWidth(20);
        grDados.getColumnModel().getColumn(1).setPreferredWidth(450);
        grDados.getColumnModel().getColumn(2).setPreferredWidth(10);
        grDados.getColumnModel().getColumn(3).setPreferredWidth(30);
        grDados.getColumnModel().getColumn(4).setPreferredWidth(30);
        grDados.getColumnModel().getColumn(5).setPreferredWidth(0);
        grDados.getColumnModel().getColumn(6).setPreferredWidth(10);

        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();

        TableColumn IDColumn = grDados.getColumnModel().getColumn(5);

        IDColumn.setMinWidth(0);
        IDColumn.setMaxWidth(0);


        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);

        grDados.getColumnModel().getColumn(0).setCellRenderer(esquerda);
        grDados.getColumnModel().getColumn(3).setCellRenderer(direita);
        grDados.getColumnModel().getColumn(4).setCellRenderer(direita);

        ButtonColumn buttonColumn = new ButtonColumn(grDados, 6);

    }

    public void MontaGrid()
    {
        String vmCampos = " prod_descricao, prod_codigo_id, prod_codigo_produto, prod_estoque_atual,  item_codigo_id, item_lote, item_quantidade, unid_unidade ";
        String vmCondicao_Consulta = " LEFT join produtos on (prod_codigo_id = item_prod_codigo_id) " +
                                     " LEFT join unidade_de_medida on (prod_unid_codigo_id = unid_codigo_id)   " +
                                     " where item_ent_codigo_id = " + lbID.getText() + " order by item_codigo_id";
        try
        {
        DefaultTableModel tableModel =(DefaultTableModel) grDados.getModel();
        tableModel.setNumRows(0);
        rs = AcessoDados.Selecao("Item_entrada_interna", vmCampos, vmCondicao_Consulta);
        MontaTituloGrid();
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
        while (rs.next())
        {
            dtm.addRow(new Object[]{rs.getString("prod_codigo_produto"),
                                    rs.getString("prod_descricao"),
                                    rs.getString("unid_unidade"),
                                    rs.getString("item_lote"),
                                    funcoes.formataNumero(rs.getString("item_quantidade").replace(".", ","), rs.getString("unid_unidade")),
                                    rs.getString("prod_codigo_id")});
        }
        rs.close();


         }catch (Exception e) {
           e.printStackTrace();
        }
    }

 public void MontaGridPesquisaCodigo()
    {
    }

 public void MontaGridPesquisaDescricao()
    {

    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        lbStatus = new javax.swing.JLabel();
        pnlDados = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbID = new javax.swing.JLabel();
        txtEmpresa = new javax.swing.JTextField();
        btnPesquisaEmpresa = new javax.swing.JButton();
        txtNomeEmpresa = new javax.swing.JTextField();
        txtCodProduto = new javax.swing.JTextField();
        btnPesquisaProduto = new javax.swing.JButton();
        txtDescricaoProduto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        grDados = new javax.swing.JTable();
        btnItens = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUN = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        txtSolicitante = new javax.swing.JTextField();
        btnPesquisaSolicitante = new javax.swing.JButton();
        txtNomeFuncionario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservacao = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        txtData = new javax.swing.JFormattedTextField();
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
        setTitle("Engenharia de Produtos");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        lbStatus.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lbStatus.setForeground(new java.awt.Color(153, 153, 153));
        lbStatus.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbStatus.setText("Registro ");
        jToolBar1.add(lbStatus);

        pnlDados.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabel1.setText("Código :");
        jLabel1.setToolTipText("");

        lbID.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lbID.setText("0");
        lbID.setToolTipText("ID");

        txtEmpresa.setColumns(8);
        txtEmpresa.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtEmpresa.setToolTipText("Código");
        txtEmpresa.setEnabled(false);
        txtEmpresa.setName("eng_emp_codigo_id");
        txtEmpresa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEmpresaFocusLost(evt);
            }
        });
        txtEmpresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmpresaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmpresaKeyReleased(evt);
            }
        });

        btnPesquisaEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Pesquisar.gif"))); // NOI18N
        btnPesquisaEmpresa.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPesquisaEmpresa.setEnabled(false);
        btnPesquisaEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaEmpresaActionPerformed(evt);
            }
        });

        txtNomeEmpresa.setEditable(false);
        txtNomeEmpresa.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtNomeEmpresa.setForeground(new java.awt.Color(102, 102, 255));
        txtNomeEmpresa.setToolTipText("");
        txtNomeEmpresa.setFocusable(false);
        txtNomeEmpresa.setName("");
        txtNomeEmpresa.setRequestFocusEnabled(false);

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

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabel5.setText("Data :");
        jLabel5.setToolTipText("");

        grDados.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        grDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        grDados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grDadosMouseClicked(evt);
            }
        });
        grDados.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                grDadosFocusGained(evt);
            }
        });
        grDados.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                grDadosKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                grDadosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(grDados);

        btnItens.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        btnItens.setText("Digitar Itens");
        btnItens.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnItens.setEnabled(false);
        btnItens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnItensActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/warning.gif"))); // NOI18N
        jLabel8.setText("0 - Coluna Código pesquisa produto");
        jLabel8.setToolTipText("");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel9.setText("--- Itens Composição ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabel3.setText("UN :");
        jLabel3.setToolTipText("");

        txtUN.setBackground(new java.awt.Color(238, 238, 238));
        txtUN.setEditable(false);
        txtUN.setForeground(new java.awt.Color(102, 102, 255));
        txtUN.setToolTipText("");
        txtUN.setFocusable(false);
        txtUN.setName("");
        txtUN.setRequestFocusEnabled(false);

        jLabel14.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabel14.setText("Fórmula Unitária :");
        jLabel14.setToolTipText("");

        jTextField5.setColumns(30);
        jTextField5.setToolTipText("");
        jTextField5.setEnabled(false);
        jTextField5.setName("eng_formula_unitaria");

        jLabel16.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabel16.setText("Tempo :");
        jLabel16.setToolTipText("");

        jFormattedTextField1.setColumns(8);
        try {
            jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField1.setToolTipText("");
        jFormattedTextField1.setEnabled(false);
        jFormattedTextField1.setName("eng_tempo");

        txtSolicitante.setColumns(8);
        txtSolicitante.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtSolicitante.setToolTipText("Código");
        txtSolicitante.setEnabled(false);
        txtSolicitante.setName("eng_func_codigo_id");
        txtSolicitante.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSolicitanteFocusLost(evt);
            }
        });
        txtSolicitante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSolicitanteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSolicitanteKeyReleased(evt);
            }
        });

        btnPesquisaSolicitante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Pesquisar.gif"))); // NOI18N
        btnPesquisaSolicitante.setToolTipText("");
        btnPesquisaSolicitante.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPesquisaSolicitante.setEnabled(false);
        btnPesquisaSolicitante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaSolicitanteActionPerformed(evt);
            }
        });

        txtNomeFuncionario.setEditable(false);
        txtNomeFuncionario.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtNomeFuncionario.setForeground(new java.awt.Color(102, 102, 255));
        txtNomeFuncionario.setToolTipText("");
        txtNomeFuncionario.setFocusable(false);
        txtNomeFuncionario.setName("");
        txtNomeFuncionario.setRequestFocusEnabled(false);

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabel2.setText("Empresa :");
        jLabel2.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabel4.setText("Produto :");
        jLabel4.setToolTipText("");

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabel10.setText("Solicitante :");
        jLabel10.setToolTipText("");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Observação", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 10))); // NOI18N

        txtObservacao.setColumns(255);
        txtObservacao.setRows(5);
        txtObservacao.setToolTipText("");
        txtObservacao.setWrapStyleWord(true);
        txtObservacao.setEnabled(false);
        txtObservacao.setName("eng_observacao");
        txtObservacao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtObservacaoFocusLost(evt);
            }
        });
        txtObservacao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtObservacaoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtObservacaoKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(txtObservacao);

        jLabel13.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/warning.gif"))); // NOI18N
        jLabel13.setText("Tecla Ctrl - Quebra de linha.");
        jLabel13.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtData.setEditable(false);
        txtData.setForeground(new java.awt.Color(102, 102, 255));
        txtData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        txtData.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtData.setToolTipText("");
        txtData.setFocusable(false);
        txtData.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtData.setName("eng_data");
        txtData.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnItens, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8))
                    .addComponent(jScrollPane1))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbID)
                                .addGap(757, 757, 757))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(txtSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnPesquisaSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(txtEmpresa)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnPesquisaEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtCodProduto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtNomeFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(btnPesquisaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtDescricaoProduto))
                                            .addComponent(txtNomeEmpresa))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtUN, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(74, 74, 74))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbID)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNomeEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnPesquisaEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCodProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addComponent(btnPesquisaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDescricaoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(txtUN, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPesquisaSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNomeFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10)
                    .addComponent(txtSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnItens, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addContainerGap())
        );

        pnlDados.addTab("Engenharia", jPanel1);

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

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Imprimir.gif"))); // NOI18N
        jButton1.setToolTipText("Imprimir");
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIDProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlDados, javax.swing.GroupLayout.PREFERRED_SIZE, 940, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDados, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void txtEmpresaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmpresaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmpresaKeyReleased

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:
        if(funcoes.Permissao("001", variaveis.usuario_id, variaveis.usuario_super, this.getTitle()))
        {
        funcoes.limparTodosCampos(rootPane);
        funcoes.HabilitaCampos(rootPane, true);
        Date data = new Date(System.currentTimeMillis());
        SimpleDateFormat formatarDate = new SimpleDateFormat("dd/MM/yyyy");
        txtData.setText(formatarDate.format(data)) ;
        variaveis.status = "I";
        lbStatus.setText("Incluindo... ");
        txtEmpresa.requestFocus();
        }
    }//GEN-LAST:event_btnNovoActionPerformed

   
    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
         if (txtEmpresa.getText().equals(null) || (txtEmpresa.getText().equals("")))
            {
                JOptionPane.showMessageDialog(null,"Empresa não informada");
                txtEmpresa.requestFocus();
                return;
            }

            if (txtCodProduto.getText().equals(null) || (txtCodProduto.getText().equals("")))
            {
                JOptionPane.showMessageDialog(null,"Código do produto não informado");
                txtCodProduto.requestFocus();
                return;
            }

            if (txtSolicitante.getText().equals(null) || (txtSolicitante.getText().equals("")))
            {
                JOptionPane.showMessageDialog(null,"Solicitante não informado");
                txtSolicitante.requestFocus();
                return;
            }
        funcoes.GravarDados(lbID.getText(), rootPane, "engenharia", "eng_codigo_id",true);
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

    private void grDadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grDadosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_grDadosMouseClicked

    private void grDadosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grDadosKeyReleased
        // TODO add your handling code here:
          
     
    }//GEN-LAST:event_grDadosKeyReleased

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
       //  MontaGrid();
    }//GEN-LAST:event_formWindowActivated

    private void txtEmpresaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmpresaFocusLost
        // TODO add your handling code here:
        if (!txtEmpresa.getText().equals("")) {
            String vmCampos = "*";
            String vmCondicao_Consulta = " WHERE emp_codigo_id = " + txtEmpresa.getText();
            try {
                rs = AcessoDados.Selecao("empresas", vmCampos, vmCondicao_Consulta);
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "Empresa não cadastrada no sistema");
                    txtEmpresa.setText(null);
                    txtEmpresa.setText(null);
                    txtEmpresa.requestFocus();
                    return;
                }
                txtNomeEmpresa.setText(rs.getString("emp_nome"));
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            txtEmpresa.setText(null);
        }
    }//GEN-LAST:event_txtEmpresaFocusLost

    private void btnPesquisaEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaEmpresaActionPerformed
        // TODO add your handling code here:
        variaveis.xColuna1 = "emp_codigo_id";
         variaveis.xColuna2 = "emp_nome";
         variaveis.xColuna3 = "emp_nome_fantasia";
         variaveis.xColuna4 = "emp_cnpj";
         variaveis.xColuna5 = "''";
         variaveis.xColuna6 = "''";
         variaveis.xColuna7 = "''";
         variaveis.xColuna8 = "''";
         variaveis.xColuna9 = "''";
         
         variaveis.xColunaNome1 = "Código";
         variaveis.xColunaNome2 = "Razão Social";
         variaveis.xColunaNome3 = "Fantasia";
         variaveis.xColunaNome4 = "CPF / CNPJ";
         variaveis.xColunaNome5 = "";
         variaveis.xColunaNome6 = "";
         variaveis.xColunaNome7 = "";
         variaveis.xColunaNome8 = "";
         variaveis.xColunaNome9 = "";
         
         variaveis.xColunaTamanho1 = 60;
         variaveis.xColunaTamanho2 = 450;
         variaveis.xColunaTamanho3 = 260;
         variaveis.xColunaTamanho4 = 120;
         variaveis.xColunaTamanho5 = 0;
         variaveis.xColunaTamanho6 = 0;
         variaveis.xColunaTamanho7 = 0;
         variaveis.xColunaTamanho8 = 0;
         variaveis.xColunaTamanho9 = 0;
         
         variaveis.xColunaStart = "emp_nome";
         variaveis.xColunaNomeStart = "Razão Social";
         variaveis.xTabela = "empresas";
         variaveis.xSql = ""; 
         
         FpesqPesquisa md = new FpesqPesquisa(null, true);
         Dimension d = new Dimension();   
         d.setSize(900, 480); 
         md.setSize(d);
         
         md.setTitle("Pesquisa Empresa - ENTER ou DUPLO CLICK no registro retorna dados.");

         md.setLocationRelativeTo(null);
         md.setVisible(true);
       txtEmpresa.setText(null);
       txtEmpresa.setText((String) md.getRetorno());
       txtEmpresa.requestFocus();
       if(!txtEmpresa.getText().equals(""))
       {
           txtEmpresaFocusLost(null);

       }
 
    }//GEN-LAST:event_btnPesquisaEmpresaActionPerformed

    private void txtObservacaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacaoKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtObservacaoKeyPressed

    private void txtObservacaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacaoKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_txtObservacaoKeyReleased

    private void txtObservacaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtObservacaoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtObservacaoFocusLost

    private void btnPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaActionPerformed
        // TODO add your handling code here:
        if(funcoes.Permissao("004", variaveis.usuario_id, variaveis.usuario_super, this.getTitle()))
        {
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
       if(!lbID.getText().equals(""))
       {
          String vmCampos = "*";
          String vmCondicao_Consulta = " WHERE eng_codigo_id = " + lbID.getText();
          funcoes.RetornarSelecao(rootPane,"engenharia", vmCampos, vmCondicao_Consulta);
          try
          {
          rs = AcessoDados.Selecao("produtos", "prod_codigo_produto", "where prod_codigo_id=" + txtIDProduto.getText());
          rs.next();
          txtCodProduto.setText(rs.getString("prod_codigo_produto"));
          }catch (Exception e) {
                e.printStackTrace();}
        }
        }
       txtCodProdutoFocusLost(null);

    }//GEN-LAST:event_btnPesquisaActionPerformed

    private void grDadosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grDadosKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_grDadosKeyPressed

    private void grDadosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grDadosFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_grDadosFocusGained

    private void btnItensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnItensActionPerformed
        // TODO add your handling code here:
        int t = grDados.getRowCount();
        if(grDados.getRowCount() == 0)
        {
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
        dtm.addRow(new Object[]{"","","","","",""});
        grDados.setCellSelectionEnabled(true);
        grDados.setRowSelectionInterval(0, 0);
        grDados.setColumnSelectionInterval(0, 0);
        grDados.editCellAt(0, 0);
        }else
        {
          javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
          dtm.addRow(new Object[]{"","","","","",""});
          grDados.setCellSelectionEnabled(true);
          grDados.setRowSelectionInterval(t, t);
          grDados.setColumnSelectionInterval(0, 0);
          grDados.editCellAt(t, 0);
        }
        grDados.requestFocus();

}//GEN-LAST:event_btnItensActionPerformed

    private void btnAlterarActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed1
        // TODO add your handling code here:
        if (txtEmpresa.getText().equals(null) || (txtEmpresa.getText().equals("")))
        {
            JOptionPane.showMessageDialog(null,"Nenhum registro para alterar.");
            return;
        }
        if(funcoes.Permissao("002", variaveis.usuario_id, variaveis.usuario_super, this.getTitle()))
        {
        funcoes.HabilitaCampos(rootPane, true);
        txtEmpresa.requestFocus();
        lbStatus.setText(" Alterando");
        }
       
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
        lbStatus.setText("Registro ");
        }
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtCodProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProdutoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodProdutoKeyReleased

    private void txtCodProdutoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodProdutoFocusLost
        // TODO add your handling code here:
        if (!txtCodProduto.getText().equals("")) {
            String vmCampos = "*";
             String vmCondicao_Consulta = " INNER JOIN unidade_de_medida on (unid_codigo_id=prod_unid_codigo_id) WHERE prod_codigo_produto ='" + txtCodProduto.getText() +"'";
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
        if(!txtIDProduto.getText().equals(""))
       {
          String vmCampos = "*";
          String vmCondicao_Consulta = " INNER JOIN unidade_de_medida on (unid_codigo_id=prod_unid_codigo_id) WHERE prod_codigo_id = " + txtIDProduto.getText();
            try
        {
        rs = AcessoDados.Selecao("produtos", vmCampos, vmCondicao_Consulta);
        rs.next();
        txtCodProduto.setText(rs.getString("prod_codigo_produto"));
        txtDescricaoProduto.setText(rs.getString("prod_descricao"));
        txtUN.setText(rs.getString("unid_unidade"));
        rs.close();
          }catch (Exception e) {
           e.printStackTrace();
        }
   
        }
        
      
    }//GEN-LAST:event_btnPesquisaProdutoActionPerformed

    private void txtSolicitanteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSolicitanteFocusLost
        // TODO add your handling code here:
        if (!txtSolicitante.getText().equals("")) {
            String vmCampos = "*";
            String vmCondicao_Consulta = " WHERE func_codigo_id = " + txtSolicitante.getText();
            try {
                rs = AcessoDados.Selecao("funcionarios", vmCampos, vmCondicao_Consulta);
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "Solicitante não cadastrado no sistema");
                    txtSolicitante.setText(null);
                    txtNomeFuncionario.setText(null);
                    txtSolicitante.requestFocus();
                    return;
                }
                txtNomeFuncionario.setText(rs.getString("func_nome"));
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            txtNomeFuncionario.setText(null);
        }
    }//GEN-LAST:event_txtSolicitanteFocusLost

    private void txtSolicitanteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSolicitanteKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 114) //tecla F3 para pesquisa
        {
            btnPesquisaSolicitanteActionPerformed(null);
        }
    }//GEN-LAST:event_txtSolicitanteKeyPressed

    private void txtSolicitanteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSolicitanteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSolicitanteKeyReleased

    private void btnPesquisaSolicitanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaSolicitanteActionPerformed
        // TODO add your handling code here:
        variaveis.xColuna1 = "func_codigo_id";
        variaveis.xColuna2 = "func_nome";
        variaveis.xColuna3 = "func_cargo";
        variaveis.xColuna4 = "''";
        variaveis.xColuna5 = "''";
        variaveis.xColuna6 = "''";
        variaveis.xColuna7 = "''";
        variaveis.xColuna8 = "''";
        variaveis.xColuna9 = "''";

        variaveis.xColunaNome1 = "Código";
        variaveis.xColunaNome2 = "Nome";
        variaveis.xColunaNome3 = "Cargo";
        variaveis.xColunaNome4 = "";
        variaveis.xColunaNome5 = "";
        variaveis.xColunaNome6 = "";
        variaveis.xColunaNome7 = "";
        variaveis.xColunaNome8 = "";
        variaveis.xColunaNome9 = "";

        variaveis.xColunaTamanho1 = 60;
        variaveis.xColunaTamanho2 = 400;
        variaveis.xColunaTamanho3 = 190;
        variaveis.xColunaTamanho4 = 0;
        variaveis.xColunaTamanho5 = 0;
        variaveis.xColunaTamanho6 = 0;
        variaveis.xColunaTamanho7 = 0;
        variaveis.xColunaTamanho8 = 0;
        variaveis.xColunaTamanho9 = 0;

        variaveis.xColunaStart = "func_nome";
        variaveis.xColunaNomeStart = "Nome";
        variaveis.xTabela = "funcionarios";
        variaveis.xSql = "";

        FpesqPesquisa md = new FpesqPesquisa(null, true);
        Dimension d = new Dimension();
        d.setSize(660, 480);
        md.setSize(d);

        md.setTitle("Pesquisa Funcionário - ENTER ou DUPLO CLICK no registro retorna dados.");

        md.setLocationRelativeTo(null);
        md.setVisible(true);

        txtSolicitante.setText((String) md.getRetorno());
        txtSolicitante.requestFocus();
         if(!txtSolicitante.getText().equals(""))
       {
           txtSolicitanteFocusLost(null);

       }
       
    }//GEN-LAST:event_btnPesquisaSolicitanteActionPerformed

    private void txtEmpresaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmpresaKeyPressed
        // TODO add your handling code here:
          if (evt.getKeyCode()== 114) //tecla F3 para pesquisa
            btnPesquisaEmpresaActionPerformed(null);
    }//GEN-LAST:event_txtEmpresaKeyPressed

    private void txtCodProdutoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProdutoKeyPressed
        // TODO add your handling code here:
          if (evt.getKeyCode()== 114) //tecla F3 para pesquisa
            btnPesquisaProdutoActionPerformed(null);
    }//GEN-LAST:event_txtCodProdutoKeyPressed

  public  void   TableEnterAction(final JTable ao_table) {
      InputMap
      im = ao_table.getInputMap(ao_table.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
      KeyStroke lo_key_enter = KeyStroke.getKeyStroke("ENTER");
      im.put(lo_key_enter, im.get(KeyStroke.getKeyStroke(KeyEvent.VK_GREATER, 0)));
      Action
      enterAction = new AbstractAction() {
                public  void    actionPerformed(ActionEvent e)
                {
                    // Faz o que quiser.
                    int Coluna = ao_table.getSelectedColumn();
                    int linha = ao_table.getSelectedRow();

                    int c =  ao_table.getSelectedRow();
                    int d = ao_table.getRowCount();

                    if(ao_table.getSelectedColumn() == 5)
                    {
                        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)ao_table.getModel();
                           dtm.addRow(new Object[]{"","","","","","",""});
                           Coluna = ao_table.getSelectedColumn();
                           linha++;
                           ao_table.setCellSelectionEnabled(true);
                           //jTable1.setSurrendersFocusOnKeystroke(true);
                           ao_table.setRowSelectionInterval(linha, linha);
                           ao_table.setColumnSelectionInterval(0, 0);
                           ao_table.editCellAt(linha, 0);

                    }else
                    {
                    ao_table.setCellSelectionEnabled(true);
                    //jTable1.setSurrendersFocusOnKeystroke(true);
                    ao_table.setColumnSelectionInterval(Coluna + 1, Coluna + 1);
                    ao_table.editCellAt(linha, Coluna + 1);

                    //aqui fax as pesquisas
                    String CodigoProduto = ao_table.getValueAt(ao_table.getSelectedRow(), 0).toString();
                    if(ao_table.getSelectedColumn() == 4) //saida do lote para  qrde
                    {
                        String lote = ao_table.getValueAt(ao_table.getSelectedRow(), 3).toString();
                        if(lote.equals(""))
                        {
                            ao_table.setValueAt("0", ao_table.getSelectedRow(), 3); // ID Produto
                        }
                    }
                    if(ao_table.getSelectedColumn() == 5) //qtde
                    {
                        try
                        {
                           double xx = Double.parseDouble(ao_table.getValueAt(ao_table.getSelectedRow(), 4).toString().replace(".", "").replace(",", "."));
                        }catch(Exception er)
                        {
                            ao_table.setValueAt("1", ao_table.getSelectedRow(), 4); // ID Produto
                        }
                    }

                    if(!CodigoProduto.equals(""))
                    {
                        if(CodigoProduto.equals("0"))
                        {
                           FpesqPesquisa md = new FpesqPesquisa(null, true);
                           md.setLocationRelativeTo(null);
                           md.setVisible(true);
                           CodigoProduto = md.getRetorno();
                           try
                           {
                             rs = AcessoDados.Selecao("produtos", "*", " where prod_codigo_id = " + CodigoProduto);
                             rs.next();
                             CodigoProduto = rs.getString("prod_codigo_produto");
                           }catch (Exception r) {
                              CodigoProduto = "";
                              r.printStackTrace();
                           }
     
                        }
                        //Buscando Produto
                        String vmCampos = "prod_codigo_id, prod_codigo_produto, prod_descricao, unid_unidade";
                        String vmCondicao_Consulta = " inner join unidade_de_medida on (prod_unid_codigo_id = unid_codigo_id) WHERE prod_codigo_produto = '" + CodigoProduto + "'";
                        try
                        {
                          rs = AcessoDados.Selecao("produtos", vmCampos, vmCondicao_Consulta);
                          if (!rs.next())
                          {
                              JOptionPane.showMessageDialog(null,"Produto não cadastrado no sistema");
                              ao_table.setValueAt("", linha, 0); //Codigo Produto
                              ao_table.setValueAt("", linha, 1); // DescriçãoProduto
                              ao_table.setValueAt("", linha, 2); // Unidade Medida
                              ao_table.setValueAt("", linha, 5); // ID Produto
                              ao_table.setColumnSelectionInterval(0, 0);
                              return;
                          }
                             ao_table.setValueAt(rs.getString("prod_codigo_produto"), linha, 0); // Codigo Produto
                             ao_table.setValueAt(rs.getString("prod_descricao"), linha, 1); // DescriçãoProduto
                             ao_table.setValueAt(rs.getString("unid_unidade"), linha, 2); // Unidade Medida
                             ao_table.setValueAt(rs.getString("prod_codigo_id"), linha, 5); // ID Produto
                             rs.close();
                         }catch (Exception r) {
                              r.printStackTrace();
                        }
                        
                        }else
                        {
                          javax.swing.table.DefaultTableModel Ex = (javax.swing.table.DefaultTableModel)ao_table.getModel();
                          Ex.removeRow(ao_table.getSelectedRow());
                          ao_table.transferFocus();
                          return;
                        }
                    }
                }
        };

        ao_table.getActionMap().put(im.get(lo_key_enter), enterAction);
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmEngenhariaDeProdutos dialog = new FrmEngenhariaDeProdutos(new javax.swing.JFrame(), true);
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
            imgExcluir = new ImageIcon("..MTDS/Imagens/excluir.gif");
            renderButton = new JButton(imgExcluir);
            renderButton.setBorderPainted(false);
            renderButton.setToolTipText("Excluir Item");
            renderButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/excluir.gif")));

            editButton = new JButton();
            editButton.setFocusPainted( false );
            editButton.addActionListener( this );
            editButton.setBorderPainted(false);
            editButton.setToolTipText("Excluir Item");
            editButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/excluir.gif")));


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

            renderButton.setText( (value == null) ? "" : value.toString() );
            return renderButton;
        }

        public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column)
        {
            text = (value == null) ? "" : value.toString();
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
            if(txtEmpresa.isEnabled())
            {
            DefaultTableModel tableModel =(DefaultTableModel) grDados.getModel();
            tableModel.removeRow(grDados.getSelectedRow());
            grDados.setEnabled(true);
            }
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnItens;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisa;
    private javax.swing.JButton btnPesquisaEmpresa;
    private javax.swing.JButton btnPesquisaProduto;
    private javax.swing.JButton btnPesquisaSolicitante;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JTable grDados;
    private javax.swing.JButton jButton1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbID;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JTabbedPane pnlDados;
    private javax.swing.JTextField txtCodProduto;
    private javax.swing.JFormattedTextField txtData;
    private javax.swing.JTextField txtDescricaoProduto;
    private javax.swing.JTextField txtEmpresa;
    private javax.swing.JTextField txtIDProduto;
    private javax.swing.JTextField txtNomeEmpresa;
    private javax.swing.JTextField txtNomeFuncionario;
    private javax.swing.JTextArea txtObservacao;
    private javax.swing.JTextField txtSolicitante;
    private javax.swing.JTextField txtUN;
    // End of variables declaration//GEN-END:variables

}
