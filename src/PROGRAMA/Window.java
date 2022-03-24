/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PROGRAMA;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author iosea
 */
public class Window {

    public static DefaultTableModel mod2;
    public DefaultTableModel modelo1;
    public int[] array;
    public int[] Condats;
    public boolean Boolean;
    public int Seconds;
    public int Stepsinit;
    public JFrame JFrame2;
    public static JScrollPane Deslizador1;
    public JLabel Archivo;
    public JLabel JLTitle;
    public JTextField txt1;
    public Threads graphics;
    public JButton Inicia;
    public JButton Grafica;
    public Object[] Chubaca;
    JTable Jt2;
    JTable Jt3;

    public Window() {
        Chubaca = new Object[1000];
        modelo1 = new DefaultTableModel();
        modelo1.addColumn("datos");
        Jt3 = new JTable(modelo1);
        Deslizador1 = new JScrollPane(Jt3);
        Boolean = true;
        Seconds = 0;
        Stepsinit = 0;
        JFrame2 = new JFrame("Graficadora");
        JFrame2.setSize(955, 600);
        JFrame2.setLayout(null);
        JFrame2.setLocationRelativeTo(null);
        JFrame2.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JFrame2.addWindowListener(new WindowAdapter() {
            public void WindowClosing(WindowEvent WindowEnvent) {
                System.exit(0);
            }
        });

        Archivo = new JLabel("", JLabel.LEFT);
        Archivo.setFont(new Font("Algerian", Font.BOLD, 20));
        Archivo.setBounds(10, 20, 760, 30);
        Archivo.setForeground(Color.black);
        Archivo.setBackground(Color.BLUE);
        Archivo.setVisible(true);

        JLabel titulo1 = new JLabel("Nombre: ", JLabel.LEFT);
        titulo1.setFont(new Font("Algerian", Font.BOLD, 20));
        titulo1.setForeground(Color.red);
        titulo1.setBounds(10, 60, 760, 30);
        titulo1.setVisible(true);

        JLTitle = new JLabel("", JLabel.LEFT);
        JLTitle.setFont(new Font("Algerian", Font.BOLD, 20));
        JLTitle.setForeground(Color.red);
        JLTitle.setBounds(100, 60, 250, 30);
        JLTitle.setBackground(Color.BLUE);
        JLTitle.setVisible(true);
        graphics = new Threads(Condats, JLTitle.getText());

        JButton cargar = new JButton("Archivo");
        cargar.setBackground(new java.awt.Color(255, 204, 0));
        cargar.setFont(new java.awt.Font("Impact", 0, 14)); 
        cargar.setBounds(785, 120, 140, 30);
        cargar.setVisible(true);
        cargar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    String text = "";
                    String auxiliar = "";
                    JFileChooser choosing = new JFileChooser();
                    choosing.showSaveDialog(null);
                    File opening = choosing.getSelectedFile();
                    if (opening != null) {
                        Archivo.setText(choosing.getSelectedFile().getAbsolutePath().toUpperCase());
                        FileReader filefinging = new FileReader(opening);
                        BufferedReader reader = new BufferedReader(filefinging);
                        while ((auxiliar = reader.readLine()) != null) {
                            text += auxiliar + "\n";}
                        reader.close();
                        Object jsonObyeto = JSONValue.parse(text);
                        JSONObject OBJECTJSON = (JSONObject) jsonObyeto;

                        Object nombre_object = OBJECTJSON.get("title");
                        JLTitle.setText(nombre_object.toString().toUpperCase());
                        Object JSONArray = OBJECTJSON.get("dataset");
                        JSONArray ObjectJ = (JSONArray) JSONArray;

                        int size = 0;
                        for (Object Array_objects : ObjectJ) {
                            size++;
                        }
                        Condats = new int[size];
                        int indice = 0;
                        for (Object obyeto_inarray : ObjectJ) {
                            Condats[indice] = Integer.parseInt(obyeto_inarray.toString());
                            indice++;
                        }
                    }
                    JFrame2.remove(graphics);
                    graphics = new Threads(Condats, JLTitle.getText());
                    JFrame2.add(graphics);
                    Inicia.setVisible(true);

                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            }
        });

        Inicia = new JButton("Ordenar");
        Inicia.setBounds(785, 160, 140, 30);
        Inicia.setBackground(new java.awt.Color(255, 204, 0));
        Inicia.setFont(new java.awt.Font("Impact", 0, 14));
        Inicia.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Inicia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    new Thread(graphics).start();
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            }
        });
        JFrame2.add(Archivo);
        JFrame2.add(graphics);
        JFrame2.add(JLTitle);
        JFrame2.add(titulo1);
        JFrame2.add(cargar);
        JFrame2.add(Inicia);
        JFrame2.setVisible(true);
    }

}
