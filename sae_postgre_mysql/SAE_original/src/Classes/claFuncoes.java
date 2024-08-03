/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Classes;
import be.savat.components.JBookPanel;
import javax.swing.JTextField;
import javax.swing.*;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.*;
import javax.*;
import javax.swing.JOptionPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.event.DocumentEvent;
import javax.swing.text.JTextComponent;
import java.awt.AWTKeyStroke;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import javax.swing.text.*;
import java.awt.event.FocusAdapter;
import java.sql.ResultSet;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.AWTEvent.*;
import java.lang.reflect.Field;
import java.util.InputMismatchException;


/**
 *
 * @author Thiago
 */
public class claFuncoes {
  claAcessoDados AcessoDados = new claAcessoDados();
  claVariaveis variaveis =new claVariaveis();


public static String formataValorNFE(String vlr, String Origem){
    if(vlr.equals("")) vlr="0";

    int xTamanho = vlr.length();

    if(!Origem.equals("BD")) //Banco de Dados
    {
       if(xTamanho > 6)
       {
          vlr = vlr.replace(".", "").replace(",", ".");

       }else
       {
          vlr = vlr.replace(",", ".");
       }
    }

   
    double valor = Double.parseDouble(vlr);
        DecimalFormat nf = new DecimalFormat("##,###,##0.00");
        return (nf.format(valor));
}

         
    public String RemoveAcentos (String passa){  
          passa = passa.replaceAll("[ÂÀÁÄÃ]","A");  
          passa = passa.replaceAll("[âãàáä]","a");  
          passa = passa.replaceAll("[ÊÈÉË]","E");  
          passa = passa.replaceAll("[êèéë]","e");  
          passa = passa.replaceAll("ÎÍÌÏ","I");  
          passa = passa.replaceAll("îíìï","i");  
          passa = passa.replaceAll("[ÔÕÒÓÖ]","O");  
          passa = passa.replaceAll("[ôõòóö]","o");  
          passa = passa.replaceAll("[ÛÙÚÜ]","U");  
          passa = passa.replaceAll("[ûúùü]","u");  
          passa = passa.replaceAll("Ç","C");  
          passa = passa.replaceAll("ç","c");   
          passa = passa.replaceAll("[ýÿ]","y");  
          passa = passa.replaceAll("Ý","Y");  
          passa = passa.replaceAll("ñ","n");  
          passa = passa.replaceAll("Ñ","N");  
          passa = passa.replaceAll("['<>\\|/]","");  
          return passa;  
       }  
    public String RetornaDados (String tabela, String CampoRetorno, String Condicao){  
    ResultSet rs;
    try
    {
        rs = AcessoDados.Selecao(tabela, CampoRetorno + " as Retorno " , Condicao );
        rs.next();
        String Retorno = rs.getString("Retorno").toString();
        return Retorno;

          
    }catch (Exception e) {
           e.printStackTrace();
           return  "";
    }    
  }  
 

  public static String formataMoeda(String vlr, String Origem){
    if(vlr.equals("")) vlr="0";

    int xTamanho = vlr.length();

    if(!Origem.equals("BD")) //Banco de Dados
    {
       if(xTamanho > 6)
       {
          vlr = vlr.replace(".", "").replace(",", ".");

       }else
       {
          vlr = vlr.replace(",", ".");
       }
    }

   
    double valor = Double.parseDouble(vlr);
        DecimalFormat nf = new DecimalFormat("##,###,##0.00");
        return (nf.format(valor));
}
public static String formataNumero(String vlr, String UM){
    vlr = vlr.replace(".", "").replace(",", ".");
    DecimalFormat nf;
    String Unidade  = UM.toUpperCase().trim();
    double valor = Double.parseDouble(vlr);
    if(Unidade.equals("TN"))
    {
      nf = new DecimalFormat("##,##0.00");
    }else if (Unidade.equals("LT"))
    {
      nf = new DecimalFormat("##,##0.00");
    }else if  (Unidade.equals("MT"))
    {
        nf = new DecimalFormat("##,##0.00");
    }else if (Unidade.equals("KG"))
    {
       nf = new DecimalFormat("##,##0.00");
    }else
    {
       nf = new DecimalFormat("##,##0");
    }

        return (nf.format(valor));
}

