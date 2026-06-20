package Patio;

import java.awt.Color;

import Control.Main;
import Soporte.Util;

public class Suelo {
	
	int humedad = 80;

	public void humedecer() {
		int rHumedad = (int)(Math.random()*10+1);
		humedad += rHumedad;
		Util.report("El suelo se ha humedecido un " + rHumedad + "%", Main.color_verde, "Patio", 1, true);
		System.out.println("El suelo se ha humedecido un " + rHumedad + "%");
	}
	
	public void secar() {
		Util.report("El suelo se ha secado", Main.color_verde, "Patio", 1, true);
		System.out.println("El suelo se ha secado");
		humedad = 80;
	}
	
	public void saturar() {
		humedad += 100;
		Util.report("El suelo se ha saturado", Main.color_verde, "Patio", 1, true);
		System.out.println("El suelo se ha saturado");
	}
	
	public int humedad() {
		if (humedad > 0) {
		Util.report("Hay un " + humedad + " % de humedad", Main.color_verde, "Patio", 4, true);
		System.out.println("Hay un " + humedad + " de humedad");
			return humedad;
		}
		else {
			return humedad;
		}
	}
}
