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
public class FrmProducao extends javax.swing.JDialog {
    claVariaveis variaveis = new claVariaveis();
    claAcessoDados AcessoDados = new claAcessoDados();
    claFuncoes funcoes = new claFuncoes();
    private ResultSet rs, rsLote, rsImprimir;
    private DefaultTableModel dtm = new DefaultTableModel();

    /** Creates new form FrmAlmoxarifados */
    public FrmProducao(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        funcoes.F_AtribuirClasse(rootPane);
        TableEnterAction(grDados);
        grDados.setDefaultRenderer(Object.class, new TabelaItemCotacao());
        //MontaTituloGrid();
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
        pnlDados = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtParceiro = new javax.swing.JTextField();
        txtNomeParceiro = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        grDados = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        lbStatusOC = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNomeParceiro1 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btnSair = new javax.swing.JButton();
        btnApuracao = new javax.swing.JButton();
        lbItem = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ordem de Produção");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        pnlDados.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabel4.setText("Produto :");
        jLabel4.setToolTipText("");

        txtParceiro.setColumns(8);
        txtParceiro.setEditable(false);
        txtParceiro.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtParceiro.setToolTipText("Código");
        txtParceiro.setEnabled(false);
        txtParceiro.setName("");
        txtParceiro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtParceiroFocusLost(evt);
            }
        });
        txtParceiro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtParceiroKeyReleased(evt);
            }
        });

        txtNomeParceiro.setEditable(false);
        txtNomeParceiro.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtNomeParceiro.setForeground(new java.awt.Color(102, 102, 255));
        txtNomeParceiro.setToolTipText("");
        txtNomeParceiro.setFocusable(false);
        txtNomeParceiro.setName("");
        txtNomeParceiro.setRequestFocusEnabled(false);

        grDados.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        grDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descrição", "Qtde", "Estoque", "Produção", "Liberado"
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
        grDados.getColumnModel().getColumn(0).setPreferredWidth(60);
        grDados.getColumnModel().getColumn(1).setPreferredWidth(300);
        grDados.getColumnModel().getColumn(2).setPreferredWidth(50);
        grDados.getColumnModel().getColumn(3).setPreferredWidth(50);
        grDados.getColumnModel().getColumn(4).setPreferredWidth(50);
        grDados.getColumnModel().getColumn(5).setPreferredWidth(10);

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel9.setText("-- Itens Composição ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        jLabel9.setToolTipText("");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        lbStatusOC.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lbStatusOC.setForeground(new java.awt.Color(0, 102, 51));
        lbStatusOC.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbStatusOC.setToolTipText("");

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabel7.setText("UM:");
        jLabel7.setToolTipText("");

        txtNomeParceiro1.setEditable(false);
        txtNomeParceiro1.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtNomeParceiro1.setForeground(new java.awt.Color(102, 102, 255));
        txtNomeParceiro1.setToolTipText("");
        txtNomeParceiro1.setFocusable(false);
        txtNomeParceiro1.setName("");
        txtNomeParceiro1.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtParceiro, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomeParceiro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomeParceiro1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbStatusOC, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtParceiro, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomeParceiro, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtNomeParceiro1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbStatusOC, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );

        pnlDados.addTab("Dados", jPanel1);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Sair.gif"))); // NOI18N
        btnSair.setToolTipText("Voltar");
        btnSair.setBorderPainted(false);
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        btnApuracao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/confirma.gif"))); // NOI18N
        btnApuracao.setToolTipText("Confirmar Produção");
        btnApuracao.setBorderPainted(false);
        btnApuracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApuracaoActionPerformed(evt);
            }
        });

        lbItem.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lbItem.setText("0");
        lbItem.setToolTipText("ID");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(btnApuracao, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(484, 484, 484)
                .addComponent(lbItem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnSair, javax.swing.GroupLayout.Alignment.LEADING, 0, 20, Short.MAX_VALUE)
                    .addComponent(btnApuracao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(lbItem)
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
                .addComponent(pnlDados, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

   
   
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

    private void grDadosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grDadosKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_grDadosKeyPressed

    private void grDadosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grDadosFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_grDadosFocusGained

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jLabel9MouseClicked

    private void btnApuracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApuracaoActionPerformed
        // TODO add your handling code here:
                
    }//GEN-LAST:event_btnApuracaoActionPerformed

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
                FrmProducao dialog = new FrmProducao(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnSair;
    private javax.swing.JTable grDados;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbItem;
    private javax.swing.JLabel lbStatusOC;
    private javax.swing.JTabbedPane pnlDados;
    private javax.swing.JTextField txtNomeParceiro;
    private javax.swing.JTextField txtNomeParceiro1;
    private javax.swing.JTextField txtParceiro;
    // End of variables declaration//GEN-END:variables

}
