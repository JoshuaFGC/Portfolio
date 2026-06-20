package Factoria;

import java.awt.Color;


import UI.UIPanel;

public class FactoriaPintor {
	
	public Pintor crearPintor(String tipo, UIPanel ui, int Cont) {
        switch (tipo) {
            case "Rayas":
                return new PintorRaya(ui, Cont);
            case "Lunares":
                return new PintorLunar(ui, Cont);
            case "Poligonos":
                return new PintorPoligono(ui, Cont);
            default:
                System.out.println( "Tipo de pintor no valido: " + tipo);
        }
		return null;
        
    }
}
