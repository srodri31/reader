/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.*;

/**
 *
 * @author JuanDiego
 */
public class Main extends JFrame{
    public Panel panel;
    
    public Main(){
        setTitle("Reader");
        setLayout(null);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension pantalla = tk.getScreenSize();
        setSize(pantalla);
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Image icon = new ImageIcon(getClass().getResource("/images/icon6.png")).getImage();
        setIconImage(icon);
        panel = new Panel();
        
        add(panel);
    }
    
    public static void main(String[] args) {
        Main frame = new Main();
        frame.setVisible(true);
    }
}
