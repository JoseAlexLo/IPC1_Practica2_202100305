/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PROGRAMA;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author iosea
 */
class Time extends JLabel implements Runnable {

    public int tiempo = 0;
    public Time() {
        setPreferredSize(new Dimension(760, 30));
        setText("0:00");
        setFont(new Font("Algerian", Font.BOLD, 30));
        setForeground(Color.BLACK);
        setVisible(true);
        setAlignmentX(JLabel.HEIGHT);
    }
    
    @Override
    public void run() {
        setForeground(Color.BLACK);
        try {
            int minutes = 0;
            while (true) {
                Threads OBJECT1 = new Threads();
                tiempo++;
                if (tiempo >= 60) {
                    tiempo = 0;
                    minutes++;
                    OBJECT1.time2++;
                }
                int seconds = tiempo;
                setText(String.format("%d:%02d", minutes, seconds));
                Thread.sleep(750);
            }
        } catch (InterruptedException e) {
        }
    }
}

