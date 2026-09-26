package repositorio;

/*
 * Excepción notificada si no existe una entidad con el identificador
 * proporcionado en el repositorio.
 */

@SuppressWarnings("serial")
public class EntidadNoEncontrada extends Exception {

		
	public EntidadNoEncontrada(String msg) {
		super(msg);		
	}
	
		
}
