package Patio;

import java.awt.Color;

import Control.Main;
import Soporte.Util;

public class Planta {
	
	int cantidadPlanta = 5;
	int cantidadFlores;

	//Planta
	public void crecer() {
		int rcantidadPlanta = (int)(Math.random()*2+2);
		cantidadPlanta += rcantidadPlanta;
		Util.report("Han crecido " + rcantidadPlanta + " nuevas plantas ", Main.color_verde, "Patio", 4, true);
		System.out.println("Han crecido " + rcantidadPlanta + " nuevas plantas ");
	}
	
	public int cantidad() {
		if (cantidadPlanta > 0) {
		Util.report("Hay un total de " + cantidadPlanta + " plantas", Main.color_verde, "Patio", 4, true);
		System.out.println("Hay un total de " + cantidadPlanta + " plantas");
			return cantidadPlanta;
		}
		else {
			return cantidadPlanta;
		}
	}
	
	public void eliminar() {
		Util.report("Se ha eliminado el exceso de plantas", Main.color_verde, "Patio", 3, true);
		System.out.println("Se ha eliminado el exceso de plantas");
		cantidadPlanta = 5;
	}
	
	
	//Flores
	public void darFlores() {
		int Tflores = (int)(Math.random()*3+2);
		cantidadFlores += Tflores;
		Util.report("Las flores ha dado " + Tflores + " nuevas flores", Main.color_verde, "Patio", 4, true);
		System.out.println("Las flores ha dado " + Tflores + " nuevas flores");
	}

	public void recogerFlores() {
		Util.report("Se han recogido las flores de las plantas", Main.color_verde, "Patio", 4, true);
		System.out.println("Se han recogido las flores de las plantas");
		cantidadFlores = 0;
	}
	
	public int cantidadFlores() {
		if (cantidadFlores > 0) {
		Util.report("La planta tiene " + cantidadFlores + " flores", Main.color_verde, "Patio", 4, true);
		System.out.println("La planta tiene " + cantidadFlores + " flores");
			return cantidadFlores;
		}
		else {
			return cantidadFlores;
		}
	}

	
}
