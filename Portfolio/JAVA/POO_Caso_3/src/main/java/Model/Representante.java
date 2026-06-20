package Model;

public class Representante {
    private int id;
    private String Name;
    private String email;
    private Zona zonaRepresentada;
    private int añosExp;

    // Constructor para inicializar los atributos
    public Representante(int id, String nombre, String email, Zona zonaRepresentada, int añosExp) {
        this.id = id;
        this.Name = nombre;
        this.email = email;
        this.zonaRepresentada = zonaRepresentada;
        this.añosExp = añosExp;
    }

    // Getter para obtener el ID
    public int getId() {
        return id;
    }

    // Getter para obtener el nombre
    public String getNombre() {
        return Name;
    } 

    // Getter para obtener el email
    public String getEmail() {
        return email;
    }

    // Getter para obtener la zona representada
    public Zona getZonaRepresentada() {
        return zonaRepresentada;
    }

    // Getter para obtener los años de experiencia
    public int getAñosExp() {
        return añosExp;
    }
}