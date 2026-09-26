package encuestas.servicio;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import encuestas.modelo.Encuesta;
import encuestas.modelo.Opcion;
import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import repositorio.RepositorioException;

public class ServicioEncuestas implements IServicioEncuestas {

	private Repositorio<Encuesta, String> repositorio = FactoriaRepositorios.getRepositorio(Encuesta.class);
		
	@Override
	public String crear(String titulo, String instrucciones, LocalDateTime apertura, LocalDateTime cierre, List<String> opciones) throws RepositorioException {
		
		// Control de integridad de los datos
	
		if (titulo == null || titulo.isEmpty())
			throw new IllegalArgumentException("titulo: no debe ser nulo ni vacio");
		
		if (apertura == null)
			throw new IllegalArgumentException("fecha apertura: no debe ser nula");
		
		if (cierre == null)
			throw new IllegalArgumentException("fecha cierre: no debe ser nula");
		
		if (cierre.isBefore(apertura))
			throw new IllegalArgumentException("fecha cierre: debe ser posterior a la apertura");
		
		if (LocalDateTime.now().isAfter(cierre))
			throw new IllegalArgumentException("fecha cierre: no debe estar en el pasado");
		
		if (opciones == null)
			throw new IllegalArgumentException("opciones: no debe ser una coleccion nula");
		
		if (opciones.size() < 2)
			throw new IllegalArgumentException("opciones: debe tener más de dos opciones");
		
		Encuesta encuesta = new Encuesta(titulo, instrucciones, apertura, cierre, opciones);
		
		String id = repositorio.add(encuesta);
		
		return id;
	}

	@Override
	public boolean haVotado(String id, String usuario) throws RepositorioException, EntidadNoEncontrada {
		
		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("id: no debe ser nulo ni vacio");
		
		if (usuario == null || usuario.isEmpty())
			throw new IllegalArgumentException("usuario: no debe ser nulo ni vacio");
		
		Encuesta encuesta = repositorio.getById(id);
		
		for (Opcion opcion : encuesta.getOpciones())
			if (opcion.getVotos().contains(usuario))
				return true;
				
		return false;
		
	}

	@Override
	public void votar(String id, int opcion, String usuario) throws RepositorioException, EntidadNoEncontrada {
				
		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("id: no debe ser nulo ni vacio");
		
		if (usuario == null || usuario.isEmpty())
			throw new IllegalArgumentException("usuario: no debe ser nulo ni vacio");
		
		if (haVotado(id, usuario))
			throw new IllegalArgumentException("usuario: ya ha votado");
		
		Encuesta encuesta = repositorio.getById(id);
		
		if (opcion < 1 || opcion > encuesta.getOpciones().size() )
			throw new IllegalArgumentException("opcion: indice no valido");
		
		LocalDateTime ahora = LocalDateTime.now();
		
		if (ahora.isBefore(encuesta.getApertura()) || ahora.isAfter(encuesta.getCierre()))
			throw new IllegalArgumentException("la encuesta no esta abierta");
		// FIXME: estrictamente debería ser una excepcion de estado IllegalStateException
		
		int indice = opcion - 1; // desde 0
		
		encuesta.getOpciones().get(indice).getVotos().add(usuario);
		
		repositorio.update(encuesta);
		
	}

	@Override
	public Encuesta getEncuesta(String id) throws RepositorioException, EntidadNoEncontrada {
		
		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("id: no debe ser nulo ni vacio");
		
		return repositorio.getById(id);
	}

	@Override
	public void eliminar(String id) throws RepositorioException, EntidadNoEncontrada {
		
		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("id: no debe ser nulo ni vacio");
		
		Encuesta encuesta = repositorio.getById(id);
		
		repositorio.delete(encuesta);
		
	}

	@Override
	public List<EncuestaResumen> getListadoResumen() throws RepositorioException, EntidadNoEncontrada {
		
		LinkedList<EncuestaResumen> resultado = new LinkedList<>();
		
		for (Encuesta encuesta : repositorio.getAll()) {
			EncuestaResumen resumen = new EncuestaResumen();
			resumen.setId(encuesta.getId());
			resumen.setTitulo(encuesta.getTitulo());
			
			resultado.add(resumen);
			
		}
				
		return resultado;
	}

}
