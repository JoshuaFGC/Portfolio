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


public class Registro {
	
	//Instancia para la funcion de los textArea
	private TextArea texto = new TextArea();
	
    public Registro() {
        JFrame frame = new JFrame("REGISTRO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel(new GridBagLayout());
        //Para posicionar
        GridBagConstraints posicion = new GridBagConstraints();
        panel.setBackground(Color.lightGray);
        
        
        
        JLabel titulo = new JLabel("REGISTRO");
        
        //Aca se llama a la funcion que me devuelve areas de texto pichuas
        //Se le manda el texto que va a tener y el cuantas rows y columnas
        JTextArea usuario = texto.textoDelTextArea("Usuario", 1, 20);
        JTextArea password = texto.textoDelTextArea("Contraseña", 1, 20);
        JTextArea passwordV = texto.textoDelTextArea("Confirmar contraseña", 1, 20);

        JButton registrar = new JButton("REGISTRAR");
        
        //Espacios entre las cosas
        //Arriba, izq, abajo, der
        Insets espacio = new Insets(5, 5, 5, 5);


        posicion.gridy = 0;
        panel.add(titulo, posicion);

        posicion.gridy++;
        posicion.insets = espacio;
        panel.add(usuario, posicion);

        posicion.gridy++;
        panel.add(password, posicion);
        
        posicion.gridy++;
        panel.add(passwordV, posicion);

        posicion.gridy++;
        panel.add(registrar, posicion);

        registrar.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		//aca deberia guardar el perfil y abrir la ventana de usuario

        	}

        });

        frame.add(panel);
        frame.setVisible(true);
    }
    
    
    



}
