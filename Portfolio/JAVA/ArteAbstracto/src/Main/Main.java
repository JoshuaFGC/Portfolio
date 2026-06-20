package Main;
import java.awt.Color;
import Factoria.*;
import UI.UIPanel;
import java.awt.*;

public class Main {
	private static PintorRaya r;
	private static PintorLunar l;
	private static PintorPoligono p;
	private Dimension sizeP;
	
	 public static void main(String[] args) {
		 UIPanel panel = new UIPanel();
		 
		 FactoriaPintor m = new FactoriaPintor();
		 r = (PintorRaya) m.crearPintor("Rayas", panel, 2);
		 l = (PintorLunar) m.crearPintor("Lunares", panel, 10);
		 p = (PintorPoligono) m.crearPintor("Poligonos", panel, 10);
		 
		 panel.addOserver(r);
		 panel.addOserver(l);
		 panel.addOserver(p);
		 
		 panel.addPaint(1, 100, 100, 100, 100, Color.cyan);

		panel.setSize(700, 700);
		Dimension sizeP = panel.getSize();
		panel.setVisible(true);
			
		 
	 }
	 
	 public Dimension getSize() {
		 return sizeP;
	 }
	 
}
