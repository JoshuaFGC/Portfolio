package GUI;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class CrearPost {
	private TextArea texto = new TextArea();
	
    public CrearPost() {
        JFrame frame = new JFrame("CREAR POST");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints posicion = new GridBagConstraints();
        panel.setBackground(Color.lightGray);
        
        
        JLabel crearPost = new JLabel("CREAR POST");
        JLabel tituloLabel = new JLabel("TITULO");
        JLabel descripcionLabel = new JLabel("DESCRIPCION");

        JTextArea titulo = texto.textoDelTextArea("Ingrese titulo del post", 1, 30);
        JTextArea descripcion = texto.textoDelTextArea("Descripcion del post, como datos importantes"
        		+ ", precio, proyeccion de ahorro, participantes y reduccion de gastos esperara  ", 10, 50);
        
        JButton publicar = new JButton("PUBLICAR");

        Insets espacio = new Insets(5, 5, 5, 5);


        posicion.gridy = 0;
        panel.add(crearPost, posicion);

        posicion.gridy++;
        posicion.insets = espacio;
        posicion.anchor = GridBagConstraints.WEST;
        panel.add(tituloLabel, posicion);
        

        posicion.gridy++;
        panel.add(titulo, posicion);



        posicion.gridy++;
        posicion.anchor = GridBagConstraints.WEST;
        panel.add(descripcionLabel, posicion);

        posicion.gridy++;
        panel.add(descripcion, posicion);


        posicion.gridy++;
        panel.add(publicar, posicion);
        
        
        publicar.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		
        	}
        });
        
        frame.getContentPane().setBackground(Color.WHITE);

        frame.add(panel);
        frame.setVisible(true);
    }
    
    

}
