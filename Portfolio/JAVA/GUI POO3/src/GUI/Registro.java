package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import Model.Imagen;
import Model.ValidacionDato;
import Control.*;  

public class Registro extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Instancia para la funcion de los textArea
	private TextArea texto = new TextArea();
	private JLabel titulo = new JLabel("REGISTRO");
	private ValidacionDato validacion = new ValidacionDato();
    //Aca se llama a la funcion que me devuelve areas de texto pichuas
    //Se le manda el texto que va a tener y el cuantas rows y columnas
	private JTextArea nombre = texto.textoDelTextArea("Nombre completo", 1, 20);
	private JLabel rol = new JLabel("ROL");
	private JTextArea usuario = texto.textoDelTextArea("Usuario", 1, 20);
	private JTextArea correo = texto.textoDelTextArea("example@gmail.com", 1, 20);
	private JTextArea ans = texto.textoDelTextArea("Años de expeciencia", 1, 20);
	private JTextArea password = texto.textoDelTextArea("Contraseña", 1, 20);
	private JTextArea passwordV = texto.textoDelTextArea("Confirmar contraseña", 1, 20);
	private JLabel fotoLabel = new JLabel();

    private JButton registrar = new JButton("REGISTRAR");
	
    public Registro(RegistroController control) {

        JFrame frame = new JFrame("REGISTRO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        JPanel panel = new JPanel(new GridBagLayout());
        //Para posicionar
        GridBagConstraints posicion = new GridBagConstraints();
        panel.setBackground(Color.lightGray);
        
        String[] opciones = {"Ingeniero", "Representante"};
        JComboBox<String> roles = new JComboBox<>(opciones);
        
        String rutaImagen = "C:\\Users\\Lenovo\\eclipse-workspace\\GUI POO3\\src\\main\\1006543.png"; // Cambia esta ruta por la de tu imagen

        // Verificar si el archivo de imagen existe
        File archivoImagen = new File(rutaImagen);
        if (!archivoImagen.exists()) {
            System.out.println("El archivo de imagen no existe en la ruta especificada.");
            return;
        }

        // Crear un objeto ImageIcon desde el archivo de imagen
        ImageIcon icono = new ImageIcon(rutaImagen);
        
        fotoLabel.setIcon(icono);        
        //Espacios entre las cosas
        //Arriba, izq, abajo, der
        Insets espacio = new Insets(5, 5, 5, 5);


        posicion.gridy = 0;
        panel.add(titulo, posicion);
        
        posicion.gridy++;
        posicion.insets = espacio;
        panel.add(nombre, posicion);
        
        posicion.gridy++;
        posicion.insets = espacio;
        panel.add(rol, posicion);
        
        posicion.gridy++;
        posicion.insets = espacio;
        panel.add(roles, posicion);
        
        posicion.gridy++;
        posicion.insets = espacio;
        panel.add(usuario, posicion);
        
        posicion.gridy++;
        posicion.insets = espacio;
        panel.add(correo, posicion);
        
        posicion.gridy++;
        posicion.insets = espacio;
        panel.add(ans, posicion);

        posicion.gridy++;
        panel.add(password, posicion);
        
        posicion.gridy++;
        panel.add(passwordV, posicion);
        
        posicion.gridy++;
        panel.add(fotoLabel, posicion);
        
        
        
        posicion.gridy++;
        panel.add(registrar, posicion);
        
        
        
        registrar.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		boolean condicionesCumplidas = true;
        		
        		if (!validacion.noEstaVacio(nombre)) {
        			mostrarAdvertencia("El campo 'Nombre completo' no puede estar vacío.");
        			condicionesCumplidas = false;
        		}
        			
        		if (!validacion.noEstaVacio(usuario)) {
        			mostrarAdvertencia("El campo 'Usuario' no puede estar vacío.");
        			condicionesCumplidas = false;
        		}
        		
        		if (!control.getDatos().getPerfiles().isEmpty()) {
	        		boolean existe = control.verificarPerfil(usuario.getText());
	        		if (existe==true) {
	        			mostrarAdvertencia("El nombre de usuario ya está ocupado.");
	        			condicionesCumplidas = false;
	        		}
        		}
        		
        		if (!validacion.noEstaVacio(correo)) {
        			mostrarAdvertencia("El campo 'Correo' no puede estar vacío.");
        			 condicionesCumplidas = false;
        		}
        		if (!validacion.noEstaVacio(ans)||!validacion.esNumeroEntero(ans)) {
        			mostrarAdvertencia("El campo 'Años de experiencia' no puede estar vacío. Además debe ser un número");
        			 condicionesCumplidas = false;
        		}
        		if (!validacion.noEstaVacio(nombre)) {
        			mostrarAdvertencia("El campo 'Contraseña' no puede estar vacío.");
        			 condicionesCumplidas = false;
        		}
        		if (!validacion.noEstaVacio(nombre)) {
        			mostrarAdvertencia("El campo 'Confirmar Contraseña' no puede estar vacío.");
        			condicionesCumplidas = false;
        		}
        		if (password.getText().length() < 8 || !password.getText().equals(passwordV.getText())) {
        			mostrarAdvertencia("Las contraseñas no coinciden o no cumplen con el requisito de tener mas de 8 caracteres.");
        			condicionesCumplidas = false;
        		}
        		
        		if (!validacion.esNumeroEntero(ans)) {
        			mostrarAdvertencia("La experiencia debe ser un numero.");
        			condicionesCumplidas = false;
        		}
        		
        		if (condicionesCumplidas) {
        			System.out.println("Perfil creado");
                    // Llamar a la función crearPerfil con los datos proporcionados
        			int years = Integer.parseInt(ans.getText());
                    control.crearPerfil(
                        nombre.getText(),
                        rol.getText(),
                        usuario.getText(),
                        correo.getText(),
                        years,
                        password.getText(),
                        fotoLabel.getIcon()
                    );    
                }
        	}

        });
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);
    }
    
    
    private void mostrarAdvertencia(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
    }




}