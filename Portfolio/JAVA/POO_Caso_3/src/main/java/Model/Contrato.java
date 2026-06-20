package Model;

import java.util.List; 
import java.util.Date;

public class Contrato {
    private int id;
    private Representante representante;
    private Date fechaInicio;
    private Date fechaFinal;
    private Plan planAcordado;
    private List<Tecnica> tecnicasAcordadas;
    private float gananciasRepresentante;

    // Constructor para inicializar los atributos
    public Contrato(int id, Representante representante, Date fechaInicio, Date fechaFinal, Plan planAcordado,
    		float gananciasRepresentante) {
        this.id = id;
        this.representante = representante;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.planAcordado = planAcordado;
        this.gananciasRepresentante = gananciasRepresentante;
    }
    
    public void addTecnicas(Tecnica pTecnica) {
    	this.tecnicasAcordadas.add(pTecnica);
    }

    // Getter para obtener el ID
    public int getId() {
        return id;
    }

    // Getter para obtener el representante
    public Representante getRepresentante() {
        return representante;
    }

    // Getter para obtener la fecha de inicio
    public Date getFechaInicio() {
        return fechaInicio;
    }

    // Getter para obtener la fecha final
    public Date getFechaFinal() {
        return fechaFinal;
    }

    // Getter para obtener el plan acordado
    public Plan getPlanAcordado() {
        return planAcordado;
    }

    // Getter para obtener las técnicas acordadas
    public List<Tecnica> getTecnicasAcordadas() {
        return tecnicasAcordadas;
    }

    // Getter para obtener las ganancias del representante
    public float getGananciasRepresentante() {
        return gananciasRepresentante;
    }
  
}