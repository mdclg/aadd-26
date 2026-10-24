package aadd.beans.parametros;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ProductoBeanV2 implements Serializable{

	private String productId;
	private String productName;
	@Inject
	private FacesContext facesContext;
	
	

	public String getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	@PostConstruct
	public void loadProductData() {
		productId = facesContext.getExternalContext().getRequestParameterMap().get("id");
		if (productId.equals("1")) {
			productName = "Producto A";
		} else if (productId.equals("2")) {
			productName = "Producto B";
		} else {
			productName = "Producto Desconocido";
		}
	}
}
