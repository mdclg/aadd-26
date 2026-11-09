package aadd.beans.errores;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/*
@Named
@SessionScoped
*/
public class CarritoErroresBean{
	
	private boolean error;
	
	private List<String> items;

	private String item;
	
	private String fecha;
	@Inject
	private FacesContext facesContext;
	
	private ResourceBundle bundle;
	
	
	
	public CarritoErroresBean() {
		error = false;
        items = new ArrayList<>();
        bundle = ResourceBundle.getBundle("i18n.text", facesContext.getViewRoot().getLocale());
    }
	
	
    public void agregarItem() {
        items.add(item);
    }

    public List<String> getItems() {
        return items;
    }

    public int getCantidad() {
        return items.size();
    }

    public void limpiarCarrito() {
        items.clear();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                bundle.getString("successClean")));
    }
    
    public void hacerPedido() {
    	if(fecha == null || items.isEmpty()) {
    		 facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
    	               "No se ha podido realizar el pedido"));
    		 error = true;
    		return;
    	}
    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
	               "Pedidio realizado"));
    	error = false;
    	
    }

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
    
    
}
