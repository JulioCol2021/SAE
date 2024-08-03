/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Classes;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class CellTableZebra extends JLabel implements TableCellRenderer {

    public CellTableZebra(){
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if ((row % 2)==0){
            setBackground(new java.awt.Color(218,228,224));
            setFont(new java.awt.Font("Dialog", 0, 11));
            //setForeground(Color.RED);
        } else {
            setBackground(Color.WHITE);
            setFont(new java.awt.Font("Dialog", 0, 11));
            //setForeground(Color.BLACK);

        }
        if(isSelected)
        {
           setBackground(new java.awt.Color(245,252,199));
           setFont(new java.awt.Font("Dialog", 1, 11));
        }
        if (value==null){
            setText("");
        } else {
            setText(value.toString());
        }
        return this;
    }

}