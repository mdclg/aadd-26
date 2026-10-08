package encuestas.modelo;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Opcion {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;
	private String texto;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> votos = new LinkedList<>();
	
	public Opcion() { // POJO
		
	}
	
	public Opcion(String texto) {
		this.texto = texto;
	}
	
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public List<String> getVotos() {
		return votos;
	}
	public void setVotos(LinkedList<String> votos) {
		this.votos = votos;
	}
	
	// Propiedad calculada
	
	public int getNumeroVotos() {		
		return this.votos.size();
	}
	
	@Override
	public String toString() {
		return "Opcion [texto=" + texto + ", votos=" + votos + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
