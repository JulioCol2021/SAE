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
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import Classes.claAcessoDados;
import Classes.claVariaveis;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import Classes.claVariaveis;
import Classes.claAcessoDados;
import sae.FrmUsuario;
import Classes.claFuncoes;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TabExpander;
import javax.swing.table.*;
import javax.swing.table.DefaultTableCellRenderer;
import org.apache.xalan.lib.sql.XConnection;
import sae.FrmCidades;

/**
 *
 * @author Thiago
 */
public class FpesqPesquisa extends javax.swing.JDialog {
    claVariaveis variaveis = new claVariaveis();
    claAcessoDados AcessoDados = new claAcessoDados();
    claFuncoes funcoes = new claFuncoes();
    private ResultSet rs;
    private String retorno;
    public String getRetorno () { return retorno; }
    int mColIndex;
    private String xCampo;


    /** Creates new form FrmUsuario */
    public FpesqPesquisa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        funcoes.F_AtribuirClasse(rootPane);
        MontaCabecalhoGrid();
        JTableHeader header = grDados.getTableHeader();
        header.addMouseListener(new ColumnHeaderListener());
        TableEnterAction(grDados);
        grDados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       

    }
    
 class ColumnHeaderListener extends MouseAdapter { public void mouseClicked(MouseEvent evt) {
  
    JTable table = ((JTableHeader)evt.getSource()).getTable();
    TableColumnModel colModel = table.getColumnModel();
    
    // índice da coluna cujo titulo foi clicado
    int vColIndex = colModel.getColumnIndexAtX(evt.getX());
    mColIndex = table.convertColumnIndexToModel(vColIndex);
    
    if(vColIndex == -1) {
      return;
    }

    if(mColIndex == 0) {
      lbPesquisa.setText("Pesquisar " + variaveis.xColunaNome1 + " :  " );
      xCampo = variaveis.xColuna1 ;
    
    }
    if(mColIndex == 1) {
      lbPesquisa.setText("Pesquisar " + variaveis.xColunaNome2 + " :  " );
      xCampo = variaveis.xColuna2;
    
    }
    if(mColIndex == 2) {
      lbPesquisa.setText("Pesquisar " + variaveis.xColunaNome3 + " :  " );
      xCampo = variaveis.xColuna3;
    
    }
    if(mColIndex == 3) {
      lbPesquisa.setText("Pesquisar " + variaveis.xColunaNome4 + " :  " );
      xCampo = variaveis.xColuna4;
    
    }
    if(mColIndex == 4) {
      lbPesquisa.setText("Pesquisar " + variaveis.xColunaNome5 + " :  " );
      xCampo = variaveis.xColuna5;
    
    }
    if(mColIndex == 5) {
      lbPesquisa.setText("Pesquisar " + variaveis.xColunaNome6 + " :  " );
      xCampo = variaveis.xColuna6;
    
    }
    if(mColIndex == 6) {
      lbPesquisa.setText("Pesquisar " + variaveis.xColunaNome7 + " :  " );
      xCampo = variaveis.xColuna7;
    
    }
    if(mColIndex == 7) {
      lbPesquisa.setText("Pesquisar " + variaveis.xColunaNome8 + " :  " );
      xCampo = variaveis.xColuna8;
    
    }
    if(mColIndex == 8) {
      lbPesquisa.setText("Pesquisar " + variaveis.xColunaNome9 + " :  " );
      xCampo = variaveis.xColuna9;
    
    }

    txtRetornoKeyReleased(null);
    txtRetorno.requestFocus();
    //System.out.println("O clique ocorreu no titulo da coluna com indice " + mColIndex);
  }
}    
 
 @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        grDados = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        lbPesquisa = new javax.swing.JLabel();
        txtRetorno = new javax.swing.JTextField();
        lbqtde = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisa Cidade - Enter ou Duplo click retorna dados");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        grDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Cód. UF", "UF", "Cód. Mun.", "Município"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        grDados.setToolTipText("");
        grDados.getTableHeader().setResizingAllowed(false);
        grDados.getTableHeader().setReorderingAllowed(false);
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                grDadosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(grDados);
        grDados.getColumnModel().getColumn(0).setResizable(false);
        grDados.getColumnModel().getColumn(0).setPreferredWidth(20);
        grDados.getColumnModel().getColumn(1).setResizable(false);
        grDados.getColumnModel().getColumn(1).setPreferredWidth(20);
        grDados.getColumnModel().getColumn(2).setResizable(false);
        grDados.getColumnModel().getColumn(2).setPreferredWidth(10);
        grDados.getColumnModel().getColumn(3).setResizable(false);
        grDados.getColumnModel().getColumn(3).setPreferredWidth(30);
        grDados.getColumnModel().getColumn(4).setResizable(false);
        grDados.getColumnModel().getColumn(4).setPreferredWidth(300);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        lbPesquisa.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lbPesquisa.setText("Pesquisa Munícipio:  ");
        lbPesquisa.setFocusable(false);
        lbPesquisa.setRequestFocusEnabled(false);
        jToolBar1.add(lbPesquisa);

        txtRetorno.setColumns(30);
        txtRetorno.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtRetorno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRetornoKeyReleased(evt);
            }
        });
        jToolBar1.add(txtRetorno);

        lbqtde.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lbqtde.setText("  Qtde. Registro: 0000");
        lbqtde.setFocusable(false);
        lbqtde.setRequestFocusEnabled(false);
        jToolBar1.add(lbqtde);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void limpaCampos(){
         }
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        xCampo = variaveis.xColunaStart;
        lbPesquisa.setText("Pesquisar " + variaveis.xColunaNomeStart + " :  " );
        MontaCabecalhoGrid();
        txtRetornoKeyReleased(null);
        txtRetorno.requestFocus();
       
    }//GEN-LAST:event_formWindowActivated

    private void grDadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grDadosMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount()==2)  
        {
            retorno = grDados.getValueAt(grDados.getSelectedRow(), 0).toString();
            variaveis.xCondicaoAND = "";
            dispose();
        }
    }//GEN-LAST:event_grDadosMouseClicked

    private void grDadosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grDadosKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_grDadosKeyReleased

    private void txtRetornoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRetornoKeyReleased
        // TODO add your handling code here:
       DefaultTableModel tableModel =(DefaultTableModel) grDados.getModel();
       tableModel.setNumRows(0);
       String vmCampos = " "+variaveis.xColuna1+" as coluna1, "
                       + " "+variaveis.xColuna2+" as coluna2, "
                       + " "+variaveis.xColuna3+" as coluna3, "
                       + " "+variaveis.xColuna4+" as coluna4, "
                       + " "+variaveis.xColuna5+" as coluna5, "
                       + " "+variaveis.xColuna6+" as coluna6, "
                       + " "+variaveis.xColuna7+" as coluna7, "
                       + " "+variaveis.xColuna8+" as coluna8, "
                       + " "+variaveis.xColuna9+" as coluna9  ";
        String CondicaoConsulta2 = " order by "+ xCampo; 
        String vmCondicao_Consulta = " where CAST("+ xCampo +" AS CHAR(50))  like '" + txtRetorno.getText() + "%' " +  variaveis.xCondicaoAND + CondicaoConsulta2;
        try
        {
        rs = AcessoDados.Selecao(variaveis.xTabela, vmCampos, variaveis.xSql + vmCondicao_Consulta);
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
        while (rs.next())
        {
            dtm.addRow(new Object[]{rs.getString("coluna1"),
                                    rs.getString("coluna2"),
                                    rs.getString("coluna3"),
                                    rs.getString("coluna4"),
                                    rs.getString("coluna5"), 
                                    rs.getString("coluna6"), 
                                    rs.getString("coluna7"), 
                                    rs.getString("coluna8"), 
                                    rs.getString("coluna9")});
        }
        lbqtde.setText("  Qtde. Registro : " + funcoes.StrZero(grDados.getRowCount(), Byte.parseByte("6")));
        rs.close();
         }catch (Exception e) {
           e.printStackTrace();
        }
    

    }//GEN-LAST:event_txtRetornoKeyReleased

    public void MontaCabecalhoGrid()
    {
       grDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {variaveis.xColunaNome1, 
                           variaveis.xColunaNome2,
                           variaveis.xColunaNome3, 
                           variaveis.xColunaNome4, 
                           variaveis.xColunaNome5, 
                           variaveis.xColunaNome6, 
                           variaveis.xColunaNome7,
                           variaveis.xColunaNome8,
                           variaveis.xColunaNome9
            }) { boolean[]  canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }

        });
        //grDados.getColumnModel().getColumn(0).setPreferredWidth(20);
        //grDados.getColumnModel().getColumn(1).setPreferredWidth(450);
        //grDados.getColumnModel().getColumn(2).setPreferredWidth(10);
        //grDados.getColumnModel().getColumn(3).setPreferredWidth(30);
        //grDados.getColumnModel().getColumn(4).setPreferredWidth(30);
        //grDados.getColumnModel().getColumn(5).setPreferredWidth(0);
        //grDados.getColumnModel().getColumn(6).setPreferredWidth(10);


        TableColumn IDColumn1 = grDados.getColumnModel().getColumn(0);
        IDColumn1.setMinWidth(variaveis.xColunaTamanho1);
        IDColumn1.setMaxWidth(variaveis.xColunaTamanho1);

        TableColumn IDColumn2 = grDados.getColumnModel().getColumn(1);
        IDColumn2.setMinWidth(variaveis.xColunaTamanho2);
        IDColumn2.setMaxWidth(variaveis.xColunaTamanho2);

        TableColumn IDColumn3 = grDados.getColumnModel().getColumn(2);
        IDColumn3.setMinWidth(variaveis.xColunaTamanho3);
        IDColumn3.setMaxWidth(variaveis.xColunaTamanho3);
        
        TableColumn IDColumn4 = grDados.getColumnModel().getColumn(3);
        IDColumn4.setMinWidth(variaveis.xColunaTamanho4);
        IDColumn4.setMaxWidth(variaveis.xColunaTamanho4);
        
        TableColumn IDColumn5 = grDados.getColumnModel().getColumn(4);
        IDColumn5.setMinWidth(variaveis.xColunaTamanho5);
        IDColumn5.setMaxWidth(variaveis.xColunaTamanho5);
        
        TableColumn IDColumn6 = grDados.getColumnModel().getColumn(5);
        IDColumn6.setMinWidth(variaveis.xColunaTamanho6);
        IDColumn6.setMaxWidth(variaveis.xColunaTamanho6);
        
        TableColumn IDColumn7 = grDados.getColumnModel().getColumn(6);
        IDColumn7.setMinWidth(variaveis.xColunaTamanho7);
        IDColumn7.setMaxWidth(variaveis.xColunaTamanho7);
        
        TableColumn IDColumn8 = grDados.getColumnModel().getColumn(7);
        IDColumn8.setMinWidth(variaveis.xColunaTamanho8);
        IDColumn8.setMaxWidth(variaveis.xColunaTamanho8);
        
        TableColumn IDColumn9 = grDados.getColumnModel().getColumn(8);
        IDColumn9.setMinWidth(variaveis.xColunaTamanho9);
        IDColumn9.setMaxWidth(variaveis.xColunaTamanho9);
        
    }
    private void grDadosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grDadosFocusGained
        // TODO add your handling code here:
        grDados.addRowSelectionInterval(0,0);
    }//GEN-LAST:event_grDadosFocusGained

    public  void   TableEnterAction(final JTable ao_table) {
      InputMap
      im = ao_table.getInputMap(ao_table.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
      KeyStroke lo_key_enter = KeyStroke.getKeyStroke("ENTER");
      im.put(lo_key_enter, im.get(KeyStroke.getKeyStroke(KeyEvent.VK_GREATER, 0)));
      Action
      enterAction = new AbstractAction() {
                public  void    actionPerformed(ActionEvent e)
                {
                    //aqui fax as pesquisas
                    retorno = ao_table.getValueAt(ao_table.getSelectedRow(), 0).toString();
                    variaveis.xCondicaoAND = "";
                    dispose();

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
                FrmUsuario dialog = new FrmUsuario(new javax.swing.JFrame(), true);
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
    private javax.swing.JTable grDados;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbPesquisa;
    private javax.swing.JLabel lbqtde;
    private javax.swing.JTextField txtRetorno;
    // End of variables declaration//GEN-END:variables

}
