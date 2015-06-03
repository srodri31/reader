/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author JuanDiego
 */
public class PanelMostrar extends JPanel {

    public JLabel lblMen1, lblPorcentaje, lblMen2;
    
    public PanelMostrar(){}

    public PanelMostrar(String name, double porcentaje) {
        setLayout(null);
        setSize(250, 150);
        setOpaque(false);

        lblMen1 = new JLabel("Comparado con ");
        lblMen2 = new JLabel(name + " : ");
        lblPorcentaje = new JLabel(Double.toString(porcentaje) + "% de plagio");

        lblMen1.setFont(new Font("Arial", Font.BOLD, 20));
        lblMen2.setFont(new Font("Arial", Font.BOLD, 20));
        lblPorcentaje.setFont(new Font("Arial", Font.BOLD, 20));

        lblMen1.setForeground(Color.white);
        lblMen2.setForeground(Color.white);

        lblMen1.setBounds(0, 0, 250, 30);
        lblMen2.setBounds(0, 25, 250, 30);
        lblPorcentaje.setBounds(0, 55, 250, 30);
        
        if (porcentaje <= 20) {
            lblPorcentaje.setForeground(Color.GREEN);
        } else if (porcentaje <= 40) {
            lblPorcentaje.setForeground(Color.white);
        } else if (porcentaje <= 60) {
            lblPorcentaje.setForeground(Color.YELLOW);
        } else if (porcentaje <= 80) {
            lblPorcentaje.setForeground(Color.ORANGE);
        } else {
            lblPorcentaje.setForeground(Color.red);
        }

        add(lblMen1);
        add(lblMen2);
        add(lblPorcentaje);
    }
}
