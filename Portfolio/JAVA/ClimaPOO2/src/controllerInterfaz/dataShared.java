package controllerInterfaz;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


import dispositivo.*;
import Region.regConstruct;
import Region.region;
import UI.*;
import dispositivo.device;

public class dataShared{
    private List<Double> datosHigro;
    private List<Double> datosBaro;
    private static ventData ventana;
    private static List<String> nombres = new ArrayList<>();
	private static List<region> regionesMain;
	private static List<String> historiales = new ArrayList<>();
	private static List<String> historial = new ArrayList<>();
	private double minBaro;
	private double maxBaro;
	private double minHigro;
	private double maxHigro;

	

	
    
    public void crearVentana() {
    	
    	regConstruct reg = new regConstruct();
		regionesMain = reg.getRegConstruct();

		//Se cargan los nombres
		for (region current : regionesMain) {
			
			String nomb2 = current.getNombre();
			nombres.add(nomb2);
		}
		
    	ventana = new ventData(this);
    	ventana.threadTabla();
    }
    
    public void actualizarTabla() {
    	
    	String op = ventana.getOpcion();
    
    	
    	for (region currentRegion:regionesMain) {
    		
			String nomb = currentRegion.getNombre();
			
			
			
			if (nomb.equals(op)) {
				
				System.out.println(op);			
				datosHigro = currentRegion.getDhigro();
	    		datosBaro = currentRegion.getDbaro();
	    		ventana.actualizarTabla(datosHigro, datosBaro);
	    		
			}

        }
	
    	
    }
    
	public List<String> getNombres(){

			return nombres;
		}

	public void calibrarDispositivos(String opcion, double pMinBaro, double pMaxBaro, double pMinHigro, double pMaxHigro) {
		minBaro = pMinBaro;
		maxBaro = pMaxBaro;
		minHigro = pMinHigro;
		maxHigro = pMaxBaro;
		
		
		for (region currentRegion : regionesMain) {
			
	        if (currentRegion.getNombre().equals(opcion)) {
	        	
	            // Aplica la calibración a los dispositivos de la región
	            List<device> dispositivos = currentRegion.getDispositivos();
	            for (device dispositivo : dispositivos) {
	            	
	                if (dispositivo instanceof barometro) {
	                    dispositivo.calibrar(minBaro, maxBaro); // Calibración para el barómetro
	                } else if (dispositivo instanceof higrometro) {
	                    dispositivo.calibrar(minHigro, maxHigro); // Calibración para el higrómetro
	                }
	            }
	            
	            // Realiza la validación de rangos y cambia el color y estado del cuadro de alerta si es necesario
	            boolean validacionExitosa = validarRangos(currentRegion);
	            if (validacionExitosa) {
	                // Cambia el color del cuadro de alerta a verde
	                // Activa el botón de recalibración si es necesario
	                ventana.cambiarColorAlerta(Color.GREEN);

	            } else {
	                // Cambia el color del cuadro de alerta a rojo
	                // Activa el botón de recalibración si es necesario
	                ventana.cambiarColorAlerta(Color.RED);

	            }
	        }
	    }
	}

	private boolean validarRangos(region currentRegion) {
	    List<device> dispositivos = currentRegion.getDispositivos();
	    
	    for (device dispositivo : dispositivos) {
	        if (dispositivo instanceof barometro) {
	            barometro barometro = (barometro) dispositivo;
	            double minActualBaro = barometro.getMin();
	            double maxActualBaro = barometro.getMax();
	            if (minActualBaro < minBaro || maxActualBaro > maxBaro) {
	                return false; // La validación falla si los rangos no están dentro de los límites
	            }
	        } else if (dispositivo instanceof higrometro) {
	            higrometro higrometro = (higrometro) dispositivo;
	            double minActualHigro = higrometro.getMin();
	            double maxActualHigro = higrometro.getMax();
	            if (minActualHigro < minHigro || maxActualHigro > maxHigro) {
	                return false; // La validación falla si los rangos no están dentro de los límites
	            }
	        }
	    }
	    
	    return true; // La validación es exitosa si todos los dispositivos están dentro de los límites
	}

	
	
	public void crearVentCalib() {
		
		
		for( region reg : regionesMain) {
			historial = new ArrayList<>();
			String name = reg.getNombre();
			double minBarometro = reg.getMinBaro();
			String sMinBaro = String.valueOf(minBarometro);
			
			double maxBarometro = reg.getMaxBaro();
			String sMaxBaro = String.valueOf(maxBarometro);
			
			double minHigrometro = reg.getMinHigro();
			String sMinHigro = String.valueOf(minHigrometro);
			
			double maxHigrometro = reg.getMaxHigro();
			String sMaxHigro = String.valueOf(maxHigrometro);

			historial.add("Region: " + name);
			historial.add("MinBaro: " + sMinBaro);
			historial.add("MaxBaro: " + sMaxBaro);
			historial.add("MinHigro: " + sMinHigro);
			historial.add("MaxHigro: " + sMaxHigro);
			historial.add("------------------------");
			
			historiales.addAll(historial);
		}
		
		ventCalib ventana2 = new ventCalib(this, ventana);
	}
	
	public List<String> getHistoriales(){
		return historiales;
	}

	public void comparar(Object pDatoF) {
		String op = ventana.getOpcion();
		for (region currentRegion:regionesMain) {
    		
			String nomb = currentRegion.getNombre();
			
			
			
			if (nomb.equals(op)) {
				double datoF = (Double) pDatoF;
				if(datoF<currentRegion.getMinBaro() || datoF>currentRegion.getMaxBaro()) {

					
					ventana.cambiarColorAlerta(Color.RED);
				}
	    		
			}

        }

		
	}
}
