package encuestas.repositorio;

import encuestas.modelo.Encuesta;
import repositorio.RepositorioJPA;

public class RepositorioEncuestasJPA  extends RepositorioJPA<Encuesta>{

	@Override
	public Class<Encuesta> getClase() {
		return Encuesta.class;
	}

}
