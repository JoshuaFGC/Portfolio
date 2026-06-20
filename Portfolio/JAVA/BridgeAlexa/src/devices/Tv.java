package devices;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import CommonClass.device;

public class Tv implements device{

	private int volume = 0;
	private BufferedImage foto;
	private BufferedImage dimension;
	private JLabel tabFoto;
	
	
	@Override
	public void setImage(JLabel label) {
		this.tabFoto = label;
		
		JFileChooser image = new JFileChooser();
		int result = image.showOpenDialog(null);
		
		if (result == JFileChooser.APPROVE_OPTION) {
			try {
				File archivo = image.getSelectedFile();
				foto = ImageIO.read(archivo);
				mostrarImagen(foto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	@Override
	public void setVolume(int pVolume) {
		if (pVolume != -1) {
			volume = pVolume;
		}	
	}

	
	
	@Override
	public int getVolume() {
		
		return volume;
	}

	
	
	@Override
	public BufferedImage ajusteImagen(BufferedImage pFoto) {
        this.foto = pFoto;
		if (foto != null) {
            int nuevoAncho = 300; 
            int nuevoAlto = 200;   

            dimension = new BufferedImage(nuevoAncho, nuevoAlto, BufferedImage.TYPE_INT_RGB);
            dimension.getGraphics().drawImage(foto, 0, 0, nuevoAncho, nuevoAlto, null);
        }
		return dimension;
	}



	@Override
	public void mostrarImagen(BufferedImage imagen) {
    	
        ImageIcon icono = new ImageIcon(ajusteImagen(imagen));
        
        tabFoto.setIcon(icono);
    }


}
