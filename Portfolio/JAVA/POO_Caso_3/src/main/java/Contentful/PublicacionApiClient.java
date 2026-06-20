package Contentful;

import com.contentful.java.cda.*;

public class PublicacionApiClient {
    private static PublicacionApiClient instance;
    private ApiClient apiClient;
    private CDAClient contentfulClient;

    private PublicacionApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.contentfulClient = apiClient.getClient();
    }

    public static PublicacionApiClient getInstance(ApiClient apiClient) {
        if (instance == null) {
            instance = new PublicacionApiClient(apiClient);
        }
        return instance;
    }

    // Método para subir una Publicacion a Contentful
    public void subirPublicacion(Publicacion publicacion) {
        CDAEntry entry = new CDAEntry("publicacionContentType"); 
        entry.setField("id", publicacion.getId());
        entry.setField("titulo", publicacion.getTitulo());
        entry.setField("descripcion", publicacion.getDescripcion());
        entry.setField("tecnicaNueva", publicacion.getTecnicaNueva().getId()); 
        entry.setField("creador", publicacion.getCreador().getId()); 

        // Guardar la entrada en Contentful
        contentfulClient.create(entry).subscribe();
    }

    // Método para consultar una Publicacion por ID
    public Publicacion consultarPublicacionPorId(String publicacionId) {
        CDAEntryDetail publicacionDetail = contentfulClient.fetch(CDAEntryDetail.class)
                .one(publicacionId);

        CDAEntry publicacionEntry = publicacionDetail.entry();
        if (publicacionEntry != null) {
            Publicacion publicacion = new Publicacion(
                publicacionEntry.getField("id"),
                publicacionEntry.getField("titulo"),
                publicacionEntry.getField("descripcion"),
                new Tecnica(publicacionEntry.getField("tecnicaNueva")), 
                new Ingeniero(publicacionEntry.getField("creador")) 
            );
            return publicacion;
        } else {
            return null;
        }
    }

    // Método para modificar una Publicacion por ID
    public void modificarPublicacion(String publicacionId, Publicacion publicacion) {
        CDAEntryDetail publicacionDetail = contentfulClient.fetch(CDAEntryDetail.class)
                .one(publicacionId);

        CDAEntry publicacionEntry = publicacionDetail.entry();
        publicacionEntry.setField("id", publicacion.getId());
        publicacionEntry.setField("titulo", publicacion.getTitulo());
        publicacionEntry.setField("descripcion", publicacion.getDescripcion());
        publicacionEntry.setField("tecnicaNueva", publicacion.getTecnicaNueva().getId()); 
        publicacionEntry.setField("creador", publicacion.getCreador().getId()); 

        contentfulClient.update(publicacionEntry).subscribe();
    }

    // Método para eliminar una Publicacion por ID
    public void eliminarPublicacion(String publicacionId) {
        contentfulClient.delete(CDAEntry.class)
                .one(publicacionId)
                .execute();
    }

   
}
