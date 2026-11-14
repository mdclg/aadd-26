package jsonb;

import java.time.LocalDate;

public class Persona {
	private String nombre;
	private String email; // opcional
	private int edad;
	private LocalDate fechaRegistro;
	private double sueldo;

	public Persona() {

	}

	public Persona(String nombre, String email, int edad, double sueldo) {
		this.fechaRegistro = LocalDate.now();
		this.nombre = nombre;
		this.email = email;
		this.edad = edad;
		this.sueldo = sueldo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

}