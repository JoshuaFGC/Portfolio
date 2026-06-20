package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Perfil {

    public Perfil() {
        JFrame frame = new JFrame("PERFIL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        
        //Se usan 3 paneles para acomodar y un cuarto para meter todo
        //Esto es porque si no quedaba peor
        JPanel panel = new JPanel(new GridBagLayout());
        JPanel panel2 = new JPanel(new GridBagLayout());
        JPanel panel3 = new JPanel(new GridBagLayout());
        
        JPanel principal = new JPanel(new GridBagLayout());
        GridBagConstraints posicion = new GridBagConstraints();

        
        
        //Para la foto de perfil
        JLabel foto = new JLabel();
        //Se dimensiona el label de una vez
        foto.setPreferredSize(new Dimension(200, 200));
        try {
        	File img = new File("C:/Users/Lenovo/Pictures/Screenshots/foto.png");
        	//Se toma la imagen
        	BufferedImage imagen = ImageIO.read(img);
        	//Se redimensiona
        	imagen = redimensionar(imagen, 200, 200);
        	
        	ImageIcon fotoPerfil = new ImageIcon(imagen);
        	//Se agrega al label
        	foto.setIcon(fotoPerfil);

        } catch (IOException e) {
        	e.printStackTrace();
        }
                
        
        
        JLabel usuarioLabel = new JLabel("Nombre - Apellido");
        JLabel rolLabel = new JLabel("Rol");
        JLabel especialidaYexpLabel = new JLabel("Especialidad - Experiencia");
        
       
        JButton crearObuscar = new JButton("CREAR/BUSCAR POST");

        Insets espacio = new Insets(10, 10, 10, 10);

        posicion.gridx = 0;
        posicion.gridy = 0;
        posicion.anchor = GridBagConstraints.WEST;
        panel.add(foto, posicion);

        posicion.gridx = 1;
        posicion.gridy = 0;
        posicion.insets = espacio;
        panel2.add(usuarioLabel, posicion);

        posicion.gridx = 1;
        posicion.gridy = 1;
        posicion.insets = espacio;
        panel2.add(rolLabel, posicion);

        posicion.gridx = 1;
        posicion.gridy = 2;
        posicion.insets = espacio;
        panel2.add(especialidaYexpLabel, posicion);

        posicion.gridx = 0;
        posicion.gridy = 4;
        posicion.insets = espacio;
        posicion.anchor = GridBagConstraints.EAST;
        panel3.add(crearObuscar, posicion);
        		
        
        
        GridBagConstraints constraints = new GridBagConstraints();
        
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
 
        principal.add(panel, constraints);
        
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.BOTH;
        principal.add(panel2, constraints);
        
        constraints.weightx = 0.0;
        constraints.gridy = 4;
        posicion.insets = espacio;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        principal.add(panel3, constraints);
        
        
        crearObuscar.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		
        	}
        });
        
        
        
        frame.getContentPane().setBackground(Color.WHITE);

        frame.add(principal);
       

        frame.setVisible(true);
    }

    //Funcion para redimensionar, recibe la imagen y el tamaño que se desea, seria el mismo que del label
    private BufferedImage redimensionar(BufferedImage imagen, int ancho, int alto) {
    	//Se crea un buffered con la dimension
        BufferedImage imagenRedimension = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        //Se carga la imagen en el buffered
        //recibe la imagen, las coordenadas donde va, ancho y alto 
        //del label y null porque solo se va a mostrar la imagen
        imagenRedimension.getGraphics().drawImage(imagen, 0, 0, ancho, alto, null);
        return imagenRedimension;
    }

}
