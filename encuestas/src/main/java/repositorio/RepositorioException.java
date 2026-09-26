package repositorio;

/*
 * Excepción que representa un fallo en el sistema de persistencia.
 * Al instanciarla, se establece la excepción interna que produce el error (causa).
 */

@SuppressWarnings("serial")
public class RepositorioException extends Exception {

	public RepositorioException(String msg, Throwable causa) {		
		super(msg, causa);
	}
	
	public RepositorioException(String msg) {
		super(msg);		
	}
	
		
}
