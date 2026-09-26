package encuestas.servicio;

import java.time.LocalDateTime;
import java.util.List;

import encuestas.modelo.Encuesta;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

public interface IServicioEncuestas {

	String crear(String titulo, String instrucciones, LocalDateTime apertura, LocalDateTime cierre, List<String> opciones) throws RepositorioException;
	
	boolean haVotado(String id, String usuario) throws RepositorioException, EntidadNoEncontrada;
	
	void votar(String id, int opcion, String usuario) throws RepositorioException, EntidadNoEncontrada;
	
	Encuesta getEncuesta(String id) throws RepositorioException, EntidadNoEncontrada;
	
	void eliminar(String id) throws RepositorioException, EntidadNoEncontrada;
	
	List<EncuestaResumen> getListadoResumen() throws RepositorioException, EntidadNoEncontrada;
}
