package Soporte;

import java.awt.Color;
import Control.Main;

public class Util {
	private static String time;
	
	public static void report(String pMensaje, Color pColor, String pArea, int pIndex, boolean pEsProblema) {
		
		String aviso = "====================\n"+
					"HORA: " + time + "\n" +
					pArea + " - " + pMensaje;
		
		
		switch(pIndex) {
			case 1:
				Main.frame.AsignarEstado(3, pColor, false, aviso);					
			case 2:
				Main.frame.AsignarEstado(0, pColor, false, aviso);
			case 3:
				Main.frame.AsignarEstado(1, pColor, false, aviso);
			case 4:
				Main.frame.AsignarEstado(7, pColor, false, aviso);
				break;
			case 5:
				Main.frame.AsignarEstado(8, pColor, false, aviso);
				break;
			case 6:
				Main.frame.AsignarEstado(7, pColor, false, aviso);
				break;
			case 7:
				Main.frame.AsignarEstado(7, pColor, false, aviso);
				break;
			case 8:
		
			case 9:
		
			case 10:
		
			case 11:
		
			case 12:
		
			case 13:
		
			case 14:
		
			case 15:
		}
			
		
	}
	
	public static void setTime(String pTime) {
		time = pTime;
	}

}
