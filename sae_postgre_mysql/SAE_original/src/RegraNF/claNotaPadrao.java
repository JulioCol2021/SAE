/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RegraNF;
import javax.swing.JTextField;
import javax.swing.*;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import Classes.claVariaveis;

/**
 *
 * @author Thiago
 */
public class claNotaPadrao {
    claVariaveis variaveis = new claVariaveis();
    public void calculaNota(double vmICMS, double vmIPI, double vmDESCONTO, double vmQTDE, double vmUNITARIO, double vmALIQDIF, double vmREDUCAOICMS){

        double vm_vl_icms = 0;
        double vm_vl_ipi = 0;
        double vm_vl_total = 0;
        double vm_desconto =0;
        double vm_reducao = 100 - vmREDUCAOICMS;

        vm_vl_total = (vmQTDE * vmUNITARIO);
        vm_desconto = (vm_vl_total * vmDESCONTO) / 100;
        vm_vl_total = vm_vl_total - vm_desconto;

        variaveis.vm_base_calculo_icms = vm_vl_total;
        if (vmALIQDIF > 0)
        vm_vl_icms = (vm_vl_total * vmALIQDIF) / 100;
        else
        vm_vl_icms = (vm_vl_total * vmICMS) / 100;

        if (vmREDUCAOICMS > 0)
        {
           vm_vl_icms = (vm_vl_total * vm_reducao) / 100;
           variaveis.vm_base_calculo_icms = vm_vl_icms;
           if (vmALIQDIF > 0)
           vm_vl_icms = (vm_vl_icms * vmALIQDIF) / 100;
           else
           vm_vl_icms = (vm_vl_icms * vmICMS) / 100;
        }

        vm_vl_ipi = (vm_vl_total * vmIPI) / 100;

        variaveis.vm_ICMS = vm_vl_icms;
        variaveis.vm_IPI = vm_vl_ipi;
        //variaveis.vm_Total = vm_vl_total + vm_vl_icms + vm_vl_ipi;
        variaveis.vm_Total = vm_vl_total;


    }
}
