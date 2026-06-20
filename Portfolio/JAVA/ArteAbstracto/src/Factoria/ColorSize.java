package Factoria;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ColorSize {
	private List<Color> colores = new ArrayList<>();
	private Random random = new Random();
	
	
	public Color getRandomColor(Color pColor) {
		
			colores.add(Color.BLACK);
			colores.add(Color.BLUE);
			colores.add(Color.CYAN);
			colores.add(Color.DARK_GRAY);
			colores.add(Color.GREEN);
			colores.add(Color.LIGHT_GRAY);
			colores.add(Color.MAGENTA);
			colores.add(Color.ORANGE);
			colores.add(Color.PINK);
			colores.add(Color.YELLOW);
		while (true) {
			int rand = random.nextInt(colores.size());
			Color seleccionado = colores.get(rand);
			if (!seleccionado.equals(pColor)) {
				return seleccionado;
			}
		}
	}
	
	public int getRandomSize(double sizeW, double sizeH) {
		
		
		return (int) (random.nextInt((int) (sizeH - sizeW + 1))+ sizeW);
		
	}
}
