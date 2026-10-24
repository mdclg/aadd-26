package encuestas.repositorio;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import encuestas.modelo.Encuesta;
import encuestas.servicio.EncuestaResumen;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import utils.EntityManagerHelper;

public class RepositorioEncuestasAdHocJPA extends RepositorioEncuestasJPA 
implements RepositorioEncuestasAdHoc{

	@Override
	public Encuesta getById(String id) throws EntidadNoEncontrada, RepositorioException {

		try {
			EntityManager em = EntityManagerHelper.getEntityManager();			
			 EntityGraph<?> graph = em.getEntityGraph(
				        "Encuesta.conOpcionesYVotos"
				    );
			Encuesta instancia = em.find(getClase(), id, Map.of("javax.persistence.loadgraph", graph));
			if (instancia == null) {
				throw new EntidadNoEncontrada(id + " no existe en el repositorio");
			}
			return instancia;

		} catch (RuntimeException e) {
			throw new RepositorioException("Error al recuperar la entidad con id " + id, e);
		}
		finally {
			EntityManagerHelper.closeEntityManager();
		}
	}
	
	@Override
	public List<Encuesta> getBySinVotos() throws RepositorioException, EntidadNoEncontrada {
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();		
            EntityGraph<?> graph = em.getEntityGraph(
				        "Encuesta.conOpcionesYVotos");


            String queryString = "SELECT e "
            		+ " FROM Encuesta e "
            		+ " WHERE e.cierre < CURRENT_TIMESTAMP "
            		+ " AND e.id NOT IN (SELECT e.id "
            		+ "                 FROM Encuesta e "
            		+ "                 JOIN e.opciones o "
            		+ "                 JOIN o.votos v)";
            
			TypedQuery<Encuesta> query = em.createQuery(queryString, Encuesta.class);
			query.setHint("javax.persistence.loadgraph", graph);				
			
			return query.getResultList();

        } catch (RuntimeException e) {
            throw new RepositorioException("Error buscando encuestas sin votos",e);
        }
        finally {
            EntityManagerHelper.closeEntityManager();
        }
	}

	@Override
	public List<Encuesta> getByNumeroOpcionesMayorQue(int numero) throws RepositorioException {
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();		
            EntityGraph<?> graph = em.getEntityGraph(
				        "Encuesta.conOpcionesYVotos");

			TypedQuery<Encuesta> query = em.createNamedQuery("Encuesta.conMinimoOpciones", Encuesta.class);
			query.setHint("javax.persistence.loadgraph", graph);				
			query.setParameter("num", numero);
				
			return query.getResultList();

        } catch (RuntimeException e) {
            throw new RepositorioException("Error buscando todas las entidades por id",e);
        }
        finally {
            EntityManagerHelper.closeEntityManager();
        }
	}

	@Override
	public List<Encuesta> getByVotante(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> getOpcionesPorVotos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<EncuestaResumen> getResumen() throws RepositorioException{
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();				

			String queryString=  "SELECT NEW encuestas.servicio.EncuestaResumen(e.id, e.titulo) " +
				    "FROM Encuesta e";
				Query query = em.createQuery(queryString);
				
				return query.getResultList();

			} catch (RuntimeException e) {
				throw new RepositorioException("Error obteniendo resumen",e);		}
			finally {
				EntityManagerHelper.closeEntityManager();
			}
	}

}
