package Patio;

import java.awt.Color;

import Control.Main;
import Soporte.Util;

public class ManagerPatio extends ManagerControlPatio{

	static Cesped cesped = new Cesped();
	static Arbol arbol = new Arbol();
	static Planta planta = new Planta();
	static Suelo suelo = new Suelo();

	
	///----------Acciones Patio----------////
	
	//Patio General
	
	public void checkPatio() {
		Util.report("Checkeo de patio", Main.color_suelo, "Patio", 0, true);
		
		System.out.println("----Checkeo de patio----");
		
		eliminarPlagas();
		limpiarPatio();
		
		if (arbol.cantidad() > 5) {
			arbol.eliminar();
		}
		
		if (planta.cantidad() > 5) {
			planta.eliminar();
		}
		
		if (planta.cantidadFlores() >= 10) {
			planta.recogerFlores();
		}
		
		if (arbol.cantidadFruto() >= 10) {
			arbol.recogerFruto();
		}
			
		if (cesped.altura() >= 30) {
			cesped.cortar();
		}
		
		if (suelo.humedad() >= 100) {
			suelo.secar();
		}
		
		Util.report("Final checkeo de patio", Main.color_suelo, "Patio", 0, true);
		System.out.println("----Final checkeo de patio----\n");
	}
	
	public void actPatio() {
		
		int rProbabilidad1 = (int)(Math.random()*3+1);
		int rProbabilidad2 = (int)(Math.random()*2+1);
		int rProbabilidad3 = (int)(Math.random()*3+1);
		
		if (rProbabilidad1 == 3) {
			arbol.crecer();
			planta.crecer();
			}
		
		if (rProbabilidad2 == 2) {
			arbol.darFruto();
			planta.darFlores();
			}
		
		if (rProbabilidad3 == 3) {
			suelo.humedecer();
			}
		
		cesped.crecer();
		System.out.println("");
	}	
	
	// Suelo
	public static void humedecerSueloM(){
		suelo.saturar();
	}
	
	//Cesped
	public static void crecerCespedM() {
		cesped.crecerMucho();
	}	


	///----------Acciones de Respuesta Patio----------////
	
	//Patio General
	public void eliminarPlagas() {
		if (plaga == false){
			plaga = false;
		} 
		
		else{
			plaga = false;
			Util.report("Se ha eliminado la plaga de " + tipo_plaga, Main.color_suelo, "Patio", 1, true);
			System.out.println("Se ha eliminado la plaga de " + tipo_plaga);
		}
	}
	
	public void limpiarPatio() {
		if (sucio == false){
			sucio = false;
		} 
		else{
			Util.report("Se ha limpiado el patio", Main.color_suelo, "Patio", 1, true);
			sucio = false;
			System.out.println("Se ha limpiado el patio");
		}	
	}

}