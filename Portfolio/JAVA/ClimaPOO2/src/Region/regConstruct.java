package Region;

import configJson.jsonParser;
import dispositivo.*;
import javax.json.JsonArray;
import javax.json.JsonObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class regConstruct {
	private static List<region> regiones;
	private static boolean serializado=false;
	
	public static List<region> crearRegiones() {
		if (!serializado){
			jsonParser parser = new jsonParser();
		    parser.parsearJson();
		    JsonArray jsonRegiones = parser.getJsonRegiones();
	
		    
		    regiones = new ArrayList<region>();
		    int id = 0;
		    for (JsonObject objeto : jsonRegiones.getValuesAs(JsonObject.class)) {
		    	id += 1;
		        String nombre = objeto.getString("nombre");
		        String nombreRegion = objeto.getString("nombre");
		        double minBaro = objeto.getJsonNumber("minBaro").doubleValue();
		        double maxBaro = objeto.getJsonNumber("maxBaro").doubleValue();
		        double minHigro = objeto.getJsonNumber("minHigro").doubleValue();
		        double maxHigro = objeto.getJsonNumber("maxHigro").doubleValue();
		        double porcentaje = objeto.getJsonNumber("porcentaje").doubleValue();
		        double probabilidad = objeto.getJsonNumber("probabilidad").doubleValue();
		        
		        // Crear y configurar el barometro
		        barometro baro = crearBarometro(nombreRegion, minBaro, maxBaro, probabilidad, porcentaje);
		        
		        
	
		        // Crear y configurar el higrometro
		        higrometro higro = crearHigrometro(nombreRegion, minHigro, maxHigro, probabilidad, porcentaje);
		        
		        regiones.add(new region(id, nombre, minBaro, maxBaro, minHigro, maxHigro, porcentaje, 
		        		probabilidad, Arrays.asList(higro, baro)));
		    }
		    try {
		    	System.out.println("Serializando");
		    	ObjectOutputStream datos = new ObjectOutputStream(new FileOutputStream("regiones.ser"));
		    	datos.writeObject(regiones);
		    	datos.close();
		    	serializado = true;
		    	ObjectOutputStream bool = new ObjectOutputStream(new FileOutputStream("validacion.ser"));
		    	bool.writeObject(serializado);
		    	bool.close();
		    	
		    }catch(IOException e){
		    	System.out.println("No se pudo serializar");
		    }
		    	    
		}else {
			try{
				System.out.println("deserializando");
				ObjectInputStream datosS = new ObjectInputStream(new FileInputStream("regiones.ser"));
				regiones = (List<region>) datosS.readObject();
				datosS.close();
				
				
			}catch(IOException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e2) {
				
				e2.printStackTrace();
			}
		}
		return regiones;
	}
	
	public List<region> getRegConstruct(){
		crearRegiones(); 
		return regiones;
	}

	private static barometro crearBarometro(String nombreRegion, double min, double max, 
			double probabilidad, double porcentaje) {
		return new barometro("Barometro de " + nombreRegion, min, max, probabilidad, porcentaje);
	}

	private static higrometro crearHigrometro(String nombreRegion, double min, double max, 
			double probabilidad, double porcentaje) {
		return new higrometro("Higrometro de " + nombreRegion, min, max, probabilidad, porcentaje);
	}
}