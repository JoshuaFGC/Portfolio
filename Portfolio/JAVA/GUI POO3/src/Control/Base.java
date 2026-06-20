package Control;

import java.util.*;    
import Model.*;
import java.io.Serializable;

public class Base implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Usuario> Perfiles;
	private List<Publicacion> publicaciones;
	
	// Singleton instance
    private static Base instance;
    
    // Private constructor to prevent instantiation outside of the class
    private Base() {
        Perfiles = new ArrayList<>();
        publicaciones = new ArrayList<>();
    }
    
    // Method to get the singleton instance
    public static Base getInstance() {
        if (instance == null) {
            instance = new Base();
        }
        return instance;
    }

	public void setPerfiles(List<Usuario> usuarios) {
		this.Perfiles = usuarios;
	}
	
	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}
	
	public void addPerfil(Usuario pPerfilNuevo) {
		Perfiles.add(pPerfilNuevo);
	}
	
	public int getCantPerfiles() {
		return Perfiles.size();
	}
	
	public List<Usuario> getPerfiles() {
		return Perfiles;
	}
	
	public void addPubli(Publicacion publi) {
		publicaciones.add(publi);
	}
	
	public int getCantpubli() {
		return publicaciones.size();
	}
	
	public List<Publicacion> getPublicaciones() {
		return publicaciones;
	}
	
	
}