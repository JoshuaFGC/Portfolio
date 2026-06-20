package Control;

import java.util.List;   
import javax.swing.Icon;
import GUI.Perfil;
import Model.*;

public class RegistroController {

	private Base datos;
	
	public RegistroController(Base datos){
	
		this.datos = datos;
	}

	public Base getDatos() {
		return datos;
	}
	
	public boolean verificarPerfil(String Nombre) {

		List<Usuario> usuarios = datos.getPerfiles();

		boolean aprobado = false;
		for (Usuario user:usuarios) {
			if (user.getNombreUsuario()==Nombre) {
				aprobado = true;
			}	
		}
		return aprobado;
	}
	
	public void crearPerfil(String nombre, String rol, String usuario, String correo, int ans, String password, Icon Image) {
		
		int id = datos.getCantPerfiles();
		Usuario perfil = new Usuario(id, 
				nombre,usuario, rol, correo, ans ,password, Image);
        datos.addPerfil(perfil);
        Perfil perf = new Perfil(perfil, datos);
		datos.addPerfil(perfil);
        System.out.println("Perfil creado");
    }
}