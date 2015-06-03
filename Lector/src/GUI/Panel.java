/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.text.*;
import lector.Lector;

/**
 *
 * @author JuanDiego
 */
public class Panel extends JPanel implements ActionListener {

    public JTextPane txpFile;
    public JTextField txtBuscar;
    public JButton btnAbrir, btnComparar, btnBuscar, btnGuardar;
    public JLabel lblFile;
    public JFileChooser fcSeleccionar;
    public PanelMostrar panel;
    public JScrollPane scroll;
    public ImageIcon imagen;
    int diff, mayorL;
    double porcentaje;
    Lector l = new Lector();
    String strText;
    String[] file;
    StyleContext sc = new StyleContext();
    DefaultStyledDocument doc = new DefaultStyledDocument(sc);
    Style rojo = sc.addStyle("ConstantWidth", null);
    SimpleAttributeSet attrs = new SimpleAttributeSet();
    Style negro = sc.addStyle("ConstantWidth", null);

    public Panel() {
        setLayout(null);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension pantalla = tk.getScreenSize();
        setSize(pantalla);

        btnAbrir = new JButton("Abrir");
        btnComparar = new JButton("Comparar");
        btnGuardar = new JButton("Guardar");
        btnBuscar = new JButton();
        txtBuscar = new JTextField();
        txpFile = new JTextPane(doc);
        scroll = new JScrollPane(txpFile);
        lblFile = new JLabel();
        panel = new PanelMostrar();

        btnAbrir.setBounds(80, 300, 157, 33);
        btnComparar.setBounds(80, 350, 157, 33);
        txtBuscar.setBounds(1053, 70, 157, 33);
        btnBuscar.setBounds(1210, 70, 40, 33);
        btnGuardar.setBounds(80, 400, 157, 33);
        scroll.setBounds(500, 150, 750, 500);
        lblFile.setBounds(500, 70, 750, 33);

        lblFile.setFont(new Font("Arial", Font.BOLD, 20));

        ImageIcon icon = new ImageIcon(getClass().getResource("/images/lupa.png"));
        btnBuscar.setIcon(icon);

        btnAbrir.addActionListener(this);
        btnComparar.addActionListener(this);
        btnBuscar.addActionListener(this);
        btnGuardar.addActionListener(this);

        add(btnAbrir);
        add(btnComparar);
        add(txtBuscar);
        add(btnBuscar);
        add(scroll);
        add(btnGuardar);
        add(lblFile);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAbrir) {
            openFile();
        }
        if (e.getSource() == btnComparar) {
            if (txpFile.getText().length() > 0) {
                comparar();
            } else {
                JOptionPane.showMessageDialog(null,
                        "El documento está vacio",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == btnBuscar) {
            if (txpFile.getText().length() > 0) {
                buscar();
            } else {
                JOptionPane.showMessageDialog(null,
                        "El documento está vacio",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == btnGuardar) {
            if (txpFile.getText().length() > 0) {
                guardar();
            } else {
                JOptionPane.showMessageDialog(null,
                        "El documento está vacio",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public void buscar() {
        ArrayList<Integer> busqueda;
        String palabra = txtBuscar.getText();
        String texto = txpFile.getText();
        busqueda = l.matching(texto, palabra);
        int length = palabra.length();
        if (length > 0) {
            StyleConstants.setForeground(rojo, Color.red);
            StyleConstants.setForeground(negro, Color.BLACK);
            doc.setCharacterAttributes(0, txpFile.getText().length(),
                    negro, false);
            if (!busqueda.isEmpty()) {
                int c = busqueda.size();
                for (int i = 0; i < c; i++) {
                    doc.setCharacterAttributes(busqueda.get(i),
                            length, rojo, false);
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "No se encontró la palabra",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Debe ingresar una palabra",
                    "Adverteencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void comparar() {
        file = abrir("Comparar");
        strText = txpFile.getText();
        diff = l.Levenshtein(file[0], strText);
        mayorL = Math.max(file[0].length(), strText.length());
        porcentaje = ((mayorL - diff) * 100) / mayorL;
        panel.removeAll();
        if (!file[1].equals("")) {
            panel = new PanelMostrar(file[1], porcentaje);
            add(panel).setBounds(80, 500, 250, 150);
        }
    }

    public void openFile() {
        String[] text = abrir("Abrir");
        StyleConstants.setBold(attrs, true);
        StyleConstants.setForeground(negro, Color.BLACK);
        doc.setCharacterAttributes(0, txpFile.getText().length(), negro, false);
        panel.removeAll();
        panel.updateUI();
        if (!text[0].equals("")) {
            txpFile.setText(text[0]);
            lblFile.setText(text[1]);
        }
    }

    public void guardar() {
        JFileChooser file = new JFileChooser();
        int seleccion = file.showSaveDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            try {
                File guarda = file.getSelectedFile();

                if (guarda != null) {
                    FileWriter save = new FileWriter(guarda + ".txt");
                    save.write(txpFile.getText());
                    save.close();
                    JOptionPane.showMessageDialog(null,
                            "El archivo se ha guardado exitosamente",
                            "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,
                        "Su archivo no se ha guardado",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public String[] abrir(String str) {
        String nombre = "", texto = "";
        String[] archivo = new String[2];

        JFileChooser file = new JFileChooser();
        int seleccion = file.showDialog(this, str);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            try {
                File abre = file.getSelectedFile();

                nombre = abre.getName();

                if (abre != null) {
                    texto = l.leerArchivo(abre);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo",
                        "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
        }

        archivo[0] = texto;
        archivo[1] = nombre;
        return archivo;
    }

    public void paint(Graphics g) {
        Dimension tamanio = getSize();
        imagen = new ImageIcon(getClass().getResource("/images/fondo_1.png"));
        g.drawImage(imagen.getImage(), 0, 0, tamanio.width, tamanio.height,
                null);
        setOpaque(false);
        super.paint(g);
    }
}
