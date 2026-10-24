package aadd.beans.parametros;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ProductoBean implements Serializable {
	
	private Integer productId;
	private String productName;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void loadProductData() {
		if (productId == 1) {
			productName = "Producto A";
		} else if (productId == 2) {
			productName = "Producto B";
		} else {
			productName = "Producto Desconocido";
		}
	}
}
