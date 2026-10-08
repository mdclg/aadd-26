package encuestas.repositorio;

import encuestas.modelo.Encuesta;
import repositorio.RepositorioXML;

public class RepositorioEncuestasXML extends RepositorioXML<Encuesta>{

	@Override
	public Class<Encuesta> getClase() {
		return Encuesta.class;
	}

}