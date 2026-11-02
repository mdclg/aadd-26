package aadd.beans.composicion;

import java.io.Serializable;
import java.util.Arrays;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ProductosBuscadorWeb extends BaseBuscadorWeb<Producto> implements Serializable {

	public ProductosBuscadorWeb() {
	        setResultados(Arrays.asList(
	            new Producto("Portátil", "Ordenador portátil de alta gama", 900d),
	            new Producto("Ratón", "Ratón inalámbrico silencioso",15d),
	            new Producto("Teclado", "Teclado ergonómico",25d),
	            new Producto("Monitor", "Monitor 4K de 27 pulgadas",200d)
	        ));
	        filtrar();
	    }
}
