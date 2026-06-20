package Model;

import java.util.List;
import java.util.ArrayList;  
public class Zona {
    private int cantAgricultores;
    private String Name;
    private List<String> Cultivos;

    // Constructor para inicializar los atributos
    public Zona(int cantAgricultores, String nombre) {
        this.cantAgricultores = cantAgricultores;
        this.Name = nombre;
        Cultivos = new ArrayList<>();
    }
    
    public void addCultivo(String pCultivo) {
    	this.Cultivos.add(pCultivo);
    }

    // Getter para obtener la cantidad de agricultores
    public int getCantAgricultores() {
        return cantAgricultores;
    }

    // Getter para obtener el nombre de la zona
    public String getNombre() {
        return Name;
    }

    // Getter para obtener la lista de cultivos
    public List<String> getCultivos() {
        return Cultivos;
    }
}