 public void registraEnterNoBotao(JButton b) {
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

    public void limparTodosCampos(Container container) {
    Component components[] = container.getComponents();
    for (Component component : components) {
        if (component instanceof JFormattedTextField) {
            JFormattedTextField field = (JFormattedTextField) component;
            field.setValue(null);
            field.setText("");
        } else if (component instanceof JTextField) {
            JTextField field = (JTextField) component;
            field.setText("");
            try
            {
               if(field.getToolTipText().equals("null")) field.setToolTipText("");
            {
            }
            }catch(Exception erro)
            {
                field.setToolTipText("");
            }
            
            if(field.getToolTipText().equals("Valor") || field.getToolTipText().equals("%"))
            {
                field.setText("0");
            }
        }else if (component instanceof JTextArea) {
            JTextArea field = (JTextArea) component;
            field.setText("");
        } else if (component instanceof JComboBox) {
            JComboBox field = (JComboBox) component;
            field.setSelectedIndex(0);
        }  else if (component instanceof JTable) {
            JTable Table = (JTable) component;
            DefaultTableModel tableModel =(DefaultTableModel) Table.getModel();
            tableModel.setNumRows(0);
        }  else if (component instanceof JLabel) {
            JLabel Label = (JLabel) component;
            try
            {
               if(Label.getToolTipText().equals("null")) Label.setToolTipText("");
            {
            }
            }catch(Exception erro)
            {
                Label.setToolTipText("");
            }

            
            if(Label.getToolTipText().equals("ID")) Label.setText("0");

        } else if (component instanceof Container) {
            limparTodosCampos((Container) component);
        }
    }
    }

    public void HabilitaCampos(Container container, boolean habilitado) {
    Component components[] = container.getComponents();
    for (Component component : components) {
        if (component instanceof JFormattedTextField) {
            JFormattedTextField field = (JFormattedTextField) component;
            field.setEnabled(habilitado);
        } else if (component instanceof JTextField) {
            JTextField field = (JTextField) component;
            if(field.isEditable()) field.setEnabled(habilitado);
        }else if (component instanceof JTextArea) {
            JTextArea field = (JTextArea) component;
            field.setEnabled(habilitado);
        } else if (component instanceof JComboBox) {
            JComboBox field = (JComboBox) component;
            field.setEnabled(habilitado);
        } else if (component instanceof JCheckBox) {
            JCheckBox field = (JCheckBox) component;
            field.setEnabled(habilitado);
        } else if (component instanceof JButton) {
            JButton field = (JButton) component;
            String xx = field.getToolTipText();
            if(field.isEnabled())
            {
                field.setEnabled(false);
            }else
            {
                field.setEnabled(true);
            }
        } else if (component instanceof Container) {
            HabilitaCampos((Container) component, habilitado);
        }
    }
    }

    public class UpperCaseDocument extends PlainDocument {
    public void insertString(int offs, String str, AttributeSet a)  throws BadLocationException {
      super.insertString(offs, str.toUpperCase(), a);
    }
    }

    public class LowerCaseDocument extends PlainDocument {
    public void insertString(int offs, String str, AttributeSet a)  throws BadLocationException {
      super.insertString(offs, str.toLowerCase(), a);
    }
    }
    public String  RetornaId(String Tabela, String CampoID)
    {
        ResultSet rs;
        String vmCampos = "MAX("+CampoID+") AS ID";
        String vmCondicao_Consulta = " ";
        try
        {
            rs = AcessoDados.Selecao(Tabela, vmCampos, vmCondicao_Consulta);
            rs.next();
            return rs.getString("ID");

        }
        catch (Exception e)
        {
           e.printStackTrace();
           return "";

        }
    }
    public String  RetornaNumeroNota(String Tabela)
    {
        ResultSet rs;
        String vmCampos = " max(coalesce(not_numero_nota,0)) + 1 AS ID ";
        String vmCondicao_Consulta = " ";
        try
        {
            rs = AcessoDados.Selecao(Tabela, vmCampos, vmCondicao_Consulta);
            rs.next();
            return rs.getString("ID");

        }
        catch (Exception e)
        {
           e.printStackTrace();
           return "";

        }
    }

public class OnlyNumberField extends PlainDocument
{
private int maxlength;
public OnlyNumberField(){}
public void insertString(int offs, String str, AttributeSet a)
{
try
{
Integer.parseInt(str);
} catch (NumberFormatException ex)
{
str = "";
}
try
{
boolean fixedLengh = (!((getLength() + str.length()) > maxlength));
if (maxlength == 0 || fixedLengh)
super.insertString(offs, str, a);
} catch (BadLocationException e)
{
e.printStackTrace();
}
}
}

public class NumberDocument extends PlainDocument{

public NumberDocument(){
super();

}
public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
//permite apenas uma virgula
 String texto = getText(0, getLength());
if(str.equals(",")){
if(texto.indexOf(",")>-1)
return;
else
super.insertString(offset, str, attr);
}
if (str.codePointAt(0) < 48 || str.codePointAt(0) > 57)   return;
super.insertString(offset, str, attr);
}
}

