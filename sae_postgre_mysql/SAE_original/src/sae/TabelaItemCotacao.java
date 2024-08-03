/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sae;

/**
 *
 * @author Marcelo
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class TabelaItemCotacao extends JLabel implements TableCellRenderer {

    public TabelaItemCotacao(){
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if ((row % 2)==0){
            setBackground(Color.WHITE);
            setFont(new java.awt.Font("Dialog", 0, 10));
            //setForeground(Color.RED);
        } else {
            setBackground(Color.WHITE);
            setFont(new java.awt.Font("Dialog", 0, 10));
            //setForeground(Color.BLACK);

        }
        if(column == 1 || column == 2 || column == 3 || column == 4 ||  column == 6)
        {
            setBackground(new java.awt.Color(218,228,224));
            setFont(new java.awt.Font("Dialog", 0, 10));
        }


        if(isSelected)
        {
           setBackground(new java.awt.Color(255,255,188));
           setFont(new java.awt.Font("Dialog", 1, 10));
        }

        if (value==null){
            setText("");
        } else {
            setText(value.toString());
        }
        return this;
    }

}
