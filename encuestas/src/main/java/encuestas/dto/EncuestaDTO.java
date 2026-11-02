package encuestas.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class EncuestaDTO implements Serializable {

	private String id;
	private String titulo;
	private String instrucciones;
	private LocalDateTime apertura;
	private LocalDateTime cierre;

    public EncuestaDTO(String id, String titulo, String instrucciones, LocalDateTime apertura, LocalDateTime cierre) {
		this.id = id;
		this.titulo = titulo;
		this.instrucciones = instrucciones;
		this.apertura = apertura;
		this.cierre = cierre;
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
}