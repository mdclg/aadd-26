package aadd.beans.localizacion;

import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class PreferenciasBean implements Serializable {

	private Locale localeActual;
	@Inject
	private FacesContext facesContext;

	private ResourceBundle bundle;

	private String idioma;

	@PostConstruct
	public void init() {

		// localeActual = facesContext.getViewRoot().getLocale();
		localeActual = facesContext.getExternalContext().getRequestLocale();
		idioma = localeActual.toLanguageTag();
		System.out.println("idioma "+idioma);

		bundle = ResourceBundle.getBundle("i18n.text", localeActual);
	}

	public void reload() {
		facesContext.getPartialViewContext().getEvalScripts().add("location.replace(location)");

	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
		localeActual = Locale.forLanguageTag(idioma);
		bundle = ResourceBundle.getBundle("i18n.text", localeActual);

		
	}
	
	public void recargarPagina() {
		String url = facesContext.getExternalContext().getRequestContextPath();

		try {
			facesContext.getExternalContext().redirect(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getIdioma() {
		return idioma;
	}

	public Locale getLocaleActual() {
		return localeActual;
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

}
