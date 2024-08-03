/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmContaCorrente.java
 *
 * Created on 26/03/2010, 09:41:59
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
import java.beans.Statement;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.MaskFormatter;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thiago
 */
public class FrmContaCorrente extends javax.swing.JDialog {
    claVariaveis variaveis = new claVariaveis();
    claAcessoDados AcessoDados = new claAcessoDados();
    claFuncoes funcoes = new claFuncoes();
    private ResultSet rs, rstitulo;
    double credito = 0;
    double  debito = 0;
    double Total = 0;
    public int Contador = 1;
     
    private static String vmCondicao_Consulta, vmCampos = "*";

    /** Creates new form FrmContaCorrente */
    public FrmContaCorrente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        funcoes.F_AtribuirClasse(rootPane);
        btnNovo.setFocusTraversalKeysEnabled(false);
        btnSalvar.setFocusTraversalKeysEnabled(false);
     
        
    }
    public void MontaGrid()
    {
        DefaultTableModel tableModel =(DefaultTableModel) grDados.getModel();
        tableModel.setNumRows(0);
        Total = 0;
        debito = 0;
        credito = 0;
        
        String Situacao = ddlRealizado.getSelectedItem().toString().trim();
                
        if(Situacao.equals("TODOS"))
            
            vmCondicao_Consulta = "WHERE cc_banc_codigo_id = " + txtBancoID.getText() +
                                  "order by cc_codigo_id desc";
                                  
        else
            vmCondicao_Consulta = "WHERE cc_banc_codigo_id = " + txtBancoID.getText() + " and cc_realizado = '"
                                        + Situacao + "'  order by cc_codigo_id desc";
        try
        {
        
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();

        rs = AcessoDados.Selecao("Contas_corrente", "*", vmCondicao_Consulta);
        while (rs.next())
        {
            dtm.addRow(new Object[]{rs.getString("cc_codigo_id"),
                                    funcoes.formataData(rs.getString("cc_data_lancamento")),
                                    funcoes.formataData(rs.getString("cc_data_vencimento")),
                                    rs.getString("cc_documento"),
                                    rs.getString("cc_numero_cheque"),
                                    funcoes.formataMoeda(rs.getString("cc_credito"),"BD"),
                                    funcoes.formataMoeda(rs.getString("cc_debito"),"BD"),
                                    rs.getString("cc_nota")});
            credito += rs.getDouble("cc_credito");
            debito  += rs.getDouble("cc_debito");
                        
        }
  
            
       
        lbCredito.setText(funcoes.formataMoeda(Double.toString(credito), "BD"));
        lbDebito.setText(funcoes.formataMoeda(Double.toString(debito), "BD"));
        Total = credito - debito;
        if(Total < 0)
        {
            lbTotal.setForeground(Color.red);
        }else
        {
            lbTotal.setForeground(Color.blue);
        }
        lbTotal.setText(funcoes.formataMoeda(Double.toString(Total), "BD"));

         }catch (Exception e) {
           e.printStackTrace();
           String erro = e.toString();
        }
   
 }

   

     public void RetornaDadosGrid()
    {
        lbID.setText(grDados.getValueAt(grDados.getSelectedRow(), 0).toString());
      //txtDataLancamento.setText(grDados.getValueAt(grDados.getSelectedRow(), 1).toString());
        txtDocumento.setText(grDados.getValueAt(grDados.getSelectedRow(), 3).toString());
        txtNumeroCheque.setText(grDados.getValueAt(grDados.getSelectedRow(), 4).toString());
         if(!lbID.getText().equals(""))
       {
          String vmCampos = "*";
          String vmCondicao_Consulta = " WHERE cc_codigo_id = " + lbID.getText();
          try
        {
        rs = AcessoDados.Selecao("Contas_corrente", vmCampos, vmCondicao_Consulta);
        rs.next();
        txtHistorico.setText(rs.getString("cc_historico"));
        txtDescricao.setText(rs.getString("cc_descricao"));
        txtBancoID.setText(rs.getString("cc_banc_codigo_id"));
        Date data = rs.getDate("cc_data_lancamento");
        SimpleDateFormat formatarDate = new SimpleDateFormat("dd/MM/yyyy");
        txtDataLancamento.setText(formatarDate.format(data));
        txtCredito.setText(grDados.getValueAt(grDados.getSelectedRow(), 5).toString());
        txtDebito.setText(grDados.getValueAt(grDados.getSelectedRow(), 6).toString());
        txtBancoIDFocusLost(null);
        rs.close();

       }catch (Exception e) {
           e.printStackTrace();
        }
      
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

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtBancoID = new javax.swing.JTextField();
        txtDescricaoBanco = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtAgencia = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtConta = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtDataLancamento = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDocumento = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNumeroCheque = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCredito = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtDebito = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        ddlRealizado = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        lbID = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtHistorico = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        grDados = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        lbStatus = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnPesquisaBancoID = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lbDebito = new javax.swing.JLabel();
        lbCredito = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Contas Correntes");
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

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel3.setText("Banco:");
        jLabel3.setToolTipText("");

        txtBancoID.setEditable(false);
        txtBancoID.setColumns(4);
        txtBancoID.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtBancoID.setToolTipText("Código");
        txtBancoID.setFocusable(false);
        txtBancoID.setName("banc_codigo_id"); // NOI18N
        txtBancoID.setRequestFocusEnabled(false);
        txtBancoID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBancoIDFocusLost(evt);
            }
        });

        txtDescricaoBanco.setEditable(false);
        txtDescricaoBanco.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtDescricaoBanco.setForeground(new java.awt.Color(102, 102, 255));
        txtDescricaoBanco.setToolTipText("");
        txtDescricaoBanco.setFocusable(false);
        txtDescricaoBanco.setName("banc_nome_banco"); // NOI18N
        txtDescricaoBanco.setRequestFocusEnabled(false);

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setText("Agência:");
        jLabel4.setToolTipText("");

        txtAgencia.setEditable(false);
        txtAgencia.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtAgencia.setForeground(new java.awt.Color(102, 102, 255));
        txtAgencia.setToolTipText("");
        txtAgencia.setFocusable(false);
        txtAgencia.setName("banc_numero_agencia"); // NOI18N
        txtAgencia.setRequestFocusEnabled(false);

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel5.setText("Conta:");
        jLabel5.setToolTipText("");

        txtConta.setEditable(false);
        txtConta.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtConta.setForeground(new java.awt.Color(102, 102, 255));
        txtConta.setToolTipText("");
        txtConta.setFocusable(false);
        txtConta.setName("banc_numero_conta"); // NOI18N
        txtConta.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBancoID, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(txtDescricaoBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConta)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtBancoID, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescricaoBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtConta, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel6.setText("Data do Lançamento:");
        jLabel6.setToolTipText("");

        try {
            txtDataLancamento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataLancamento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDataLancamento.setToolTipText("");
        txtDataLancamento.setEnabled(false);
        txtDataLancamento.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtDataLancamento.setName("cc_data_lancamento"); // NOI18N
        txtDataLancamento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDataLancamentoFocusGained(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel7.setText("Número do Documento:");
        jLabel7.setToolTipText("");

        txtDocumento.setColumns(20);
        txtDocumento.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtDocumento.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDocumento.setToolTipText("");
        txtDocumento.setEnabled(false);
        txtDocumento.setName("cc_documento"); // NOI18N
        txtDocumento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDocumentoFocusGained(evt);
            }
        });
        txtDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDocumentoKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel8.setText("Cheque:");

        txtNumeroCheque.setColumns(20);
        txtNumeroCheque.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtNumeroCheque.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtNumeroCheque.setToolTipText("");
        txtNumeroCheque.setEnabled(false);
        txtNumeroCheque.setName("cc_numero_cheque"); // NOI18N
        txtNumeroCheque.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNumeroChequeFocusGained(evt);
            }
        });
        txtNumeroCheque.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroChequeKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel9.setText("Histórico:");
        jLabel9.setToolTipText("");

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel10.setText("Créditos:");

        txtCredito.setColumns(12);
        txtCredito.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtCredito.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCredito.setToolTipText("Valor");
        txtCredito.setEnabled(false);
        txtCredito.setName("cc_credito"); // NOI18N
        txtCredito.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCreditoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCreditoFocusLost(evt);
            }
        });
        txtCredito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCreditoKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel11.setText("Débitos:");

        txtDebito.setColumns(12);
        txtDebito.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtDebito.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDebito.setToolTipText("Valor");
        txtDebito.setEnabled(false);
        txtDebito.setName("cc_debito"); // NOI18N
        txtDebito.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDebitoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDebitoFocusLost(evt);
            }
        });
        txtDebito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDebitoKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel12.setText("Realizado:");
        jLabel12.setToolTipText("");

        ddlRealizado.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        ddlRealizado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SIM", "NÃO", "TODOS" }));
        ddlRealizado.setSelectedIndex(1);
        ddlRealizado.setToolTipText("");
        ddlRealizado.setName("cc_realizado"); // NOI18N
        ddlRealizado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ddlRealizadoItemStateChanged(evt);
            }
        });
        ddlRealizado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ddlRealizadoFocusLost(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setText("Código:");
        jLabel1.setToolTipText("");

        lbID.setBackground(new java.awt.Color(255, 255, 255));
        lbID.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lbID.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbID.setText("0");
        lbID.setToolTipText("ID");
        lbID.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lbID.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setText("Nome /  Razão Social:");
        jLabel2.setToolTipText("");

        txtDescricao.setColumns(50);
        txtDescricao.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtDescricao.setToolTipText("");
        txtDescricao.setEnabled(false);
        txtDescricao.setName("cc_descricao"); // NOI18N
        txtDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescricaoKeyReleased(evt);
            }
        });

        txtHistorico.setColumns(255);
        txtHistorico.setRows(5);
        txtHistorico.setToolTipText("");
        txtHistorico.setEnabled(false);
        txtHistorico.setName("cc_historico"); // NOI18N
        txtHistorico.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtHistoricoFocusLost(evt);
            }
        });
        txtHistorico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtHistoricoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHistoricoKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(txtHistorico);

        jLabel13.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/warning.gif"))); // NOI18N
        jLabel13.setText("Tecla Ctrl - Quebra de linha.");
        jLabel13.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lbID, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtDataLancamento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(13, 13, 13)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel8)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNumeroCheque, 0, 1, Short.MAX_VALUE)
                    .addComponent(txtDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(ddlRealizado, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(ddlRealizado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNumeroCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel1)
                            .addComponent(lbID, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDataLancamento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel10)
                            .addComponent(txtCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        grDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Lançamento", "Vencimento", "Documento", "Número Cheque", "Crédito", "Débito", "Nota"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        grDados.setFocusable(false);
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
        grDados.getColumnModel().getColumn(0).setResizable(false);
        grDados.getColumnModel().getColumn(1).setResizable(false);
        grDados.getColumnModel().getColumn(2).setResizable(false);
        grDados.getColumnModel().getColumn(3).setResizable(false);
        grDados.getColumnModel().getColumn(4).setResizable(false);
        grDados.getColumnModel().getColumn(5).setResizable(false);
        grDados.getColumnModel().getColumn(6).setResizable(false);
        grDados.getColumnModel().getColumn(7).setResizable(false);
        grDados.getColumnModel().getColumn(7).setPreferredWidth(60);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        lbStatus.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lbStatus.setForeground(new java.awt.Color(153, 153, 153));
        lbStatus.setText("Registro ");
        lbStatus.setToolTipText("");
        jToolBar1.add(lbStatus);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/alterar.gif"))); // NOI18N
        btnAlterar.setToolTipText("Alterar");
        btnAlterar.setBorderPainted(false);
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/excluir.gif"))); // NOI18N
        btnExcluir.setToolTipText("Excluir");
        btnExcluir.setBorderPainted(false);
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnPesquisaBancoID.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Localizar.gif"))); // NOI18N
        btnPesquisaBancoID.setToolTipText("Pesquisar");
        btnPesquisaBancoID.setBorderPainted(false);
        btnPesquisaBancoID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaBancoIDActionPerformed(evt);
            }
        });

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/novo.gif"))); // NOI18N
        btnNovo.setToolTipText("Novo");
        btnNovo.setBorderPainted(false);
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
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

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Imprimir.gif"))); // NOI18N
        btnImprimir.setToolTipText("Imprimir");
        btnImprimir.setBorderPainted(false);
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPesquisaBancoID, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnPesquisaBancoID, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Totalizadores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N
        jPanel3.setToolTipText("");

        jLabel14.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel14.setText("Débito...:");
        jLabel14.setToolTipText("");

        jLabel15.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel15.setText("Crédito..:");
        jLabel15.setToolTipText("");

        jLabel16.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel16.setText("Saldo....:");
        jLabel16.setToolTipText("");

        lbDebito.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lbDebito.setForeground(new java.awt.Color(255, 0, 0));
        lbDebito.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbDebito.setText("0,00");
        lbDebito.setToolTipText("");

        lbCredito.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lbCredito.setForeground(new java.awt.Color(0, 0, 255));
        lbCredito.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbCredito.setText("0,00");
        lbCredito.setToolTipText("");

        lbTotal.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lbTotal.setForeground(new java.awt.Color(0, 0, 0));
        lbTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbTotal.setText("0,00");
        lbTotal.setToolTipText("");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDebito, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(lbCredito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(201, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lbDebito))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(lbCredito))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(lbTotal))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
        if (variaveis.status == "I")
        {
            if (txtDescricao.getText().equals(null) || (txtDescricao.getText().equals("")))
            {
                JOptionPane.showMessageDialog(null,"Nome / Razão Social / Descrição não foi informada");
                txtDescricao.requestFocus();
                return;
            }
            Object[] options = { " Sim ", " Não " };
            int confirmar = JOptionPane.showOptionDialog(null, "Confirma Inclusão", " Mensagem...",JOptionPane.DEFAULT_OPTION, JOptionPane. QUESTION_MESSAGE,null,options, options[0]);
            if(confirmar==1){
                txtDescricao.requestFocus();
                return;
               }
            try
            {
               String vmCampos = "cc_descricao,"+
                                 "cc_banc_codigo_id,"+
                                 "cc_data_lancamento,"+
                                 "cc_documento,"+
                                 "cc_numero_cheque,"+
                                 "cc_historico,"+
                                 "cc_credito,"+
                                 "cc_debito,"+
                                 "cc_realizado,"+
                                 "cc_cp_codigo_id,"+
                                 "cc_cr_codigo_id";

               String vmParametros = "'" + txtDescricao.getText() + "'," +
                                     "'" + txtBancoID.getText() + "'," +
                                     "'" + txtDataLancamento.getText() + "'," +
                                     "'" + txtDocumento.getText() + "'," +
                                     "'" + txtNumeroCheque.getText() + "'," +
                                     "'" + txtHistorico.getText() + "'," +
                                     "'" + txtCredito.getText().replace(".", "").replace(",", ".") + "'," +
                                     "'" + txtDebito.getText().replace(".", "").replace(",", ".") + "'," +
                                     "'" + ddlRealizado.getSelectedItem().toString() + "'," +
                                     "Null," +
                                     "Null" ;


            AcessoDados.Inserir("contas_corrente", vmCampos, vmParametros);

            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(variaveis.status == "A")
        {
            Object[] options = { " Sim ", " Não " };
            int confirmar = JOptionPane.showOptionDialog(null, "Confirma Alteração", " Mensagem...",JOptionPane.DEFAULT_OPTION, JOptionPane. QUESTION_MESSAGE,null,options, options[0]);
            if(confirmar==1){
                txtDescricao.requestFocus();
                return;
               }
                 try
            {
               String vmCampos_Parametros =
                                         "cc_descricao = '" + txtDescricao.getText() + "'," +
                                         "cc_banc_codigo_id = '" + txtBancoID.getText() + "'," +
                                         "cc_data_lancamento = '" + txtDataLancamento.getText() + "'," +
                                         "cc_documento = '" + txtDocumento.getText() + "'," +
                                         "cc_numero_cheque = '" + txtNumeroCheque.getText() + "'," +
                                         "cc_historico = '" + txtHistorico.getText() + "'," +
                                         "cc_credito = '" + txtCredito.getText().replace(".", "").replace(",", ".") + "'," +
                                         "cc_debito = '" + txtDebito.getText().replace(".", "").replace(",", ".") + "'," +
                                         "cc_realizado = '" + ddlRealizado.getSelectedItem().toString() +"'";



               String vmCondicao_Alteracao ="cc_codigo_id = " + lbID.getText();

            AcessoDados.Alterar("contas_corrente", vmCampos_Parametros, vmCondicao_Alteracao);

            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        lbID.setText("0");
        txtDescricao.setText(null);
        txtDataLancamento.setText(null);
        txtDocumento.setText(null);
        txtNumeroCheque.setText(null);
        txtHistorico.setText(null);
        txtCredito.setText(null);
        txtDebito.setText(null);
        ddlRealizado.setSelectedItem("0");
        lbStatus.setText("Registro ");
        ddlRealizado.setEnabled(true);
        btnNovo.requestFocus();
        //MontaGrid();
        
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:
        if(funcoes.Permissao("001", variaveis.usuario_id, variaveis.usuario_super, this.getTitle()))
        {
        if(txtBancoID.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null,"Selecione uma conta bancária para lançamento.");
            return;
        }
        funcoes.HabilitaCampos(rootPane, true);
        lbStatus.setText(" Incluindo...");
        txtDescricao.requestFocus();
        Date data = new Date(System.currentTimeMillis());
        SimpleDateFormat formatarDate = new SimpleDateFormat("dd/MM/yyyy");
        txtDataLancamento.setText(formatarDate.format(data));
        txtDescricao.requestFocus();
        variaveis.status = "I";
        }
        
    }//GEN-LAST:event_btnNovoActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formWindowActivated

    private void grDadosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grDadosKeyReleased
        // TODO add your handling code here:
         if ((evt.getKeyCode() ==  java.awt.event.KeyEvent.VK_UP)  || (evt.getKeyCode() ==  java.awt.event.KeyEvent.VK_DOWN)) {
              RetornaDadosGrid();
         };
    }//GEN-LAST:event_grDadosKeyReleased

    private void grDadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grDadosMouseClicked
        // TODO add your handling code here:
         RetornaDadosGrid();       
    }//GEN-LAST:event_grDadosMouseClicked

    private void txtBancoIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBancoIDFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBancoIDFocusLost

    private void btnPesquisaBancoIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaBancoIDActionPerformed
        // TODO add your handling code here:
        if(funcoes.Permissao("004", variaveis.usuario_id, variaveis.usuario_super, this.getTitle()))
        {
         variaveis.xColuna1 = "banc_codigo_id";
         variaveis.xColuna2 = "banc_nome_banco";
         variaveis.xColuna3 = "banc_numero_agencia";
         variaveis.xColuna4 = "banc_numero_conta";
         variaveis.xColuna5 = "''";
         variaveis.xColuna6 = "''";
         variaveis.xColuna7 = "''";
         variaveis.xColuna8 = "''";
         variaveis.xColuna9 = "''";
         
         variaveis.xColunaNome1 = "Código";
         variaveis.xColunaNome2 = "Descrição";
         variaveis.xColunaNome3 = "Agência";
         variaveis.xColunaNome4 = "C/C";
         variaveis.xColunaNome5 = "";
         variaveis.xColunaNome6 = "";
         variaveis.xColunaNome7 = "";
         variaveis.xColunaNome8 = "";
         variaveis.xColunaNome9 = "";
         
         variaveis.xColunaTamanho1 = 60;
         variaveis.xColunaTamanho2 = 250;
         variaveis.xColunaTamanho3 = 75;
         variaveis.xColunaTamanho4 = 75;
         variaveis.xColunaTamanho5 = 0;
         variaveis.xColunaTamanho6 = 0;
         variaveis.xColunaTamanho7 = 0;
         variaveis.xColunaTamanho8 = 0;
         variaveis.xColunaTamanho9 = 0;
         
         variaveis.xColunaStart = "banc_nome_banco";
         variaveis.xColunaNomeStart = "Descrição";
         variaveis.xTabela = "contas_bancarias";
         variaveis.xSql = ""; 
         
         FpesqPesquisa md = new FpesqPesquisa(null, true);
         Dimension d = new Dimension();   
         d.setSize(470, 480); 
         md.setSize(d);
         
         md.setTitle("Pesquisa Conta Bancária - ENTER ou DUPLO CLICK no registro retorna dados.");

         md.setLocationRelativeTo(null);
         md.setVisible(true);
       //txtBancoID.setText("0");
       if(!md.getRetorno().trim().equals("") ||!md.getRetorno().equals(null))
       {
          txtBancoID.setText((String) md.getRetorno());
       }
       
       if(!txtBancoID.getText().equals(""))
       {
          String vmCampos = "*";
          String vmCondicao_Consulta = " WHERE banc_codigo_id = " + txtBancoID.getText();
          funcoes.RetornarSelecao(rootPane,"contas_bancarias", vmCampos, vmCondicao_Consulta);
       
           MontaGrid();
       }
       }
    }//GEN-LAST:event_btnPesquisaBancoIDActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        //funcoes.limparTodosCampos(rootPane);
        funcoes.HabilitaCampos(rootPane, false);
        lbID.setText("0");
        txtDescricao.setText("");
        txtDocumento.setText("");
        txtDataLancamento.setText("");
        txtDebito.setText("0");
        txtCredito.setText("0");
        txtNumeroCheque.setText("");
        txtHistorico.setText("");
        ddlRealizado.setEnabled(true);
        
        btnNovo.requestFocus();
        lbStatus.setText(" Registro");
        txtBancoID.setText("1");

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // TODO add your handling code here:
        if(funcoes.Permissao("002", variaveis.usuario_id, variaveis.usuario_super, this.getTitle()))
        {
        if (lbID.getText().equals(null) || (lbID.getText().equals("0"))) {
            JOptionPane.showMessageDialog(null, "Selecione um Documento antes de alterar");
            return;
        }
        funcoes.HabilitaCampos(rootPane, true);
        lbStatus.setText(" Alterando");
        txtDescricao.requestFocus();
        variaveis.status = "A";
        }

    }//GEN-LAST:event_btnAlterarActionPerformed

    private void ddlRealizadoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ddlRealizadoFocusLost
        // TODO add your handling code here:
       
    }//GEN-LAST:event_ddlRealizadoFocusLost

    private void ddlRealizadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ddlRealizadoItemStateChanged
        // TODO add your handling code here:
        MontaGrid();
    }//GEN-LAST:event_ddlRealizadoItemStateChanged

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void txtCreditoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCreditoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCreditoFocusLost

    private void txtDebitoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDebitoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDebitoFocusLost

    private void txtDocumentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocumentoKeyReleased
        // TODO add your handling code here:
         //funcoes.ValidaNumero(txtDocumento);
        JTextField tf = (JTextField) evt.getComponent();
        if (evt.getKeyCode()!=32 & evt.getKeyCode()!=8 & evt.getKeyCode()!=37 & evt.getKeyCode()!=39)
        tf.setText(tf.getText().toUpperCase());
        String str = txtDocumento.getText();
        if (str.length()>20){
            String strCut = str.substring(0,20);
            txtDocumento.setText(strCut);}
    }//GEN-LAST:event_txtDocumentoKeyReleased

    private void txtNumeroChequeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroChequeKeyReleased
        // TODO add your handling code here:
        //funcoes.ValidaNumero(txtNumeroCheque);
        JTextField tf = (JTextField) evt.getComponent();
        if (evt.getKeyCode()!=32 & evt.getKeyCode()!=8 & evt.getKeyCode()!=37 & evt.getKeyCode()!=39)
        tf.setText(tf.getText().toUpperCase());
        String str = txtNumeroCheque.getText();
        if (str.length()>20){
            String strCut = str.substring(0,20);
            txtNumeroCheque.setText(strCut);}
    }//GEN-LAST:event_txtNumeroChequeKeyReleased

    private void txtCreditoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCreditoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCreditoKeyReleased

    private void txtDebitoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDebitoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDebitoKeyReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        //MontaGrid();
    }//GEN-LAST:event_formWindowOpened

    private void txtDescricaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescricaoKeyReleased
        // TODO add your handling code here:
         JTextField tf = (JTextField) evt.getComponent();
        if (evt.getKeyCode()!=32 & evt.getKeyCode()!=8 & evt.getKeyCode()!=37 & evt.getKeyCode()!=39)
        tf.setText(tf.getText().toUpperCase());
        String str = txtDescricao.getText();
        if (str.length()>60){
            String strCut = str.substring(0,60);
            txtDescricao.setText(strCut);}
    }//GEN-LAST:event_txtDescricaoKeyReleased

    private void txtHistoricoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHistoricoKeyReleased
        // TODO add your handling code here:
         JTextArea tf = (JTextArea) evt.getComponent();
        if (evt.getKeyCode()!=32 & evt.getKeyCode()!=8 & evt.getKeyCode()!=37 & evt.getKeyCode()!=39)
        tf.setText(tf.getText().toUpperCase());
        txtHistorico.setLineWrap(true);
        txtHistorico.setWrapStyleWord(true);
        String str = txtHistorico.getText();
        if (str.length()>255){
            String strCut = str.substring(0,255);
            txtHistorico.setText(strCut);}
    }//GEN-LAST:event_txtHistoricoKeyReleased

    private void txtHistoricoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHistoricoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10) {
            txtCredito.requestFocus();
        }
    }//GEN-LAST:event_txtHistoricoKeyPressed

    private void txtHistoricoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHistoricoFocusLost
        // TODO add your handling code here:
        txtHistorico.setText(txtHistorico.getText().trim());
    }//GEN-LAST:event_txtHistoricoFocusLost

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        if(funcoes.Permissao("003", variaveis.usuario_id, variaveis.usuario_super, this.getTitle()))
        {
        funcoes.ExcluirDados(lbID.getText(), rootPane, "contas_corrente", "cc_codigo_id");
        btnNovo.requestFocus();
//        MontaGrid();
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void txtDataLancamentoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDataLancamentoFocusGained
        // TODO add your handling code here:
         JTextField tf = (JTextField) evt.getComponent();
         tf.selectAll();
    }//GEN-LAST:event_txtDataLancamentoFocusGained

    private void txtDocumentoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDocumentoFocusGained
        // TODO add your handling code here:
         JTextField tf = (JTextField) evt.getComponent();
         tf.selectAll();
    }//GEN-LAST:event_txtDocumentoFocusGained

    private void txtNumeroChequeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumeroChequeFocusGained
        // TODO add your handling code here:
        JTextField tf = (JTextField) evt.getComponent();
        tf.selectAll();
    }//GEN-LAST:event_txtNumeroChequeFocusGained

    private void txtCreditoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCreditoFocusGained
        // TODO add your handling code here:
        JTextField tf = (JTextField) evt.getComponent();
        tf.selectAll();
    }//GEN-LAST:event_txtCreditoFocusGained

    private void txtDebitoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDebitoFocusGained
        // TODO add your handling code here:
        JTextField tf = (JTextField) evt.getComponent();
        tf.selectAll();
    }//GEN-LAST:event_txtDebitoFocusGained

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnImprimirActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmContaCorrente dialog = new FrmContaCorrente(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisaBancoID;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox ddlRealizado;
    private javax.swing.JTable grDados;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbCredito;
    private javax.swing.JLabel lbDebito;
    private javax.swing.JLabel lbID;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JTextField txtAgencia;
    private javax.swing.JTextField txtBancoID;
    private javax.swing.JTextField txtConta;
    private javax.swing.JTextField txtCredito;
    private javax.swing.JFormattedTextField txtDataLancamento;
    private javax.swing.JTextField txtDebito;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtDescricaoBanco;
    private javax.swing.JTextField txtDocumento;
    private javax.swing.JTextArea txtHistorico;
    private javax.swing.JTextField txtNumeroCheque;
    // End of variables declaration//GEN-END:variables

}
