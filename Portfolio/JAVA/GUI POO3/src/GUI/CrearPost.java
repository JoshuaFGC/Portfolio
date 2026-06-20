package GUI;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import Model.Publicacion;
import Model.Usuario;
import Model.ValidacionDato;
import Model.Imagen;
import javax.swing.border.LineBorder;

import Control.Base;



public class CrearPost extends JFrame {
	
	private TextArea texto = new TextArea();

	private ValidacionDato validacion = new ValidacionDato();
	private List<Publicacion> publicaciones = new ArrayList<>();
	
    public CrearPost(Usuario user, Base base) {
        JFrame frame = new JFrame("CREAR POST");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 900);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints posicion = new GridBagConstraints();
        panel.setBackground(Color.lightGray);
        
        JPanel panel2 = new JPanel(new GridBagLayout());
        panel2.setBackground(Color.lightGray);
        
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBackground(Color.lightGray);
        
        
        JLabel crearPost = new JLabel("CREAR POST");
        JLabel tituloLabel = new JLabel("TITULO");
        JLabel fotoLabel = new JLabel();
        Dimension d = new Dimension(300, 300);
        fotoLabel.setPreferredSize(d);
        fotoLabel.setIcon(new ImageIcon("C:\\Users\\Lenovo\\Documents\\TEC-RETO3-POO\\src\\main\\agricultura-e1551193452226.jpg"));
        
     

        JTextArea titulo = texto.textoDelTextArea("Ingrese titulo del post", 1, 20);
        JTextArea info = texto.textoDelTextArea("Cantidad de material ahorrado por hectarea"
        + " Tamaño estimado de la finca", 10, 50);
        JTextArea costo = texto.textoDelTextArea("Costo del sistema utilizado y costo total", 1, 5);
        JTextArea materiales = texto.textoDelTextArea("Materiales o maquinaria a utilizar", 5, 10);
        JTextArea periodo = texto.textoDelTextArea("Periodo de la implementacion de la idea", 1, 5);
        JTextArea link = texto.textoDelTextArea("LINK...", 1, 5);
        JTextArea tecnica = texto.textoDelTextArea("Tecnica", 1, 5);
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
        panel.add(fotoLabel, posicion);
        
        

        

        posicion.gridy++;
        panel.add(link, posicion);

        posicion.gridy = 1;
        panel2.add(info, posicion);
        
        posicion.gridy++;
        panel2.add(tecnica, posicion);
        
        posicion.gridy++;
        panel2.add(costo, posicion);
        
        posicion.gridy++;
        panel2.add(materiales, posicion);
        
        posicion.gridy++;
        panel2.add(periodo, posicion);
        
        posicion.gridy++;
        panel2.add(publicar, posicion);
        
        
        publicar.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		
        		boolean condicionesCumplidas = true;
        		
        		if (!validacion.noEstaVacio(titulo)) {
        			mostrarAdvertencia("El campo 'Titulo' no puede estar vacío.");
        			condicionesCumplidas = false;
        		}
        		
        		if (!validacion.noEstaVacio(link)) {
        			mostrarAdvertencia("El campo 'LINK...' no puede estar vacío.");
        			condicionesCumplidas = false;
        		}
        		
        		if (!validacion.noEstaVacio(info)) {
        			mostrarAdvertencia("El campo de la informacion no puede estar vacío.");
        			condicionesCumplidas = false;
        		}
        		
        		if (!validacion.noEstaVacio(tecnica)) {
        			mostrarAdvertencia("El campo 'Tecnica' no puede estar vacío.");
        			condicionesCumplidas = false;
        		}
        		
        		if (!validacion.noEstaVacio(materiales)) {
        			mostrarAdvertencia("El campo de los materiales no puede estar vacío.");
        			condicionesCumplidas = false;
        		}
        		
        		if (!validacion.noEstaVacio(periodo)) {
        			mostrarAdvertencia("El campo 'Periodo' no puede estar vacío.");
        			condicionesCumplidas = false;
        		}
        		
        		if (!validacion.esNumeroEntero(costo)){
        			JOptionPane.showMessageDialog(frame, "El costo debe ser un numero");	
        		}
        		
        		if (condicionesCumplidas){
        			int intCostos = Integer.parseInt(costo.getText());
        			Publicacion publi = new Publicacion(titulo.getText(), fotoLabel.getIcon(), link.getText(),
            				info.getText(), tecnica.getText(), intCostos, materiales.getText(), periodo.getText());
     

            		base.addPubli(publi);
            		
            		
            		
            		JOptionPane.showMessageDialog(frame, "Agregada con exito");
        		}
        		
        		
        	}
        });
        
       
        
        panelPrincipal.add(panel);
        panelPrincipal.add(panel2);
        
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panelPrincipal);
        frame.setVisible(true);
    }
    
    
    private void mostrarAdvertencia(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
    }
    
}
