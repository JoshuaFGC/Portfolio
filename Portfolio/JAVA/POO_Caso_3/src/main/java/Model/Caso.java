package Model;
import java.util.List;

public class Caso {
	
	private int id;
    private Representante representante;
    private String cultivo;
    private String problemaDetectado;
    private List<Tecnica> tecnicasRecomendadas;
    private float ahorroEstimado;

    // Constructor para inicializar los atributos
    public Caso(int id, Representante representante, String cultivo, String problemaDetectado,
                    float ahorroEstimado) {
        this.id = id;
        this.representante = representante;
        this.cultivo = cultivo;
        this.problemaDetectado = problemaDetectado;
        this.ahorroEstimado = ahorroEstimado;
    }
    
    public void addTecnica(Tecnica pTecnica) {
    	this.tecnicasRecomendadas.add(pTecnica);
    }

    // Getter para obtener el ID
    public int getId() {
        return id;
    }

    // Getter para obtener el representante
    public Representante getRepresentante() {
        return representante;
    }

    // Getter para obtener el cultivo
    public String getCultivo() {
        return cultivo;
    }

    // Getter para obtener el problema detectado
    public String getProblemaDetectado() {
        return problemaDetectado;
    }

    // Getter para obtener las técnicas recomendadas
    public List<Tecnica> getTecnicasRecomendadas() {
        return tecnicasRecomendadas;
    }

    // Getter para obtener el ahorro estimado
    public float getAhorroEstimado() {
        return ahorroEstimado;
    }

}