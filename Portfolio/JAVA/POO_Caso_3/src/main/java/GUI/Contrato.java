package GUI;


import java.awt.Color; 
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Contrato {
	private TextArea texto = new TextArea();

    public Contrato() {
        JFrame frame = new JFrame("CREAR CONTRATO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints posicion = new GridBagConstraints();
        panel.setBackground(Color.lightGray);

        JTextArea crearArea = texto.textoDelTextArea("CREAR CONTRATO", 1, 20);
        JTextArea agriArea = texto.textoDelTextArea("N AGRICULTOR", 1, 20);
        JTextArea mediArea = texto.textoDelTextArea("N MEDIADOR", 1, 20);
        JTextArea ideaArea = texto.textoDelTextArea("N DEL DE LA IDEA", 1, 20);
        JTextArea contratoArea = texto.textoDelTextArea("TIPO CONTRATO", 1, 20);
        JTextArea fechasArea = texto.textoDelTextArea("INICIO-DURACIÓN ESTIMADA", 1, 20);
        JTextArea terminosArea = texto.textoDelTextArea("TÉRMINOS DEL CONTRATO", 10, 20);
        JTextArea firmaAgriArea = texto.textoDelTextArea("F Agricultor", 1, 20);
        JTextArea firmaMediArea = texto.textoDelTextArea("F MEDIADOR", 1, 20);
        JTextArea firmaIdeaArea = texto.textoDelTextArea("F DEL DE LA IDEA", 1, 20);

        JButton crear = new JButton("CREAR");
        JButton cancelar = new JButton("CANCELAR");

        Insets espacio = new Insets(10, 10, 10, 10); 

        posicion.gridx = 0;
        posicion.gridy = 0;
        posicion.insets = espacio;
        posicion.anchor = GridBagConstraints.CENTER;
        panel.add(crearArea, posicion);

        posicion.gridy++;
        panel.add(agriArea, posicion);

        posicion.gridy++;
        panel.add(mediArea, posicion);

        posicion.gridy++;
        panel.add(ideaArea, posicion);

        posicion.gridy++;
        panel.add(contratoArea, posicion);

        posicion.gridy++;
        posicion.anchor = GridBagConstraints.EAST;
        panel.add(fechasArea, posicion);

        posicion.gridy++;
        posicion.anchor = GridBagConstraints.CENTER;
        panel.add(terminosArea, posicion);

        posicion.gridy++;
        posicion.anchor = GridBagConstraints.WEST;
        panel.add(firmaAgriArea, posicion);

        posicion.gridy++;
        panel.add(firmaMediArea, posicion);

        posicion.gridy++;
        posicion.anchor = GridBagConstraints.EAST;
        panel.add(firmaIdeaArea, posicion);

        posicion.gridy++;
        posicion.anchor = GridBagConstraints.WEST;
        panel.add(crear, posicion);

        posicion.anchor = GridBagConstraints.EAST;
        panel.add(cancelar, posicion);
        
        crear.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		
        	}
        });
        
        cancelar.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		
        	}
        });
        
        
        frame.add(panel);
        frame.setVisible(true);
    }


}
