package encuestas.reglas;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import encuestas.modelo.Encuesta;
import encuestas.modelo.Opcion;

/*
 * Ejemplo de reglas de negocio.
 * Se pueden utilizar individialmente o combinadas para consultas o validaciones
 */
public class ReglasEncuesta {

	public static Predicate<Encuesta> activas() {
		LocalDateTime ahora = LocalDateTime.now();
		
		return encuesta -> encuesta.getApertura().isBefore(ahora) &&
				encuesta.getCierre().isAfter(ahora);
	}
	
	public static Predicate<Encuesta> opcionesSuficientes() {
		return encuesta -> encuesta.getNumeroOpciones() > 1;		
	}

	public static Predicate<Encuesta> sinVotos() {
		
		LocalDateTime ahora = LocalDateTime.now();
		return encuesta -> encuesta.getCierre().isAfter(ahora) && 
				encuesta.getOpciones().stream().map(Opcion::getNumeroVotos).reduce(0, Integer::sum) == 0;
	}
	
	
}