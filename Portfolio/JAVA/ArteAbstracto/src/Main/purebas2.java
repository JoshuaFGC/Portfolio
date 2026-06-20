package Main;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class purebas2 extends JPanel{
	private Graphics pGraphics;
	

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		pGraphics = g;
		//           x    y   x    y
		
		
		//g.drawRect(300, 100, 100, 100);
		
		//g.drawOval(500, 100, 100, 100);
		
		//g.fillRect(300, 400, 100, 100);
		
		//g.fillOval(500, 400, 100, 100);
		//g.drawPolyline(100, 100, 100);
	}
	
	public Graphics getGraphics() {
		
		return pGraphics;
	}
	
    public void dibujarRectangulo(JFrame ventana, int x, int y, int ancho, int alto) {
    		Graphics p = ventana.getGraphics();
        	System.out.println("a");
            p.drawOval(x, y, ancho, alto);
            repaint();
            
            
        
    }
    
    
	public static void main(String[] args) {
		JFrame ventana = new JFrame("A");
		purebas2 objeto = new purebas2();
		
		ventana.add(objeto);
		ventana.setSize(700, 700);
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		objeto.dibujarRectangulo(ventana, 100, 100, 100, 5000);
		
	}
}
