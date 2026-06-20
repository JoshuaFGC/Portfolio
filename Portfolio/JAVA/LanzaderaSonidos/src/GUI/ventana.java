package GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ventana {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Lanzadera de sonidos");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel();

            JButton button1 = new JButton("Botón 1");
            JButton button2 = new JButton("Botón 2");
            JButton button3 = new JButton("Botón 3");
            JButton button4 = new JButton("Botón 4");
            JButton button5 = new JButton("Botón 5");
            JButton button6 = new JButton("Botón 6");

            sonidos reproductor = new sonidos();

            button1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    reproductor.sonido("S6.wav.wav");
                }
            });

            button2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    reproductor.sonido("S5.wav.wav");
                }
            });

            button3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    reproductor.sonido("S4.wav.wav");
                }
            });

            button4.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    reproductor.sonido("S4.wav.wav");
                }
            });

            button5.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    reproductor.sonido("S5.wav.wav");
                }
            });

            button6.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    reproductor.sonido("S6.wav.wav");
                }
            });

            panel.add(button1);
            panel.add(button2);
            panel.add(button3);
            panel.add(button4);
            panel.add(button5);
            panel.add(button6);

            frame.add(panel);
            frame.setSize(400, 200);
            frame.setVisible(true);
        });
    }
}



