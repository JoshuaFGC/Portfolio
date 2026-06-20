package Model;

import java.util.List;

public class Tecnica {
		private int id;
	 	private String nombre;
	    private String descripcion;
	    private List<String> CultivosDestino;
	    private float costoEstimado;
	    private int tiempoImplementacion;
	    private List<String> productosRequeridos;
	    private List<Zona> zonasAplicables;

	    // Constructor para inicializar los atributos
	    public Tecnica(int id, String nombre, String descripcion, float costoEstimado, int tiempoImplementacion) {
	        this.id = id;
	        this.nombre = nombre;
	        this.descripcion = descripcion;
	        this.costoEstimado = costoEstimado;
	        this.tiempoImplementacion = tiempoImplementacion;     
	    }
	    
	    public void addCultivos(String pCultivo) {
	    	this.CultivosDestino.add(pCultivo);
	    }
	    
	    public void addProductos(String pProducto) {
	    	this.productosRequeridos.add(pProducto);
	    }
	    
	    public void addZona(Zona zona) {
	    	this.zonasAplicables.add(zona);
	    }

	    // Getter para obtener el ID
	    public int getId() {
	        return id;
	    }

	    // Getter para obtener el nombre
	    public String getNombre() {
	        return nombre;
	    }

	    // Getter para obtener la descripción
	    public String getDescripcion() {
	        return descripcion;
	    }
	    
	    // Getter para obtener la lista de cultivos de destino
	    public List<String> getCultivosDestino() {
	        return CultivosDestino;
	    }

	    // Getter para obtener el costo estimado
	    public float getCostoEstimado() {
	        return costoEstimado;
	    }

	    // Getter para obtener el tiempo de implementación
	    public int getTiempoImplementacion() {
	        return tiempoImplementacion;
	    }

	    // Getter para obtener la lista de productos requeridos
	    public List<String> getProductosRequeridos() {
	        return productosRequeridos;
	    }

	    // Getter para obtener la lista de zonas aplicables
	    public List<Zona> getZonasAplicables() {
	        return zonasAplicables;
	    }

}