package Contentful;

import com.contentful.java.cma.CMAClient;
import com.contentful.java.cma.model.*;
import Model.*;
public class ZonaApiClient {
	private CMAClient cMaclient;
	
	public ZonaApiClient(CMAClient client) {
		this.cMaclient = client;
	}
    

	// Método para crear una entrada en Contentful
	public void createEntry(Zona zona) {
	    // Crea un local CMAEntry.
	    final CMAEntry entry = new CMAEntry()
	            .setId("zona")  // Cambia esto según tu lógica
	            .setField("name", "en-US", "zona.getNombre()")
	            .setField("cantAgricultores", "en-US", zona.getCantAgricultores())  
	            .setField("cultivos", "en-US", zona.getCultivos());

	    // Create the same entry in Contentful.
	    final CMAEntry createdEntry = cMaclient
	            .entries()
	            .create("zona", entry);

	    // Imprime el ID de la nueva entrada
	    System.out.println("Nueva entrada creada con ID: " + createdEntry.getId());
	}

    // Método para obtener una entrada específica en Contentful
    public void getZona(String entryId) {
        final CMAEntry entry = cMaclient
                .entries()
                .fetchOne(entryId);

        // Accede a los campos de la entrada 
        System.out.println("ID: " + entry.getId());
        System.out.println("Fields: " + entry.getFields());
    }
    
    public void deleteZona() {
    	final CMAEntry entry =
    	   cMaclient
    		        .entries()
    		        .fetchOne("<entry_id>");

    	   cMaclient
    		    .entries()
    		    .delete(entry);
    }

    
}
