package encuestas.servicio;

public class EncuestaResumen {

	private String id;
	private String titulo;
	
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
	@Override
	public String toString() {
		return "EncuestaResumen [id=" + id + ", titulo=" + titulo + "]";
	}	
	
}
