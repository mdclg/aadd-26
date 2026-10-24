package aadd.beans.ambitos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class CarritoBean implements Serializable{
	
	private List<String> items;

	private String item;
	
	public CarritoBean() {
		System.out.println("Nuevo CarritoWeb");
        items = new ArrayList<>();
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
    }

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}
    
    
}
