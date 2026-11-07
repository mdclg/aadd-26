package encuestas.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import encuestas.dto.EncuestaDTO;
import encuestas.servicio.IServicioEncuestas;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;

@Named
@ViewScoped
@SuppressWarnings("serial")
public class EncuestaDetailBean implements Serializable{

	private String idEncuesta;	
	private IServicioEncuestas servicioEncuestas;	
	private EncuestaDTO encuesta;
	
	private String usuario;
	private String opcion;
	
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
    
    public void votar() {
		if (usuario == null || usuario.isBlank()) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe indicar un nombre para votar"));
			return;
		}
		if (opcion == null) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe seleccionar una de las opciones"));
			return;
		}
		
		int opcionSeleccionado = encuesta.getOpciones().indexOf(opcion) + 1;
		
		try {
			servicioEncuestas.votar(idEncuesta, opcionSeleccionado, usuario);
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Voto realizado"));
		} catch (IllegalArgumentException e) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "",e.getMessage()));
		} catch (RepositorioException e) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se pudo votar "+e.getMessage()));
		} catch (EntidadNoEncontrada e) {
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "La encuesta no existe"));
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
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getOpcion() {
		return opcion;
	}
	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}
	    
}
