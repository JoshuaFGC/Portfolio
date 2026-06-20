package UI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import controllerInterfaz.dataShared;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ventCalib {
	
	private static dataShared shared;
	private static ventData ventana;
	private List<String> historiales;
	
	
    public ventCalib(dataShared pShared, ventData pVentana) {
    	this.shared = pShared;
    	this.historiales  = shared.getHistoriales();
    	this.ventana = pVentana;
    	
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Ventana de calibración");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints posicion = new GridBagConstraints();
            
            
            
            
            JTextArea historialesLabel = new JTextArea();
            
            historialesLabel.setBorder(new LineBorder(Color.BLACK));
            StringBuilder hist = new StringBuilder();
            for (String historial : historiales) {
            	hist.append(historial).append("\n");
            	
            }
            historialesLabel.setEditable(false);

            
            panel.add(historialesLabel);

            historialesLabel.setText(hist.toString());
            

            
            JTextField calibB1 = new JTextField(20);
            calibB1.setEditable(true);
            
            JTextField calibH1 = new JTextField(20);
            calibH1.setEditable(true);
            
            JTextField calibB2 = new JTextField(20);
            calibB1.setEditable(true);
            
            JTextField calibH2 = new JTextField(20);
            calibH1.setEditable(true);
            
            JLabel baro = new JLabel("Barómetro: ");
            JLabel higro = new JLabel("Higrómetro: ");
            
            JButton calibrar = new JButton("Calibrar");


            // Posicion de todo usando los ejes x y y, dando el ancho y la alineacion del texto a la izquierda
            posicion.gridx = 5;
            posicion.gridy = 0;
            //historiales.append("Historiales");
            panel.add(historialesLabel, posicion);



            posicion.gridx = 0;
            posicion.gridy = 1;
            panel.add(baro, posicion);
            
            posicion.gridx = 1;
            posicion.gridy = 1;
            panel.add(calibB1, posicion);

            posicion.gridx = 0;
            posicion.gridy = 3;
            panel.add(higro, posicion);
            
            posicion.gridx = 1;
            posicion.gridy = 2;
            panel.add(calibH1, posicion);
            
            posicion.gridx = 1;
            posicion.gridy = 3;
            panel.add(calibB2, posicion);
            
            posicion.gridx = 1;
            posicion.gridy = 4;
            panel.add(calibH2, posicion);
            
            posicion.gridx = 1;
            posicion.gridy = 5;
            posicion.gridwidth = 2;
            posicion.fill = GridBagConstraints.HORIZONTAL;
            panel.add(calibrar, posicion);   
            
            calibrar.addActionListener(new ActionListener() {
            	
                @Override
                
                public void actionPerformed(ActionEvent e) {
                	
            		
                    String minBaroText = calibB1.getText();
                    String maxBaroText = calibB2.getText();
                    String minHigroText = calibH1.getText();
                    String maxHigroText = calibH2.getText();
                    
                    double minBaro = Double.parseDouble(minBaroText);
                    double maxBaro = Double.parseDouble(maxBaroText);
                    double minHigro = Double.parseDouble(minHigroText);
                    double maxHigro = Double.parseDouble(maxHigroText);
                    
                    if (ventana != null) {
                    	
                    	String op = ventana.getOpcion();
                    	if (op != null) {
                    		shared.calibrarDispositivos(op, minBaro, maxBaro, minHigro, maxHigro);

                    	}
                    }
                    calibB1.setText("");
                    calibB2.setText("");
                    calibH1.setText("");
                    calibH2.setText("");
                }
            });

            

            
            
            frame.add(panel);
            frame.setSize(800, 800);
            frame.setVisible(true);
        });
    }
}