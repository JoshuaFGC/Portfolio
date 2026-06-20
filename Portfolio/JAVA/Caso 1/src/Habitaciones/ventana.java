package Habitaciones;

public class ventana {
	private boolean estado;
	private int altura;
	private int ancho;
	
	public int[] crearVentana(int pAltura, int pAncho) {
		altura = pAltura;
		ancho = pAncho;
		return new int[] {altura, ancho};
	}
	
	
	public boolean abrirse(boolean pEstado) {
		this.estado = pEstado;
		System.out.println("Ventana abierta");
		return estado;
		
	}
	
	public boolean cerrarse(boolean pEstado) {
		this.estado = pEstado;
		System.out.println("Ventana cerrada");
		return estado;		
	}
}
