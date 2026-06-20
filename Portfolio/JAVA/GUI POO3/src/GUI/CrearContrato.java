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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerDateModel;

import java.time.LocalDate;
import java.util.Date;

import Model.Contrato;
import Model.Plan;
import Model.Usuario;
import Model.ValidacionDato;

public class CrearContrato extends JFrame{
	private TextArea texto = new TextArea();
	private JLabel nContrato = new JLabel();
	private ValidacionDato validacion = new ValidacionDato();
	
	private Contrato data = new Contrato();

    public CrearContrato(Perfil perfilAct, Usuario user, int id) {
    	
    	
    	Plan planS = user.getPlan();
    	
    	LocalDate fecha = LocalDate.now();
        JFrame frame = new JFrame("CREAR CONTRATO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints posicion = new GridBagConstraints();
        panel.setBackground(Color.lightGray);

        nContrato.setText("Contrato# " + String.valueOf(id));
        
        JLabel fechaLabel = new JLabel("Fecha: " + String.valueOf(fecha));
        JLabel mediadorLabel = new JLabel("Representante: " + user.getNombre());
        
        JLabel plan = new JLabel("Plan: " + planS.getNombre());
        
        int costo = (int) (planS.getCosto()*0.05);
        
        JLabel costosTotales = new JLabel("Costo total: " + String.valueOf(costo) + " dolares.");
        
        JTextArea agriArea = texto.textoDelTextArea("Nombre del agricultor: ", 1, 20);

        JLabel duracion = new JLabel("Duracion del contrato: " + planS.getDuracion());
        JLabel urlTerminos = new JLabel("Link: " + planS.getUrl());

        //Esta fue la mejor forma que encontre, para no tener que usar un comboBOx
        JSpinner time = new JSpinner( new SpinnerDateModel() );
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(time, "HH:mm:ss");
        time.setEditor(timeEditor);
        time.setValue(new Date()); 
        
        
        JButton crear = new JButton("CREAR");
        JButton cancelar = new JButton("CANCELAR");

        Insets espacio = new Insets(10, 10, 10, 10);
        
        posicion.gridx = 0;
        posicion.gridy = 0;
        posicion.insets = espacio;
        posicion.anchor = GridBagConstraints.CENTER;
        panel.add(nContrato, posicion);
        
        posicion.gridy++;
        panel.add(fechaLabel, posicion);
        
        posicion.gridy++;
        panel.add(agriArea, posicion);

        posicion.gridy++;
        panel.add(mediadorLabel, posicion);
        
        posicion.gridy++;
        panel.add(plan, posicion);

        posicion.gridy++;
        posicion.anchor = GridBagConstraints.EAST;
        panel.add(time, posicion);
        
        posicion.gridy++;
        posicion.anchor = GridBagConstraints.CENTER;
        panel.add(duracion, posicion);

        posicion.gridy++;
        posicion.anchor = GridBagConstraints.CENTER;
        panel.add(urlTerminos, posicion);

        posicion.gridy++;
        panel.add(costosTotales, posicion);
        
        posicion.gridy++;
        posicion.anchor = GridBagConstraints.WEST;
        panel.add(crear, posicion);

        posicion.anchor = GridBagConstraints.EAST;
        panel.add(cancelar, posicion);
        
        Date fechaSelect = (Date) time.getValue();
        
        crear.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		//Se guarda todo el contrato en una instancia contrato
        		boolean condicionesCumplidas = true;
        		
        		if (!validacion.noEstaVacio(agriArea)) {
        			mostrarAdvertencia("El campo 'Nombre del agricultor' no puede estar vacío.");
        			condicionesCumplidas = false;
        		}
        		
        		if (condicionesCumplidas) {
        			data.setId(id);
            		data.setFecha(fecha);
                    data.setNombre(agriArea.getText());
                    data.setHora(fechaSelect);
                    data.setDuracion(duracion.getText());
                    data.setUrl(urlTerminos.getText());
                    
                    try {
                    	int costos = Integer.parseInt(costosTotales.getText());
                    	data.setCostos(costos);
                    	
                    } catch(NumberFormatException y) {
                    	
                    }
                    
                    perfilAct.addContrato(data);//Actualizar la tabla de contratos
        		}
        		
        	}
        });
        
        cancelar.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
                agriArea.setText("");
                duracion.setText("");
                urlTerminos.setText("");
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