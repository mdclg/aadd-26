package editorial.modelo;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity	
public class Distribuidor implements Serializable{
	
	@Id
	private String cif;
	private String nombre;
	
	/*
	  @ManyToMany(mappedBy="distribuidores")
    private List<Editorial> editoriales;
	 */
	
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
		

}
