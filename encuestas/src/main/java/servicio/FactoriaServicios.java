package servicio;

import java.util.HashMap;
import java.util.Map;

import utils.PropertiesReader;

/*
 * Factoría que encapsula la implementación de un servicio.
 * 
 * Utiliza un fichero de propiedades para cargar la implementación.
 *
 * Los servicios se gestionan como un singleton (instancia única).
 *
 */

public class FactoriaServicios {
	
	private static final String PROPERTIES = "servicios.properties";
	
	private static Map<Class<?>, Object> servicios = new HashMap<>();
	
	@SuppressWarnings("unchecked")
	public static <T> T getServicio(Class<T> servicio) {
				
			
			try {
				if (servicios.containsKey(servicio)) {
					return (T) servicios.get(servicio);
				}
				else {
					PropertiesReader properties = new PropertiesReader(PROPERTIES);			
					String clase = properties.getProperty(servicio.getName());
					
					T servicioInstancia = (T) Class.forName(clase).getConstructor().newInstance();
					servicios.put(servicio, servicioInstancia);
					return servicioInstancia;
				}
				
			}
			catch (Exception e) {
				
				e.printStackTrace(); // útil para depuración
				
				throw new RuntimeException("No se ha podido obtener la implementación del servicio: " + servicio.getName());
			}
			
	}
	
}
