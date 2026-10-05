package editorial.modelo;

import java.io.Serializable;

public class Revista extends Publicacion{

	private String issn;
	private String volumen;
	
	public String getIssn() {
		return issn;
	}
	public void setIssn(String issn) {
		this.issn = issn;
	}
	public String getVolumen() {
		return volumen;
	}
	public void setVolumen(String volumen) {
		this.volumen = volumen;
	}
	
	
	
}
