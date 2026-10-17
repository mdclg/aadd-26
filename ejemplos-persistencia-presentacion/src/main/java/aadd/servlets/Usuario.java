package aadd.servlets;

public class Usuario {

	private String nombre;
	private String apellidos;
	private String usermail;
	private String username;
	private String password;
			
	public Usuario() {
		super();
	}
	public Usuario(String nombre, String apellidos, String usermail, String username, String password) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.usermail = usermail;
		this.username = username;
		this.password = password;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getUsermail() {
		return usermail;
	}
	public void setUsermail(String usermail) {
		this.usermail = usermail;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
