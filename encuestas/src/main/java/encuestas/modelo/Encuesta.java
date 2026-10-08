package encuestas.modelo;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.annotations.GenericGenerator;

import repositorio.Identificable;
import utils.LocalDateTimeAdapter;

@XmlRootElement // anotación JAXB
@Entity
public class Encuesta implements Identificable {
	
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid", strategy="uuid2")
	private String id;
	
	private String titulo;
	@Lob
	private String instrucciones;
	
	private LocalDateTime apertura;
	
	private LocalDateTime cierre;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "encuesta_fk")
	private List<Opcion> opciones = new LinkedList<>();
	
	public Encuesta() { // POJO
		
	}
	
	public Encuesta(String titulo, String instrucciones, LocalDateTime apertura, LocalDateTime cierre,
			List<String> opcionesTexto) {
		
		this.titulo = titulo;
		this.instrucciones = instrucciones;
		this.apertura = apertura;
		this.cierre = cierre;
		this.opciones = new LinkedList<>();
		
		for (String opcionTexto : opcionesTexto)
			this.opciones.add(new Opcion(opcionTexto));
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getInstrucciones() {
		return instrucciones;
	}
	public void setInstrucciones(String instrucciones) {
		this.instrucciones = instrucciones;
	}
	
	@XmlJavaTypeAdapter(value=LocalDateTimeAdapter.class) // anotación JAXB
	public LocalDateTime getApertura() {
		return apertura;
	}
	public void setApertura(LocalDateTime apertura) {
		this.apertura = apertura;
	}
	@XmlJavaTypeAdapter(value=LocalDateTimeAdapter.class) // anotación JAXB
	public LocalDateTime getCierre() {
		return cierre;
	}
	public void setCierre(LocalDateTime cierre) {
		this.cierre = cierre;
	}
	public List<Opcion> getOpciones() {
		return opciones;
	}
	public void setOpciones(LinkedList<Opcion> opciones) {
		this.opciones = opciones;
	}
	
	// Propiedad calculada
	
	public int getNumeroOpciones() {
		
		return this.opciones.size();
	}

	@Override
	public String toString() {
		return "Encuesta [id=" + id + ", titulo=" + titulo + ", instruccion=" + instrucciones + ", apertura=" + apertura
				+ ", cierre=" + cierre + ", opciones=" + opciones + "]";
	}
	
}
