package aadd.beans.conversorvalidador;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

@FacesValidator(value = "validadorPremioNobelLit", managed = true)
public class PremioNobelLitValidator implements Validator<String> {

	@Inject
	private NobelApp premios;
	@Override
	public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
		{
			if (value == null || value.isEmpty()) {
				return; 
			}
			String pattern = "^\\d{4}-[A-Za-z\\s]+$";
			
			boolean isValid = value.matches(pattern);
			if(!isValid) {
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Formato inadecuado"));
			}
			
			String[] partes = value.split("-");
			
			PremioNobelLit resultado = premios.getNobelesLiteratura().get(Integer.parseInt(partes[0]));
			
			
			if(resultado != null && resultado.getNombre().equals(partes[1])) {
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN,"","Premiado duplicado"));
			}
			
		}
	}

}
