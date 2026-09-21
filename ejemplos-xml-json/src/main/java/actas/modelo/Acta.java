package actas.modelo;

import java.util.LinkedList;

public class Acta {
	
	private String convocatoria;
	private String curso;
	private String asignatura;
	
	private LinkedList<Calificacion> calificaciones;
	private LinkedList<Diligencia> diligencias;
	public String getConvocatoria() {
		return convocatoria;
	}
	public void setConvocatoria(String convocatoria) {
		this.convocatoria = convocatoria;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getAsignatura() {
		return asignatura;
	}
	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}
	public LinkedList<Calificacion> getCalificaciones() {
		return calificaciones;
	}
	public void setCalificaciones(LinkedList<Calificacion> calificaciones) {
		this.calificaciones = calificaciones;
	}
	public LinkedList<Diligencia> getDiligencias() {
		return diligencias;
	}
	public void setDiligencias(LinkedList<Diligencia> diligencias) {
		this.diligencias = diligencias;
	}
	

}