/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Classes;
import java.sql.*;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
/**
 *
 * @author Thiago
 */
public class claConexao {
      String serverName ;
      String mydatabase ;
      
       private void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


public Connection getConexao () {
 try {
     String Caminho = new File("config.ini").getAbsolutePath();
     //JOptionPane.showMessageDialog(null, Caminho, "Aviso", JOptionPane.ERROR_MESSAGE);
     File file = new File(Caminho);
            FileInputStream in = new FileInputStream(file);
            Scanner scanner = new Scanner(in);

            while (scanner.hasNext()) {
                //String readLine = scanner.next();
               serverName = scanner.next();
               mydatabase = scanner.next();
              
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
Connection conn = null;
try {
// Carregando o JDBC Driver

//não esquecer de adicionar o conector do postgre ao netbeans




// Criando a conexão com o Banco de Dados

//------MYSQL--------------------------------------------------------
//String driverName = "org.gjt.mm.mysql.Driver"; // MySQL MM JDBC driver
//Class.forName(driverName);
//String serverName = "agendageral.com.br:3306";    //caminho do servidor do BD
//String mydatabase ="banco_mysql";        //nome do seu banco de dados
//String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
//String username = "user_Mysql";        //nome de um usuário de seu BD      
//String password = "senha_do_mysql";      //sua senha de acesso

/*String driverName = "org.gjt.mm.mysql.Driver"; // MySQL MM JDBC driver
Class.forName(driverName);
String serverName = "seusite.com.br:3306";    //caminho do servidor do BD
String mydatabase ="agendage_erp";        //nome do seu banco de dados
String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
String username = "agendage_erp";        //nome de um usuário de seu BD      
String password = "senha";      //sua senha de acesso
*/
//------FIM MYSQL--------------------------------------------------------

//------POSTGRESQL--------------------------------------------------------
// Criando a conexão com o Banco de Dados
String driverName = "org.postgresql.Driver"; // Postgresql JDBC driver
Class.forName(driverName);
String serverName = "127.0.0.1:5432";  // localhost
String mydatabase = "SAE";
String url = "jdbc:postgresql://" + serverName + "/" + mydatabase; // a JDBC local
//String url = "jdbc:postgresql://SERVIDOR_ONLINE:5432/SAE"; // a JDBC web - use apenas se for via postgre online
String username = "postgres";
String password = "!191218bc";
//------FIM POSTGRESQL--------------------------------------------------------

conn = DriverManager.getConnection(url, username, password);
//se ok ele manda essa msg
//JOptionPane.showMessageDialog(null, "Banco de dados conectado com sucesso!", "Conexão", JOptionPane.ERROR_MESSAGE);
} catch (ClassNotFoundException e){
//Driver não encontrado
JOptionPane.showMessageDialog(null, "O driver especificado não foi encontrado (.jar)", "Aviso", JOptionPane.ERROR_MESSAGE);
} catch (SQLException e) {
//Não está conseguindo se conectar ao banco e fecha aplicação.
JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco de dados", "Aviso", JOptionPane.ERROR_MESSAGE);
 this.dispose();
}
return conn;
}

   
}
