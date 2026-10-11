package encuestas.servicio.test;

import java.util.List;

import encuestas.modelo.Encuesta;
import encuestas.repositorio.RepositorioEncuestasAdHoc;
import repositorio.FactoriaRepositorios;

public class ProgramaEspecificacion {

	public static void main(String[] args) throws Exception {
		
		RepositorioEncuestasAdHoc repositorioConcreto = FactoriaRepositorios.getRepositorio(Encuesta.class);

		System.out.println(repositorioConcreto.getByActivas());
		System.out.println("encuestas de juan "
		+repositorioConcreto.getByVotante("juan@um.es"));
		System.out.println("encuesta  sin votos "+repositorioConcreto.getBySinVotos());
		List<Object[]> resultado = repositorioConcreto.getOpcionesPorVotos();
		for(Object[] o:resultado) {
			System.out.println("en la encusta "+o[0]+" gana "+o[1]+" por "+o[2]+" votos");
		}
	}
}