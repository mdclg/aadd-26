package aadd.beans.conversorvalidador;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;


@FacesConverter(forClass=PremioNobelLit.class, managed=true)
public class PremioNobelLitConversor implements Converter<PremioNobelLit>{
	
	@Inject
	private NobelApp premios;

	@Override
	public PremioNobelLit getAsObject(FacesContext context, UIComponent component, String value) {

		if(value == null || value.isEmpty()) {
			return null;
		}
		try {
			return premios.getNobelesLiteratura().get(Integer.parseInt(value));
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
			throw new ConverterException();
		}

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, PremioNobelLit value) {
		if(value == null) {
			return "";
		}
		if(value.getAnyo() != null) {
			return value.getAnyo().toString();
		}
		else {
			throw new ConverterException();
		}
	}

}