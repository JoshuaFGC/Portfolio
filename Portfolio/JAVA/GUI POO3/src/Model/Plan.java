package Model;

import java.util.List; 

public class Plan {
    private String nombre;
    private String tecnica;
    private String ahorro;
    private int costo;
    private String url;
    private String duracion;
    

    public Plan(String pNombre, String pTecnica, String pAhorro, int pCosto, String pUrl
    		, String pDuracion) {
        this.nombre = pNombre;
        this.tecnica = pTecnica;   
        this.ahorro = pAhorro;
        this.costo = pCosto;
        this.url = pUrl;
        this.duracion = pDuracion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTecnica() {
        return tecnica;
    }
    
    public String getAhorro() {
        return ahorro;
    }
    
    public int getCosto() {
    	return costo;
    }
      
    public String getUrl() {
    	return url;
    }
    
    public String getDuracion() {
    	return duracion;
    }
      
}