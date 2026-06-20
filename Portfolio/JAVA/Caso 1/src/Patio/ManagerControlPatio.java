package Patio;

import java.awt.Color;

import Soporte.Util;

public class ManagerControlPatio {
	
	String[] str_plagas = {"Saltamontes", "Hormigas", "Mosquitos", "Gusanos", "Lombrices", "Moscas"};
	String tipo_plaga = null ;

	//Estados patio
	
	boolean plaga = false;
	boolean sucio = false;
	
	//Metodos 
	
	public void humedecerSuelo() {
		ManagerPatio.humedecerSueloM();
	}
	
	public void mandarPlagas() {
		tipo_plaga = str_plagas[(int) (Math.random()*5)];
		Util.report("Ha aparecido una plaga de " + tipo_plaga, Color.red, "Patio", 1, true);
		System.out.println("Ha aparecido una plaga de " + tipo_plaga);
		plaga = true;
	}
	
	public void ensuciarPatio() {
		Util.report("Se ha ensuciado el patio", Color.gray, "Patio", 1, true);
		System.out.println("Se ha ensuciado el patio");
		sucio = true;
	}
}
   