/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PROGRAMA;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author iosea
 */
public class Threads extends JPanel implements Runnable {
    
    JTable Jtable1;
    JTable Jtable2;    
    public DefaultTableModel modelo1;
    public DefaultTableModel modelo2;
    public int[] dinicial;
    public JFreeChart barras;
    public JLabel titulo;
    public int arreglo2[];
    public static int time2;
    public int txt;
    public int steps;
    public Time time;
    public int m=0;
    public int[] data1;
    public  int[] arreglo1 = new int[1000];
    int[] arreglo11;
    

    public Threads(int[] _datos, String title) {
        this.setBackground(Color.ORANGE);
        modelo2 = new DefaultTableModel();
        modelo2.addColumn("Datos antes de ordenamiento");
        Jtable2 = new JTable(modelo2);
        JScrollPane deslizador1 = new JScrollPane(Jtable2);
        modelo1 = new DefaultTableModel();
        modelo1.addColumn("Datos iniciales");
        Jtable1 = new JTable(modelo1);
        JScrollPane deslizador2 = new JScrollPane(Jtable1);

        this.data1 = new int[1];
        this.data1 = _datos;
        if (this.data1 != null) {
            this.dinicial = _datos.clone();
        }
        setBounds(10, 120, 760, 400);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.orange));
        time = new Time();
        add(time, BorderLayout.PAGE_END);

        titulo = new JLabel("pasos = 0");
        titulo.setFont(new Font("Algerian", Font.BOLD, 20));
        titulo.setForeground(Color.blue);
        titulo.setPreferredSize(new Dimension(760, 30));
        add(titulo, BorderLayout.PAGE_START);

        titulo.setVisible(true);
        barras = ChartFactory.createBarChart(title, "", "Valor", new DefaultCategoryDataset(),PlotOrientation.VERTICAL, true, true, true);
        ChartPanel chartPanel = new ChartPanel(barras);
        chartPanel.setPreferredSize(new Dimension(760, 400));
        add(chartPanel, BorderLayout.CENTER);
        setVisible(true);

    }
    Threads() {
    }
    public void graficar(int[] datos) {
        CategoryPlot plot = (CategoryPlot) barras.getPlot();
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int k = 0; k < datos.length; k++) {
            dataset.addValue(datos[k], String.valueOf(k), "Datos");
        }
        plot.setDataset(dataset);
    }
    @Override
    public void run() {
        Thread time_running = new Thread((Runnable) time);
        txt = 0;
        time_running.start();
        graficar(data1);
        steps = 0;
        
        try {
            int n = data1.length;
            arreglo11 = new int[n];
            arreglo2 = new int[n];
            int temp = 0;
            for (int i = 0; i < n; i++) {
                arreglo2[i] = data1[i];
                Object[] value = {arreglo2[i]};
                modelo2.addRow(value);
            }
            // BURBUJA
            for (int i = 0; i < (n - 1); i++) {
                for (int j = 0; j < (n - 1); j++) {
                    if (data1[j] > data1[j + 1]) {
                        temp = data1[j];
                        data1[j] = data1[j + 1];
                        data1[j + 1] = temp;
                        Thread.sleep(70);
                        graficar(data1);
                        steps++;
                        titulo.setText("pasos:" + steps);
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                arreglo11[i] = data1[i];
                Object[] values = {arreglo11[i]};
                modelo1.addRow(values);
            }
            int timecc = 0;
            txt = time.tiempo;
            time_running.interrupt();
            try {
                final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
            } catch (Exception ex) {
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

   

   
}
 