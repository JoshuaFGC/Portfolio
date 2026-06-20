package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.table.DefaultTableModel;

import controllerInterfaz.dataShared;
import dispositivo.device;

import java.util.ArrayList;
import java.util.List;

public class ventData {
	
	private static DefaultTableModel tabData;
	private JTable data;

	private Timer timer;
	private static dataShared shared;
	private static String opcion;
	private JPanel alertCircle;
	private JButton recalibrar;

    public ventData(dataShared shared) {
    	this.shared = shared;
    	tabData = new DefaultTableModel();
        tabData.addColumn("Barómetro");
        tabData.addColumn("Higrómetro");

        data = new JTable(tabData);
        
        data.setFillsViewportHeight(true);
        
        JScrollPane panelScroll = new JScrollPane(data);
        
        data.getColumnModel().getColumn(0).setPreferredWidth(300);
        data.getColumnModel().getColumn(1).setPreferredWidth(300);
        SwingUtilities.invokeLater(() -> {
        	
            JFrame frame = new JFrame("Control de datos");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints posicion = new GridBagConstraints();	
            
            
            
            
            List<String> nomb = shared.getNombres();
            String[] nombres = nomb.toArray(new String[0]);
            
            
            
            posicion.gridx = 0;
            posicion.gridy = 0;
            panel.add(panelScroll, posicion); 
            
            JLabel alerta = new JLabel("Alerta: ");
            
            alertCircle= new JPanel();
            alertCircle.setPreferredSize(new Dimension(30, 30));
            alertCircle.setBackground(Color.GREEN); //Esta es la alerta que cambiará a rojo

            
                        
            JLabel fecha = new JLabel("Fecha");//Tomaran la fecha y hora con funciones especificas
            JLabel hora = new JLabel("Hora");
            
            //Panel para tener un mejor orden en la interfaz
            JPanel panelB = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            panelB.add(alerta);
            panelB.add(alertCircle);
            
            panelB.add(fecha);
            panelB.add(hora);
            
            //Lista de regiones
            JComboBox<String> regiones = new JComboBox<>(nombres);
     

            
            //Lista regiones
            posicion.gridx = 1;
            posicion.gridy = 0;
            panel.add(regiones, posicion);
            
            regiones.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		opcion = (String) regiones.getSelectedItem();
            		shared.actualizarTabla();
            	
            	}
            });
            
            //Panel de botones y alerta
            posicion.gridx = 0;
            posicion.gridy = 1;
            posicion.gridwidth = 2;
            panel.add(panelB, posicion);

            
            frame.add(panel);
            frame.setSize(800, 800);
            frame.setVisible(true);
        });
    }
    
    
    public String getOpcion() {
    	return opcion;
    }
    
    public void threadTabla() {
    	timer = new Timer();
    	int delay = 1000;
    	timer.scheduleAtFixedRate(new TimerTask(){
    		@Override
    		public void run() {
    			shared.actualizarTabla();
    		}
    	}, 0, delay);
    }
    
    
    public void detener() {
    	if (timer != null){
    		timer.cancel();
    	}
    }
    
    
    public void actualizarTabla(List<Double> higro, List<Double> baro) {
    	DefaultTableModel model = (DefaultTableModel) data.getModel();

    	SwingUtilities.invokeLater(() -> {
            
            // Eliminar todas las filas existentes
            model.setRowCount(0);

            // Agregar filas para datos de barómetro y higrómetro en el orden correcto
            for (int i = 0; i < Math.max(higro.size(), baro.size()); i++) {
                Object[] rowData = { (i < baro.size()) ? baro.get(i) : "", (i < higro.size()) ? higro.get(i) : "" };
                model.addRow(rowData);
                
               
            }
        });
        int columna = 0;
        int fila = model.getRowCount()-1;
        if (fila>=0) {
	        Object datoF = model.getValueAt(fila, columna);
	        shared.comparar(datoF);
        }
    }

    public void cambiarColorAlerta(Color color) {
        alertCircle.setBackground(color);
    }

}