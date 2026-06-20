package CommonClass;

import java.awt.image.BufferedImage;

import javax.swing.JLabel;

public interface device {
	
	void setImage(JLabel label);
	
	void setVolume(int pVolume);
	
	
	int getVolume();

	BufferedImage ajusteImagen(BufferedImage pFoto);
	
	void mostrarImagen(BufferedImage imagen);
	

}
