package aadd.beans.ambitos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class CarritoBean implements Serializable{
	
	private List<String> items;

	private String item;
	
	@Inject
	private FacesContext facesContext;
	
	private ResourceBundle bundle;
	
	
	
	public CarritoBean() {
		System.out.println("Nuevo CarritoWeb");
        items = new ArrayList<>();
    }
	
	@PostConstruct
	public void initBundle() {

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

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}
    
    
}
