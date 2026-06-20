package Model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class Imagen {
	private BufferedImage foto;
	private BufferedImage dimension;
	private JLabel tabFoto;
	
	//Metodo para ajustar la imagen al Label
	public void setImage(JLabel label, int alto, int ancho) {
		this.tabFoto = label;
		
		JFileChooser image = new JFileChooser();
		int result = image.showOpenDialog(null);
		
		if (result == JFileChooser.APPROVE_OPTION) {
			try {
				File archivo = image.getSelectedFile();
				foto = ImageIO.read(archivo);
				mostrarImagen(foto, alto, ancho);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public BufferedImage ajusteImagen(BufferedImage pFoto, int alto, int ancho) {
        this.foto = pFoto;
		if (foto != null) {
              

            dimension = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
            dimension.getGraphics().drawImage(foto, 0, 0, ancho, alto, null);
        }
		return dimension;
	}


	public void mostrarImagen(BufferedImage imagen, int alto, int ancho) {
		
        ImageIcon icono = new ImageIcon(ajusteImagen(imagen, alto, ancho));
        
        tabFoto.setIcon(icono);
    }
	
	public BufferedImage resizeIcon(Icon icon, int sizeX, int sizeY) {
        if (icon instanceof ImageIcon) {
            ImageIcon imageIcon = (ImageIcon) icon;
            Image image = imageIcon.getImage();

            BufferedImage bufferedImage = new BufferedImage(
                imageIcon.getIconWidth(),
                imageIcon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB
            );

            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();

            return ajusteImagen(bufferedImage, sizeX, sizeY);
        }
        return null;
    }

}
