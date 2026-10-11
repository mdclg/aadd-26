package encuestas.repositorio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import encuestas.modelo.Encuesta;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import repositorio.RepositorioString;

/*
 * Esta interfaz extiende la definición genérica con operaciones *ad hoc* de consulta.
 * 
 * Es una interfaz concreta para una entidad (Encuesta).
 */
public interface RepositorioEncuestasAdHoc extends RepositorioString<Encuesta> {

	/*
	 * Ejemplo de implementación por defecto (útil para un repositorio en memoria).
	 */
	public default List<Encuesta> getByActivas() throws RepositorioException {
		LocalDateTime ahora = LocalDateTime.now();
		return getAll().stream().filter(encuesta -> encuesta.getApertura().isBefore(ahora) &&
				encuesta.getCierre().isAfter(ahora)).collect(Collectors.toList());
	}
	
	public List<Encuesta> getBySinVotos() throws RepositorioException, EntidadNoEncontrada ;
	
	public List<Encuesta> getByNumeroOpcionesMayorQue(int numero);
	
	// ...
	public List<Encuesta> getByVotante(String nombre);
	public List<Object[]> getOpcionesPorVotos();
	
}