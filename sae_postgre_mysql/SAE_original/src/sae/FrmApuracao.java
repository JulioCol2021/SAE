/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmUsuario.java
 *
 * Created on 02/01/2010, 13:07:26
 */

package sae;
import Classes.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.io.*;
import java.net.URL;
import java.security.Security;
import java.sql.Connection;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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

import org.apache.axiom.om.OMElement;  
import org.apache.axiom.om.util.AXIOMUtil; 
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;
import java.sql.PreparedStatement;
import java.util.concurrent.ExecutionException;


/**
 *
 * @author Thiago
 */
public class FrmApuracao extends javax.swing.JDialog {
    claVariaveis variaveis = new claVariaveis();
    claAcessoDados AcessoDados = new claAcessoDados();
    claFuncoes funcoes = new claFuncoes();
    Assinador AsinarXML = new Assinador();
    private ResultSet rs, rsApuracao, rsCliente;
    private PrintStream p;
    Connection conn = new claConexao().getConexao();

    /** Creates new form FrmUsuario */
    public FrmApuracao(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        funcoes.F_AtribuirClasse(rootPane);
        //MontaTituloColunaGrid();
        //MontaGridNota();
        grFornecedores.setDefaultRenderer(Object.class, new TabelaNotaEletronica());
        grProdutos.setDefaultRenderer(Object.class, new TabelaNotaEletronica());
        //RetornaLote();
        grFornecedores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Apuracao();
    }
    public void Apuracao()
    {
        DefaultTableModel tableModel =(DefaultTableModel) grFornecedores.getModel();
        tableModel.setNumRows(0);
        
        DefaultTableModel tableModel1 =(DefaultTableModel) grProdutos.getModel();
        tableModel1.setNumRows(0);

        String Campos = " distinct MIN(item_total) AS MENOR_VALOR,"+
                        "COT.COT_CODIGO_ID,"+
                        "COT.COT_NUMERO_SOLICITACAO,"+
                        "PROD.PROD_DESCRICAO, PROD.PROD_CODIGO_ID, ITEM_QUANTIDADE";

        String Consulta = "";
        javax.swing.table.DefaultTableModel dtmFornecedor = (javax.swing.table.DefaultTableModel)grFornecedores.getModel();

        try
        {
           //rs = AcessoDados.Selecao("ITEM_COTACAO", "*", " WHERE ITEM_COT_CODIGO_ID =  " + variaveis.vmCotacao + " ORDER BY ITEM_PROD_CODIGO_ID");   
           //while(rs.next())
           //{
             Consulta = " INNER JOIN COTACAO COT ON (ITEM_COT_CODIGO_ID = COT_CODIGO_ID)"+
                          " INNER JOIN PRODUTOS PROD ON (PROD_CODIGO_ID = ITEM_PROD_CODIGO_ID)"+
                          " WHERE item_cot_codigo_id =  " + variaveis.vmCotacao +
                          " GROUP BY"+
                          " COT.COT_CODIGO_ID,"+
                          " COT.COT_NUMERO_SOLICITACAO,"+
                          " PROD.PROD_DESCRICAO, PROD.PROD_CODIGO_ID, ITEM_QUANTIDADE order by PROD.PROD_DESCRICAO";

               rsApuracao = AcessoDados.Selecao("ITEM_COTACAO ITEM", Campos, Consulta); 
               AcessoDados.Excluir("item_apuracao", " ITEM_COT_CODIGO_ID = " + variaveis.vmCotacao);
               while (rsApuracao.next())
               {
                   rsCliente = AcessoDados.Selecao("PARCEIROS", " PAR_NOME_RAZAO_SOCIAL, PAR_CODIGO_ID ", " INNER JOIN ITEM_COTACAO ON (ITEM_PAR_CODIGO_ID = PAR_CODIGO_ID) " +
                                                                                                          " WHERE ITEM_TOTAL =  " + rsApuracao.getString("MENOR_VALOR"));   
                   
                   rsCliente.next();
                   
                   
                   String SqlInsert = "  item_cot_codigo_id , "+
                                      "  item_par_codigo_id ,"+
                                      "  item_prod_codigo_id ,"+
                                      "  item_quantidade ,"+
                                      "  item_unitario ,"+
                                      "  item_total ";
                   
                   String SqlParam ="";
                   SqlParam += rsApuracao.getString("COT_CODIGO_ID") + ",";
                   SqlParam += rsCliente.getString("PAR_CODIGO_ID") + ",";
                   SqlParam += rsApuracao.getString("PROD_CODIGO_ID") + ",";
                   SqlParam += rsApuracao.getString("ITEM_QUANTIDADE") + ",";
                   double Unitario = rsApuracao.getDouble("MENOR_VALOR") / rsApuracao.getDouble("ITEM_QUANTIDADE");
                   SqlParam += Double.toString(Unitario) + ",";
                   SqlParam += rsApuracao.getString("MENOR_VALOR") ;
                   AcessoDados.Inserir("item_apuracao", SqlInsert, SqlParam);
                   lbSolicitacao.setText(funcoes.StrZero(Integer.parseInt(rsApuracao.getString("COT_NUMERO_SOLICITACAO")),Byte.parseByte("6")));
                   
               }
           //}
           
           //Carregando Apuraçao Fornecedor
           rsCliente = AcessoDados.Selecao("PARCEIROS", " DISTINCT PAR_NOME_RAZAO_SOCIAL, PAR_CODIGO_ID ", " INNER JOIN ITEM_APURACAO ON (ITEM_PAR_CODIGO_ID = PAR_CODIGO_ID) " +
                                                                                                  " WHERE ITEM_COT_CODIGO_ID = " + variaveis.vmCotacao + " ORDER BY PAR_NOME_RAZAO_SOCIAL");
           while (rsCliente.next())
           {
                   dtmFornecedor.addRow(new Object[]{
                       rsCliente.getString("PAR_CODIGO_ID"),
                       rsCliente.getString("PAR_NOME_RAZAO_SOCIAL") });
               
           }
           grFornecedores.setRowSelectionInterval(0, 0);
           grFornecedoresKeyReleased(null);
           grFornecedores.requestFocus();
        
        }catch(Exception e)
        {
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

        jToolBar1 = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        btnRetorno = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        grFornecedores = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        grProdutos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        lbSolicitacao = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Apuração");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel1.setText("Apuração / Ordem de Compra");
        jToolBar1.add(jLabel1);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        btnRetorno.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        btnRetorno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/confirma.gif"))); // NOI18N
        btnRetorno.setToolTipText("Emitir Ordem de Compra");
        btnRetorno.setBorderPainted(false);
        btnRetorno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetornoActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(btnRetorno, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 858, Short.MAX_VALUE)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnRetorno, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btnSair.getAccessibleContext().setAccessibleDescription("20");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fornecedores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N

        grFornecedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null},
                {"", null},
                {"", null},
                {"", null}
            },
            new String [] {
                "Código", "Fornecedor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        grFornecedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grFornecedoresMouseClicked(evt);
            }
        });
        grFornecedores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                grFornecedoresKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                grFornecedoresKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(grFornecedores);
        grFornecedores.getColumnModel().getColumn(0).setResizable(false);
        grFornecedores.getColumnModel().getColumn(0).setPreferredWidth(50);
        grFornecedores.getColumnModel().getColumn(1).setResizable(false);
        grFornecedores.getColumnModel().getColumn(1).setPreferredWidth(300);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Produtos / Fornecedor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N

        grProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Produto", "Qtde", "Vlr. Unit.", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(grProdutos);
        grProdutos.getColumnModel().getColumn(0).setResizable(false);
        grProdutos.getColumnModel().getColumn(0).setPreferredWidth(250);
        grProdutos.getColumnModel().getColumn(1).setResizable(false);
        grProdutos.getColumnModel().getColumn(1).setPreferredWidth(40);
        grProdutos.getColumnModel().getColumn(2).setResizable(false);
        grProdutos.getColumnModel().getColumn(2).setPreferredWidth(40);
        grProdutos.getColumnModel().getColumn(3).setResizable(false);
        grProdutos.getColumnModel().getColumn(3).setPreferredWidth(40);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setText("Nº Solicitação:");

        lbSolicitacao.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lbSolicitacao.setForeground(new java.awt.Color(153, 0, 0));
        lbSolicitacao.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbSolicitacao.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbSolicitacao, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lbSolicitacao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    public void RemontaTabelas()
    {

    }
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    
    }//GEN-LAST:event_formWindowActivated
    public class SimpleErrorHandler implements ErrorHandler  
    {  
        public void error(SAXParseException exception) {  
             System.out.println("error: "+ exception.getMessage());  
             //txtRetorno.setText("error: "+exception.getMessage());
         }  
                
         public void fatalError(SAXParseException exception) {  
             System.out.println("fatalError: "+ exception.getMessage());  
             //txtRetorno.setText("fatalError: "+exception.getMessage());
         }  
                
         public void warning(SAXParseException exception) {  
             System.out.println("warning: "+ exception.getMessage());  
             //txtRetorno.setText("warning: "+ exception.getMessage());
         }  
    }  

    private void btnRetornoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetornoActionPerformed
        // TODO add your handling code here:
       //if(txtChaveNFE.getText().equals(""))
       //{
       //   JOptionPane.showMessageDialog(null,"NF-e não emitida!");
       //   return;
       //}

       for(int i = 0; i <= grFornecedores.getRowCount() -1; i++)
       {


       String vmCampos = "";
       vmCampos = " PARCEIROS.*, ITEM_APURACAO.*, COTACAO.*,  CONDICAO_PAGAMENTO.*, CIDADES.CID_MUNICIPIO AS CID_PAR, CIDADES.CID_UF AS UF_PAR, "+
                  " SOLICITACAO_COMPRAS.*, FUNCIONARIOS.*, EMPRESAS.*, EMP.CID_MUNICIPIO AS CID_EMP, EMP.CID_UF AS UF_EMP, PRODUTOS.*, UNIDADE_DE_MEDIDA.* ";


       String vmCondicao_Consulta = "";
       vmCondicao_Consulta =  "left join parceiros on (par_codigo_id = item_par_codigo_id) "+
                              "left join cotacao on (cot_codigo_id =  item_cot_codigo_id) "+
                              "left join condicao_pagamento on (cpgto_codigo_id = cot_cpgto_codigo_id) "+
                              "left join cidades on (cid_codigo_id = par_cidade_comercial_codigo_id) "+
                              "left join solicitacao_compras on (cot_numero_solicitacao = com_numero_solicitacao) "+
                              "left join funcionarios on (func_codigo_id = com_func_codigo_id) "+
                              "left join empresas on (emp_codigo_id = com_emp_codigo_id) "+
                              "left join cidades emp on (emp.cid_codigo_id = emp_cid_codigo_id) "+
                              "left join produtos on (prod_codigo_id = item_prod_codigo_id) "+
                              "left join unidade_de_medida on (unid_codigo_id = prod_unid_codigo_id) "+
                              "where item_cot_codigo_id = "+ variaveis.vmCotacao + " and item_par_codigo_id =  " + grFornecedores.getValueAt(i, 0).toString() ;

       try
       {
        rs = AcessoDados.Selecao("item_apuracao", vmCampos, vmCondicao_Consulta);

       }catch (Exception e) {
           e.printStackTrace();
       }
           HashMap map = new HashMap();
        try
        {

        //String caminho = getClass().getResource("/Imagens/Logo.jpg").toString();
        //map.put("pLogo", caminho);

        JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getClassLoader().getResource("Relatorios/RelOrdemCompras.jasper"));
        JRResultSetDataSource jrRS1 = new JRResultSetDataSource(rs);
        //JasperReport jr = (JasperReport) JRLoader.loadObject(arquivo);

        JasperPrint jp = JasperFillManager.fillReport(jr, map,jrRS1);
        JasperViewer jv = new JasperViewer(jp,false);
        JDialog viewer = new  JDialog(jv, true);
        viewer.setSize(1000,700);
        viewer.setLocationRelativeTo(null);
        viewer.getContentPane().add(jv.getContentPane());
        viewer.setVisible(true);
        
        AcessoDados.Alterar("Solicitacao_Compras", " com_situacao = 'O.C EMITIDA' ", " COM_NUMERO_SOLICITACAO = " + lbSolicitacao.getText());

          }catch (Exception e) {
           e.printStackTrace();
        }
       }


  }//GEN-LAST:event_btnRetornoActionPerformed

    private void grFornecedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grFornecedoresMouseClicked
        // TODO add your handling code here:
        //Carregando Apuraçao Fornecedor
        CarregaProdutos();
        
    }//GEN-LAST:event_grFornecedoresMouseClicked

    private void grFornecedoresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grFornecedoresKeyPressed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_grFornecedoresKeyPressed

    private void grFornecedoresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grFornecedoresKeyReleased
        // TODO add your handling code here:
        CarregaProdutos();
    }//GEN-LAST:event_grFornecedoresKeyReleased
    public void CarregaProdutos()
    {
        DefaultTableModel tableModel1 =(DefaultTableModel) grProdutos.getModel();
        tableModel1.setNumRows(0);
        javax.swing.table.DefaultTableModel dtmProduto = (javax.swing.table.DefaultTableModel)grProdutos.getModel();

        try{ 
           rsCliente = AcessoDados.Selecao("ITEM_APURACAO", " * ", " INNER JOIN PRODUTOS ON (PROD_CODIGO_ID = ITEM_PROD_CODIGO_ID) WHERE ITEM_COT_CODIGO_ID = " + variaveis.vmCotacao + " AND ITEM_PAR_CODIGO_ID = " + 
                                                                        grFornecedores.getValueAt(grFornecedores.getSelectedRow(), 0).toString() + " ORDER BY PROD_DESCRICAO ");
           while (rsCliente.next())
           {
                   dtmProduto.addRow(new Object[]{
                       rsCliente.getString("PROD_DESCRICAO"),
                       funcoes.formataMoeda(rsCliente.getString("ITEM_QUANTIDADE"),"BD"),
                       funcoes.formataMoeda(rsCliente.getString("ITEM_UNITARIO"),"BD"),
                       funcoes.formataMoeda(rsCliente.getString("ITEM_TOTAL"),"BD")
                   });
           }
        
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    
    }
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmApuracao dialog = new FrmApuracao(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnRetorno;
    private javax.swing.JButton btnSair;
    private javax.swing.JTable grFornecedores;
    private javax.swing.JTable grProdutos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbSolicitacao;
    // End of variables declaration//GEN-END:variables

}
