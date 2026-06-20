package Model;

public class Ingeniero {
 	private int id;
    private String Name;
    private String email;
    private int años_exp;
    private String especialidad;

    // Constructor para inicializar los atributos
    public Ingeniero(int id, String nombre, String email, int años_exp, String especialidad) {
        this.id = id;
        this.Name = nombre;
        this.email = email;
        this.años_exp = años_exp;
        this.especialidad = especialidad;
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

    // Getter para obtener los años de experiencia
    public int getAñosExp() {
        return años_exp;
    }

    // Getter para obtener la especialidad
    public String getEspecialidad() {
        return especialidad;
    }

}
