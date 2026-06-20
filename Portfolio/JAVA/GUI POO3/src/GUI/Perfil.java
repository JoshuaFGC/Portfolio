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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import Control.Base;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.List;
import Model.*;

public class Perfil {
	
	private List<Contrato> listaContratos = new ArrayList<>();
	private JTextArea contratos = new JTextArea(10, 21);
	private int id = 1;
	private JLabel usuarioLabel = new JLabel();
	private Imagen ajusteImg = new Imagen();
	private Usuario user;
    
    public Perfil(Usuario usuario, Base base) {
    	
    	this.user = usuario;
    	
    	
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

        Border borde = BorderFactory.createLineBorder(Color.BLACK, 1);
        
        //Para la foto de perfil

        
        BufferedImage buffered = ajusteImg.resizeIcon(user.getFoto(), 200, 200);
        JLabel foto = new JLabel(new ImageIcon(buffered));

        
        JLabel nombre = new JLabel("Nombre");
        JLabel rol = new JLabel("Rol");
        JLabel exp = new JLabel("Experiencia");
        
        
        usuarioLabel.setText(user.getNombre());
        usuarioLabel.setBorder(borde);
       
        JLabel rolLabel = new JLabel(user.getTipo());
        rolLabel.setBorder(borde);
        
        JLabel experiencia = new JLabel(String.valueOf(user.getAñosExp()));
        experiencia.setBorder(borde);
        
        //Esto va a contener la informacion de los contratos
        contratos.setEditable(false);
        contratos.setFocusable(false);
        JScrollPane contratosPanel = new JScrollPane(contratos);
       
        JButton buscar = new JButton("BUSCAR POST");
        JButton crearContrato = new JButton("CREAR CONTRATO");
        

        Insets espacio = new Insets(10, 10, 10, 10);

        posicion.gridx = 0;
        posicion.gridy = 0;
        posicion.anchor = GridBagConstraints.WEST;
        panel.add(foto, posicion);
        

        
        
        posicion.gridx++;
        posicion.gridy = 0;
        panel2.add(nombre, posicion);
        
        
        posicion.gridy++;
        panel2.add(usuarioLabel, posicion);

        posicion.gridy++;
        panel2.add(rol, posicion);
        
        posicion.gridy++;
        posicion.insets = espacio;
        panel2.add(rolLabel, posicion);
        
        posicion.gridy++;
        panel2.add(exp, posicion);


        posicion.gridy++;
        panel2.add(experiencia, posicion);
        
        
        // Para presentar distintas opciones a cada tipo de usuario
        if (user.getTipo().equals("Representante")) {
        	
        	posicion.gridy = 1;
            posicion.insets = espacio;
            posicion.anchor = GridBagConstraints.WEST;
            panel.add(contratosPanel, posicion);
            
        	
            posicion.gridy++;
            panel3.add(buscar, posicion);
            
            posicion.gridy++;
            panel3.add(crearContrato, posicion);
            
            buscar.addActionListener(new ActionListener() {
            	@Override
            	public void actionPerformed(ActionEvent e) {
            		Navegador nav = new Navegador(user);
            		
            		List<Publicacion> publicaciones = base.getPublicaciones();
            		
            		nav.actualizar(publicaciones, user);
            		nav.setVisible(true);
            	}
            });
            
            
            crearContrato.addActionListener(new ActionListener() {
            	@Override
            	public void actionPerformed(ActionEvent e) {
            		abrirContratoVent();
            	}
            });
            
        } else {
        	JButton crear = new JButton("CREAR POST");
            

            posicion.gridx = 0;
            posicion.gridy = 4;
            posicion.anchor = GridBagConstraints.EAST;
            panel3.add(crear, posicion);
            
            crear.addActionListener(new ActionListener() {
            	@Override
            	public void actionPerformed(ActionEvent e) {
            		CrearPost post = new CrearPost(user, base);
            		post.setVisible(true);
            	}
            });
            
        	
        }
        
        
        
        
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
        
        
        
        
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(principal);
       

        frame.setVisible(true);
    }

    
    private void abrirContratoVent() {
    	//Aca es donde se abre contrato con el id del nuevo contrato y el usuario, hay que
    	//pasar como argumento el plan seleccionado y cambiar el constructor
    	CrearContrato contra = new CrearContrato(this, user, id);
    }
    
    public void addContrato(Contrato nContrato) {
    	listaContratos.add(nContrato);
    	id++;
    	actualizarTabla();
    }
    
    public void eliminarContrato(Contrato nContrato) {
    	listaContratos.remove(nContrato);
    	actualizarTabla();
    }
    
    private void actualizarTabla() {
    	StringBuilder sb = new StringBuilder();
    	contratos.removeAll();
    	for (Contrato contrato : listaContratos) {
    		//Agregar todos los campos del contrato
    		sb.append("ID").append(contrato.getId()).append("\n");
    		sb.append("URL").append(contrato.getUrl()).append("\n");
    		sb.append("\n");
    	}
    	contratos.setText(sb.toString());
    	contratos.revalidate();
    	contratos.repaint();
    	
    }

}