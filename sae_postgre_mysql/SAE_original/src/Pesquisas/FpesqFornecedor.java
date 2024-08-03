/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmUsuario.java
 *
 * Created on 02/01/2010, 13:07:26
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



/**
 *
 * @author Thiago
 */
public class FpesqFornecedor extends javax.swing.JDialog {
    claVariaveis variaveis = new claVariaveis();
    claAcessoDados AcessoDados = new claAcessoDados();
    claFuncoes funcoes = new claFuncoes();
    private ResultSet rs;
    private String retorno;
    public String getRetorno () { return retorno; }


    /** Creates new form FrmUsuario */
    public FpesqFornecedor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        HashSet conj = new HashSet(this.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        this.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
        registraEnterNoBotao(btnPesquisaCodigo);
        registraEnterNoBotao(btnPesquisaRazaoSocial);
        registraEnterNoBotao(btnPesquisaNomeFantasia);
        registraEnterNoBotao(btnPesqCPFCNPJ);
        btnPesquisaCodigo.setFocusTraversalKeysEnabled(false);
        btnPesquisaRazaoSocial.setFocusTraversalKeysEnabled(false);
        btnPesquisaNomeFantasia.setFocusTraversalKeysEnabled(false);
        btnPesqCPFCNPJ.setFocusTraversalKeysEnabled(false);
    }
   
