package encuestas.servicio.test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import encuestas.servicio.EncuestaResumen;
import encuestas.servicio.IServicioEncuestas;
import servicio.FactoriaServicios;

public class Programa {

	public static void main(String[] args) throws Exception {

		
		IServicioEncuestas servicio = FactoriaServicios.getServicio(IServicioEncuestas.class);
		
		// Configura la encuesta
		
		String titulo = "Fecha del parcial";
		String instrucciones = "Llevar DNI";
		LocalDateTime apertura = LocalDateTime.now();
		LocalDateTime cierre = LocalDateTime.now().plusDays(1);
		List<String> opciones = Arrays.asList("Jueves", "Viernes");
		
		// Alta de la encuesta
		
		String id = servicio.crear(titulo, instrucciones, apertura, cierre, opciones);
		
		// Voto
		
		servicio.votar(id, 1, "juan@um.es");
		
		System.out.println("¿Ha votado juan? " + servicio.haVotado(id, "juan@um.es"));
		System.out.println("¿Ha votado jose? " + servicio.haVotado(id, "jose@um.es"));
						
		for (EncuestaResumen resumen : servicio.getListadoResumen()) {
			System.out.println(resumen);
			
			if (! resumen.getId().equals(id))
				servicio.eliminar(resumen.getId());
		}
		
		System.out.println("fin.");

	}
}
