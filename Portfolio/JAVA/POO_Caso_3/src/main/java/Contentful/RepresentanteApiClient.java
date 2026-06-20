package Contentful;

import com.contentful.java.cda.*;

public class RepresentanteApiClient {
    private static RepresentanteApiClient instance;
    private ApiClient apiClient;
    private CDAClient contentfulClient;

    private RepresentanteApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.contentfulClient = apiClient.getClient();
    }

    public static RepresentanteApiClient getInstance(ApiClient apiClient) {
        if (instance == null) {
            instance = new RepresentanteApiClient(apiClient);
        }
        return instance;
    }

    // Método para subir un Representante a Contentful
    public void subirRepresentante(Representante representante) {
        CDAEntry entry = new CDAEntry("representanteContentType"); 
        entry.setField("id", representante.getId());
        entry.setField("nombre", representante.getNombre());
        entry.setField("email", representante.getEmail());
        entry.setField("zonaRepresentada", representante.getZonaRepresentada().getId()); 
        entry.setField("añosExp", representante.getAñosExp());
        
        // Guardar la entrada en Contentful
        contentfulClient.create(entry).subscribe();
    }

    // Método para consultar un Representante por ID
    public Representante consultarRepresentantePorId(String representanteId) {
        CDAEntryDetail representanteDetail = contentfulClient.fetch(CDAEntryDetail.class)
                .one(representanteId);

        CDAEntry representanteEntry = representanteDetail.entry();
        if (representanteEntry != null) {
            Representante representante = new Representante(
                representanteEntry.getField("id"),
                representanteEntry.getField("nombre"),
                representanteEntry.getField("email"),
                new Zona(representanteEntry.getField("zonaRepresentada")), 
                representanteEntry.getField("añosExp")
            );
            return representante;
        } else {
            return null;
        }
    }

    // Método para modificar un Representante por ID
    public void modificarRepresentante(String representanteId, Representante representante) {
        CDAEntryDetail representanteDetail = contentfulClient.fetch(CDAEntryDetail.class)
                .one(representanteId);

        CDAEntry representanteEntry = representanteDetail.entry();
        representanteEntry.setField("id", representante.getId());
        representanteEntry.setField("nombre", representante.getNombre());
        representanteEntry.setField("email", representante.getEmail());
        representanteEntry.setField("zonaRepresentada", representante.getZonaRepresentada().getId()); 
        representanteEntry.setField("añosExp", representante.getAñosExp());

        contentfulClient.update(representanteEntry).subscribe();
    }

    // Método para eliminar un Representante por ID
    public void eliminarRepresentante(String representanteId) {
        contentfulClient.delete(CDAEntry.class)
                .one(representanteId)
                .execute();
    }

   
}
