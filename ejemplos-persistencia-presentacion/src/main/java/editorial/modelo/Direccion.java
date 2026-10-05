package editorial.modelo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Direccion {
	@Column(name="calle")
	private String calle;

	@Column(name="numero")
	private Integer numero;

	@Column(name="ciudad")
	private String ciudad;

	@Column(name="codigo_postal")
	private String codigoPostal;

	public Direccion() {
		
	}
	
	public Direccion(String calle, Integer numero, String ciudad, String codigoPostal) {
		super();
		this.calle = calle;
		this.numero = numero;
		this.ciudad = ciudad;
		this.codigoPostal = codigoPostal;
	}

	public String getCalle() {

		return calle;

	}

	public void setCalle(String calle) {

		this.calle = calle;

	}

	public Integer getNumero() {

		return numero;

	}

	public void setNumero(Integer numero) {

		this.numero = numero;

	}

	public String getCiudad() {

		return ciudad;

	}

	public void setCiudad(String ciudad) {

		this.ciudad = ciudad;

	}

	public String getCodigoPostal() {

		return codigoPostal;

	}

	public void setCodigoPostal(String codigoPostal) {

		this.codigoPostal = codigoPostal;

	}
}
