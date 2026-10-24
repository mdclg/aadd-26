package aadd.beans.ambitos;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class SaludarBean implements Serializable{
	
	private String nombre;
	private String mensaje;
	
	public SaludarBean() {
		System.out.println("Nuevo SaludarWeb");
	}
	public void generarSaludo() {
        mensaje = "¡Hola, " + nombre + "!";
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMensaje() {
		return mensaje;
	}

}
