package Patio;

import java.awt.Color;

import Control.Main;
import Soporte.Util;

public class Cesped {
	
	 int alturaCesped;
	 int Humedad;
	 int Extension;

	public void crecer() {
		alturaCesped += (int)(Math.random()*10+1);
		Util.report("El cesped ha crecido a " + alturaCesped + " cm", new Color(0, 150, 10), "Patio", 2, true);
		System.out.println("El cesped ha crecido a " + alturaCesped + " cm");
	}
	
	public void crecerMucho() {
		alturaCesped += 30;
		Util.report("El cesped ha crecido a " + alturaCesped + " cm", Color.green, "Patio", 2, true);
		System.out.println("El cesped ha crecido a " + alturaCesped + " cm");
	}
	
	public void cortar() {
		alturaCesped = 0;
		Util.report("El cesped ha sido cortado",  Main.color_verde, "Patio", 2, true);
		System.out.println("El cesped ha sido cortado");
	}
	
	public int altura() {
		return alturaCesped;
	}
}
