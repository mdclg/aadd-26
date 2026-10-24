package aadd.beans.ambitos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ConfiguracionBean implements Serializable{
	
	private Map<String, String> configuraciones;

    public ConfiguracionBean() {
    	System.out.println("Se crea nuevo ConfiguracionWeb");
        configuraciones = new HashMap<>();
        configuraciones.put("appName", "Mi Aplicación JSF");
        configuraciones.put("version", "1.0");
        configuraciones.put("maxUsers", "100");
    }

    public String getConfiguracion(String key) {
        return configuraciones.get(key);
    }

    public void setConfiguracion(String key, String value) {
        configuraciones.put(key, value);
    }
	

}
