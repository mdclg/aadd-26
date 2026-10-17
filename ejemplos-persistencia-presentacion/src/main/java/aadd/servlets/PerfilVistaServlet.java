package aadd.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PerfilVistaServlet
 */
@WebServlet("/PerfilVistaServlet")
public class PerfilVistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nombre = (String) request.getAttribute("nombre");

		if (nombre == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No se ha recibido el nombre del usuario");
			return;
		}

		response.setContentType("text/html;charset=UTF-8");

		response.getWriter().println("<!DOCTYPE html>");
		response.getWriter().println("<html lang='es'>");
		response.getWriter().println("<head>");
		response.getWriter().println("<meta charset='UTF-8'>");
		response.getWriter().println("<title>Mi perfil</title>");
		response.getWriter().println("</head>");
		response.getWriter().println("<body>");

		response.getWriter().println("<h1>Mi perfil</h1>");
		response.getWriter().println("<p>Usuario: " + nombre + "</p>");

		response.getWriter().println("<p><a href='ZonaPrivadaServlet'>Volver a la zona privada</a></p>");

		response.getWriter().println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
