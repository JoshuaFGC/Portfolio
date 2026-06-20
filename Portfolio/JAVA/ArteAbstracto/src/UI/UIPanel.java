package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Observador.Observable;

import Observador.PintorObserver;

public class UIPanel extends JPanel implements Observable{
	private List<PintorObserver> pintores = new ArrayList<>();
	private JPanel lienzo;
	private int id;
	private int sizeX;
	private int sizeY;
	private int posicionX;
	private int posicionY;
	private Color color;
	private Graphics pGraphics;

	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		pGraphics = g;
		//g.drawLine(100, 100, 200, 200);
		
		//g.drawRect(300, 100, 100, 100);
		
		//g.drawOval(500, 100, 100, 100);
	}
	
	@Override
	public void addOserver(PintorObserver observador) {
		pintores.add(observador);
	}
	
	public void addPaint(int pId, int pSizeX, int pSizeY,
			int pPosicionX, int pPosicionY, Color pColor) {
		
		this.id = pId;
		this.sizeX = pSizeX;
		this.sizeY = pSizeY;
		this.posicionX = pPosicionX;
		this.posicionY = pPosicionY;
		this.color = pColor;
		
		this.notifyObserver();
		System.out.println("si");
	}
	
	
	
	public int getId() {
		return id;
	}
	
	public int getSizeX() {
		return sizeX;
	}
	
	public int getSizeY() {
		return sizeY;
	}
	
	public int getPosicionX() {
		return posicionX;
	}
	
	public int getPosicionY() {
		return posicionY;
	}
	
	public Color getColor() {
		return color;
	}
	
	public Graphics getGraphicsUI() {
		return pGraphics;
	}
	@Override
	public void notifyObserver() {
		for (PintorObserver pintor : pintores) {
			pintor.update();
		}
	}

}
