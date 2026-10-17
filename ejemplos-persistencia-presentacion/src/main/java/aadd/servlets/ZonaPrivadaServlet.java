package aadd.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ZonaPrivadaServlet
 */
@WebServlet("/ZonaPrivadaServlet")
public class ZonaPrivadaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession sesion = request.getSession(false);

		
		String usuario = (String) sesion.getAttribute("usuario");

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html lang='es'><head><meta charset='UTF-8'>");
		out.println("<title>Zona privada</title></head><body>");
		out.println("<h1>Bienvenido, " + usuario + "</h1>");
		out.println("<p>Has iniciado sesión correctamente.</p>");

		out.println("<form method='post' action='" + request.getContextPath() + "/LogoutServlet'>");
		out.println("<button type='submit'>Cerrar sesión</button>");
		response.getWriter().println("<p><a href='PerfilServlet'>Ver perfil</a></p>");
		out.println("</form>");

		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
