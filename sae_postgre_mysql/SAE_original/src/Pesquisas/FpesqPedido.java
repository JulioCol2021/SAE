/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FpesqPedido.java
 *
 * Created on 27/02/2010, 16:29:35
 */

package Pesquisas;
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
import javax.swing.table.DefaultTableCellRenderer;
import sae.FrmUsuario;
import Classes.claFuncoes;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TabExpander;
import javax.swing.table.*;


/**
 *
 * @author thiago
 */
public class FpesqPedido extends javax.swing.JDialog {
    claVariaveis variaveis = new claVariaveis();
    claAcessoDados AcessoDados = new claAcessoDados();
    claFuncoes funcoes = new claFuncoes();
    private ResultSet rs;
    private String retorno;
    public String getRetorno () { return retorno; }

    /** Creates new form FpesqPedido */
    public FpesqPedido(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        funcoes.F_AtribuirClasse(rootPane);
        btnPesquisaNumeroPedido.setFocusTraversalKeysEnabled(false);
        btnPesquisaNotaFiscal.setFocusTraversalKeysEnabled(false);
        btnPesquisaDataFaturamento.setFocusTraversalKeysEnabled(false);
        btnPesquisaParceiro.setFocusTraversalKeysEnabled(false);
        btnPesquisaDataEmissao.setFocusTraversalKeysEnabled(false);
        
    }

