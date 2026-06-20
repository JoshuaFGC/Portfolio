package Habitaciones;

public class llave {
	

	private boolean estado = true;

	public boolean crearLlave() {
		estado = false;
		return estado;
	}
	
	
	
	public boolean abrirse(boolean pEstado, int pPresion, boolean pTuberias) {
		
		if(pTuberias) {
			this.estado = pEstado;
			System.out.println("LLave abierta con una presion de "+pPresion);
			return estado;
		
		}
		return pTuberias;
	}
	
	public boolean cerrarse(boolean pEstado) {
		this.estado = pEstado;
		System.out.println("Llave cerrada");
		return estado;
	}
}
