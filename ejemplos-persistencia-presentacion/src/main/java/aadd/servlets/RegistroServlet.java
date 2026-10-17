package aadd.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistroServlet
 */
@WebServlet("/RegistroServlet")
public class RegistroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistroServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		ConcurrentHashMap<String, Usuario> usuarios = (ConcurrentHashMap<String, Usuario>) getServletContext()
				.getAttribute("usuarios");

		if (usuarios == null) {
			synchronized (getServletContext()) {
				usuarios = (ConcurrentHashMap<String, Usuario>) getServletContext().getAttribute("usuarios");

				if (usuarios == null) {
					usuarios = new ConcurrentHashMap<>();

					getServletContext().setAttribute("usuarios", usuarios);
				}
			}
		}

		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String usermail = request.getParameter("usermail");
		String username = request.getParameter("usuario");
		String password = request.getParameter("password");

		if (nombre.isBlank() || apellidos.isBlank() || usermail.isBlank() || username.isBlank() || password.isBlank()) {

			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Debes indicar usuario y contraseña");
			return;
		}

		nombre = nombre.trim();

		Usuario nuevo = new Usuario(nombre, apellidos, usermail, username, password);

		Usuario existente = usuarios.putIfAbsent(username, nuevo);

		if (existente != null) {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().println("<h1>El usuario ya existe</h1>");
			return;
		}

		response.sendRedirect("login.html");
	}

}
