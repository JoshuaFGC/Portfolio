package Contentful;

import com.contentful.java.cda.*;
import java.util.List;

public class IngenieroApiClient {
    private static IngenieroApiClient instance;
    private ApiClient apiClient;
    private CDAClient contentfulClient;

    private IngenieroApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.contentfulClient = apiClient.getClient();
    }

    public static IngenieroApiClient getInstance(ApiClient apiClient) {
        if (instance == null) {
            instance = new IngenieroApiClient(apiClient);
        }
        return instance;
    }

    // Método para subir un Ingeniero a Contentful
    public void subirIngeniero(Ingeniero ingeniero) {
        CDAEntry entry = new CDAEntry("ingenieroContentType"); 
        entry.setField("id", ingeniero.getId());
        entry.setField("nombre", ingeniero.getNombre());
        entry.setField("email", ingeniero.getEmail());
        entry.setField("añosExp", ingeniero.getAñosExp());
        entry.setField("especialidad", ingeniero.getEspecialidad());
        
        // Guardar la entrada en Contentful
        contentfulClient.create(entry).subscribe();
    }

    // Método para consultar un Ingeniero por ID
    public Ingeniero consultarIngenieroPorId(String ingenieroId) {
        CDAEntryDetail ingenieroDetail = contentfulClient.fetch(CDAEntryDetail.class)
                .one(ingenieroId);

        CDAEntry ingenieroEntry = ingenieroDetail.entry();
        if (ingenieroEntry != null) {
            Ingeniero ingeniero = new Ingeniero(
                ingenieroEntry.getField("id"),
                ingenieroEntry.getField("nombre"),
                ingenieroEntry.getField("email"),
                ingenieroEntry.getField("añosExp"),
                ingenieroEntry.getField("especialidad")
            );
            return ingeniero;
        } else {
            return null;
        }
    }

    // Método para modificar un Ingeniero por ID
    public void modificarIngeniero(String ingenieroId, Ingeniero ingeniero) {
        CDAEntryDetail ingenieroDetail = contentfulClient.fetch(CDAEntryDetail.class)
                .one(ingenieroId);

        CDAEntry ingenieroEntry = ingenieroDetail.entry();
        ingenieroEntry.setField("id", ingeniero.getId());
        ingenieroEntry.setField("nombre", ingeniero.getNombre());
        ingenieroEntry.setField("email", ingeniero.getEmail());
        ingenieroEntry.setField("añosExp", ingeniero.getAñosExp());
        ingenieroEntry.setField("especialidad", ingeniero.getEspecialidad());

        contentfulClient.update(ingenieroEntry).subscribe();
    }

    // Método para eliminar un Ingeniero por ID
    public void eliminarIngeniero(String ingenieroId) {
        contentfulClient.delete(CDAEntry.class)
                .one(ingenieroId)
                .execute();
    }

    
}
