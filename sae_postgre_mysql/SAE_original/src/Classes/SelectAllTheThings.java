/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Classes;

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

/**
 *
 * @author Sekkuar
 */
public class SelectAllTheThings extends FocusAdapter {

    private static FocusListener f;

    /**
     * Construtor privado para não ser instaciado fora da classe
     */
    private SelectAllTheThings() {

    }

    /**
     * Evento focusGained, que vai ser executado quando o usuário
     * selecionar o campo de texto, que vai fazer todo o texto
     * (se tiver algum) ser selecionado automaticamente
     */
    public void focusGained(FocusEvent e) {
        Object o = e.getSource();

        if (o instanceof JTextComponent) {
            JTextComponent j = (JTextComponent) o;
            j.selectAll();
            j.setBackground(new java.awt.Color(245,252,199));
            //j.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102,102,255),2));
        }
    }
     public void focusLost(FocusEvent e) {
        Object o = e.getSource();

        if (o instanceof JTextComponent) {
            JTextComponent j = (JTextComponent) o;
            j.selectAll();
            j.setBackground(Color.white);
            //j.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        }
     }

    /**
     * @return A única instância de SelectAllTheThings
     */
    public static FocusListener getListener() {
        if (f == null) {
            f = new SelectAllTheThings();
        }
        return f;
    }

    /**
     * Dado um Conteiner, adiciona o FocusListener para todos os
     * componentes de texto dentro do conteiner, e de quaisquer
     * Conteirners ascendentes.
     *
     * @param o Conteiner ancestral de todos os componentes de texto
     * que se quer adicionar o FocusListener
     */
    public static void addListeners(Container c) {


        if (c instanceof JTextComponent) {
            JTextComponent j = (JTextComponent) c;
            j.addFocusListener(getListener());
        } else {
            for (Component comp : c.getComponents()) {
                if (comp instanceof Container) {
                    addListeners((Container) comp);
                }
            }
        }

    }
}