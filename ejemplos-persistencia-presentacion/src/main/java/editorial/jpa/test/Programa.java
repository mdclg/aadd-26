package editorial.jpa.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import javax.persistence.EntityManager;

import editorial.modelo.Direccion;
import editorial.modelo.Distribuidor;
import editorial.modelo.Editorial;
import editorial.modelo.Empleado;
import editorial.modelo.Genero;
import utils.EntityManagerHelper;

public class Programa {

	private static LocalDate parseDate(String date) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate.parse(date, formatter);
		try {
			return LocalDate.parse(date, formatter);
		} catch (DateTimeParseException e) {
			return null;
		}
	}

	private static String crearEditorial() {

		Editorial e = new Editorial();
		e.setFechaFundacion(parseDate("01/11/2020"));
		e.setNombre("Duermevela");
		e.setGeneroPrincipal(Genero.NARRATIVO);
		e.setTelefonos(Arrays.asList("968223322", "666897555"));
		Direccion d = new Direccion("Juan Carlos I", 22, "Murcia", "30220");
		e.setDireccion(d);

		EntityManager em = EntityManagerHelper.getEntityManager();

		try {
			em.getTransaction().begin();

			em.persist(e);

			em.getTransaction().commit();

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;

		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			EntityManagerHelper.closeEntityManager();
		}
		return e.getId();

	}

	private static Integer addEmpleadoDirector(String editorialId) {

		Empleado empleadoDirector = new Empleado();
		empleadoDirector.setNombre("Paolo");
		empleadoDirector.setApellidos("Pinto Poeta");
		empleadoDirector.setFechaNacimiento(parseDate("08/03/1980"));
		empleadoDirector.setNss("11111111111");
		empleadoDirector.setSalario(40500.0);

		EntityManager em = EntityManagerHelper.getEntityManager();

		try {
			em.getTransaction().begin();

			Editorial editorial = em.find(Editorial.class, editorialId);
			empleadoDirector.setEditorial(editorial); // el empleado trabaja en la editorial

			em.persist(empleadoDirector);

			editorial.setDirector(empleadoDirector);

			em.getTransaction().commit();

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
		EntityManagerHelper.closeEntityManager();
		return empleadoDirector.getId();
	}
	
	private static Integer addEmpleado(String editorialId) {

		Empleado nuevoEmpleado = new Empleado();
		nuevoEmpleado.setNombre("Luciana");
		nuevoEmpleado.setApellidos("Lucas Lax");
		nuevoEmpleado.setFechaNacimiento(parseDate("18/06/1982"));
		nuevoEmpleado.setNss("123456789");
		nuevoEmpleado.setSalario(32500.0);

		EntityManager em = EntityManagerHelper.getEntityManager();

		try {
			em.getTransaction().begin();

			Editorial editorial = em.find(Editorial.class, editorialId);
			editorial.addEmpleado(nuevoEmpleado);
			
			em.getTransaction().commit();

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
		EntityManagerHelper.closeEntityManager();
		return nuevoEmpleado.getId();
	}
	

	private static String addDistribuidor(String editorialId) {

		Distribuidor distribuidor = new Distribuidor();
		distribuidor.setCif("2w345567897643226633");
		distribuidor.setNombre("Distribuciones Mercurio");

		EntityManager em = EntityManagerHelper.getEntityManager();

		try {
			em.getTransaction().begin();

			Editorial editorial = em.find(Editorial.class, editorialId);

			// distribuidor.addEditorial(editorial);
			// em.persist(distribuidor);

			// editorial.addDistribuidor(distribuidor);

			em.getTransaction().commit();

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
		EntityManagerHelper.closeEntityManager();
		return distribuidor.getCif();
	}

	private static void cicloVidaEntidad() {

		Editorial e = new Editorial();
		e.setFechaFundacion(parseDate("20/05/2014"));
		e.setNombre("Hoja de Lata");
		e.setGeneroPrincipal(Genero.NARRATIVO);

		EntityManager em = EntityManagerHelper.getEntityManager();

		try {
			em.getTransaction().begin();

			em.persist(e);

			em.flush();

			if (em.contains(e)) {
				System.out.println("TRAS PERSIST: La editorial Hoja de Lata está Managed");
			} else {
				System.out.println("TRAS PERSIST: La editorial Hoja de Lata está Detached");
			}
			em.getTransaction().commit();

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			EntityManagerHelper.closeEntityManager();
		}

		// Al cerrar el entityManager que gestionaba la entidad, se queda detached.
		// Aunque volvamos a abrir otro, la editorial seguirá detached

		EntityManager em2 = EntityManagerHelper.getEntityManager();

		if (em2.contains(e)) {
			System.out.println("TRAS ENTITYMANAGER CLOSE: La editorial Hoja de Lata está Managed");
		} else {
			System.out.println("TRAS ENTITYMANAGER CLOSE: La editorial Hoja de Lata está Detached");
		}

		e = em2.merge(e);

		if (em2.contains(e)) {
			System.out.println("TRAS MERGE: La editorial Hoja de Lata está Managed");
		} else {
			System.out.println("TRAS MERGE: La editorial Hoja de Lata está Detached");
		}

		// La vuelvo a cerrar

		EntityManagerHelper.closeEntityManager();
		EntityManager em3 = EntityManagerHelper.getEntityManager();

		if (em3.contains(e)) {
			System.out.println("TRAS ENTITYMANAGER CLOSE 2: La editorial Hoja de Lata está Managed");
		} else {
			System.out.println("TRAS ENTITYMANAGER CLOSE 2: La editorial Hoja de Lata está Detached");
		}

		e = em3.find(Editorial.class, e.getId());

		if (em3.contains(e)) {
			System.out.println("TRAS FIND: La editorial Hoja de Lata está Managed");
		} else {
			System.out.println("TRAS FIND: La editorial Hoja de Lata está Detached");
		}

	}

	public static void main(String[] args) {
		String id = crearEditorial();
		addEmpleadoDirector(id);
		addEmpleado(id);
		addEmpleado(id);
		//addDistribuidor(id);
		//cicloVidaEntidad();

	}

}
