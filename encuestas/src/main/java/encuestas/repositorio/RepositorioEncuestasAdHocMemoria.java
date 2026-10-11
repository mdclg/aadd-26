package encuestas.repositorio;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import encuestas.modelo.Encuesta;
import encuestas.modelo.Opcion;
import repositorio.RepositorioMemoria;

public class RepositorioEncuestasAdHocMemoria extends RepositorioMemoria<Encuesta> implements RepositorioEncuestasAdHoc {

	public RepositorioEncuestasAdHocMemoria() {

		// Datos iniciales para pruebas

		Encuesta encuesta = new Encuesta("Test 1", "Instrucciones", LocalDateTime.now().minusDays(1),
				LocalDateTime.now().plusDays(1), Arrays.asList("Opción 1", "Opción 2"));

		this.add(encuesta);

	}
	
	/*
	 * Ejemplo de implementción de las consultas en un repositorio.
	 */
	
	@Override
	public List<Encuesta> getBySinVotos() {
		
		LinkedList<Encuesta> resultado = new LinkedList<Encuesta>();
		
		LocalDateTime ahora = LocalDateTime.now();

		for (Encuesta e : getAll())
			if (e.getCierre().isAfter(ahora) && 
				e.getOpciones().stream().map(Opcion::getNumeroVotos).reduce(0, Integer::sum) == 0)
				resultado.add(e);
		
		return resultado;
	}

	@Override
	public List<Encuesta> getByNumeroOpcionesMayorQue(int numero) {
		
		return getAll().stream().filter(e -> e.getNumeroOpciones() > numero).collect(Collectors.toList());
	}

	@Override
	public List<Encuesta> getByVotante(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> getOpcionesPorVotos() {
		// TODO Auto-generated method stub
		return null;
	}

}