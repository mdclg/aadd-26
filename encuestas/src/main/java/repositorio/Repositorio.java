package repositorio;

import java.util.List;

/*
 *  Repositorio para entidades gestionadas con identificador.
 *  El parámetro T representa el tipo de datos de la entidad.
 *  El parámetro K es el tipo del identificador.
 */

public interface Repositorio <T, K> {    
		
    K add(T entity) throws RepositorioException;
    
    void update(T entity) throws RepositorioException, EntidadNoEncontrada;
    
    void delete(T entity) throws RepositorioException, EntidadNoEncontrada;

    T getById(K id) throws RepositorioException, EntidadNoEncontrada;
    
	List<T> getAll() throws RepositorioException;

	List<K> getIds()throws RepositorioException;
}
