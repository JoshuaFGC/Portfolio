package Model;
 

public class Publicacion {
    private int id;
    private String titulo;
    private String descripcion;
    private Tecnica tecnicaNueva;
    private Ingeniero creador;

    public Publicacion(int id, String titulo, String descripcion, Tecnica tecnicaNueva, Ingeniero creador) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tecnicaNueva = tecnicaNueva;
        this.creador = creador;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Tecnica getTecnicaNueva() {
        return tecnicaNueva;
    }

    public Ingeniero getCreador() {
        return creador;
    }
 

}
