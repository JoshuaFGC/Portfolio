package Habitaciones;



public class Puerta {
	private boolean estado;
	private int altura;
	private int ancho;
	
	public int[] crearPuerta(int pAltura, int pAncho) {
		altura = pAltura;
		ancho = pAncho;
		return new int[] {altura, ancho};
	}
	
	
	public boolean abrirse(boolean pEstado) {
		estado = pEstado;
		System.out.println("Puerta abierta");
		return estado;
		
	}
	
	public boolean cerrarse(boolean pEstado) {
		this.estado = pEstado;
		System.out.println("Puerta cerrada");
		return estado;
		
	}
}
