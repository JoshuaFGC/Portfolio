package Patio;

import java.awt.Color;

import Control.Main;
import Soporte.Util;

public class Arbol {
	
	int cantidadFruto;
	int cantidadArbol = 5;
	
	///Arbol
	public void crecer() {
		int rcantidadArbol = (int)(Math.random()*2+2);
		cantidadArbol += rcantidadArbol;
		Util.report("Han crecido " + rcantidadArbol + " nuevos arboles ", Main.color_verde, "Patio", 4, true);
		System.out.println("Han crecido " + rcantidadArbol + " nuevos arboles ");
	}
		
	public int cantidad() {
		if (cantidadArbol > 0) {
		Util.report("Hay un total de " + cantidadArbol + " arboles", Main.color_verde, "Patio", 3, true);
		System.out.println("Hay un total de " + cantidadArbol + " arboles");
			return cantidadArbol;
		}
		else {
			return cantidadArbol;
		}
	}
	
	public void eliminar() {
		Util.report("Se ha eliminado el exceso de arboles", Main.color_verde, "Patio", 3, true);
		System.out.println("Se ha eliminado el exceso de arboles");
		cantidadArbol = 5;
	}
	
	
	///Frutos
	public void darFruto() {
		int rCantidadFruto = (int)(Math.random()*5+2);
		cantidadFruto += rCantidadFruto;
		Util.report("Los arboles han dado " + rCantidadFruto + " frutos", Main.color_verde, "Patio", 3, true);
		System.out.println("Los arboles han dado " + rCantidadFruto + " frutos");
	}
	
	public void recogerFruto() {
		Util.report("Se han recogido los frutos", Main.color_verde, "Patio", 3, true);
		System.out.println("Se han recogido los frutos");
		cantidadFruto = 0;
	}
	
	public int cantidadFruto() {
		if (cantidadFruto > 0) {
		Util.report("Los arboles tienen " + cantidadFruto + " frutos", Main.color_verde, "Patio", 3, true);
		System.out.println("Los arboles tienen " + cantidadFruto + " frutos");
			return cantidadFruto;
		}
		else {
			return cantidadFruto;
		}
	}

}
