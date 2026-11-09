package aadd.beans.errores;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@SuppressWarnings("serial")
@Named
@ApplicationScoped
public class AutoriaBean implements Serializable{
	
	private String autor;

    public AutoriaBean() {
        autor = "Profesorado de AADD";
    }
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
}
