package jaxb;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Persona {

	// Por defecto, son elementos y tienen cardinalidad 0..1
	private String nombre;
	private String apellidos;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
}