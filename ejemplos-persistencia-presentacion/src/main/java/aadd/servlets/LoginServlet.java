package aadd.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// se obtienen los parámetros de la petición
		String username = request.getParameter("usuario");
		String password = request.getParameter("password");

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		/*
		Map<String, Usuario> usuarios = (Map<String, Usuario>) getServletContext().getAttribute("usuarios");

		if (username == null || password == null || usuarios == null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuario o contraseña incorrectos");
			return;
		}
		
		*/
		
		if (username == null || password == null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuario o contraseña incorrectos");
			return;
		}
		
		//Usuario usuario = usuarios.get(username.trim());
		
		boolean credencialesCorrectas = "usuarioPruebas".equals(username) && "aadd".equals(password);

        /*if (usuario == null
                || !usuario.getPassword().equals(password)) {

        	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html>");
			out.println("<html lang='es'><head><meta charset='UTF-8'>");
			out.println("<title>Error de acceso</title></head><body>");
			out.println("<h1>Credenciales incorrectas</h1>");
			out.println("<p>Comprueba el usuario y la contraseña.</p>");
			out.println("<a href='" + request.getContextPath() + "/login.html'>Volver al login</a>");
			out.println("</body></html>");
            return;
        }
        else {
        	credencialesCorrectas = true;
        }*/

		

		if (credencialesCorrectas) {

			// comprobamos por si acaso si había ya una sesión anterior, Si la hay se
			// invalida y se crea una nueva
			// El objetivo es que al hacer login empecemos de cero con la sesión
			HttpSession sesionAnterior = request.getSession(false);
			if (sesionAnterior != null) {
				sesionAnterior.invalidate();
			}

			HttpSession sesion = request.getSession(true);
			sesion.setAttribute("usuario", username);

			// Este servlet redirige a otro
			response.sendRedirect(request.getContextPath() + "/ZonaPrivadaServlet");
		} else {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html>");
			out.println("<html lang='es'><head><meta charset='UTF-8'>");
			out.println("<title>Error de acceso</title></head><body>");
			out.println("<h1>Credenciales incorrectas</h1>");
			out.println("<p>Comprueba el usuario y la contraseña.</p>");
			out.println("<a href='" + request.getContextPath() + "/login.html'>Volver al login</a>");
			out.println("</body></html>");
		}
	}
}
