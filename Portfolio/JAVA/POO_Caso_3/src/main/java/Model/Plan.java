package Model;

import java.util.List; 

public class Plan {
    private int id;
    private Caso caso;
    private List<Tecnica> tecnicasImplementar;
    private Cronograma cronograma;

    public Plan(int id, Caso caso, List<Tecnica> tecnicasImplementar, Cronograma cronograma) {
        this.id = id;
        this.caso = caso;
        this.tecnicasImplementar = tecnicasImplementar;
        this.cronograma = cronograma;
    }

    public int getId() {
        return id;
    }

    public Caso getCaso() {
        return caso;
    }

    public List<Tecnica> getTecnicasImplementar() {
        return tecnicasImplementar;
    }

    public Cronograma getCronograma() {
        return cronograma;
    }
}
