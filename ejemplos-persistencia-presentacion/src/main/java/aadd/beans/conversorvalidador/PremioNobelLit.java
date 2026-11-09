package aadd.beans.conversorvalidador;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PremioNobelLit implements Serializable{

	private String nombre;
	private Integer anyo;
	
	
	
	public PremioNobelLit(String nombre, Integer anyo) {
		super();
		this.nombre = nombre;
		this.anyo = anyo;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getAnyo() {
		return anyo;
	}
	public void setAnyo(Integer anyo) {
		this.anyo = anyo;
	}
	
}
