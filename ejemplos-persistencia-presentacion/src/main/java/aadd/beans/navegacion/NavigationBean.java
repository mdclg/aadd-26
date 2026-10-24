package aadd.beans.navegacion;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class NavigationBean implements Serializable{
	
	@Inject 
	private FacesContext facesContext;

    public void navigateUsingFacesContext() {
        try {
            facesContext.getExternalContext().redirect("destino.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String navigateUsingAction() {
        return "destino?faces-redirect=true";
    }


    // Redirección
    public String redirectToPage() {
        return "destino?faces-redirect=true"; 
    }

    // Forward
    public String forwardToPage() {
        return "destino"; 
    }
}