    public void F_AtribuirClasse(Container container) {
    Component components[] = container.getComponents();
    for (final Component component : components) {
        if (component instanceof JFormattedTextField) {
            JFormattedTextField field = (JFormattedTextField) component;
            field.setDocument(new UpperCaseDocument());
            field.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
            field.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
            field.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                JFormattedTextField jt = (JFormattedTextField) e.getComponent();
                if(jt.getText().length() == 10)
                {
                    //validar data
                    jt.setText(ValidarData(jt.getText(), jt));
                }else if(jt.getText().length() == 14)
                {
                   //validar cpf
                   if(!jt.getText().equals("   .   .   -  "))
                   {
                      String vmsg = ValidarCPF(jt.getText());
                      if(!vmsg.equals("CPF Válido!"))
                      {
                        JOptionPane.showMessageDialog(null, vmsg);
                        jt.setText("");
                        jt.requestFocus();
                      }
                   }

                }else if(jt.getText().length() == 18)
                {
                   //validar cnpj
                   if(!jt.getText().equals("  .   .   /    -  "))
                   {
                       String vmsg = ValidarCNPJ(jt.getText());
                       if(!vmsg.equals("CNPJ Válido"))
                      {
                        JOptionPane.showMessageDialog(null, vmsg);
                        jt.setText("");
                        jt.requestFocus();
                     }
                   }
                }
            }
            });
        } else if (component instanceof JTextField) {
            JTextField field = (JTextField) component;
            field.setDocument(new UpperCaseDocument());
            field.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
           try
            {
               if(field.getToolTipText().equals("null")) field.setToolTipText("");
            {
            }
            }catch(Exception erro)
            {
                field.setToolTipText("");
            }
            
            if(!field.isEditable())
            {
                field.setForeground(new java.awt.Color(86, 133, 133));
            }

            if(field.getToolTipText().equals("email") || field.getToolTipText().equals("homepage"))
            {
               field.setDocument(new LowerCaseDocument());
            }
            //Valores: Valor:
            if(field.getToolTipText().equals("Valor") || field.getToolTipText().equals("%"))
            {
                field.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
                field.setDocument(new  NumberDocument());
                field.setText("0");
            }
            //so numero: Código
            if(field.getToolTipText().equals("Código") || field.getToolTipText().equals("Número"))
            {
                field.setDocument(new OnlyNumberField());
            }

            field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                JTextField jt = (JTextField) e.getComponent();
                if (jt.getText().length()> jt.getColumns()){
                jt.setText(jt.getText().substring(0,jt.getColumns()));}
            }
        });
           field.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
              JTextField jt = (JTextField) e.getComponent();
              try
              {
                 if(jt.getToolTipText().equals("null")) jt.setToolTipText("");
              {
              }
              }catch(Exception erro)
              {
                 jt.setToolTipText("");
              }
              
              try
              {
                 if(jt.getToolTipText().equals("Valor") || jt.getToolTipText().equals("%"))
                 {
                     jt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
                     if(jt.getText().equals("")) jt.setText("0");
                     if(jt.getText().equals(",")) jt.setText("0");
                     jt.setText(formataMoeda(jt.getText(),"T"));
                 }
              } catch (Exception t) {

              }
             }
            });
        } else if (component instanceof JTextArea) {
            JTextArea field = (JTextArea) component;
            field.setDocument(new UpperCaseDocument());
            field.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
            field.setLineWrap(true);
            field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                JTextArea jt = (JTextArea) e.getComponent();
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_TAB) {
                    if (e.getModifiers() > 0) {
                        jt.transferFocusBackward();
                    } else {
                        jt.transferFocus();
                    }
                    e.consume();
                }
                if(e.getKeyCode() == (KeyEvent.VK_CONTROL) )
                {
                    jt.setText(jt.getText() + "\n");
                    e.consume();
                }

            }
            public void keyReleased(KeyEvent e) {
                JTextArea jt = (JTextArea) e.getComponent();
                if (jt.getText().length()> jt.getColumns()){
                jt.setText(jt.getText().substring(0,jt.getColumns()));}
            }

        });
        } else if (component instanceof JTable) {
            JTable Table = (JTable) component;
            Table.setDefaultRenderer(Object.class, new CellTableZebra());
        }else if (component instanceof JButton) {
            JButton Botao = (JButton) component;
            Botao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            Botao.setBorderPainted(true);
            registraEnterNoBotao(Botao);
          
        } else if (component instanceof Container) {
            F_AtribuirClasse((Container) component);
        }

    }
        SelectAllTheThings.addListeners(container);
        HashSet conj = new HashSet(container.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        container.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

    }

public static String formataPercentual(String vlr){
    if(vlr.equals("")) vlr="0";

    vlr = vlr.replace(".", "").replace(",", ".");
    double valor = Double.parseDouble(vlr);
        DecimalFormat nf = new DecimalFormat("##0.00");
        return (nf.format(valor));
}


public String ValidarData(String inDate,JFormattedTextField tf) {
  if (inDate.equals("  /  /    ")) {
   return "";
  }else
  {
  SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
  if (inDate.trim().length() != dateFormat.toPattern().length()) {
    tf.requestFocus();
    return "";
  }
  dateFormat.setLenient(false);
  try {
    dateFormat.parse(inDate.trim());
  } catch (ParseException pe) {
     JOptionPane.showMessageDialog(null, "Data Inválida" ,"Informação",JOptionPane.INFORMATION_MESSAGE);
     tf.requestFocus();
     return "";
  }
   return inDate;
    }
}


public static String formataData(String vlr){


         SimpleDateFormat formatIso = new SimpleDateFormat("yyyy-MM-dd");
         SimpleDateFormat formatOut = new SimpleDateFormat("dd/MM/yyyy");
         String str = vlr;
         Date date;

         try {
             date = formatIso.parse(str);
             return (formatOut.format(date));
         } catch (ParseException e) {
             //JOptionPane.showMessageDialog(null, "Data inválida" ,"Informação",JOptionPane.INFORMATION_MESSAGE);
             return ("");
         }
         
    }

public static String formataDataNFE(String vlr){


         SimpleDateFormat formatIso = new SimpleDateFormat("yyyy-MM-dd");
         SimpleDateFormat formatOut = new SimpleDateFormat("dd/MM/yyyy");
         String str = vlr;
         Date date;

         try {
             date = formatOut.parse(str);
             return (formatIso.format(date));
         } catch (ParseException e) {
             //JOptionPane.showMessageDialog(null, "Data inválida" ,"Informação",JOptionPane.INFORMATION_MESSAGE);
             return ("");
         }

    }

