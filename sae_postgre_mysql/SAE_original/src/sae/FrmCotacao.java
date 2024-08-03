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
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Thiago
 */
public class FrmCotacao extends javax.swing.JDialog {
    claVariaveis variaveis = new claVariaveis();
    claAcessoDados AcessoDados = new claAcessoDados();
    claFuncoes funcoes = new claFuncoes();
    private ResultSet rs, rsLote, rsImprimir;
    private DefaultTableModel dtm = new DefaultTableModel();

    /** Creates new form FrmAlmoxarifados */
    public FrmCotacao(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        funcoes.F_AtribuirClasse(rootPane);
        btnNovo.setFocusTraversalKeysEnabled(false);
        btnSalvar.setFocusTraversalKeysEnabled(false);
        btnItens.setFocusTraversalKeysEnabled(false);
        TableEnterAction(grDados);
        grDados.setDefaultRenderer(Object.class, new TabelaItemCotacao());
        MontaTituloGrid();
        lbItem.setVisible(false);
       
    }
    public void MontaTituloGrid()
    {
       grDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"", "Código", "Descrição", "UM", "QTDE", "Vlr. Unit.", "Vlr. Total"
            }) { boolean[]  canEdit = new boolean [] {
                false, false, false, false, false, true, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }

        });
        grDados.getColumnModel().getColumn(0).setPreferredWidth(0);
        grDados.getColumnModel().getColumn(1).setPreferredWidth(20);
        grDados.getColumnModel().getColumn(2).setPreferredWidth(450);
        grDados.getColumnModel().getColumn(3).setPreferredWidth(10);
        grDados.getColumnModel().getColumn(4).setPreferredWidth(30);
        grDados.getColumnModel().getColumn(5).setPreferredWidth(30);
        grDados.getColumnModel().getColumn(6).setPreferredWidth(40);
        //grDados.getColumnModel().getColumn(7).setPreferredWidth(10);

        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();

        TableColumn IDColumn = grDados.getColumnModel().getColumn(0);

        IDColumn.setMinWidth(0);
        IDColumn.setMaxWidth(0);


        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);

        //grDados.getColumnModel().getColumn(0).setCellRenderer(esquerda);
        //grDados.getColumnModel().getColumn(3).setCellRenderer(direita);
        //grDados.getColumnModel().getColumn(4).setCellRenderer(direita);

        //ButtonColumn buttonColumn = new ButtonColumn(grDados, 7);

    }

    public void MontaGrid()
    {
        String vmCampos = " prod_descricao, prod_codigo_id, prod_codigo_produto, prod_estoque_atual,  item_codigo_id, item_lote, item_quantidade, unid_unidade ";
        String vmCondicao_Consulta = " LEFT join produtos on (prod_codigo_id = item_prod_codigo_id) " +
                                     " LEFT join unidade_de_medida on (prod_unid_codigo_id = unid_codigo_id)   " +
                                     " where item_com_codigo_id = " + lbItem.getText() + " order by PROD_DESCRICAO";
        try
        {
        DefaultTableModel tableModel =(DefaultTableModel) grDados.getModel();
        tableModel.setNumRows(0);
        rs = AcessoDados.Selecao("item_solicitacao_compras", vmCampos, vmCondicao_Consulta);
        MontaTituloGrid();
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
        while (rs.next())
        {
            dtm.addRow(new Object[]{rs.getString("prod_codigo_id"),
                                    rs.getString("prod_codigo_produto"),
                                    rs.getString("prod_descricao"),
                                    rs.getString("unid_unidade"),
                                    funcoes.formataNumero(rs.getString("item_quantidade").replace(".", ","),rs.getString("unid_unidade")),
                                    "",
                                    "0"
                                    });
        }
        rs.close();


         }catch (Exception e) {
           e.printStackTrace();
        }
    }

    
    public void limpaCampos(){
        lbID.setText("0");
        txtParceiro.setText(null);
        txtNomeParceiro.setText(null);
        txtData.setText(null);
        txtControleInterno.setText(null);
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
        jLabel4 = new javax.swing.JLabel();
        txtParceiro = new javax.swing.JTextField();
        btnPesquisaFornecedor = new javax.swing.JButton();
        txtNomeParceiro = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtControleInterno = new javax.swing.JTextField();
        txtData = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        grDados = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtDataEntrega = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        txtFrete = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtCondicaoDePagamentoID = new javax.swing.JTextField();
        btnPesquisaCondicaoPagamentoID = new javax.swing.JButton();
        txtCondicaoDePagamentoDescricao = new javax.swing.JTextField();
        btnItens = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        lbQtde = new javax.swing.JLabel();
        lbStatusOC = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnPesquisa = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnApuracao = new javax.swing.JButton();
        lbItem = new javax.swing.JLabel();
        btnImprimir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cotação / Ordem de Compra");
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

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setText("Cotação:");
        jLabel1.setToolTipText("");

        lbID.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lbID.setText("0");
        lbID.setToolTipText("ID");

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setText("Parceiro:");
        jLabel4.setToolTipText("");

        txtParceiro.setColumns(8);
        txtParceiro.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtParceiro.setToolTipText("Código");
        txtParceiro.setEnabled(false);
        txtParceiro.setName(""); // NOI18N
        txtParceiro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtParceiroFocusLost(evt);
            }
        });
        txtParceiro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtParceiroKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtParceiroKeyReleased(evt);
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

        txtNomeParceiro.setEditable(false);
        txtNomeParceiro.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtNomeParceiro.setForeground(new java.awt.Color(102, 102, 255));
        txtNomeParceiro.setToolTipText("");
        txtNomeParceiro.setFocusable(false);
        txtNomeParceiro.setName(""); // NOI18N
        txtNomeParceiro.setRequestFocusEnabled(false);

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel5.setText("Data Cotação:");
        jLabel5.setToolTipText("");

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel6.setText("Nº Solicitação:");
        jLabel6.setToolTipText("");

        txtControleInterno.setEditable(false);
        txtControleInterno.setColumns(20);
        txtControleInterno.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtControleInterno.setToolTipText("Número");
        txtControleInterno.setEnabled(false);
        txtControleInterno.setFocusable(false);
        txtControleInterno.setName("cot_numero_solicitacao"); // NOI18N
        txtControleInterno.setRequestFocusEnabled(false);
        txtControleInterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtControleInternoKeyReleased(evt);
            }
        });

        txtData.setEditable(false);
        txtData.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtData.setForeground(new java.awt.Color(102, 102, 255));
        txtData.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtData.setToolTipText("");
        txtData.setFocusable(false);
        txtData.setName("cot_data"); // NOI18N
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

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/warning.gif"))); // NOI18N
        jLabel8.setText("0 - Coluna Código pesquisa produto");
        jLabel8.setToolTipText("");

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel9.setText("-- Itens Solicitação ------------------------------------------------------------------------------------------------------------------------------------");
        jLabel9.setToolTipText("");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel10.setText("Data Entrega:");
        jLabel10.setToolTipText("");

        try {
            txtDataEntrega.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataEntrega.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDataEntrega.setToolTipText("");
        txtDataEntrega.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        txtDataEntrega.setEnabled(false);
        txtDataEntrega.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtDataEntrega.setName("cot_data_entrega"); // NOI18N
        txtDataEntrega.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDataEntregaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDataEntregaFocusLost(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel11.setText("Valor Frete:");
        jLabel11.setToolTipText("");

        txtFrete.setColumns(8);
        txtFrete.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtFrete.setToolTipText("Valor");
        txtFrete.setEnabled(false);
        txtFrete.setName("cot_frete"); // NOI18N
        txtFrete.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFreteFocusLost(evt);
            }
        });
        txtFrete.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFreteKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel12.setText("Condição de Pgto:");
        jLabel12.setToolTipText("");

        txtCondicaoDePagamentoID.setColumns(8);
        txtCondicaoDePagamentoID.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtCondicaoDePagamentoID.setToolTipText("Código");
        txtCondicaoDePagamentoID.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        txtCondicaoDePagamentoID.setEnabled(false);
        txtCondicaoDePagamentoID.setName("cot_cpgto_codigo_id"); // NOI18N
        txtCondicaoDePagamentoID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCondicaoDePagamentoIDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCondicaoDePagamentoIDFocusLost(evt);
            }
        });
        txtCondicaoDePagamentoID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCondicaoDePagamentoIDKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCondicaoDePagamentoIDKeyReleased(evt);
            }
        });

        btnPesquisaCondicaoPagamentoID.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Pesquisar.gif"))); // NOI18N
        btnPesquisaCondicaoPagamentoID.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPesquisaCondicaoPagamentoID.setEnabled(false);
        btnPesquisaCondicaoPagamentoID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaCondicaoPagamentoIDActionPerformed(evt);
            }
        });

        txtCondicaoDePagamentoDescricao.setEditable(false);
        txtCondicaoDePagamentoDescricao.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtCondicaoDePagamentoDescricao.setForeground(new java.awt.Color(102, 102, 255));
        txtCondicaoDePagamentoDescricao.setToolTipText("");
        txtCondicaoDePagamentoDescricao.setFocusable(false);
        txtCondicaoDePagamentoDescricao.setName(""); // NOI18N
        txtCondicaoDePagamentoDescricao.setRequestFocusEnabled(false);

        btnItens.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        btnItens.setText("Digitar Itens");
        btnItens.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnItens.setEnabled(false);
        btnItens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnItensActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Total de Fornecedores na Cotação:");
        jLabel14.setToolTipText("");

        lbQtde.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lbQtde.setForeground(new java.awt.Color(51, 51, 255));
        lbQtde.setText("00");
        lbQtde.setToolTipText("");

        lbStatusOC.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lbStatusOC.setForeground(new java.awt.Color(0, 102, 51));
        lbStatusOC.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbStatusOC.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 917, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtCondicaoDePagamentoID, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                                            .addComponent(txtParceiro, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnPesquisaCondicaoPagamentoID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnPesquisaFornecedor, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)))
                                    .addComponent(lbID))
                                .addGap(8, 8, 8)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtCondicaoDePagamentoDescricao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
                                        .addComponent(txtNomeParceiro, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtControleInterno, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel11))
                                .addGap(16, 16, 16)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDataEntrega, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtFrete)
                                    .addComponent(txtData)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbStatusOC, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnItens, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbQtde)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6)
                                .addComponent(txtControleInterno, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbID)
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNomeParceiro, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10)
                                .addComponent(txtDataEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtParceiro, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4))))
                    .addComponent(btnPesquisaFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtFrete, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtCondicaoDePagamentoDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPesquisaCondicaoPagamentoID, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCondicaoDePagamentoID, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnItens, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(lbQtde))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lbStatusOC, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        btnPesquisa.setToolTipText("Pesquisar Solicitação Compra");
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

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/confirma.gif"))); // NOI18N
        btnCancelar.setToolTipText("Finalizar Coleta");
        btnCancelar.setBorderPainted(false);
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnApuracao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/filter.png"))); // NOI18N
        btnApuracao.setToolTipText("Apuração Coleta");
        btnApuracao.setBorderPainted(false);
        btnApuracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApuracaoActionPerformed(evt);
            }
        });

        lbItem.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lbItem.setText("0");
        lbItem.setToolTipText("ID");

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Imprimir.gif"))); // NOI18N
        btnImprimir.setToolTipText("Imprimir O.C. Emitida");
        btnImprimir.setBorderPainted(false);
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnApuracao, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(229, 229, 229)
                .addComponent(lbItem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 372, Short.MAX_VALUE)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbItem)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnPesquisa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
                            .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnSair, javax.swing.GroupLayout.Alignment.LEADING, 0, 20, Short.MAX_VALUE)
                            .addComponent(btnSalvar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
                            .addComponent(btnApuracao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
                            .addComponent(btnImprimir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlDados, javax.swing.GroupLayout.PREFERRED_SIZE, 927, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:
         if(txtControleInterno.getText().equals(""))
         {
           JOptionPane.showMessageDialog(null,"Nenhuma solicitação selecioanada para cotação ");
           return;
         }

         if(lbStatusOC.getText().equals("O.C EMITIDA"))
         {
           JOptionPane.showMessageDialog(null,"O.C já emitida para solicitação. Operação cancelada! ");
           return;
         }

        if(funcoes.Permissao("001", variaveis.usuario_id, variaveis.usuario_super, this.getTitle()))
        {
         funcoes.HabilitaCampos(rootPane, true);
         Date data = new Date(System.currentTimeMillis());
         SimpleDateFormat formatarDate = new SimpleDateFormat("dd/MM/yyyy");
         txtData.setText(formatarDate.format(data)) ;
         txtParceiro.requestFocus();
         lbStatus.setText("Realizando Coleta de Preços");
         
         //lbQtde.setText("00");
        }
    }//GEN-LAST:event_btnNovoActionPerformed

   
    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:

       if(txtControleInterno.getText().equals(""))
       {
           JOptionPane.showMessageDialog(null,"Nº Solicitação não informado.");
           txtControleInterno.requestFocus();
           return;
       }

       if(txtDataEntrega.getText().equals("  /  /    "))
       {
           JOptionPane.showMessageDialog(null,"Data de entrega não informada");
           txtDataEntrega.requestFocus();
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
           double xQtde = Double.parseDouble(grDados.getValueAt(i, 5).toString().replace(".", "").replace(",", "."));
           }catch(Exception er)
           {
              JOptionPane.showMessageDialog(null,"Valor Inválido");
              grDados.setCellSelectionEnabled(true);
              grDados.setRowSelectionInterval(i,i);
              grDados.setColumnSelectionInterval(5, 5);
              grDados.editCellAt(i, 5);
              grDados.requestFocus();
              return;
           }

       }

       if (txtParceiro.getText().equals("") || txtParceiro.getText().equals(null))
       {
           JOptionPane.showMessageDialog(null,"Nenhum parceiro foi informado");
           txtParceiro.requestFocus();
           return;
       }

            
       if(lbQtde.getText().equals("00"))
       {
          funcoes.GravarDadosCotacao(lbID.getText(), rootPane, "cotacao", "cot_codigo_id",false);
          lbStatus.setText(" Registro");
          if(lbID.getText().equals("0")) lbID.setText(funcoes.RetornaId("Cotacao", "cot_codigo_id"));
       }
       lbQtde.setText(funcoes.StrZero(Integer.parseInt(lbQtde.getText())+1, Byte.parseByte("2")));
           try
           {

            //Incluindo Item Pedido
            String vmCampos = "";
            String vmParametros =  "";
            vmCampos = "";
            vmCampos += "  item_cot_codigo_id,  ";
            vmCampos += "  item_prod_codigo_id, ";
            vmCampos += "  item_unitario, ";
            vmCampos += "  item_quantidade, ";
            vmCampos += "  item_total, item_par_codigo_id, item_frete ";

            AcessoDados.Excluir("ITEM_COTACAO", " ITEM_PAR_CODIGO_ID = "+txtParceiro.getText()+" AND ITEM_COT_CODIGO_ID = " + lbID.getText());
            for(int i = 0; i <= grDados.getRowCount() - 1; i++)
            {
              vmParametros = "";
              vmParametros += lbID.getText() + ",";
              vmParametros += grDados.getValueAt(i, 0).toString() +",";
              vmParametros += grDados.getValueAt(i, 5).toString().replace(".", "").replace(",", ".") + ",";
              vmParametros += grDados.getValueAt(i, 4).toString().replace(".", "").replace(",", ".") + ",";
              vmParametros += grDados.getValueAt(i, 6).toString().replace(".", "").replace(",", ".") + ",";
              vmParametros +=  txtParceiro.getText() + ",";
              vmParametros +=  txtFrete.getText().toString().replace(".", "").replace(",", ".");

              AcessoDados.Inserir("item_cotacao", vmCampos, vmParametros);

            }
              txtParceiro.setText("");
              txtNomeParceiro.setText("");
              txtDataEntrega.setText("");
              txtFrete.setText("0");
              txtCondicaoDePagamentoID.setText("");
              txtCondicaoDePagamentoDescricao.setText("");
              MontaGrid();
              txtParceiro.requestFocus();

            }catch (Exception e) {
                e.printStackTrace();
            }
       
    }//GEN-LAST:event_btnSalvarActionPerformed

   
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        funcoes.HabilitaCampos(rootPane, false);
        //funcoes.limparTodosCampos(rootPane);
        txtParceiro.setText("");
        txtNomeParceiro.setText("");
        txtDataEntrega.setText("");
        txtFrete.setText("0");
        txtCondicaoDePagamentoID.setText("");
        txtCondicaoDePagamentoDescricao.setText("");
         MontaGrid();        
        lbStatus.setText("Coleta Finalizada ");
        //lbQtde.setText("00");

        btnNovo.requestFocus();
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

    private void txtParceiroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtParceiroFocusLost
        // TODO add your handling code here:
        if (!txtParceiro.getText().equals("")) {
            String vmCampos = "*";
            String vmCondicao_Consulta = " WHERE par_codigo_id = " + txtParceiro.getText();
            try {
                rs = AcessoDados.Selecao("parceiros", vmCampos, vmCondicao_Consulta);
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "Parceiro não cadastrado no sistema");
                    txtParceiro.setText(null);
                    txtNomeParceiro.setText(null);
                    txtParceiro.requestFocus();
                    return;
                }
                txtNomeParceiro.setText(rs.getString("par_nome_razao_social"));
                BuscarDadosParceiro();
                
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            txtNomeParceiro.setText(null);
        }
    }//GEN-LAST:event_txtParceiroFocusLost

    private void txtParceiroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtParceiroKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtParceiroKeyReleased

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

        txtParceiro.setText((String) md.getRetorno());
        txtParceiro.requestFocus();
        if (!txtParceiro.getText().equals("")) {
            String vmCampos = "*";
            String vmCondicao_Consulta = " WHERE par_codigo_id = " +txtParceiro.getText();
            try {
                rs = AcessoDados.Selecao("parceiros", vmCampos, vmCondicao_Consulta);
                rs.next();
                txtNomeParceiro.setText(rs.getString("par_nome_razao_social"));
                rs.close();
                BuscarDadosParceiro();
                txtParceiro.requestFocus();
                
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_btnPesquisaFornecedorActionPerformed

    public void BuscarDadosParceiro()
    {
        String vmCampos = " prod_descricao, prod_codigo_id, prod_codigo_produto, item_quantidade, unid_unidade, item_total, item_unitario, item_frete, cotacao.* ";
        String vmCondicao_Consulta = " INNER join produtos on (prod_codigo_id = item_prod_codigo_id) " +
                                     " INNER join unidade_de_medida on (prod_unid_codigo_id = unid_codigo_id)   " +
                                     " INNER join COTACAO on (COT_codigo_id = ITEM_COT_codigo_id)   " +
                                     " WHERE ITEM_PAR_CODIGO_ID = "+txtParceiro.getText()+" AND ITEM_COT_CODIGO_ID = " + lbID.getText() +
                                     " ORDER BY PROD_DESCRICAO";
                                    
        DefaultTableModel tableModel =(DefaultTableModel) grDados.getModel();
        tableModel.setNumRows(0);
        MontaTituloGrid();

        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
        
        try {
                rs = AcessoDados.Selecao("ITEM_COTACAO", vmCampos, vmCondicao_Consulta);
                if(rs.next())
                {
                    txtCondicaoDePagamentoID.setText(rs.getString("cot_cpgto_codigo_id"));
                    txtFrete.setText(funcoes.formataMoeda(rs.getString("item_frete"),"BD"));
                    txtDataEntrega.setText(funcoes.formataData(rs.getString("cot_data_entrega")));
                    txtCondicaoDePagamentoIDFocusLost(null);
                    rs.close();
      
                rs = AcessoDados.Selecao("ITEM_COTACAO", vmCampos, vmCondicao_Consulta);
                while (rs.next())
                {
                    dtm.addRow(new Object[]{rs.getString("prod_codigo_id"),
                                    rs.getString("prod_codigo_produto"),
                                    rs.getString("prod_descricao"),
                                    rs.getString("unid_unidade"),
                                    funcoes.formataNumero(rs.getString("item_quantidade").replace(".", ","),rs.getString("unid_unidade")),
                                    funcoes.formataMoeda(rs.getString("item_unitario"),"BD"),
                                    funcoes.formataMoeda(rs.getString("item_total"),"BD")
                                    });
                    
                }
                }else
                {
                    MontaGrid();
                    txtCondicaoDePagamentoID.setText("");
                    txtFrete.setText("0");
                    txtDataEntrega.setText("");
                    txtCondicaoDePagamentoDescricao.setText("");
                }
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    private void btnPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaActionPerformed
        // TODO add your handling code here:
        if(funcoes.Permissao("004", variaveis.usuario_id, variaveis.usuario_super, this.getTitle()))
        {
         variaveis.xColuna1 = "com_codigo_id";
         variaveis.xColuna2 = "func_nome";
         variaveis.xColuna3 = "to_char(com_data, 'DD/MM/YYYY')";
         variaveis.xColuna4 = "com_numero_solicitacao";
         variaveis.xColuna5 = "cot_codigo_id";
         variaveis.xColuna6 = "com_situacao";
         variaveis.xColuna7 = "''";
         variaveis.xColuna8 = "''";
         variaveis.xColuna9 = "''";
         
         variaveis.xColunaNome1 = "Código";
         variaveis.xColunaNome2 = "Solicitante";
         variaveis.xColunaNome3 = "Data";
         variaveis.xColunaNome4 = "Solicitação";
         variaveis.xColunaNome5 = "Cotação / O.C";
         variaveis.xColunaNome6 = "Situação";
         variaveis.xColunaNome7 = "";
         variaveis.xColunaNome8 = "";
         variaveis.xColunaNome9 = "";
         
         variaveis.xColunaTamanho1 = 60;
         variaveis.xColunaTamanho2 = 200;
         variaveis.xColunaTamanho3 = 70;
         variaveis.xColunaTamanho4 = 70;
         variaveis.xColunaTamanho5 = 80;
         variaveis.xColunaTamanho6 = 160;
         variaveis.xColunaTamanho7 = 0;
         variaveis.xColunaTamanho8 = 0;
         variaveis.xColunaTamanho9 = 0;
         
         variaveis.xColunaStart = "com_situacao";
         variaveis.xColunaNomeStart = "Situação";
         variaveis.xTabela = "solicitacao_compras";
         variaveis.xSql = " INNER JOIN FUNCIONARIOS ON (FUNC_CODIGO_ID = com_func_codigo_id) "
                        + " LEFT JOIN cotacao ON (cot_numero_solicitacao = com_numero_solicitacao) "; 
         
         FpesqPesquisa md = new FpesqPesquisa(null, true);
         Dimension d = new Dimension();   
         d.setSize(650, 480); 
         md.setSize(d);
         
         md.setTitle("Pesquisa Solicitação de Compra - ENTER ou DUPLO CLICK no registro retorna dados.");

         md.setLocationRelativeTo(null);
         md.setVisible(true);
        pnlDados.setSelectedIndex(0);
        if(!md.getRetorno().trim().equals("") ||!md.getRetorno().equals(null))
        {
         lbID.setText((String) md.getRetorno());
        }
        //lbID.setText((String) md.getRetorno());
        if(!lbID.getText().equals(""))
        {
          String vmCampos = " SOLICITACAO_COMPRAS.*, COALESCE(COT_CODIGO_ID,0) AS COTACAO ";
          String vmCondicao_Consulta = " LEFT JOIN COTACAO ON (COT_NUMERO_SOLICITACAO = com_numero_solicitacao) where com_codigo_id = " + lbID.getText();
          try
        {
        rs = AcessoDados.Selecao("solicitacao_compras", vmCampos, vmCondicao_Consulta);
        rs.next();
        txtControleInterno.setText(rs.getString("com_numero_solicitacao"));
        lbStatusOC.setText(rs.getString("com_situacao"));
        lbItem.setText(lbID.getText());
        lbID.setText(rs.getString("COTACAO"));
        
        txtParceiroFocusLost(null);
        MontaGrid();
        
        rs = AcessoDados.Selecao("ITEM_COTACAO", " COUNT(ITEM_PAR_CODIGO_ID) AS FORNEC  ", " WHERE ITEM_COT_CODIGO_ID = " + lbID.getText() +
                                 " GROUP BY ITEM_PAR_CODIGO_ID ");
        int Contador = 0;
        while (rs.next())
        {
            Contador++;
        }
        lbQtde.setText(funcoes.StrZero(Contador, Byte.parseByte("2")));

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

    }//GEN-LAST:event_grDadosKeyPressed

    private void txtControleInternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtControleInternoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtControleInternoKeyReleased

    private void grDadosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grDadosFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_grDadosFocusGained

    private void txtDataEntregaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDataEntregaFocusGained
        // TODO add your handling code here:
        JTextField tf = (JTextField) evt.getComponent();
        tf.selectAll();
    }//GEN-LAST:event_txtDataEntregaFocusGained

    private void txtDataEntregaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDataEntregaFocusLost
        // TODO add your handling code here:
        if (txtDataEntrega.getText().equals("  /  /    ")) {
            Date data = new Date(System.currentTimeMillis());
            SimpleDateFormat formatarDate = new SimpleDateFormat("dd/MM/yyyy");
            txtDataEntrega.setText(formatarDate.format(data));
        }
    }//GEN-LAST:event_txtDataEntregaFocusLost

    private void txtFreteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFreteFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFreteFocusLost

    private void txtFreteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFreteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFreteKeyReleased

    private void txtCondicaoDePagamentoIDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCondicaoDePagamentoIDFocusGained
        // TODO add your handling code here:
        JTextField tf = (JTextField) evt.getComponent();
        tf.selectAll();
    }//GEN-LAST:event_txtCondicaoDePagamentoIDFocusGained

    private void txtCondicaoDePagamentoIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCondicaoDePagamentoIDFocusLost
        // TODO add your handling code here:
        if (!txtCondicaoDePagamentoID.getText().equals("")) {
            String vmCampos = "*";
            String vmCondicao_Consulta = " WHERE cpgto_codigo_id = " + txtCondicaoDePagamentoID.getText();
            try {
                rs = AcessoDados.Selecao("condicao_pagamento", vmCampos, vmCondicao_Consulta);
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "Condição de Pagamento não cadastrada no sistema");
                    txtCondicaoDePagamentoID.setText(null);
                    txtCondicaoDePagamentoDescricao.setText(null);
                    txtCondicaoDePagamentoID.requestFocus();
                    return;
                }
                txtCondicaoDePagamentoDescricao.setText(rs.getString("cpgto_descricao"));
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            txtCondicaoDePagamentoDescricao.setText(null);
        }
    }//GEN-LAST:event_txtCondicaoDePagamentoIDFocusLost

    private void txtCondicaoDePagamentoIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCondicaoDePagamentoIDKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 114) //tecla F3 para pesquisa
        {
            btnPesquisaCondicaoPagamentoIDActionPerformed(null);
        }
    }//GEN-LAST:event_txtCondicaoDePagamentoIDKeyPressed

    private void txtCondicaoDePagamentoIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCondicaoDePagamentoIDKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCondicaoDePagamentoIDKeyReleased

    private void btnPesquisaCondicaoPagamentoIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaCondicaoPagamentoIDActionPerformed
        // TODO add your handling code here:
         variaveis.xColuna1 = "cpgto_codigo_id";
         variaveis.xColuna2 = "cpgto_qtde_parcelas";
         variaveis.xColuna3 = "cpgto_prazo_medio";
         variaveis.xColuna4 = "cpgto_descricao";
         variaveis.xColuna5 = "''";
         variaveis.xColuna6 = "''";
         variaveis.xColuna7 = "''";
         variaveis.xColuna8 = "''";
         variaveis.xColuna9 = "''";
         
         variaveis.xColunaNome1 = "Código";
         variaveis.xColunaNome2 = "Qtde Parcelas";
         variaveis.xColunaNome3 = "Prazo Médio";
         variaveis.xColunaNome4 = "Descrição";
         variaveis.xColunaNome5 = "";
         variaveis.xColunaNome6 = "";
         variaveis.xColunaNome7 = "";
         variaveis.xColunaNome8 = "";
         variaveis.xColunaNome9 = "";
         
         variaveis.xColunaTamanho1 = 70;
         variaveis.xColunaTamanho2 = 110;
         variaveis.xColunaTamanho3 = 110;
         variaveis.xColunaTamanho4 = 150;
         variaveis.xColunaTamanho5 = 0;
         variaveis.xColunaTamanho6 = 0;
         variaveis.xColunaTamanho7 = 0;
         variaveis.xColunaTamanho8 = 0;
         variaveis.xColunaTamanho9 = 0;
         
         variaveis.xColunaStart = "cpgto_descricao";
         variaveis.xColunaNomeStart = "Descrição";
         variaveis.xTabela = "condicao_pagamento";
         variaveis.xSql = ""; 
         
         FpesqPesquisa md = new FpesqPesquisa(null, true);
         Dimension d = new Dimension();   
         d.setSize(450, 480); 
         md.setSize(d);
         
         md.setTitle("Pesquisa Cond. Pagamento - ENTER ou DUPLO CLICK no registro retorna dados.");

         md.setLocationRelativeTo(null);
         md.setVisible(true);

        if (!md.getRetorno().trim().equals("") || !md.getRetorno().equals(null)) {
            txtCondicaoDePagamentoID.setText((String) md.getRetorno());
        }
        txtCondicaoDePagamentoID.requestFocus();
        if (!txtCondicaoDePagamentoID.getText().equals("")) {
            String vmCampos = "*";
            String vmCondicao_Consulta = " WHERE cpgto_codigo_id = " + txtCondicaoDePagamentoID.getText();
            try {
                rs = AcessoDados.Selecao("condicao_pagamento", vmCampos, vmCondicao_Consulta);
                rs.next();
                txtCondicaoDePagamentoDescricao.setText(rs.getString("cpgto_descricao"));
                txtCondicaoDePagamentoID.requestFocus();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_btnPesquisaCondicaoPagamentoIDActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jLabel9MouseClicked

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
        grDados.getColumnModel().getColumn(5).setCellEditor(editor);        

        grDados.setCellSelectionEnabled(true);
       grDados.setRowSelectionInterval(0, 0);
       grDados.setColumnSelectionInterval(0, 5);
       grDados.editCellAt(0, 5);
       grDados.requestFocus();
        
    }//GEN-LAST:event_btnItensActionPerformed

    private void btnApuracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApuracaoActionPerformed
        // TODO add your handling code here:
        if (lbID.getText().equals("0"))
        {
            JOptionPane.showMessageDialog(null,"Nenhuma solicitação selecionada para apuração!");
            return;
        }

         if(lbStatusOC.getText().equals("O.C EMITIDA"))
         {
           JOptionPane.showMessageDialog(null,"O.C já emitida para solicitação. Operação cancelada! ");
           return;
         }

        variaveis.vmCotacao = lbID.getText();
        FrmApuracao md = new FrmApuracao(null, true);
        md.setLocationRelativeTo(null);
        md.setVisible(true);
        
        pnlDados.setSelectedIndex(0);
      
        if(!lbID.getText().equals(""))
        {
          String vmCampos = " SOLICITACAO_COMPRAS.*, COALESCE(COT_CODIGO_ID,0) AS COTACAO ";
          String vmCondicao_Consulta = " LEFT JOIN COTACAO ON (COT_NUMERO_SOLICITACAO = com_numero_solicitacao) where cot_codigo_id = " + lbID.getText();
          try
        {
        rs = AcessoDados.Selecao("solicitacao_compras", vmCampos, vmCondicao_Consulta);
        rs.next();
        txtControleInterno.setText(rs.getString("com_numero_solicitacao"));
        lbStatusOC.setText(rs.getString("com_situacao"));
        lbItem.setText(lbID.getText());
        lbID.setText(rs.getString("COTACAO"));
        
        txtParceiroFocusLost(null);
        MontaGrid();
        
        rs = AcessoDados.Selecao("ITEM_COTACAO", " COUNT(ITEM_PAR_CODIGO_ID) AS FORNEC  ", " WHERE ITEM_COT_CODIGO_ID = " + lbID.getText() +
                                 " GROUP BY ITEM_PAR_CODIGO_ID ");
        int Contador = 0;
        while (rs.next())
        {
            Contador++;
        }
        lbQtde.setText(funcoes.StrZero(Contador, Byte.parseByte("2")));

        btnNovo.requestFocus();
        
        rs.close();
        }catch (Exception e) {
           e.printStackTrace();
        }
        }
       
        
        
                
    }//GEN-LAST:event_btnApuracaoActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
        if (funcoes.Permissao("005", variaveis.usuario_id, variaveis.usuario_super, this.getTitle())) {
        if(!lbStatusOC.getText().equals("O.C EMITIDA"))
        {
          JOptionPane.showMessageDialog(null,"O.C não emitida!");
          return;
        }

       
       try
       {
        rs = AcessoDados.Selecao("ITEM_APURACAO", " DISTINCT(ITEM_PAR_CODIGO_ID) AS PARCEIRO ", " WHERE item_cot_codigo_id = " + lbID.getText());
        while(rs.next())
        {

           String vmCampos = "";
           vmCampos = " PARCEIROS.*, ITEM_APURACAO.*, COTACAO.*,  CONDICAO_PAGAMENTO.*, CIDADES.CID_MUNICIPIO AS CID_PAR, CIDADES.CID_UF AS UF_PAR, "+
                      " SOLICITACAO_COMPRAS.*, FUNCIONARIOS.*, EMPRESAS.*, EMP.CID_MUNICIPIO AS CID_EMP, EMP.CID_UF AS UF_EMP, PRODUTOS.*, UNIDADE_DE_MEDIDA.* ";


           String vmCondicao_Consulta = "";
           vmCondicao_Consulta =  "LEFT JOIN PARCEIROS ON (PAR_CODIGO_ID = ITEM_PAR_CODIGO_ID) "+
                              "LEFT JOIN COTACAO ON (COT_CODIGO_ID =  ITEM_COT_CODIGO_ID) "+
                              "LEFT JOIN CONDICAO_PAGAMENTO ON (CPGTO_CODIGO_ID = COT_CPGTO_CODIGO_ID) "+
                              "LEFT JOIN CIDADES ON (CID_CODIGO_ID = par_cidade_comercial_codigo_id) "+
                              "LEFT JOIN SOLICITACAO_COMPRAS ON (cot_numero_solicitacao = com_numero_solicitacao) "+
                              "LEFT JOIN FUNCIONARIOS ON (func_codigo_id = com_func_codigo_id) "+
                              "LEFT JOIN EMPRESAS ON (EMP_CODIGO_ID = COM_EMP_CODIGO_ID) "+
                              "LEFT JOIN CIDADES EMP ON (EMP.CID_CODIGO_ID = EMp_CID_codigo_id) "+
                              "LEFT JOIN PRODUTOS ON (prod_codigo_id = item_prod_codigo_id) "+
                              "LEFT JOIN UNIDADE_DE_MEDIDA ON (unid_codigo_id = prod_unid_codigo_id) "+
                              "WHERE ITEM_COT_CODIGO_ID = "+ lbID.getText() + " AND ITEM_PAR_CODIGO_ID =  " + rs.getString("parceiro") ;

       
        rsImprimir = AcessoDados.Selecao("ITEM_APURACAO", vmCampos, vmCondicao_Consulta);
        
        HashMap map = new HashMap();
        
        JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getClassLoader().getResource("Relatorios/RelOrdemCompras.jasper"));
        JRResultSetDataSource jrRS1 = new JRResultSetDataSource(rsImprimir);
        
        JasperPrint jp = JasperFillManager.fillReport(jr, map,jrRS1);
        JasperViewer jv = new JasperViewer(jp,false);
        JDialog viewer = new  JDialog(jv, true);
        viewer.setSize(1000,700);
        viewer.setLocationRelativeTo(null);
        viewer.getContentPane().add(jv.getContentPane());
        viewer.setVisible(true);

        }

       }catch (Exception e) {
           e.printStackTrace();
       }
        
        
       }
        
    
        
        
        
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void txtParceiroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtParceiroKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 114) //tecla F3 para pesquisa
        {
            btnPesquisaFornecedorActionPerformed(null);
        }

    }//GEN-LAST:event_txtParceiroKeyPressed

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
                    int Coluna = 5;
                    int linha = ao_table.getSelectedRow();

                    int c =  ao_table.getSelectedRow();
                    int d = ao_table.getRowCount();

                    if(ao_table.getSelectedColumn() == 6)
                    {
                    int xx1 = linha + 1;
                    int xx2 = ao_table.getRowCount();
                    if(xx1 == xx2)
                    {
                       ao_table.transferFocus();
                       btnSalvar.requestFocus();

                    }else
                    {
                       ao_table.setCellSelectionEnabled(true);
                       ao_table.setRowSelectionInterval(linha + 1, linha + 1);
                       ao_table.setColumnSelectionInterval(5, 5);
                       ao_table.editCellAt(linha + 1, 5);
                        
                    }
                    }else
                    {
                    ao_table.setCellSelectionEnabled(true);
                    //jTable1.setSurrendersFocusOnKeystroke(true);
                    ao_table.setColumnSelectionInterval(Coluna + 1, Coluna + 1);
                    ao_table.editCellAt(linha, Coluna + 1);

                    if(ao_table.getSelectedColumn() == 6) //valor unitario
                    {
                        try
                        {
                           double xx = Double.parseDouble(ao_table.getValueAt(ao_table.getSelectedRow(), 5).toString().replace(".", "").replace(",", "."));
                           double xxQ = Double.parseDouble(ao_table.getValueAt(ao_table.getSelectedRow(), 4).toString().replace(".", "").replace(",", "."));
                           String newValor = funcoes.formataMoeda(ao_table.getValueAt(ao_table.getSelectedRow(), 5).toString(),"T");
                           double Total = xx *xxQ;
                           String newTotal = funcoes.formataMoeda(Double.toString(Total),"BD");
                           ao_table.setValueAt(newValor, ao_table.getSelectedRow(), 5); // valor
                           ao_table.setValueAt(newTotal, ao_table.getSelectedRow(), 6); // total

                        }catch(Exception er)
                        {
                            ao_table.setValueAt("0,01", ao_table.getSelectedRow(), 5); 
                            double xx = Double.parseDouble(ao_table.getValueAt(ao_table.getSelectedRow(), 5).toString().replace(".", "").replace(",", "."));
                            double xxQ = Double.parseDouble(ao_table.getValueAt(ao_table.getSelectedRow(), 4).toString().replace(".", "").replace(",", "."));
                            String newValor = funcoes.formataMoeda(ao_table.getValueAt(ao_table.getSelectedRow(), 5).toString(),"T");
                            double Total = xx *xxQ;
                            String newTotal = funcoes.formataMoeda(Double.toString(Total),"BD");
                            ao_table.setValueAt(newTotal, ao_table.getSelectedRow(), 6); // total
                        }
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
                FrmCotacao dialog = new FrmCotacao(new javax.swing.JFrame(), true);
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
            if(txtParceiro.isEnabled())
            {
            DefaultTableModel tableModel =(DefaultTableModel) grDados.getModel();
            tableModel.removeRow(grDados.getSelectedRow());
            grDados.setEnabled(true);
            }
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApuracao;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnItens;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisa;
    private javax.swing.JButton btnPesquisaCondicaoPagamentoID;
    private javax.swing.JButton btnPesquisaFornecedor;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JTable grDados;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbID;
    private javax.swing.JLabel lbItem;
    private javax.swing.JLabel lbQtde;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JLabel lbStatusOC;
    private javax.swing.JTabbedPane pnlDados;
    private javax.swing.JTextField txtCondicaoDePagamentoDescricao;
    private javax.swing.JTextField txtCondicaoDePagamentoID;
    private javax.swing.JTextField txtControleInterno;
    private javax.swing.JTextField txtData;
    private javax.swing.JFormattedTextField txtDataEntrega;
    private javax.swing.JTextField txtFrete;
    private javax.swing.JTextField txtNomeParceiro;
    private javax.swing.JTextField txtParceiro;
    // End of variables declaration//GEN-END:variables

}
