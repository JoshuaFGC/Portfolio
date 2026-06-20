package Model;

import java.time.LocalDate;
import java.util.Date;

public class Contrato {
	private int id;
	private LocalDate fecha;
	private String nombreAgricultor;
	private Date hora;
	private String duracion;
	private String url;
	private int costos;
	
	
	
	//Setters
	public void setId(int pId) {
		this.id = pId;
	}
	
	public void setFecha(LocalDate pFecha) {
		this.fecha = pFecha;
	}
	
	public void setNombre(String pNombre) {
		this.nombreAgricultor = pNombre;
	}
	
	
	public void setHora(Date pHora) {
		this.hora = pHora;
	}
	
	public void setDuracion(String pDuracion) {
		this.duracion = pDuracion;
	}
	
	public void setUrl(String pUrl) {
		this.url = pUrl;
	}
	
	public void setCostos(int pCostos) {
		this.costos = pCostos;
	}
	
	//Getters
	public int getId() {
		return id;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public String getNombre() {
		return nombreAgricultor;
	}
	
	
	public Date getHora() {
		return hora;
	}
	
	public String getDuracion() {
		return duracion;
	}
	
	public String getUrl() {
		return url;
	}
	
	public int getCostos() {
		return costos;
	}
}
