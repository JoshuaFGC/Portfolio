package main;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.SwingUtilities;

import Region.*;
import UI.ventData;
import controllerInterfaz.dataShared;
import dispositivo.device;

public class Main {
	
	
	
	
	

	public static void main(String[] args) {
		dataShared ventana = new dataShared();
		
		
		ventana.crearVentana();
		ventana.crearVentCalib();
		
	    	             
	}


}

	

/*El sistema consiste en regiones, las regiones tienen su configuracion
 * dada por un Json, luego, cada region tiene 2 dispositivos los cuales
 * tienen un hilo que genera datos con los datos brindados por el Json
 * estos dispositivos van a tener un min y un max para generar los datos
 * y tambien tienen metodos para calibrarse y descalibrarse, la descalibracion
 * sucede gracias a datos brindados por el Json, esto hara que los datos sean
 * incorrectos con el pasar del tiempo, entonces el metodo calibrar va a corregir
 * el min y el max para volver a dar datos correctos.
 * 
 * estrategia C: voy a implementar los controllers y las clases de mi modelo 
 * completas de tal forma que desde el controlador con un main yo pueda 
 * verificar que todo funciona correctamente y con sentido. Para ya al final 
 * solo hacer pantallas que es más sencillo y así tengo menos riesgo por 
 * si me dejan más trabajo en otro curso.
 */
