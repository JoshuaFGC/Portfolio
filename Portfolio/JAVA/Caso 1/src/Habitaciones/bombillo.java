package Habitaciones;



public class bombillo{

	private boolean estado = true;
	
	public boolean crearBombillo() {
		estado = false;
		return estado;
	}
	
	
	public boolean encerderse(boolean pEstado, boolean pElectricidad) {
		
		if (pElectricidad) {
			this.estado = pEstado;
			System.out.println("Luz encendida");
			return estado;	
		} else {
			System.out.println("No hay electricidad");
		}
		return pElectricidad;
	}
	
	public boolean apagarse(boolean pEstado) {
		this.estado = pEstado;
		System.out.println("Luz apagada");
		return estado;
		
	}
}
