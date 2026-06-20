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

import javax.swing.border.LineBorder;


import Model.Publicacion;

import Model.Plan;
import Model.Usuario;

public class PostConcreto extends JFrame{
	private Plan pSeleccionado;
	    public PostConcreto(Publicacion publi, Usuario user) {
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
	        
	        
	      
	        JLabel titulo = new JLabel(publi.getTitulo());
	        JLabel fotoLabel = new JLabel(publi.getFoto());
	        Dimension d = new Dimension(300, 300);
	        fotoLabel.setPreferredSize(d);
	        fotoLabel.setBorder(new LineBorder(Color.BLACK));
	        
	     

	        
	        JLabel info = new JLabel(publi.getAhorro());
	        JLabel tecnica = new JLabel(publi.getTecnica());
	        JLabel costo = new JLabel(String.valueOf(publi.getCostos()));
	        JLabel materiales = new JLabel(publi.getMaquinaria());
	        JLabel periodo = new JLabel(publi.getPeriodo());
	        JLabel link = new JLabel(publi.getLink());

	        JButton seleccionar = new JButton("Selecionar");
	        seleccionar.addActionListener(new ActionListener() {
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		Plan pSeleccionado = new Plan(titulo.getText(), tecnica.getText(), 
	        				info.getText(), publi.getCostos(), link.getText(), periodo.getText());
	        		user.setPlan(pSeleccionado);
	        		
	        	}

	        });
	        
	        Insets espacio = new Insets(5, 5, 5, 5);




	        posicion.gridy = 0;
	        posicion.insets = espacio;
	        posicion.anchor = GridBagConstraints.WEST;
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
	        panel2.add(seleccionar, posicion);
	        
	        panelPrincipal.add(panel);
	        panelPrincipal.add(panel2);
	        
	        frame.getContentPane().setBackground(Color.WHITE);
	        frame.setResizable(false);
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        frame.add(panelPrincipal);
	        frame.setVisible(true);
	    }
}