package encuestas.repositorio;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import encuestas.modelo.Encuesta;
import encuestas.servicio.EncuestaResumen;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import repositorio.RepositorioJPA;
import utils.EntityManagerHelper;

public class RepositorioEncuestasJPA extends RepositorioJPA<Encuesta> {

	@Override
	public Class<Encuesta> getClase() {
		return Encuesta.class;
	}

	
	
	
	
	

}
