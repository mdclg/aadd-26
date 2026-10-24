package aadd.beans.ambitos;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ContadorBean implements Serializable{
	
	public ContadorBean() {
		System.out.println("Recarga de la vista");
	}

	private int contador = 0;

    public void incrementar() {
        contador++;
    }

    public int getContador() {
        return contador;
    }
}
