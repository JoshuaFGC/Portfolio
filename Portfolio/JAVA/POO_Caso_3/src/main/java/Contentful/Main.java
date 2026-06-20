package Contentful;
import com.contentful.java.cma.CMAClient;

import Model.Zona;

public class Main {
    public static void main(String[] args) {
    	ApiClient client = new ApiClient();
    	CMAClient cMaclient = client.getClient();
        
    	ZonaApiClient zonaApiClient = new ZonaApiClient(cMaclient);
        // Crear una instancia de Zona
        Zona zona = new Zona(10, "Zona A");
        zona.addCultivo("Maíz");
        zona.addCultivo("Trigo");
        
        // Enviar la Zona a Contentful
        zonaApiClient.createEntry(zona);;
    }
}