package Control;

import GUI.Perfil; 
import Model.*;
public class ControlSingIn {
	
	public ControlSingIn() {}
	
	public boolean verificarPerfil(String Nombre, String Password, Base base ) {
		System.out.println("Perfil en revision");
		for (Usuario user: base.getPerfiles()) {
			System.out.println(user.getNombreUsuario());
			System.out.println(user.getPassword());
			if (user.getNombreUsuario().equals(Nombre) & user.getPassword().equals(Password)) {
				System.out.println("Perfil ncontrado");
				Perfil perf = new Perfil(user, base);
				return true;
			}	
		}
		return false;
	}

}