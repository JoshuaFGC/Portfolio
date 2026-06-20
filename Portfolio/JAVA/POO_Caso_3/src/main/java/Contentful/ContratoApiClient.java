package Contentful;

import com.contentful.java.cda.*;

public class ContratoApiClient {
    private static ContratoApiClient instance;
    private ApiClient apiClient;
    private CDAClient contentfulClient;

    private ContratoApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.contentfulClient = apiClient.getClient();
    }

    public static ContratoApiClient getInstance(ApiClient apiClient) {
        if (instance == null) {
            instance = new ContratoApiClient(apiClient);
        }
        return instance;
    }

    // Método para subir un Contrato a Contentful
    public void subirContrato(Contrato contrato) {
        CDAEntry entry = new CDAEntry("contratoContentType"); 
        entry.setField("id", contrato.getId());
        entry.setField("representante", contrato.getRepresentante().getId()); 
        entry.setField("fechaInicio", contrato.getFechaInicio());
        entry.setField("fechaFinal", contrato.getFechaFinal());
        entry.setField("planAcordado", contrato.getPlanAcordado().getId()); 
        entry.setField("tecnicasAcordadas", contrato.getTecnicasAcordadas());
        entry.setField("gananciasRepresentante", contrato.getGananciasRepresentante());

        // Guardar la entrada en Contentful
        contentfulClient.create(entry).subscribe();
    }

    // Método para consultar un Contrato por ID
    public Contrato consultarContratoPorId(String contratoId) {
        CDAEntryDetail contratoDetail = contentfulClient.fetch(CDAEntryDetail.class)
                .one(contratoId);

        CDAEntry contratoEntry = contratoDetail.entry();
        if (contratoEntry != null) {
            Contrato contrato = new Contrato(
                contratoEntry.getField("id"),
                new Representante(contratoEntry.getField("representante")), 
                contratoEntry.getField("fechaInicio"),
                contratoEntry.getField("fechaFinal"),
                new Plan(contratoEntry.getField("planAcordado")), 
                contratoEntry.getField("gananciasRepresentante")
            );
            contrato.setTecnicasAcordadas(contratoEntry.getField("tecnicasAcordadas"));
            return contrato;
        } else {
            return null;
        }
    }

    // Método para modificar un Contrato por ID
    public void modificarContrato(String contratoId, Contrato contrato) {
        CDAEntryDetail contratoDetail = contentfulClient.fetch(CDAEntryDetail.class)
                .one(contratoId);

        CDAEntry contratoEntry = contratoDetail.entry();
        contratoEntry.setField("id", contrato.getId());
        contratoEntry.setField("representante", contrato.getRepresentante().getId()); 
        contratoEntry.setField("fechaInicio", contrato.getFechaInicio());
        contratoEntry.setField("fechaFinal", contrato.getFechaFinal());
        contratoEntry.setField("planAcordado", contrato.getPlanAcordado().getId()); 
        contratoEntry.setField("tecnicasAcordadas", contrato.getTecnicasAcordadas());
        contratoEntry.setField("gananciasRepresentante", contrato.getGananciasRepresentante());

        contentfulClient.update(contratoEntry).subscribe();
    }

    // Método para eliminar un Contrato por ID
    public void eliminarContrato(String contratoId) {
        contentfulClient.delete(CDAEntry.class)
                .one(contratoId)
                .execute();
    }
}