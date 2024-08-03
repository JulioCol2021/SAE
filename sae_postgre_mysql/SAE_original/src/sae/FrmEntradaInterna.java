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
import javax.swing.text.JTextComponent;

/**
 *
 * @author Thiago
 */
public class FrmEntradaInterna extends javax.swing.JDialog {
    claVariaveis variaveis = new claVariaveis();
    claAcessoDados AcessoDados = new claAcessoDados();
    claFuncoes funcoes = new claFuncoes();
    private ResultSet rs, rsLote;
    private DefaultTableModel dtm = new DefaultTableModel();
    Dimension dP = new Dimension(); 

    /** Creates new form FrmAlmoxarifados */
    public FrmEntradaInterna(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        funcoes.F_AtribuirClasse(rootPane);
        btnNovo.setFocusTraversalKeysEnabled(false);
        btnSalvar.setFocusTraversalKeysEnabled(false);
        btnItens.setFocusTraversalKeysEnabled(false);
        TableEnterAction(grDados);
        grDados.setDefaultRenderer(Object.class, new TabelaItemEntrada());
        MontaTituloGrid();
       
    }
    public void MontaTituloGrid()
    {
       grDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"* Código", "Descrição", "UM", "Lote", "QTDE", "", ""
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

 public void limpaCampos(){
        lbID.setText("0");
        txtEmpresa.setText(null);
        txtNomeEmpresa.setText(null);
        txtFornecedor.setText(null);
        txtNomeFornecedor.setText(null);
        txtData.setText(null);
        txtControleInterno.setText(null);
        txtObservacao.setText(null);
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
        jLabel2 = new javax.swing.JLabel();
        txtEmpresa = new javax.swing.JTextField();
        btnPesquisaEmpresa = new javax.swing.JButton();
        txtNomeEmpresa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtFornecedor = new javax.swing.JTextField();
        btnPesquisaFornecedor = new javax.swing.JButton();
        txtNomeFornecedor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtControleInterno = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservacao = new javax.swing.JTextArea();
        txtData = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        grDados = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        btnItens = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnPesquisa = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Entrada Interna");
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
        jLabel1.setText("Código:");
        jLabel1.setToolTipText("");

        lbID.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lbID.setText("0");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabel2.setText("Empresa:");
        jLabel2.setToolTipText("");

        txtEmpresa.setColumns(8);
        txtEmpresa.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtEmpresa.setToolTipText("Código");
        txtEmpresa.setEnabled(false);
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
        txtNomeEmpresa.setFocusable(false);
        txtNomeEmpresa.setRequestFocusEnabled(false);

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabel4.setText("Parceiro:");
        jLabel4.setToolTipText("");

        txtFornecedor.setColumns(8);
        txtFornecedor.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtFornecedor.setToolTipText("Código");
        txtFornecedor.setEnabled(false);
        txtFornecedor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFornecedorFocusLost(evt);
            }
        });
        txtFornecedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFornecedorKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFornecedorKeyReleased(evt);
            }
        });

        btnPesquisaFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Pesquisar.gif"))); // NOI18N
        btnPesquisaFornecedor.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPesquisaFornecedor.setEnabled(false);
        btnPesquisaFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaFornecedorActionPerformed(evt);
            }
        });

        txtNomeFornecedor.setEditable(false);
        txtNomeFornecedor.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtNomeFornecedor.setForeground(new java.awt.Color(102, 102, 255));
        txtNomeFornecedor.setFocusable(false);
        txtNomeFornecedor.setRequestFocusEnabled(false);

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabel5.setText("Data Entrada:");
        jLabel5.setToolTipText("");

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabel6.setText("Controle Interno:");
        jLabel6.setToolTipText("");

        txtControleInterno.setColumns(20);
        txtControleInterno.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtControleInterno.setEnabled(false);
        txtControleInterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtControleInternoKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabel7.setText("Observação:");
        jLabel7.setToolTipText("");

        txtObservacao.setColumns(255);
        txtObservacao.setRows(5);
        txtObservacao.setWrapStyleWord(true);
        txtObservacao.setEnabled(false);
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

        txtData.setEditable(false);
        txtData.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtData.setForeground(new java.awt.Color(102, 102, 255));
        txtData.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtData.setFocusable(false);
        txtData.setRequestFocusEnabled(false);

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

        jLabel13.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/warning.gif"))); // NOI18N
        jLabel13.setText("Tecla Ctrl - Quebra de linha.");
        jLabel13.setToolTipText("");

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
        jLabel8.setText("Coluna(s) com * - F3 - Pesquisa");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel9.setText("--- Itens Entrada ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnItens, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 915, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 724, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 644, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(btnPesquisaFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(btnPesquisaEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtNomeFornecedor, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                                                    .addComponent(txtNomeEmpresa))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(16, 16, 16)
                                                .addComponent(lbID)
                                                .addGap(7, 7, 7)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel5))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtData, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                                            .addComponent(txtControleInterno, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbID)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNomeEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addComponent(btnPesquisaEmpresa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtNomeFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtControleInterno, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addComponent(btnPesquisaFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnItens, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(9, 9, 9))
        );

        pnlDados.addTab("Dados", jPanel1);

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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnPesquisa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnSair, javax.swing.GroupLayout.Alignment.LEADING, 0, 20, Short.MAX_VALUE)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlDados)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDados, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        pnlDados.setSelectedIndex(0);
        btnCancelar.setEnabled(true);
        btnSalvar.setEnabled(true);
        btnNovo.setEnabled(false);
        btnPesquisa.setEnabled(false);
        grDados.setEnabled(false);

        limpaCampos();
        DefaultTableModel tableModel =(DefaultTableModel) grDados.getModel();
        tableModel.setNumRows(0);


        txtEmpresa.setEnabled(true);
        txtFornecedor.setEnabled(true);
        txtControleInterno.setEnabled(true);
        txtObservacao.setEnabled(true);

        btnPesquisaEmpresa.setEnabled(true);
        btnPesquisaFornecedor.setEnabled(true);
        btnItens.setEnabled(true);

        Date data = new Date(System.currentTimeMillis());
        SimpleDateFormat formatarDate = new SimpleDateFormat("dd/MM/yyyy");
        txtData.setText(formatarDate.format(data)) ;

        variaveis.status = "I";
        lbStatus.setText("Incluindo... ");
        txtEmpresa.setEnabled(true);
        grDados.setEnabled(true);

        txtEmpresa.requestFocus();
        }
    }//GEN-LAST:event_btnNovoActionPerformed

    public int retornaID() {
        String vmCampos = " max(ent_codigo_id)as ID ";
        String vmCondicao_Consulta = "";
        try
        {
        rs = AcessoDados.Selecao("Entrada_Interna", vmCampos, vmCondicao_Consulta);
        rs.next();
        lbID.setText(rs.getString("ID"));
        return  rs.getInt("ID");
        }catch (Exception e) {
           e.printStackTrace();
           return 0;
        }

    }
    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
       if(grDados.getRowCount() == 0)
       {
           JOptionPane.showMessageDialog(null,"Nenhum item digitado");
           btnItens.requestFocus();
           return;
       }

       //Consiste Itens
       for(int i = 0; i <= grDados.getRowCount() -1; i++)
       {
           String xCodigo = grDados.getValueAt(i, 0).toString();
           if(xCodigo.equals(""))
           {
               javax.swing.table.DefaultTableModel Ex = (javax.swing.table.DefaultTableModel)grDados.getModel();
               Ex.removeRow(i);
               i= 0;
           }
        }

       for(int i = 0; i <= grDados.getRowCount() - 1; i++)
       {
           try
           {
           double xQtde = Double.parseDouble(grDados.getValueAt(i, 4).toString().replace(".", "").replace(",", "."));
           }catch(Exception er)
           {
              JOptionPane.showMessageDialog(null,"Quantidade informada inválida.");
              grDados.setCellSelectionEnabled(true);
              grDados.setRowSelectionInterval(i,i);
              grDados.setColumnSelectionInterval(4, 4);
              //grDados.editCellAt(i, 4);
              grDados.requestFocus();
              return;
           }
           String xLote = grDados.getValueAt(i, 3).toString();
           if(xLote.equals(""))
           {
              JOptionPane.showMessageDialog(null,"Lote não informado.");
              grDados.setCellSelectionEnabled(true);
              grDados.setRowSelectionInterval(i,i);
              grDados.setColumnSelectionInterval(3, 3);
              //grDados.editCellAt(i, 3);
              grDados.requestFocus();
              return;
           }

       }

       if (txtEmpresa.getText().equals("") || txtEmpresa.getText().equals(null))
       {
           JOptionPane.showMessageDialog(null,"Nenhuma empresa foi informada");
           txtEmpresa.requestFocus();
           return;
       }
       if (txtFornecedor.getText().equals("") || txtFornecedor.getText().equals(null))
       {
           JOptionPane.showMessageDialog(null,"Nenhuma fornecedor foi informado");
           txtFornecedor.requestFocus();
           return;
       }
       if (variaveis.status == "I")
        {
            Object[] options = { " Sim ", " Não " };
            int confirmar = JOptionPane.showOptionDialog(null, "Confirma ENTRADA INTERNA de estoque?", " Mensagem...",JOptionPane.DEFAULT_OPTION, JOptionPane. QUESTION_MESSAGE,null,options, options[0]);
            if(confirmar==1){
                txtEmpresa.requestFocus();
                return;
               }
            try
            {
               String vmCampos = "ent_emp_codigo_id, ent_par_codigo_id, ent_data, ent_controle_interno, ent_observacao, ent_usuario";

               String vmParametros =  txtEmpresa.getText() + "," +
                                      txtFornecedor.getText() + "," +
                                      "'" + txtData.getText() + "'," +
                                      "'" + txtControleInterno.getText() + "'," +
                                      "'" + txtObservacao.getText() + "'," +
                                      "'" + variaveis.nome_usuario + "'";

            AcessoDados.Inserir("Entrada_Interna", vmCampos, vmParametros);
            int VMCodigoMaster = retornaID();
            //Incluindo Item Pedido
            vmCampos = "";
            vmCampos += "  item_ent_codigo_id,  ";
            vmCampos += "  item_prod_codigo_id, ";
            vmCampos += "  item_lote, ";
            vmCampos += "  item_quantidade ";

            //Dlecarar vairavel de tabela

            for(int i = 0; i <= grDados.getRowCount() - 1; i++)
            {
              vmParametros = "";
              vmParametros += VMCodigoMaster + ",";
              vmParametros += grDados.getValueAt(i, 5).toString() +",";
              vmParametros += "'" + grDados.getValueAt(i, 3).toString() +"',";
              vmParametros += grDados.getValueAt(i, 4).toString().replace(".", "").replace(",", ".") ;
              AcessoDados.Inserir("item_entrada_interna", vmCampos, vmParametros);
              AcertoEstoque(grDados.getValueAt(i, 0).toString(), grDados.getValueAt(i, 3).toString(), grDados.getValueAt(i, 4).toString());

            }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

       btnCancelarActionPerformed(null);
       lbStatus.setText("Registro ");
        
    }//GEN-LAST:event_btnSalvarActionPerformed

    public void AcertoEstoque(String pProduto, String pLote, String pquantidade)
    {
            try
            {
               //verifica se existe o lote
               String CondicaoConsulta = " where lot_numero_lot = '" + pLote + "' and lot_prod_codigo_id = "+ pProduto ;
               rsLote = AcessoDados.Selecao("lotes", " * ", CondicaoConsulta);
               if (!rsLote.next())
               {
                 //se não achou o lote ele insere um novo
                 String vmCampos = " lot_prod_codigo_id, ";
                 vmCampos += " lot_numero_lot, ";
                 vmCampos += " lot_qtde ";

                String vmParametros = pProduto + "," +
                                      "'" + pLote + "'," +
                                      pquantidade.replace(".", "").replace(",", ".") ;

                AcessoDados.Inserir("lotes", vmCampos, vmParametros);

               }
               else
               {
                 //se achou o lote ele soma a quantidade

                 String vmCampos = " lot_prod_codigo_id, ";
                 vmCampos += " lot_numero_lot, ";
                 vmCampos += " lot_qtde ";

                String vmParametros = "lot_qtde = lot_qtde + " +  pquantidade.replace(".", "").replace(",", ".");
                CondicaoConsulta = " lot_numero_lot = '" + pLote + "' and lot_prod_codigo_id = "+ pProduto ;
                AcessoDados.Alterar("lotes", vmParametros, CondicaoConsulta);
               }
            }catch (Exception e) {
                e.printStackTrace();
            }

    }
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        txtEmpresa.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnSalvar.setEnabled(false);
      
        btnNovo.setEnabled(true);
        btnPesquisa.setEnabled(true);

        txtEmpresa.setEnabled(false);
        txtFornecedor.setEnabled(false);
        txtControleInterno.setEnabled(false);
        txtObservacao.setEnabled(false);

        btnPesquisaEmpresa.setEnabled(false);
        btnPesquisaFornecedor.setEnabled(false);
        btnItens.setEnabled(false);

        grDados.setEnabled(true);
        grDados.setEnabled(false);

        DefaultTableModel tableModel =(DefaultTableModel) grDados.getModel();
        tableModel.setNumRows(0);

        limpaCampos();
        variaveis.status = "";
        btnNovo.requestFocus();
        lbStatus.setText("Registro ");
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
                txtFornecedor.requestFocus();
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

        txtEmpresa.setText((String) md.getRetorno());
        txtEmpresa.requestFocus();
        if (!txtEmpresa.getText().equals("")) {
            String vmCampos = "*";
            String vmCondicao_Consulta = " WHERE emp_codigo_id = " + txtEmpresa.getText();
            try {
                rs = AcessoDados.Selecao("empresas", vmCampos, vmCondicao_Consulta);
                rs.next();
                txtNomeEmpresa.setText(rs.getString("emp_nome"));
                txtEmpresa.requestFocus();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
 
    }//GEN-LAST:event_btnPesquisaEmpresaActionPerformed

    private void txtFornecedorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFornecedorFocusLost
        // TODO add your handling code here:
        if (!txtFornecedor.getText().equals("")) {
            String vmCampos = "*";
            String vmCondicao_Consulta = " WHERE  par_codigo_id = " + txtFornecedor.getText();
            try {
                rs = AcessoDados.Selecao("parceiros", vmCampos, vmCondicao_Consulta);
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "Fornecedor não cadastrado no sistema");
                    txtFornecedor.setText(null);
                    txtNomeFornecedor.setText(null);
                    txtFornecedor.requestFocus();
                    return;
                }
                txtNomeFornecedor.setText(rs.getString("par_nome_razao_social"));
                txtControleInterno.requestFocus();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            txtNomeFornecedor.setText(null);
        }
    }//GEN-LAST:event_txtFornecedorFocusLost

    private void txtFornecedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFornecedorKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFornecedorKeyReleased

    private void btnPesquisaFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaFornecedorActionPerformed
        // TODO add your handling code here:
         variaveis.xColuna1 = "par_codigo_id";
         variaveis.xColuna2 = "par_nome_razao_social";
         variaveis.xColuna3 = "par_fantasia";
         variaveis.xColuna4 = "par_cnpj_cpf";
         variaveis.xColuna5 = "''";
         variaveis.xColuna6 = "''";
         variaveis.xColuna7 = "''";
         variaveis.xColuna8 = "''";
         variaveis.xColuna9 = "''";
         
         variaveis.xColunaNome1 = "Código";
         variaveis.xColunaNome2 = "Nome / Razão Social";
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
         
         variaveis.xColunaStart = "par_nome_razao_social";
         variaveis.xColunaNomeStart = "Nome / Razão Social";
         variaveis.xTabela = "parceiros";
         variaveis.xSql = ""; 
         
         FpesqPesquisa md = new FpesqPesquisa(null, true);
         Dimension d = new Dimension();   
         d.setSize(900, 480); 
         md.setSize(d);
         
         md.setTitle("Pesquisa Parceiros - ENTER ou DUPLO CLICK no registro retorna dados.");

         md.setLocationRelativeTo(null);
         md.setVisible(true);

        txtFornecedor.setText((String) md.getRetorno());
        txtFornecedor.requestFocus();
        if (!txtFornecedor.getText().equals("")) {
            String vmCampos = "*";
            String vmCondicao_Consulta = " WHERE par_codigo_id = " +txtFornecedor.getText();
            try {
                rs = AcessoDados.Selecao("parceiros", vmCampos, vmCondicao_Consulta);
                rs.next();
                txtNomeFornecedor.setText(rs.getString("par_nome_razao_social"));
                txtFornecedor.requestFocus();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_btnPesquisaFornecedorActionPerformed

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
         variaveis.xColuna1 = "ent_codigo_id";
         variaveis.xColuna2 = "par_nome_razao_social";
         variaveis.xColuna3 = "to_char(ent_data, 'DD/MM/YYYY')";
         variaveis.xColuna4 = "ent_controle_interno";
         variaveis.xColuna5 = "''";
         variaveis.xColuna6 = "''";
         variaveis.xColuna7 = "''";
         variaveis.xColuna8 = "''";
         variaveis.xColuna9 = "''";
         
         variaveis.xColunaNome1 = "Código";
         variaveis.xColunaNome2 = "Parceiro";
         variaveis.xColunaNome3 = "Ocorrência";
         variaveis.xColunaNome4 = "Controle Interno";
         variaveis.xColunaNome5 = "";
         variaveis.xColunaNome6 = "";
         variaveis.xColunaNome7 = "";
         variaveis.xColunaNome8 = "";
         variaveis.xColunaNome9 = "";
         
         variaveis.xColunaTamanho1 = 60;
         variaveis.xColunaTamanho2 = 430;
         variaveis.xColunaTamanho3 = 80;
         variaveis.xColunaTamanho4 = 120;
         variaveis.xColunaTamanho5 = 0;
         variaveis.xColunaTamanho6 = 0;
         variaveis.xColunaTamanho7 = 0;
         variaveis.xColunaTamanho8 = 0;
         variaveis.xColunaTamanho9 = 0;
         
         variaveis.xColunaStart = "ent_controle_interno";
         variaveis.xColunaNomeStart = "Controle Interno";
         variaveis.xTabela = "entrada_interna";
         variaveis.xSql = " INNER JOIN PARCEIROS ON (PAR_CODIGO_ID = ent_par_codigo_id) "; 
         
         FpesqPesquisa md = new FpesqPesquisa(null, true);
         Dimension d = new Dimension();   
         d.setSize(700, 480); 
         md.setSize(d);
         
         md.setTitle("Pesquisa Entrada Interna - ENTER ou DUPLO CLICK no registro retorna dados.");

         md.setLocationRelativeTo(null);
         md.setVisible(true);        
         pnlDados.setSelectedIndex(0);
        lbID.setText((String) md.getRetorno());
        if(!lbID.getText().equals(""))
       {
          String vmCampos = "*";
          String vmCondicao_Consulta = " where ent_codigo_id = " + lbID.getText();
          try
        {
        rs = AcessoDados.Selecao("Entrada_interna", vmCampos, vmCondicao_Consulta);
        rs.next();
        txtEmpresa.setText(rs.getString("ent_emp_codigo_id"));
        txtFornecedor.setText(rs.getString("ent_par_codigo_id"));
        Date data = rs.getDate("ent_data");
        SimpleDateFormat formatarDate = new SimpleDateFormat("dd/MM/yyyy");
        txtData.setText(formatarDate.format(data));
        txtControleInterno.setText(rs.getString("ent_controle_interno"));
        txtObservacao.setText(rs.getString("ent_observacao"));
        txtEmpresaFocusLost(null);
        txtFornecedorFocusLost(null);
        MontaGrid();
        btnNovo.requestFocus();
        rs.close();
          }catch (Exception e) {
           e.printStackTrace();
        }
            }
       }

    }//GEN-LAST:event_btnPesquisaActionPerformed

    private void grDadosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grDadosKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 114) //tecla F3 para pesquisa
        {
            if(grDados.getSelectedColumn() == 0)
            {
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
                             
                 dP.setSize(850, 480); 
                 md.setSize(dP);
                 md.setTitle("Pesquisa Produto - ENTER ou DUPLO CLICK no registro retorna dados.");
                 md.setLocationRelativeTo(null);
                 md.setVisible(true);
                 try
                 {
                     
                     rs = AcessoDados.Selecao("produtos", "*", " where prod_codigo_id = " + md.getRetorno());
                     rs.next();
                     DefaultTableModel tabela = (DefaultTableModel) grDados.getModel(); 
                     tabela.setValueAt(rs.getString("prod_codigo_produto"), grDados.getSelectedRow(), 0);
                  }catch (Exception r) {
                     r.printStackTrace();
                 }
            }
        }

        
    }//GEN-LAST:event_grDadosKeyPressed

    private void txtControleInternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtControleInternoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtControleInternoKeyReleased

    private void grDadosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grDadosFocusGained
        // TODO add your handling code here:
  
    }//GEN-LAST:event_grDadosFocusGained

    private void btnItensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnItensActionPerformed
        // TODO add your handling code here:
        JTextField field = new JTextField();  
        DefaultCellEditor editor = new DefaultCellEditor(field) {  
            @Override  
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {  
                Component comp = super.getTableCellEditorComponent(table, value, isSelected, row, column);  
                if (comp instanceof JTextComponent) {  
                    ((JTextComponent)comp).setBackground(new java.awt.Color(255,255,188));
                    ((JTextComponent)comp).selectAll();  
                }  
                return comp;  
            }  
        };  
        grDados.getColumnModel().getColumn(0).setCellEditor(editor);        
        grDados.getColumnModel().getColumn(3).setCellEditor(editor);        
        grDados.getColumnModel().getColumn(4).setCellEditor(editor);        

        int t = grDados.getRowCount();
        if(grDados.getRowCount() == 0)
        {
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
        dtm.addRow(new Object[]{"","","","","",""});
        grDados.setCellSelectionEnabled(true);
        grDados.setRowSelectionInterval(0, 0);
        grDados.setColumnSelectionInterval(0, 0);
        //grDados.editCellAt(0, 0);
        }else
        {
          javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
          dtm.addRow(new Object[]{"","","","","",""});
          grDados.setCellSelectionEnabled(true);
          grDados.setRowSelectionInterval(t, t);
          grDados.setColumnSelectionInterval(0, 0);
          //grDados.editCellAt(t, 0);
        }
        grDados.requestFocus();

}//GEN-LAST:event_btnItensActionPerformed

    private void txtEmpresaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmpresaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 114) //tecla F3 para pesquisa
        {
            btnPesquisaEmpresaActionPerformed(null);
        }
    }//GEN-LAST:event_txtEmpresaKeyPressed

    private void txtFornecedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFornecedorKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 114) //tecla F3 para pesquisa
        {
            btnPesquisaFornecedorActionPerformed(null);
        }
    }//GEN-LAST:event_txtFornecedorKeyPressed

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
                           //ao_table.editCellAt(linha, 0);

                    }else
                    {
                    ao_table.setCellSelectionEnabled(true);
                    //jTable1.setSurrendersFocusOnKeystroke(true);
                    ao_table.setColumnSelectionInterval(Coluna + 1, Coluna + 1);
                    String CodigoProduto = ao_table.getValueAt(ao_table.getSelectedRow(), 0).toString();
                    ao_table.editCellAt(linha, Coluna + 1);

                    //aqui fax as pesquisas
                    
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
                            ao_table.setValueAt("1", ao_table.getSelectedRow(), 4); 
                        }
                    }

                    if(!CodigoProduto.equals(""))
                    {
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
                           String DescricaoProduto = ao_table.getValueAt(ao_table.getSelectedRow(), 1).toString();
                           if(!DescricaoProduto.equals(""))
                           {
                           Object[] options = { " Sim ", " Não " };
                           int confirmar = JOptionPane.showOptionDialog(null, "Confirma Exclusão do Produto?", " Mensagem...",JOptionPane.DEFAULT_OPTION, JOptionPane. QUESTION_MESSAGE,null,options, options[0]);
                           if(confirmar==1){
                               ao_table.setColumnSelectionInterval(0, 0);
                               return;
                           }
                           }
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
                FrmEntradaInterna dialog = new FrmEntradaInterna(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnItens;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisa;
    private javax.swing.JButton btnPesquisaEmpresa;
    private javax.swing.JButton btnPesquisaFornecedor;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JTable grDados;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbID;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JTabbedPane pnlDados;
    private javax.swing.JTextField txtControleInterno;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtEmpresa;
    private javax.swing.JTextField txtFornecedor;
    private javax.swing.JTextField txtNomeEmpresa;
    private javax.swing.JTextField txtNomeFornecedor;
    private javax.swing.JTextArea txtObservacao;
    // End of variables declaration//GEN-END:variables

}
