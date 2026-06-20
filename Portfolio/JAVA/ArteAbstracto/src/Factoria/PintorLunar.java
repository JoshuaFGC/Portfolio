package Factoria;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import Observador.Observable;
import Observador.PintorObserver;
import UI.UIPanel;

public class PintorLunar implements PintorObserver, Pintor{
	private Observable observer;
	private Graphics grafico;
	private int id;
	private int sizeX;
	private int sizeY;
	private int posicionX;
	private int posicionY;
	private Color color;
	private ColorSize cRandsRand= new ColorSize();
	private Dimension size;
	private double sizeW;
	private double sizeH;
	private int contador;
	private int contadorLocal = 0;

	
	public PintorLunar(UIPanel lienzo, int pCantidad) {
		this.observer = lienzo;
		this.grafico = lienzo.getGraphics();
		this.id = lienzo.getId()+1;
		this.sizeX = lienzo.getSizeX();
		this.sizeY = lienzo.getSizeY();
		this.posicionX = lienzo.getPosicionX();
		this.posicionY = lienzo.getPosicionY();
		this.color = lienzo.getColor();
		this.size = lienzo.getSize();
		this.sizeW = size.getWidth();
		this.sizeH = size.getHeight();
		this.contador = pCantidad;
		
		
	}


	@Override
	public void update() {
		
		if (contadorLocal > contador) {
			contadorLocal++;
			this.pintar(id, sizeX, sizeY, posicionX, posicionY, color);
		}
		
	}


	@Override
	public void pintar(int id, int sizeX, int sizeY, int posicionX,
			int posicionY, Color color) {
		
		grafico.setColor(cRandsRand.getRandomColor(color));
		sizeX = cRandsRand.getRandomSize(sizeW, sizeH);
		sizeY = cRandsRand.getRandomSize(sizeW, sizeH);
		posicionX = cRandsRand.getRandomSize(sizeW, sizeH);
		posicionY = cRandsRand.getRandomSize(sizeW, sizeH);
		grafico.drawOval(sizeX, sizeY, posicionX, posicionY);
	}
	
	public Graphics getGraficoLunar() {
		return grafico;
	}
}
