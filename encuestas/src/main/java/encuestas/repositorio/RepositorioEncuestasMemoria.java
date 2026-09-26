package encuestas.repositorio;

import java.time.LocalDateTime;
import java.util.Arrays;

import encuestas.modelo.Encuesta;
import repositorio.RepositorioMemoria;

/*
 * Repositorio preparado con datos de prueba
 */
public class RepositorioEncuestasMemoria extends RepositorioMemoria<Encuesta> {

	public RepositorioEncuestasMemoria() {

		// Datos iniciales para pruebas

		Encuesta encuesta = new Encuesta("Test 1", "Instrucciones", LocalDateTime.now().minusDays(1),
				LocalDateTime.now().plusDays(1), Arrays.asList("Opción 1", "Opción 2"));

		this.add(encuesta);

	}

}
