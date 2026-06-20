package Model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Publicacion implements java.io.Serializable{
	private String titulo;
	private Icon foto;
	private String link;
	private String ahorro; //Ejemplo: "100Kg por hectarea"
	private String tecnica;
	private int costos;
	private String maquinaria;
	private String periodo; //Ejemplo: "3 Meses"
	
	
	public Publicacion(String pTitulo, Icon pFoto, String pLink,
			String pAhorro, String pTecnica, int pCostos, String pMateriales, String pPeriodo) {
		this.titulo = pTitulo;
		this.foto = pFoto;
		this.link = pLink;
		this.ahorro = pAhorro;
		this.tecnica = pTecnica;
		this.costos = pCostos;
		this.maquinaria = pMateriales;
		this.periodo = pPeriodo;
		
	}

	
	
	//Getters
	public String getTitulo() {
		return titulo;
	}
	
	public Icon getFoto() {
		return foto;
	}
	
	public String getLink() {
		return link;
	}
	
	public String getAhorro() {
		return ahorro;
	}
	
	public String getTecnica() {
		return tecnica;
	}
	
	public int getCostos() {
		return costos;
	}
	
	public String getMaquinaria() {
		return maquinaria;
	}
	
	public String getPeriodo() {
		return periodo;
	}

}