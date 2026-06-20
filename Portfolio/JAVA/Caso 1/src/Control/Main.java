package Control;

import java.awt.Color;

import javax.swing.SwingUtilities;

import GUI.Window;
import Patio.TaskPatio;
import Soporte.Action;
import plannerControl.*;

public class Main {
	
	public static Color color_verde = new Color(0,160,50);
	public static Color color_suelo = new Color(170,115,30);
	public static Color color_casa = new Color(200,250,230);
	public static Window frame = new Window();
	

    private static PruebaPatio PruebaPatio = new PruebaPatio();
    private static PruebaDispositivos PruebaDispositivo = new PruebaDispositivos();
    private static PruebaHabitaciones PruebaHabitacion = new PruebaHabitaciones();
    private static PruebaAlmacenamiento PruebaAlmacen = new PruebaAlmacenamiento();
    private static PruebaAtmosfera PruebaAtmosfer = new PruebaAtmosfera();
	
	public static TaskPatio tareasPatio = new TaskPatio();
	//public static TaskHabitaciones tareasHabitaciones = new TaskHabitaciones();
	//public static TaskDispositivos tareasDispositivos = new TaskDispositivos();

	public static void main(String[] args) {
		
		Scheduler myscheduler = new Scheduler();
		Clock threadControl = new Clock(myscheduler);
		
		SwingUtilities.invokeLater(() -> {
	        frame.setVisible(true);
	    	threadControl.start();
	    });
		
	}
	
	public static void doTask(String pTask, String pSector, boolean pEsControl) {
		switch(pSector) {
		case "Patio":
			if(pEsControl) {PruebaPatio.hacerPrueba(pTask);}
			else {tareasPatio.execute(Action.valueOf(pTask), false);}
			break;
		case "Habitaciones":
			//if(pEsControl) {PruebaHabitaciones.hacerPrueba(pTask);}
			//else {tareasHabitaciones.execute(Action.valueOf(pTask), false);}
			break;
		case "Dispositivos":
			//if(pEsControl) {PruebaDispositivos.hacerPrueba(pTask);}
			//else {tareasDispositivos.execute(Action.valueOf(pTask), false);}
			break;
		}
	}

}
