package encuestas.modelo;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import repositorio.Identificable;


public class Encuesta implements Identificable {
	
	private String id;
	
	private String titulo;
	private String instrucciones;
	
	private LocalDateTime apertura;
	
	private LocalDateTime cierre;
	
	private LinkedList<Opcion> opciones = new LinkedList<>();
	
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
	
	public LocalDateTime getApertura() {
		return apertura;
	}
	public void setApertura(LocalDateTime apertura) {
		this.apertura = apertura;
	}
	
	public LocalDateTime getCierre() {
		return cierre;
	}
	public void setCierre(LocalDateTime cierre) {
		this.cierre = cierre;
	}
	public LinkedList<Opcion> getOpciones() {
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