public static String formataDataCalculo(String vlr){


         SimpleDateFormat formatIso = new SimpleDateFormat("yyyy/MM/dd");
         SimpleDateFormat formatOut = new SimpleDateFormat("dd/MM/yyyy");
         String str = vlr;
         Date date;

         try {
             date = formatOut.parse(str);
             return (formatIso.format(date));
         } catch (ParseException e) {
             //JOptionPane.showMessageDialog(null, "Data inválida" ,"Informação",JOptionPane.INFORMATION_MESSAGE);
             return ("");
         }

    }

public static String RemoveCaracter(String xValor)
   {
     int ini = 0;
     String retorno = "";
        for (int j = 1; j <= xValor.length(); j++){
            String vM = xValor.substring(0 + ini, j);
            if(!vM.equals(".") && !vM.equals("-") && !vM.equals("/") && !vM.equals("(") && !vM.equals(")"))
            {
                //if(j == 1)
                retorno += xValor.substring(0 + ini, j);
                
               // else
               //     retorno += xValor.substring(0, j - 1);

            }else
            {
                //ini++;
            }
            ini++;
        }
        return (retorno);

   }
 public static String ValidarCNPJ(String strCNPJ)
 {
 int dig1; int dig2; int dig3; int dig4; int dig5; int dig6;
 int dig7; int dig8; int dig9; int dig10; int dig11;
 int dig12; int dig13; int dig14; int dv1; int dv2; int qDig;
 strCNPJ = RemoveCaracter(strCNPJ).trim();
 if (strCNPJ.length() == 0) //Validação do preenchimento
 {
     return "CNPJ não informado!"; //Caso não seja informado o Título
 }
 else
 {
 int sttqtde = strCNPJ.length();
 if (strCNPJ.length() < 14)
 {
 //Completar 14 dígitos
 strCNPJ = "00000000000000" + strCNPJ;
 strCNPJ = strCNPJ.substring(strCNPJ.length() - 14);
 }
 else if (strCNPJ.length() > 14)
 {
 return "CNPJ Inválido!"; //Caso tenha mais que 14 dígitos
 }
   if(strCNPJ.equals("00000000000000")) return "CNPJ Inválido!";
 }
 qDig = strCNPJ.length(); //Total de caracteres
 //Gravar posição dos caracteres
 dig1 = Integer.parseInt(strCNPJ.substring(qDig - 14, 1));
 dig2 = Integer.parseInt(strCNPJ.substring(qDig - 13, 2));
 dig3 = Integer.parseInt(strCNPJ.substring(qDig - 12, 3));
 dig4 = Integer.parseInt(strCNPJ.substring(qDig - 11, 4));
 dig5 = Integer.parseInt(strCNPJ.substring(qDig - 10, 5));
 dig6 = Integer.parseInt(strCNPJ.substring(qDig - 9, 6));
 dig7 = Integer.parseInt(strCNPJ.substring(qDig - 8, 7));
 dig8 = Integer.parseInt(strCNPJ.substring(qDig - 7, 8));
 dig9 = Integer.parseInt(strCNPJ.substring(qDig - 6, 9));
 dig10 = Integer.parseInt(strCNPJ.substring(qDig - 5, 10));
 dig11 = Integer.parseInt(strCNPJ.substring(qDig - 4, 11));
 dig12 = Integer.parseInt(strCNPJ.substring(qDig - 3, 12));
 dig13 = Integer.parseInt(strCNPJ.substring(qDig - 2, 13));
 dig14 = Integer.parseInt(strCNPJ.substring(qDig - 1, 14));
 //Cálculo para o primeiro dígito validador
 dv1 = (dig1 * 6) + (dig2 * 7) + (dig3 * 8) + (dig4 * 9) + (dig5 * 2) +
 (dig6 * 3) + (dig7 * 4) + (dig8 * 5) + (dig9 * 6) +
 (dig10 * 7) + (dig11 * 8) + (dig12 * 9);
 dv1 = dv1 % 11;
 if (dv1 == 10)
 {
 dv1 = 0; //Se o resto for igual a 10, dv1 igual a zero
 }
 //Cálculo para o segundo dígito validador
 dv2 = (dig1 * 5) + (dig2 * 6) + (dig3 * 7) + (dig4 * 8) + (dig5 * 9) +
 (dig6 * 2) + (dig7 * 3) + (dig8 * 4) + (dig9 * 5) +
 (dig10 * 6) + (dig11 * 7) + (dig12 * 8) + (dv1 * 9);
 dv2 = dv2 % 11;
 if (dv2 == 10)
 {
 dv2 = 0; //Se o resto for igual a 10, dv1 igual a zero
 }
 //Validação dos dígitos validadores, após o cálculo realizado
 if (dig13 == dv1 && dig14 == dv2)
 {
     return "CNPJ Válido";
 }
 else
 {
     return "CNPJ Inválido";
 }
 }
 public static String ValidarCPF(String strCPF)
 {
 int dig1; int dig2; int dig3; int dig4; int dig5; int dig6; int dig7; int dig8; int dig9;
 int dig10; int dig11; int dv1; int dv2; int qDig;
 strCPF = RemoveCaracter(strCPF).trim();

 if (strCPF.length() == 0) //Validação do preenchimento
 {
 return "Campo em branco!"; //Caso não seja informado o CPF
 }
 else
 {
 if (strCPF.length() < 11)
 {
 strCPF = "00000000000" + strCPF;
 strCPF = strCPF.substring(strCPF.length() - 11); //Completar 11 dígitos
 }
 else if (strCPF.length() > 11)
 {
 return "CPF Inválido!"; //Caso tenha mais que 11 dígitos
 }
   if(strCPF.equals("00000000000")) return "CPF Inválido!";
 }

 qDig = strCPF.length(); //Quantidade total de caracteres

 //Gravar posição dos caracteres
 dig1 = Integer.parseInt(strCPF.substring(qDig - 11, 1));
 dig2 = Integer.parseInt(strCPF.substring(qDig - 10, 2));
 dig3 = Integer.parseInt(strCPF.substring(qDig - 9, 3));
 dig4 = Integer.parseInt(strCPF.substring(qDig - 8, 4));
 dig5 = Integer.parseInt(strCPF.substring(qDig - 7, 5));
 dig6 = Integer.parseInt(strCPF.substring(qDig - 6, 6));
 dig7 = Integer.parseInt(strCPF.substring(qDig - 5, 7));
 dig8 = Integer.parseInt(strCPF.substring(qDig - 4, 8));
 dig9 = Integer.parseInt(strCPF.substring(qDig - 3, 9));
 dig10 = Integer.parseInt(strCPF.substring(qDig - 2, 10));
 dig11 = Integer.parseInt(strCPF.substring(qDig - 1, 11));


 //Cálculo para o primeiro dígito validador
 dv1 = dig1 + (dig2 * 2) + (dig3 * 3) + (dig4 * 4) + (dig5 * 5) + (dig6 * 6) + (dig7 * 7) + (dig8 * 8) +
(dig9 * 9);
 dv1 = dv1 % 11;

 if (dv1 == 10)
 {
 dv1 = 0; //Se o resto for igual a 10, dv1 igual a zero
 }

 //Cálculo para o segundo dígito validador
 dv2 = dig2 + (dig3 * 2) + (dig4 * 3) + (dig5 * 4) + (dig6 * 5) + (dig7 * 6) + (dig8 * 7) + (dig9 * 8) +
(dv1 * 9);
 dv2 = dv2 % 11;

 if (dv2 == 10)
 {
 dv2 = 0; //Se o resto for igual a 10, dv1 igual a zero
 }

 //Validação dos dígitos validadores, após o cálculo realizado
 if (dig10 == dv1 && dig11 == dv2)
 {
 return "CPF Válido!";
 }
 else
 {
 return "CPF Inválido!";
 }
 }



 public String AllTrim(String xMes)
    {
        return xMes.trim();
    }


    public String StrZero(long xVal, byte xTam)
    {

        String xRet = Long.toString(xVal);
        xRet = PadL(xRet,xTam, '0');
        return xRet;
    }

    public String Empty(String vString)
    {
        return AllTrim(vString = "");
    }


    public Boolean IIF_componete(Boolean Expression)
    {
        if (Expression)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    public String IIF(Boolean Expression, String IfTrue, String IfFalse)
    {
        if (Expression)
        {
            return IfTrue;
        }
        else
        {
            return IfFalse;
        }

    }

    public static String Replicate(char xChar, int xQtde)
    {
        String retorno = "";
        for (int j = 1; j <= xQtde; j++) retorno += xChar;
        return (retorno);
    }

   
    public static String PadR(String Nome, int xTam, char xChar)
    {
        String retorno = "";
        if (Nome.length() > xTam) Nome = Nome.substring(0, xTam);
        retorno = (Nome + Replicate(xChar, (xTam - Nome.length())));
        return (retorno);
    }

    public static String PadL(String Nome, int xTam, char xChar)
    {
        String retorno = "";
        if (Nome.length() > xTam) Nome = Nome.substring(0, xTam);
        retorno = (Replicate(xChar, (xTam - Nome.length())) + Nome);
        return (retorno);
    }

    public static String Pad(String Nome, int xTam, char xChar)
    {
        return (PadR(Nome, xTam, xChar));
    }

    public static String PadC(String Mens, byte xTam, char xChar)
    {
        String retorno = "";
        if (Mens.length() > xTam) Mens = Mens.substring(0, xTam);
        String Espacos = Replicate(xChar, (xTam - Mens.length()) / 2);
        retorno = (Espacos + Mens);
        retorno = (Mens + Replicate(xChar, xTam - Mens.length()));
        return (retorno);
    }

    public String IfThen(Boolean AValue, String ATrue, String AFalse)
    {
        if (AValue)
        {
            return ATrue;
        }
        else
        {
            return AFalse;
        }
    }

//Montar Insert e Update
    public void MontarCamposInsert(Container container) {
    Component components[] = container.getComponents();
    for (Component component : components) {
        if (component instanceof JFormattedTextField) {
            JFormattedTextField field = (JFormattedTextField) component;
            if(!field.getName().equals("")) variaveis.vmCampos += field.getName() + ",";
        } else if (component instanceof JTextField) {
            JTextField field = (JTextField) component;
            if(!field.getName().equals("")) variaveis.vmCampos += field.getName()+ ",";
        }else if (component instanceof JTextArea) {
            JTextArea field = (JTextArea) component;
            if(!field.getName().equals("")) variaveis.vmCampos += field.getName()+ ",";
        } else if (component instanceof JComboBox) {
            JComboBox field = (JComboBox) component;
            if(!field.getName().equals("")) variaveis.vmCampos += field.getName()+ ",";
        } else if (component instanceof JCheckBox) {
            JCheckBox field = (JCheckBox) component;
            if(!field.getName().equals("")) variaveis.vmCampos += field.getName()+ ",";
        } else if (component instanceof Container) {
            MontarCamposInsert((Container) component);
        }
    }
    }

    public void MontarParametrosInsert(Container container) {
    Component components[] = container.getComponents();
    for (Component component : components) {
        if (component instanceof JFormattedTextField) {
            JFormattedTextField field = (JFormattedTextField) component;
            if(!field.getName().equals(""))
            {
                if(field.getText().equals("  /  /    ") || field.getText().equals(null) || field.getText().equals(""))
                {
                    variaveis.vmParametros +=  "Null,";
                }else
                {
                  variaveis.vmParametros += "'" + field.getText() + "',";
                }
            }
        } else if (component instanceof JTextField) {
            JTextField field = (JTextField) component;

            //valores
            if(field.getToolTipText().equals("Valor") || field.getToolTipText().equals("%")
            || field.getToolTipText().equals("Código") || field.getToolTipText().equals("Número"))
            {
                 if(!field.getName().equals("")) variaveis.vmParametros +=  IIF(field.getText().equals(""),"Null",field.getText()).replace(".", "").replace(",", ".")+ ",";
            }else
            {
                if(!field.getName().equals("")) variaveis.vmParametros += "'" + field.getText()+ "',";
            }

        }else if (component instanceof JTextArea) {
            JTextArea field = (JTextArea) component;
            if(!field.getName().equals("")) variaveis.vmParametros += "'" + field.getText()+ "',";
        } else if (component instanceof JComboBox) {
            JComboBox field = (JComboBox) component;
            if(!field.getName().equals("")) variaveis.vmParametros += "'" + field.getSelectedItem().toString()+ "',";
        } else if (component instanceof JCheckBox) {
            JCheckBox field = (JCheckBox) component;
            if(!field.getName().equals("")) variaveis.vmParametros += "'" + field.isSelected() + "',";
        } else if (component instanceof Container) {
            MontarParametrosInsert((Container) component);
        }
    }
    }

    public void MontarParametrosUpdate(Container container) {
    Component components[] = container.getComponents();
    for (Component component : components) {
        if (component instanceof JFormattedTextField) {
            JFormattedTextField field = (JFormattedTextField) component;
            if(!field.getName().equals(""))
            {
                if(field.getText().equals("  /  /    ") || field.getText().equals(null) || field.getText().equals(""))
                {
                    variaveis.vmParametros += field.getName() + "= Null,";
                }else
                {
                  variaveis.vmParametros += field.getName() + "= '" + field.getText() + "',";
                }
            }
        } else if (component instanceof JTextField) {
            JTextField field = (JTextField) component;

            //valores
            if(field.getToolTipText().equals("Valor") || field.getToolTipText().equals("%")
            || field.getToolTipText().equals("Código") || field.getToolTipText().equals("Número"))
            {
                 if(!field.getName().equals("")) variaveis.vmParametros += field.getName() + "= " + IIF(field.getText().equals(""),"Null",field.getText()).replace(".", "").replace(",", ".")+ ",";
            }else
            {
                if(!field.getName().equals("")) variaveis.vmParametros += field.getName() + "= '" + field.getText()+ "',";
            }

        }else if (component instanceof JTextArea) {
            JTextArea field = (JTextArea) component;
            if(!field.getName().equals("")) variaveis.vmParametros += field.getName() + "= '" + field.getText()+ "',";
        } else if (component instanceof JComboBox) {
            JComboBox field = (JComboBox) component;
            if(!field.getName().equals("")) variaveis.vmParametros += field.getName() + "= '" + field.getSelectedItem().toString()+ "',";
        } else if (component instanceof JCheckBox) {
            JCheckBox field = (JCheckBox) component;
            if(!field.getName().equals("")) variaveis.vmParametros += field.getName() + "= '" + field.isSelected() + "',";
        } else if (component instanceof Container) {
            MontarParametrosUpdate((Container) component);
        }
    }
    }

    public void RetornarSelecao(Container container, String vTabela, String vCampos, String vmCondicao_Consulta) {
    ResultSet rs;
    try
    {
       
       rs = AcessoDados.Selecao(vTabela, vCampos, vmCondicao_Consulta);
       rs.next();
       Component components[] = container.getComponents();
       for (Component component : components) {
        if (component instanceof JFormattedTextField) {
            JFormattedTextField field = (JFormattedTextField) component;
            String Campos= "";
            if(!field.getName().equals("")) field.setText(rs.getString(field.getName()));
            if(!field.getName().equals("")) Campos = rs.getString(field.getName());

            try
            {
               if(Campos.equals("null"))Campos = "";
            } catch(Exception erro)
            {
                Campos = "";
            }


                if(Campos.length() == 10)
                {
                    if(!field.getName().equals("")){
                        Date data = rs.getDate(field.getName());
                        SimpleDateFormat formatarDate = new SimpleDateFormat("dd/MM/yyyy");
                        field.setText(formatarDate.format(data));
                    }

                }else if(Campos.length() == 14)
                {
                   field.setText(null);
                   field.setFocusLostBehavior(JFormattedTextField.COMMIT);
                   field.setFormatterFactory(null);
                   MaskFormatter format = new MaskFormatter("###.###.###-##");
                   field.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(format));
                   field.setText(Campos);

                }else if(Campos.length() == 18)
                {
                   field.setText(null);
                   field.setFocusLostBehavior(JFormattedTextField.COMMIT);
                   field.setFormatterFactory(null);
                   MaskFormatter format = new MaskFormatter("##.###.###/####-##");
                   field.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(format));
                   field.setText(Campos);
                }

            } else if (component instanceof JTextField) {
            JTextField field = (JTextField) component;
            //valores
            if(field.getToolTipText().equals("Valor") || field.getToolTipText().equals("%"))
            {
                if(!field.getName().equals("")) field.setText(formataMoeda(rs.getString(field.getName()),"BD"));
            }else if  (field.getToolTipText().equals("Código") || field.getToolTipText().equals("Número"))
            {
                if(!field.getName().equals("")){
                    field.setText(rs.getString(field.getName()));
                    field.requestFocus();
                    field.transferFocus();
                }
            }else
            {
                if(!field.getName().equals("")) field.setText(rs.getString(field.getName()));
            }

        }else if (component instanceof JTextArea) {
            JTextArea field = (JTextArea) component;
            if(!field.getName().equals("")) field.setText(rs.getString(field.getName()));
        } else if (component instanceof JComboBox) {
            JComboBox field = (JComboBox) component;
            if(!field.getName().equals("")) field.setSelectedItem(rs.getString(field.getName()));
        } else if (component instanceof JCheckBox) {
            JCheckBox field = (JCheckBox) component;
            if(!field.getName().equals("")) field.setText(rs.getString(field.getName()));
        } else if (component instanceof Container) {
            RetornarSelecao((Container) component, vTabela, vCampos, vmCondicao_Consulta);
        }
    }
    }
    catch (Exception e)
    {
       e.printStackTrace();
    }

    }
    public void GravarDados(String Codigo,  Container container, String  Tabela, String CampoID, boolean LimparCampos)
    {
        variaveis.vmCampos ="";
        Object[] options = { " Sim ", " Não " };
        if(Codigo.equals("0"))
        {
            int confirmar = JOptionPane.showOptionDialog(null, "Confirma Inclusão", " Mensagem...",JOptionPane.DEFAULT_OPTION, JOptionPane. QUESTION_MESSAGE,null,options, options[0]);
            if(confirmar==1){
                return;
             }
             MontarCamposInsert(container);
             variaveis.vmCampos= variaveis.vmCampos.substring(0, (variaveis.vmCampos.length()- 1));
             variaveis.vmParametros="";
             MontarParametrosInsert(container);
             variaveis.vmParametros = variaveis.vmParametros.substring(0, (variaveis.vmParametros.length()- 1));
             try
             {
             AcessoDados.Inserir(Tabela, variaveis.vmCampos, variaveis.vmParametros);
              }catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(!Codigo.equals("0"))
        {
            int confirmar = JOptionPane.showOptionDialog(null, "Confirma Alteração", " Mensagem...",JOptionPane.DEFAULT_OPTION, JOptionPane. QUESTION_MESSAGE,null,options, options[0]);
            if(confirmar==1){
                return;
             }
               variaveis.vmParametros="";
               MontarParametrosUpdate(container);
               variaveis.vmParametros = variaveis.vmParametros.substring(0, (variaveis.vmParametros.length()- 1));
               String vmCondicao_Alteracao = CampoID + " = " + Codigo ;
            try
             {
                AcessoDados.Alterar(Tabela, variaveis.vmParametros, vmCondicao_Alteracao);
              }catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(LimparCampos) //Não limpar campos para master e detalhe
        {
           limparTodosCampos(container);
           HabilitaCampos(container, false);
        }

    }

     public void GravarDadosCotacao(String Codigo,  Container container, String  Tabela, String CampoID, boolean LimparCampos)
    {
        variaveis.vmCampos ="";
        Object[] options = { " Sim ", " Não " };
        int confirmar = JOptionPane.showOptionDialog(null, "Confirma Inclusão", " Mensagem...",JOptionPane.DEFAULT_OPTION, JOptionPane. QUESTION_MESSAGE,null,options, options[0]);
        if(confirmar==1){
           return;
        }
        MontarCamposInsert(container);
        variaveis.vmCampos= variaveis.vmCampos.substring(0, (variaveis.vmCampos.length()- 1));
        variaveis.vmParametros="";
        MontarParametrosInsert(container);
        variaveis.vmParametros = variaveis.vmParametros.substring(0, (variaveis.vmParametros.length()- 1));
        try
        {
         AcessoDados.Inserir(Tabela, variaveis.vmCampos, variaveis.vmParametros);
        }catch (Exception e) {
            e.printStackTrace();
        }
 
    }
    public void ExcluirDados(String Codigo,  Container container, String  Tabela, String CampoID)
    {
        Object[] options = { " Sim ", " Não " };
        if(!Codigo.equals("0"))
        {
            int confirmar = JOptionPane.showOptionDialog(null, "Confirma Exclusão", " Mensagem...",JOptionPane.DEFAULT_OPTION, JOptionPane. QUESTION_MESSAGE,null,options, options[0]);
            if(confirmar==1){
                return;
             }
                String vmCondicao_Exclusao = CampoID + " = " + Codigo ;
             try
             {
                AcessoDados.Excluir(Tabela, vmCondicao_Exclusao);
              }catch (Exception e) {
                e.printStackTrace();
            }
            limparTodosCampos(container);
        }
        if(Codigo.equals("0"))
        {
           JOptionPane.showMessageDialog(null,"Nenhum registro para exclusão.");
        }
    }

 public String gerarChaveDeAcessoNfe(String chaveSemDigito) throws InputMismatchException {

        // UMA CHAVE DE ACESSO DE NF-E TEM 44 DIGITOS, ENTAO O CALCULO SE DÁ COM OS 43 ANTERIORES
        if (chaveSemDigito.length() != 43) {
            throw new InputMismatchException("Chave Invalida possui " + chaveSemDigito.length());
        }
        int[] aux = new int[chaveSemDigito.length()];
        int variavel = 2;
        int total = 0;
        int dv = 0;
        for (int i = aux.length - 1; i >= 0; i--) {
            aux[i] = Integer.parseInt("" + chaveSemDigito.charAt(i));
            aux[i] = aux[i] * variavel;
            variavel++;
            if (variavel > 9)
                variavel = 2;
            total += aux[i];
        }

        if (total == 0 || total == 1)
            dv = 0;
        else
            dv = 11 - (total % 11);


        String chaveFinal = (chaveSemDigito + dv);
        System.out.println("Digito Verificador: " + dv);
        System.out.println("chave FInal: " + chaveFinal);
        return chaveFinal;

}
public static int getMod11(String num, int base, int r){
    /**
     *   Autor:
     *           Douglas Tybel <dtybel@yahoo.com.br>
     *
     *   Função:
     *    Calculo do Modulo 11 para geracao do digito verificador
     *    de boletos bancarios conforme documentos obtidos
     *    da Febraban - www.febraban.org.br
     *
     *   Entrada:
     *     $num: string numérica para a qual se deseja calcularo digito verificador;
     *     $base: valor maximo de multiplicacao [2-$base]
     *     $r: quando especificado um devolve somente o resto
     *
     *   Saída:
     *     Retorna o Digito verificador.
     *
     *   Observações:
     *     - Script desenvolvido sem nenhum reaproveitamento de código existente.
     *     - Script original de Pablo Costa <pablo@users.sourceforge.net>
     *     - Transportado de php para java
     *     - Exemplo de uso: getMod11(nossoNumero, 9,1)
     *     - 9 e 1 são fixos de acordo com a base
     *     - Assume-se que a verificação do formato das variáveis de entrada é feita antes da execução deste script.
     */
    base = 9;
    r    = 0;

    int soma = 0;
    int fator = 2;
    String[] numeros,parcial;
    numeros = new String[num.length()+1];
    parcial = new String[num.length()+1];

    /* Separacao dos numeros */
    for (int i = num.length(); i > 0; i--) {
        // pega cada numero isoladamente
        numeros[i] = num.substring(i-1,i);
        // Efetua multiplicacao do numero pelo falor
        parcial[i] = String.valueOf(Integer.parseInt(numeros[i]) * fator);
        // Soma dos digitos
        soma += Integer.parseInt(parcial[i]);
        if (fator == base) {
            // restaura fator de multiplicacao para 2
            fator = 1;
        }
        fator++;

    }

    /* Calculo do modulo 11 */
    if (r == 0) {
        soma *= 10;
        int digito = soma % 11;
        if (digito == 10) {
            digito = 0;
        }
        return digito;
    } else {
        int resto = soma % 11;
        return resto;
    }
}

  public Boolean Permissao(String CodigoTabela, String Cod_Usuario, String SuperUsuario, String Tela) {

    if(SuperUsuario.equals("SIM"))
    {
      return true;
    }

    ResultSet rs;
    String vmCampos = "_"+CodigoTabela, vmCondicao_Consulta = " where per_usu_codigo_id =" + Cod_Usuario + " and per_tela = '" + Tela + "'";
    try
    {
        rs = AcessoDados.Selecao("permissao", vmCampos, vmCondicao_Consulta );
        rs.next();
        String Permissao = rs.getString("_"+CodigoTabela);
        if(Permissao.equals("f"))
        {
            JOptionPane.showMessageDialog(null, "Usuário não tem permissão para operação." ,"Informação",JOptionPane.INFORMATION_MESSAGE);
            return false;
        }else
        {
           return true;
        }

          
    }catch (Exception e) {
           e.printStackTrace();
           return  false;
    }
}

  public Boolean PermissaoMenu(String Cod_Usuario, String SuperUsuario, String Tela) {

    String vmTodosCampos = "";
    String vmCampoTabela = "_";
    int vPosicao = 1;
    if(SuperUsuario.equals("SIM"))
    {
      return true;
    }

    ResultSet rs;
    String vmCampos = "*", vmCondicao_Consulta = " where per_usu_codigo_id =" + Cod_Usuario + " and per_tela = '" + Tela + "'";
    try
    {
        rs = AcessoDados.Selecao("permissao", vmCampos, vmCondicao_Consulta );
        if(rs.next())
        {
            for(int i = 0; i <= 15 - 1; i++)
            {

            vmCampoTabela = "_" + StrZero(vPosicao, Byte.parseByte("3"));
            vmTodosCampos += rs.getString(vmCampoTabela);
            vPosicao++;
            }
        }
        if(vmTodosCampos.contains("t"))
        {
            return true;
        } else
            return false;


    }catch (Exception e) {
           e.printStackTrace();
           return  false;
    }
}


}//final


