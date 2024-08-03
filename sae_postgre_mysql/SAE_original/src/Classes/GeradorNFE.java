/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package Classes;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author Marcelo
 */

//Atenção ALT + INsert Gerar Get e Set
@XStreamAlias("geradorNFE")
public class GeradorNFE {
 private String Rua;
 private String Cidade;

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String Cidade) {
        this.Cidade = Cidade;
    }

    public String getRua() {
        return Rua;
    }

    public void setRua(String Rua) {
        this.Rua = Rua;
    }

}
