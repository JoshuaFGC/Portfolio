package Region;


import java.util.List;

import dispositivo.*;


public class region {

    private int id;
    private String nombre;
    private List<device> dispositivos;
    private List<region> listaRegiones;
    private double minBaro;
    private double maxBaro;
    private double minHigro;
    private double maxHigro;
    private double porcentaje;
    private double probabilidad;
    private static List<Double> datosHigrometros;
    private static List<Double> datosBarometros;

    public region(int pIdRegion, String pNombre, double pMinBaro, double pMaxBaro, double pMinHigro, double pMaxHigro, 
    		double pPorcentaje, double pProbabilidad, List<device> pDispositivos) {
        
    	
    	this.id = pIdRegion;
        this.nombre = pNombre;
        this.dispositivos = pDispositivos;
        this.maxBaro = pMaxBaro;
        this.minBaro = pMinBaro;
        this.minHigro = pMinHigro;
        this.maxHigro = pMaxHigro;
        this.porcentaje = pPorcentaje;
        this.probabilidad = pProbabilidad;
       
        
        for (device currentDevice:this.dispositivos) {
        	
        	if(currentDevice instanceof higrometro) {
        		
        		Thread deviceThread = new Thread(currentDevice);
            	deviceThread.start();
            	datosHigrometros = currentDevice.getData();
        		
        	}else {
        		
        		Thread deviceThread = new Thread(currentDevice);
	        	deviceThread.start();
	        	datosBarometros = currentDevice.getData();
	        	}
        	
        }
        
        
        
    }


    
    
    public List<region> getRegiones(){
    	regConstruct lista = new regConstruct();
    
    	listaRegiones = lista.getRegConstruct();
    	
		return listaRegiones;
    }
    
    public List<device> getDispositivos() {
        return dispositivos;
    }
    
    public List<Double> getDbaro(){
    	return datosBarometros;
    }
    
    public List<Double> getDhigro(){
    	return datosHigrometros;
    }
    
    public String getNombre() {
        return nombre;
    }

    public int getIdRegion() {
        return id;
    }
    
    public double getMinBaro() {
        return minBaro;
    }
    
    public double getMaxBaro() {
        return maxBaro;
    }
    
    public double getMinHigro() {
        return minHigro;
    }
    
    public double getMaxHigro() {
        return maxHigro;
    }
    
    public double getPorcentaje() {
        return porcentaje;
    }
    
    public double getProbabilidad() {
        return probabilidad;
    }
    

}



