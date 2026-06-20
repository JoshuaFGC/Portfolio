package GUI;

import java.awt.Color; 

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Control.*;

public class InicioSesion extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Instancia para la funcion de los textArea
	private TextArea texto = new TextArea();
	private JLabel titulo = new JLabel("Inicio de sesion");
	
    private ControlSingIn control = new ControlSingIn();
    //Aca se llama a la funcion que me devuelve areas de texto pichuas
    //Se le manda el texto que va a tener y el cuantas rows y columnas
	private JTextArea usuario = texto.textoDelTextArea("Usuario", 1, 20);
	private JTextArea password = texto.textoDelTextArea("Contraseña", 1, 20);
    
	private boolean valBusqueda = false; //Para valirad si se encontro o no

    private JButton ingresar = new JButton("Ingresar");
    private JButton registrar = new JButton("Registrar");
	
    public InicioSesion(Base base) {
        JFrame frame = new JFrame("Inicio");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel(new GridBagLayout());
        //Para posicionar
        GridBagConstraints posicion = new GridBagConstraints();
        panel.setBackground(Color.lightGray);
        
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
        panel.add(ingresar, posicion);
        
        posicion.gridy++;
        panel.add(registrar, posicion);
        
        
        ingresar.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		
        		valBusqueda = control.verificarPerfil(usuario.getText(), password.getText(), base);
        		if (!valBusqueda) {
        			JOptionPane.showMessageDialog(frame, "Usuario no registrado");
        		}
        		
        	}
        });

        registrar.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {

                RegistroController control = new RegistroController(base);
        		Registro reg = new Registro(control);
        		reg.setVisible(true);

        	}

        });
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);
    }
    
    
    




}