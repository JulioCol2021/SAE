/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Classes;
import java.sql.SQLException;
import Classes.claConexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author Thiago
 */
public class claAcessoDados {
    private PreparedStatement sql;
    private ResultSet rs;
    Connection conn = new claConexao().getConexao();


    public void Inserir(String NomeTabela, String Campos, String Parametros) throws SQLException, Exception {

        try
        {
        // DECLARA CAMPO SERIAL NO POSTGRE PARA AUTO INCREMENTO E NÃO DECLARA O CAMPO NA INSERT
        sql = conn.prepareStatement("Insert Into " + NomeTabela + "  ( " +  Campos + " ) Values ( " + Parametros + " )");
        sql.executeUpdate();
        //JOptionPane.showMessageDialog(null,"Registro INCLUIDO com sucesso!");
        sql.close();
        }catch (Exception e) {
           String vm_error = e.getMessage();
            JOptionPane.showMessageDialog(null,vm_error);
        }

     }
    public void Alterar(String NomeTabela, String Campos_Parametros, String Condicao_Alteracao) throws SQLException, Exception {
        try
        {
        sql = conn.prepareStatement("Update " + NomeTabela + " set " +  Campos_Parametros + " where " + Condicao_Alteracao );
        sql.executeUpdate();
        //JOptionPane.showMessageDialog(null,"Registro ALTERADO com sucesso!");
        sql.close();
        }catch (Exception e) {
           String vm_error = e.getMessage();
            JOptionPane.showMessageDialog(null,vm_error);
        }

     }
    
      public ResultSet Selecao(String NomeTabela, String Campos, String Condicao_Consulta) throws Exception {
        try {
        sql = conn.prepareStatement("Select " + Campos + " FROM " + NomeTabela + " " + Condicao_Consulta  );
        rs = sql.executeQuery();
        } catch (Exception e) {
            String vm_error = e.getMessage();
            JOptionPane.showMessageDialog(null,vm_error);
        } 
        return rs;
    }

      public ResultSet SelecaoUNION(String Condicao_Consulta) throws Exception {
        try {
        sql = conn.prepareStatement(Condicao_Consulta);
        rs = sql.executeQuery();
        } catch (Exception e) {
            String vm_error = e.getMessage();
            JOptionPane.showMessageDialog(null,vm_error);
        }
        return rs;
    }

      public Boolean Excluir(String NomeTabela, String Condicao_Exclusao) throws SQLException, Exception {
        try
        {
        sql = conn.prepareStatement("Delete From " + NomeTabela + " where " +  Condicao_Exclusao);
        sql.executeUpdate();
        //JOptionPane.showMessageDialog(null,"Registro EXCLUIDO com sucesso!");
        sql.close();
       
        return true;
        }catch (Exception e) {
           String vm_error = e.getMessage();
           JOptionPane.showMessageDialog(null,vm_error);
           return false;
        }

     }

}
