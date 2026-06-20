package Jugador;

import Pista.track;

public class carro {
	
	private String nombre;
	private track pistaActual;
	
	public carro(String pnombre) {
		this.nombre = pnombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void asigPista(track pPista) {
		this.pistaActual = pPista;
		System.out.println("A "+this.getNombre()+"se le asigna la pista "+pPista.getLocation());
	}
	
	public void acelerar() {
		System.out.println(this.getNombre()+" Acelerando...");
	}
	
	public void der() {
		System.out.println("Jugador a la derecha");
	}
	
	public void izq() {
		System.out.println("Jugador a la izquierda");
	}
}