    private void registraEnterNoBotao(JButton b) {
        b.registerKeyboardAction(
                b.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                JComponent.WHEN_FOCUSED);

        b.registerKeyboardAction(
                b.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                JComponent.WHEN_FOCUSED);
    }
    public void RetornaDadosGrid()
    {
       
    }
    public void MontaGridCodigo()
    {

        String vmCampos = "*";
      
        String vmCondicao_Consulta = "";
        if (!txtCodigo.getText().equals(""))
            vmCondicao_Consulta = " WHERE PAR_CODIGO_ID = " + txtCodigo.getText() + " and par_tipo_parceiro = 'FORNECEDOR'  ORDER BY PAR_CODIGO_ID";
        else
            vmCondicao_Consulta = " where par_tipo_parceiro = 'FORNECEDOR' ORDER BY PAR_CODIGO_ID";
        
        try
        {
        rs = AcessoDados.Selecao("PARCEIROS", vmCampos, vmCondicao_Consulta);
        grDados.setModel(new javax.swing.table.DefaultTableModel(
                      new Object [][] { },
                      new String [] {"Código","Razão Social","Nome Fantasia", "CPF / CNPJ"}));
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
        while (rs.next())
        {
            dtm.addRow(new Object[]{rs.getString("PAR_CODIGO_ID"),rs.getString("PAR_NOME_RAZAO_SOCIAL"),rs.getString("PAR_FANTASIA"),rs.getString("PAR_CNPJ_CPF")});
        }
        rs.close();
        grDados.getColumnModel().getColumn(0).setPreferredWidth(40);
        grDados.getColumnModel().getColumn(1).setPreferredWidth(300);
        grDados.getColumnModel().getColumn(2).setPreferredWidth(250);
        grDados.getColumnModel().getColumn(3).setPreferredWidth(100);
      
        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();

        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);

        grDados.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        grDados.getColumnModel().getColumn(1).setCellRenderer(esquerda);
        grDados.getColumnModel().getColumn(2).setCellRenderer(esquerda);
        grDados.getColumnModel().getColumn(3).setCellRenderer(centralizado);

           }catch (Exception e) {
           e.printStackTrace();
        }


    }
    public void MontaGridRazaoSocial()
    {

        String vmCampos = "*";
        String vmCondicao_Consulta = "";
        if (!txtRazaoSocial.getText().equals(""))
            vmCondicao_Consulta = " WHERE PAR_NOME_RAZAO_SOCIAL LIKE '" + txtRazaoSocial.getText() + "%' and par_tipo_parceiro = 'FORNECEDOR' ORDER BY PAR_NOME_RAZAO_SOCIAL";
        else
            vmCondicao_Consulta = " where par_tipo_parceiro = 'FORNECEDOR' order by PAR_NOME_RAZAO_SOCIAL";

        try
        {
        rs = AcessoDados.Selecao("PARCEIROS", vmCampos, vmCondicao_Consulta);
        grDados.setModel(new javax.swing.table.DefaultTableModel(
                      new Object [][] { },
                      new String [] {"Código","Razão Social","Nome Fantasia", "CPF / CNPJ"}));
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
        while (rs.next())
        {
            dtm.addRow(new Object[]{rs.getString("PAR_CODIGO_ID"),rs.getString("PAR_NOME_RAZAO_SOCIAL"),rs.getString("PAR_FANTASIA"),rs.getString("PAR_CNPJ_CPF")});
        }
        rs.close();
        grDados.getColumnModel().getColumn(0).setPreferredWidth(40);
        grDados.getColumnModel().getColumn(1).setPreferredWidth(300);
        grDados.getColumnModel().getColumn(2).setPreferredWidth(250);
        grDados.getColumnModel().getColumn(3).setPreferredWidth(100);

        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();

        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);

        grDados.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        grDados.getColumnModel().getColumn(1).setCellRenderer(esquerda);
        grDados.getColumnModel().getColumn(2).setCellRenderer(esquerda);
        grDados.getColumnModel().getColumn(3).setCellRenderer(centralizado);

           }catch (Exception e) {
           e.printStackTrace();
        }

    }
    public void MontaGridNomeFantasia()
    {

        String vmCampos = "*";

        String vmCondicao_Consulta = "";
        if (!txtNomeFantasia.getText().equals(""))
            vmCondicao_Consulta = " WHERE PAR_FANTASIA LIKE '" + txtNomeFantasia.getText() + "%' and par_tipo_parceiro = 'FORNECEDOR' ORDER BY PAR_FANTASIA";
        else
            vmCondicao_Consulta = " where par_tipo_parceiro = 'FORNECEDOR' ORDER BY PAR_FANTASIA";

        try
        {
        rs = AcessoDados.Selecao("PARCEIROS", vmCampos, vmCondicao_Consulta);
        grDados.setModel(new javax.swing.table.DefaultTableModel(
                      new Object [][] { },
                      new String [] {"Código","Razão Social","Nome Fantasia", "CPF / CNPJ"}));
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
        while (rs.next())
        {
            dtm.addRow(new Object[]{rs.getString("PAR_CODIGO_ID"),rs.getString("PAR_NOME_RAZAO_SOCIAL"),rs.getString("PAR_FANTASIA"),rs.getString("PAR_CNPJ_CPF")});
        }
        rs.close();
        grDados.getColumnModel().getColumn(0).setPreferredWidth(40);
        grDados.getColumnModel().getColumn(1).setPreferredWidth(300);
        grDados.getColumnModel().getColumn(2).setPreferredWidth(250);
        grDados.getColumnModel().getColumn(3).setPreferredWidth(100);

        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();

        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);

        grDados.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        grDados.getColumnModel().getColumn(1).setCellRenderer(esquerda);
        grDados.getColumnModel().getColumn(2).setCellRenderer(esquerda);
        grDados.getColumnModel().getColumn(3).setCellRenderer(centralizado);

           }catch (Exception e) {
           e.printStackTrace();
        }

    }
    public void MontaGridCPFCNPJ()
    {

        String vmCampos = "*";

        String vmCondicao_Consulta = "";
        if (!txtCPFCNPJ.getText().equals(""))
            vmCondicao_Consulta = " WHERE PAR_CNPJ_CPF LIKE '" + txtCPFCNPJ.getText() + "%' and par_tipo_parceiro = 'FORNECEDOR' ORDER BY PAR_CODIGO_ID";
        else
            vmCondicao_Consulta = " where par_tipo_parceiro = 'FORNECEDOR' ORDER BY PAR_CODIGO_ID";

        try
        {
        rs = AcessoDados.Selecao("PARCEIROS", vmCampos, vmCondicao_Consulta);
        grDados.setModel(new javax.swing.table.DefaultTableModel(
                      new Object [][] { },
                      new String [] {"Código","Razão Social","Nome Fantasia", "CPF / CNPJ"}));
        javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel)grDados.getModel();
        while (rs.next())
        {
            dtm.addRow(new Object[]{rs.getString("PAR_CODIGO_ID"),rs.getString("PAR_NOME_RAZAO_SOCIAL"),rs.getString("PAR_FANTASIA"),rs.getString("PAR_CNPJ_CPF")});
        }
        rs.close();
        grDados.getColumnModel().getColumn(0).setPreferredWidth(40);
        grDados.getColumnModel().getColumn(1).setPreferredWidth(300);
        grDados.getColumnModel().getColumn(2).setPreferredWidth(250);
        grDados.getColumnModel().getColumn(3).setPreferredWidth(100);

        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();

        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);

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

        jScrollPane1 = new javax.swing.JScrollPane();
        grDados = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnOK = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtCodigo = new javax.swing.JTextField();
        btnPesquisaCodigo = new javax.swing.JButton();
        abaRazaoSocial = new javax.swing.JPanel();
        txtRazaoSocial = new javax.swing.JTextField();
        btnPesquisaRazaoSocial = new javax.swing.JButton();
        abaNomeFantasia = new javax.swing.JPanel();
        txtNomeFantasia = new javax.swing.JTextField();
        btnPesquisaNomeFantasia = new javax.swing.JButton();
        abaCPFCNPJ = new javax.swing.JPanel();
        txtCPFCNPJ = new javax.swing.JTextField();
        btnPesqCPFCNPJ = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisa de Fornecedor");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

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

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/cancelar.gif"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnOK.setText("OK");
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
                .addContainerGap()
                .addComponent(btnOK, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnOK))
                .addContainerGap())
        );

        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        btnPesquisaCodigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Localizar.gif"))); // NOI18N
        btnPesquisaCodigo.setText("Pesquisar");
        btnPesquisaCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaCodigoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 519, Short.MAX_VALUE)
                .addComponent(btnPesquisaCodigo)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisaCodigo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Código", jPanel1);

        txtRazaoSocial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRazaoSocialKeyReleased(evt);
            }
        });

        btnPesquisaRazaoSocial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Localizar.gif"))); // NOI18N
        btnPesquisaRazaoSocial.setText("Pesquisar");
        btnPesquisaRazaoSocial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaRazaoSocialActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout abaRazaoSocialLayout = new javax.swing.GroupLayout(abaRazaoSocial);
        abaRazaoSocial.setLayout(abaRazaoSocialLayout);
        abaRazaoSocialLayout.setHorizontalGroup(
            abaRazaoSocialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaRazaoSocialLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtRazaoSocial, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnPesquisaRazaoSocial)
                .addContainerGap())
        );
        abaRazaoSocialLayout.setVerticalGroup(
            abaRazaoSocialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaRazaoSocialLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaRazaoSocialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisaRazaoSocial))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Razão Social", abaRazaoSocial);

        txtNomeFantasia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomeFantasiaKeyReleased(evt);
            }
        });

        btnPesquisaNomeFantasia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Localizar.gif"))); // NOI18N
        btnPesquisaNomeFantasia.setText("Pesquisar");
        btnPesquisaNomeFantasia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaNomeFantasiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout abaNomeFantasiaLayout = new javax.swing.GroupLayout(abaNomeFantasia);
        abaNomeFantasia.setLayout(abaNomeFantasiaLayout);
        abaNomeFantasiaLayout.setHorizontalGroup(
            abaNomeFantasiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaNomeFantasiaLayout.createSequentialGroup()
                .addContainerGap(652, Short.MAX_VALUE)
                .addComponent(btnPesquisaNomeFantasia)
                .addContainerGap())
            .addGroup(abaNomeFantasiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(abaNomeFantasiaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txtNomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(140, Short.MAX_VALUE)))
        );
        abaNomeFantasiaLayout.setVerticalGroup(
            abaNomeFantasiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaNomeFantasiaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPesquisaNomeFantasia)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(abaNomeFantasiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(abaNomeFantasiaLayout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(txtNomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(15, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Nome Fantasia", abaNomeFantasia);

        txtCPFCNPJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCPFCNPJKeyReleased(evt);
            }
        });

        btnPesqCPFCNPJ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Localizar.gif"))); // NOI18N
        btnPesqCPFCNPJ.setText("Pesquisar");
        btnPesqCPFCNPJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesqCPFCNPJActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout abaCPFCNPJLayout = new javax.swing.GroupLayout(abaCPFCNPJ);
        abaCPFCNPJ.setLayout(abaCPFCNPJLayout);
        abaCPFCNPJLayout.setHorizontalGroup(
            abaCPFCNPJLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCPFCNPJLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtCPFCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 482, Short.MAX_VALUE)
                .addComponent(btnPesqCPFCNPJ)
                .addContainerGap())
        );
        abaCPFCNPJLayout.setVerticalGroup(
            abaCPFCNPJLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCPFCNPJLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaCPFCNPJLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCPFCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesqCPFCNPJ))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("CPF / CNPJ", abaCPFCNPJ);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void limpaCampos(){
         }
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        MontaGridCodigo();
        txtCodigo.requestFocus();
        
    }//GEN-LAST:event_formWindowActivated

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

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
}//GEN-LAST:event_btnCancelarActionPerformed

    private void btnPesquisaCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaCodigoActionPerformed
        // TODO add your handling code here:
        MontaGridCodigo();
    }//GEN-LAST:event_btnPesquisaCodigoActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        // TODO add your handling code here:
        retorno = grDados.getValueAt(grDados.getSelectedRow(), 0).toString();
        this.dispose();
    }//GEN-LAST:event_btnOKActionPerformed

    private void btnPesquisaRazaoSocialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaRazaoSocialActionPerformed
        // TODO add your handling code here:
       MontaGridRazaoSocial();
    }//GEN-LAST:event_btnPesquisaRazaoSocialActionPerformed

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtRazaoSocialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazaoSocialKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazaoSocialKeyReleased

    private void txtNomeFantasiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeFantasiaKeyReleased
        // TODO add your handling code here:
       
    }//GEN-LAST:event_txtNomeFantasiaKeyReleased

    private void btnPesquisaNomeFantasiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaNomeFantasiaActionPerformed
        // TODO add your handling code here:
        MontaGridNomeFantasia();
    }//GEN-LAST:event_btnPesquisaNomeFantasiaActionPerformed

    private void txtCPFCNPJKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCPFCNPJKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPFCNPJKeyReleased

    private void btnPesqCPFCNPJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesqCPFCNPJActionPerformed
        // TODO add your handling code here:
        MontaGridCPFCNPJ();
    }//GEN-LAST:event_btnPesqCPFCNPJActionPerformed

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
    private javax.swing.JPanel abaCPFCNPJ;
    private javax.swing.JPanel abaNomeFantasia;
    private javax.swing.JPanel abaRazaoSocial;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnOK;
    private javax.swing.JButton btnPesqCPFCNPJ;
    private javax.swing.JButton btnPesquisaCodigo;
    private javax.swing.JButton btnPesquisaNomeFantasia;
    private javax.swing.JButton btnPesquisaRazaoSocial;
    private javax.swing.JTable grDados;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField txtCPFCNPJ;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNomeFantasia;
    private javax.swing.JTextField txtRazaoSocial;
    // End of variables declaration//GEN-END:variables

}
