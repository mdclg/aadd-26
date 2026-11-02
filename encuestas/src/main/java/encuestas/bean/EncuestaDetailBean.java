package encuestas.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import encuestas.dto.EncuestaDTO;
import encuestas.servicio.IServicioEncuestas;
import servicio.FactoriaServicios;

@Named
@ViewScoped
@SuppressWarnings("serial")
public class EncuestaDetailBean implements Serializable{

	private String idEncuesta;	
	private IServicioEncuestas servicioEncuestas;	
	private EncuestaDTO encuesta;	
	
	@Inject
	protected FacesContext facesContext;
		
	public EncuestaDetailBean() {		
		servicioEncuestas = FactoriaServicios.getServicio(IServicioEncuestas.class);
    }
    public void load() {
        try {
        	encuesta = servicioEncuestas.getEncuesta(idEncuesta);
        } catch (Exception e) {
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));
        }
    }
	public String getIdEncuesta() {
		return idEncuesta;
	}
	public void setIdEncuesta(String idEncuesta) {
		this.idEncuesta = idEncuesta;
	}
	public EncuestaDTO getEncuesta() {
		return encuesta;
	}
	    
}
