package aadd.beans.conversorvalidador;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class PremioNobelLitBean implements Serializable{
	
	@Inject
	private NobelApp premios;
	
	@Inject
	private FacesContext facesContext;

	private PremioNobelLit premioNobel;
	
	private String inputNobel;

	
	public PremioNobelLit getPremioNobel() {
		return premioNobel;
	}

	public void setPremioNobel(PremioNobelLit premioNobel) {
		this.premioNobel = premioNobel;
	}

	public String getInputNobel() {
		return inputNobel;
	}

	public void setInputNobel(String inputNobel) {
		this.inputNobel = inputNobel;
	}

	
	public void addNobel() {
		System.out.println("Llama a ADD NOBEL");
		String[] partes = inputNobel.split("-");
		premios.addNewNobel(Integer.valueOf(partes[0]), partes[1]);
		
		 facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
	                "Premio Nobel para el año "+partes[0]+" añadido."));
	}


}