    public void MontaTituloColunaGrid()
    {
        grDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"Pedido ","Tipo ","Nota", "Emissão", "Faturamento", "Parceiro",""
            }) { boolean[]  canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        grDados.getColumnModel().getColumn(0).setPreferredWidth(80);
        grDados.getColumnModel().getColumn(1).setPreferredWidth(150);
        grDados.getColumnModel().getColumn(2).setPreferredWidth(80);
        grDados.getColumnModel().getColumn(3).setPreferredWidth(90);
        grDados.getColumnModel().getColumn(4).setPreferredWidth(90);
        grDados.getColumnModel().getColumn(5).setPreferredWidth(350);
        grDados.getColumnModel().getColumn(6).setPreferredWidth(50);

        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();

        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);

        ButtonColumn buttonColumn = new ButtonColumn(grDados, 6);

    }



    public void RetornaDadosGrid()
    {

    }
    public void MontaGridNumPedido()
    {
        String vmCampos = "PED_CODIGO_ID, PED_TIPO_PEDIDO, PED_NUMERO_NOTA_FISCAL, to_char(PED_DATA_EMISSAO, 'DD/MM/YYYY') AS PED_DATA_EMISSAO, to_char(PED_DATA_FATURAMENTO, 'DD/MM/YYYY') AS PED_DATA_FATURAMENTO, PAR_NOME_RAZAO_SOCIAL";
        String vmCondicao_Consulta = "";

        if (!txtNumeroPedido.getText().equals(""))
            vmCondicao_Consulta = " INNER JOIN PARCEIROS ON (PED_PAR_CODIGO_ID = PAR_CODIGO_ID) WHERE PED_CODIGO_ID = " + txtNumeroPedido.getText() + " ORDER BY PED_CODIGO_ID DESC ";
        else
            vmCondicao_Consulta = " INNER JOIN PARCEIROS ON (PED_PAR_CODIGO_ID = PAR_CODIGO_ID) ORDER BY PED_CODIGO_ID DESC ";

        try
        {
        rs = AcessoDados.Selecao("PEDIDOS", vmCampos, vmCondicao_Consulta);
        MontaTituloColunaGrid();
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
        while (rs.next())
        {
            dtm.addRow(new Object[]{rs.getString("PED_CODIGO_ID"),rs.getString("PED_TIPO_PEDIDO"),rs.getString("PED_NUMERO_NOTA_FISCAL"),rs.getString("PED_DATA_EMISSAO"),rs.getString("PED_DATA_FATURAMENTO"),rs.getString("PAR_NOME_RAZAO_SOCIAL"),""});
        }
        rs.close();

           }catch (Exception e) {
           e.printStackTrace();
        }
    }
    public void MontaGridNumNotaFiscal()
    {

        String vmCampos = "PED_CODIGO_ID, PED_TIPO_PEDIDO, PED_NUMERO_NOTA_FISCAL, to_char(PED_DATA_EMISSAO, 'DD/MM/YYYY') AS PED_DATA_EMISSAO, to_char(PED_DATA_FATURAMENTO, 'DD/MM/YYYY') AS PED_DATA_FATURAMENTO, PAR_NOME_RAZAO_SOCIAL";
        String vmCondicao_Consulta = "";

        if (!txtNotaFical.getText().equals(""))
            vmCondicao_Consulta = " INNER JOIN PARCEIROS ON (PED_PAR_CODIGO_ID = PAR_CODIGO_ID) WHERE PED_NUMERO_NOTA_FISCAL = " + txtNotaFical.getText() + " ORDER BY PED_CODIGO_ID DESC";
        else
            vmCondicao_Consulta = " INNER JOIN PARCEIROS ON (PED_PAR_CODIGO_ID = PAR_CODIGO_ID) ORDER BY PED_CODIGO_ID DESC";

        try
        {
             rs = AcessoDados.Selecao("PEDIDOS", vmCampos, vmCondicao_Consulta);
             MontaTituloColunaGrid();
            javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
            while (rs.next())
            {
                dtm.addRow(new Object[]{rs.getString("PED_CODIGO_ID"),rs.getString("PED_TIPO_PEDIDO"),rs.getString("PED_NUMERO_NOTA_FISCAL"),rs.getString("PED_DATA_EMISSAO"),rs.getString("PED_DATA_FATURAMENTO"),rs.getString("PAR_NOME_RAZAO_SOCIAL"),""});
            }
            rs.close();

        }catch (Exception e) {
           e.printStackTrace();
        }

    }
    public void MontaGridDataEmissao()
    {

        String vmCondicao_Consulta = "";
        String vmCampos = "PED_CODIGO_ID, PED_TIPO_PEDIDO, PED_NUMERO_NOTA_FISCAL, to_char(PED_DATA_EMISSAO, 'DD/MM/YYYY') AS PED_DATA_EMISSAO, to_char(PED_DATA_FATURAMENTO, 'DD/MM/YYYY') AS PED_DATA_FATURAMENTO, PAR_NOME_RAZAO_SOCIAL";
        if (!txtDataEmissao.getText().equals(""))
            vmCondicao_Consulta = " INNER JOIN PARCEIROS ON (PED_PAR_CODIGO_ID = PAR_CODIGO_ID) WHERE PED_DATA_EMISSAO = '" + txtDataEmissao.getText() + "' ORDER BY PED_CODIGO_ID DESC";
        else
            vmCondicao_Consulta = " INNER JOIN PARCEIROS ON (PED_PAR_CODIGO_ID = PAR_CODIGO_ID) ORDER BY PED_CODIGO_ID DESC";

        try
        {
             rs = AcessoDados.Selecao("PEDIDOS", vmCampos, vmCondicao_Consulta);
             MontaTituloColunaGrid();
            javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
            while (rs.next())
            {
                dtm.addRow(new Object[]{rs.getString("PED_CODIGO_ID"),rs.getString("PED_TIPO_PEDIDO"),rs.getString("PED_NUMERO_NOTA_FISCAL"),rs.getString("PED_DATA_EMISSAO"),rs.getString("PED_DATA_FATURAMENTO"),rs.getString("PAR_NOME_RAZAO_SOCIAL"),""});
            }
            rs.close();

        }catch (Exception e) {
           e.printStackTrace();
        }

    }
    public void MontaGridDataFaturamento()
    {

        String vmCampos = "PED_CODIGO_ID, PED_TIPO_PEDIDO, PED_NUMERO_NOTA_FISCAL, to_char(PED_DATA_EMISSAO, 'DD/MM/YYYY') AS PED_DATA_EMISSAO, to_char(PED_DATA_FATURAMENTO, 'DD/MM/YYYY') AS PED_DATA_FATURAMENTO, PAR_NOME_RAZAO_SOCIAL";
        String vmCondicao_Consulta = "";

        if (!txtDataFaturamento.getText().equals(""))
            vmCondicao_Consulta = " INNER JOIN PARCEIROS ON (PED_PAR_CODIGO_ID = PAR_CODIGO_ID) WHERE PED_DATA_EMISSAO = '" + txtDataFaturamento.getText() + "' ORDER BY PED_CODIGO_ID DESC";
        else
            vmCondicao_Consulta = " INNER JOIN PARCEIROS ON (PED_PAR_CODIGO_ID = PAR_CODIGO_ID) ORDER BY PED_CODIGO_ID DESC";

        try
        {
             rs = AcessoDados.Selecao("PEDIDOS", vmCampos, vmCondicao_Consulta);
             MontaTituloColunaGrid();
            javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
            while (rs.next())
            {
                dtm.addRow(new Object[]{rs.getString("PED_CODIGO_ID"),rs.getString("PED_TIPO_PEDIDO"),rs.getString("PED_NUMERO_NOTA_FISCAL"),rs.getString("PED_DATA_EMISSAO"),rs.getString("PED_DATA_FATURAMENTO"),rs.getString("PAR_NOME_RAZAO_SOCIAL"),""});
            }
            rs.close();

        }catch (Exception e) {
           e.printStackTrace();
        }

    }
    public void MontaGridParceiro()
    {

        String vmCampos = "PED_CODIGO_ID, PED_TIPO_PEDIDO, PED_NUMERO_NOTA_FISCAL, to_char(PED_DATA_EMISSAO, 'DD/MM/YYYY') AS PED_DATA_EMISSAO, to_char(PED_DATA_FATURAMENTO, 'DD/MM/YYYY') AS PED_DATA_FATURAMENTO, PAR_NOME_RAZAO_SOCIAL";
        String vmCondicao_Consulta = "";

        if (!txtParceiro.getText().equals(""))
            vmCondicao_Consulta = " INNER JOIN PARCEIROS ON (PED_PAR_CODIGO_ID = PAR_CODIGO_ID) WHERE PAR_NOME_RAZAO_SOCIAL LIKE '" + txtParceiro.getText() + "%' ORDER BY PED_CODIGO_ID DESC";
        else
            vmCondicao_Consulta = " INNER JOIN PARCEIROS ON (PED_PAR_CODIGO_ID = PAR_CODIGO_ID) ORDER BY PED_CODIGO_ID DESC";

        try
        {
             rs = AcessoDados.Selecao("PEDIDOS", vmCampos, vmCondicao_Consulta);
            MontaTituloColunaGrid();
            javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
            while (rs.next())
            {
                dtm.addRow(new Object[]{rs.getString("PED_CODIGO_ID"),rs.getString("PED_TIPO_PEDIDO"),rs.getString("PED_NUMERO_NOTA_FISCAL"),rs.getString("PED_DATA_EMISSAO"),rs.getString("PED_DATA_FATURAMENTO"),rs.getString("PAR_NOME_RAZAO_SOCIAL"),""});
            }
            rs.close();

        }catch (Exception e) {
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtNumeroPedido = new javax.swing.JTextField();
        btnPesquisaNumeroPedido = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnPesquisaNotaFiscal = new javax.swing.JButton();
        txtNotaFical = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        txtDataEmissao = new javax.swing.JFormattedTextField();
        btnPesquisaDataEmissao = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        txtDataFaturamento = new javax.swing.JFormattedTextField();
        btnPesquisaDataFaturamento = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        txtParceiro = new javax.swing.JTextField();
        btnPesquisaParceiro = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        grDados = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisa Pedidos");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jTabbedPane1.setForeground(new java.awt.Color(102, 102, 102));
        jTabbedPane1.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N

        txtNumeroPedido.setColumns(8);
        txtNumeroPedido.setToolTipText("Código");
        txtNumeroPedido.setName(""); // NOI18N
        txtNumeroPedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroPedidoKeyReleased(evt);
            }
        });

        btnPesquisaNumeroPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Localizar.gif"))); // NOI18N
        btnPesquisaNumeroPedido.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPesquisaNumeroPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaNumeroPedidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNumeroPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPesquisaNumeroPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(793, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNumeroPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisaNumeroPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("N° Pedido", jPanel1);

        btnPesquisaNotaFiscal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Localizar.gif"))); // NOI18N
        btnPesquisaNotaFiscal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPesquisaNotaFiscal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaNotaFiscalActionPerformed(evt);
            }
        });

        txtNotaFical.setColumns(8);
        txtNotaFical.setToolTipText("Código");
        txtNotaFical.setName(""); // NOI18N
        txtNotaFical.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNotaFicalActionPerformed(evt);
            }
        });
        txtNotaFical.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNotaFicalKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNotaFical, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPesquisaNotaFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(795, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPesquisaNotaFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNotaFical, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("N° Nota Fiscal", jPanel3);

        try {
            txtDataEmissao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataEmissao.setToolTipText("");
        txtDataEmissao.setName(""); // NOI18N
        txtDataEmissao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDataEmissaoKeyReleased(evt);
            }
        });

        btnPesquisaDataEmissao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Localizar.gif"))); // NOI18N
        btnPesquisaDataEmissao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPesquisaDataEmissao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaDataEmissaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPesquisaDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(818, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPesquisaDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Data Emissão", jPanel4);

        try {
            txtDataFaturamento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataFaturamento.setToolTipText("");
        txtDataFaturamento.setName(""); // NOI18N
        txtDataFaturamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDataFaturamentoKeyReleased(evt);
            }
        });

        btnPesquisaDataFaturamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Localizar.gif"))); // NOI18N
        btnPesquisaDataFaturamento.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPesquisaDataFaturamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaDataFaturamentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDataFaturamento, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPesquisaDataFaturamento, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(818, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPesquisaDataFaturamento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDataFaturamento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Data Faturamento", jPanel5);

        txtParceiro.setColumns(60);
        txtParceiro.setToolTipText("");
        txtParceiro.setName(""); // NOI18N
        txtParceiro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtParceiroKeyReleased(evt);
            }
        });

        btnPesquisaParceiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Localizar.gif"))); // NOI18N
        btnPesquisaParceiro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPesquisaParceiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaParceiroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtParceiro, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPesquisaParceiro, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(527, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPesquisaParceiro, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtParceiro, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Parceiro", jPanel6);

        grDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        grDados.setToolTipText("");
        grDados.setName(""); // NOI18N
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

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNumeroPedidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroPedidoKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtNumeroPedidoKeyReleased

    private void btnPesquisaNumeroPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaNumeroPedidoActionPerformed
        // TODO add your handling code here:
        MontaGridNumPedido();
}//GEN-LAST:event_btnPesquisaNumeroPedidoActionPerformed

    private void btnPesquisaNotaFiscalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaNotaFiscalActionPerformed
        // TODO add your handling code here:
        MontaGridNumNotaFiscal();
}//GEN-LAST:event_btnPesquisaNotaFiscalActionPerformed

    private void grDadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grDadosMouseClicked
        // TODO add your handling code here:
        RetornaDadosGrid();
}//GEN-LAST:event_grDadosMouseClicked

    private void grDadosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grDadosKeyReleased
        // TODO add your handling code here:
         if ((evt.getKeyCode() ==  java.awt.event.KeyEvent.VK_UP)  || (evt.getKeyCode() ==  java.awt.event.KeyEvent.VK_DOWN)) {
              RetornaDadosGrid();
         };
}//GEN-LAST:event_grDadosKeyReleased

    private void txtNotaFicalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNotaFicalKeyReleased
       
    }//GEN-LAST:event_txtNotaFicalKeyReleased

    private void txtNotaFicalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNotaFicalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNotaFicalActionPerformed

    private void btnPesquisaDataEmissaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaDataEmissaoActionPerformed
        // TODO add your handling code here:
        MontaGridDataEmissao();
    }//GEN-LAST:event_btnPesquisaDataEmissaoActionPerformed

    private void btnPesquisaDataFaturamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaDataFaturamentoActionPerformed
        // TODO add your handling code here:
        MontaGridDataFaturamento();
    }//GEN-LAST:event_btnPesquisaDataFaturamentoActionPerformed

    private void btnPesquisaParceiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaParceiroActionPerformed
        // TODO add your handling code here:
        MontaGridParceiro();
    }//GEN-LAST:event_btnPesquisaParceiroActionPerformed

    private void txtDataEmissaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDataEmissaoKeyReleased
        // TODO add your handling code here:
         JTextField tf = (JTextField) evt.getComponent();
        if (evt.getKeyCode()!=32 & evt.getKeyCode()!=8 & evt.getKeyCode()!=37 & evt.getKeyCode()!=39)
            tf.setText(tf.getText().toUpperCase());
    }//GEN-LAST:event_txtDataEmissaoKeyReleased

    private void txtDataFaturamentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDataFaturamentoKeyReleased
        // TODO add your handling code here:
         JTextField tf = (JTextField) evt.getComponent();
        if (evt.getKeyCode()!=32 & evt.getKeyCode()!=8 & evt.getKeyCode()!=37 & evt.getKeyCode()!=39)
            tf.setText(tf.getText().toUpperCase());
    }//GEN-LAST:event_txtDataFaturamentoKeyReleased

    private void txtParceiroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtParceiroKeyReleased
        // TODO add your handling code here:
         JTextField tf = (JTextField) evt.getComponent();
        if (evt.getKeyCode()!=32 & evt.getKeyCode()!=8 & evt.getKeyCode()!=37 & evt.getKeyCode()!=39)
            tf.setText(tf.getText().toUpperCase());
    }//GEN-LAST:event_txtParceiroKeyReleased

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
         MontaGridNumPedido();
        txtNumeroPedido.requestFocus();
    }//GEN-LAST:event_formWindowActivated

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


            imgExcluir = new ImageIcon("..MTDS/Imagens/confirma.gif");
            renderButton = new JButton(imgExcluir);
            renderButton.setBorderPainted(false);
            renderButton.setToolTipText("Retornar Pesquisa");
            renderButton.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
            renderButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/confirma.gif")));

            editButton = new JButton();
            editButton.setFocusPainted( false );
            editButton.addActionListener( this );
            editButton.setBorderPainted(false);
            editButton.setToolTipText("Retornar Pesquisa");
            editButton.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
            editButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/confirma.gif")));


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

            renderButton.setText( (value == "") ? "" : value.toString() );
            return renderButton;
        }

        public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column)
        {
            text = (value == "") ? "" : value.toString();
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
            retorno = grDados.getValueAt(grDados.getSelectedRow(), 0).toString();
            dispose();
        }
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FpesqPedido dialog = new FpesqPedido(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnPesquisaDataEmissao;
    private javax.swing.JButton btnPesquisaDataFaturamento;
    private javax.swing.JButton btnPesquisaNotaFiscal;
    private javax.swing.JButton btnPesquisaNumeroPedido;
    private javax.swing.JButton btnPesquisaParceiro;
    private javax.swing.JTable grDados;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JFormattedTextField txtDataEmissao;
    private javax.swing.JFormattedTextField txtDataFaturamento;
    private javax.swing.JTextField txtNotaFical;
    private javax.swing.JTextField txtNumeroPedido;
    private javax.swing.JTextField txtParceiro;
    // End of variables declaration//GEN-END:variables

}
