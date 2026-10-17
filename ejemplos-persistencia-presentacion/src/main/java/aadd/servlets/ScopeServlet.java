package aadd.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ScopeServlet
 */
@WebServlet("/ScopeServlet")
public class ScopeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Compartido entre las peticiones de esta instancia del servlet
	private final AtomicInteger contadorServlet = new AtomicInteger(0);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		// 1. Variable local: pertenece a esta ejecución
		int contadorLocal = 1;

		// 2. Contador del servlet
		int visitasServlet = contadorServlet.incrementAndGet();

		// 3. Obtenemos la sesión actual y guardamos un contador
		HttpSession session = request.getSession();

		AtomicInteger contadorSesion;
		synchronized (session) {
			contadorSesion = (AtomicInteger) session.getAttribute("contadorSesion");

			if (contadorSesion == null) {
				contadorSesion = new AtomicInteger(0);
				session.setAttribute("contadorSesion", contadorSesion);
			}
		}

		int visitasSesion = contadorSesion.incrementAndGet();

		// 4. Obtenemos el contexto de aplicación y guardamos un contador
		AtomicInteger contadorAplicacion;
		synchronized (getServletContext()) {
			contadorAplicacion = (AtomicInteger) getServletContext().getAttribute("contadorAplicacion");

			if (contadorAplicacion == null) {
				contadorAplicacion = new AtomicInteger(0);
				getServletContext().setAttribute("contadorAplicacion", contadorAplicacion);
			}
		}

		int visitasAplicacion = contadorAplicacion.incrementAndGet();

		// Respuesta HTML
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html lang='es'>");
		out.println("<head><meta charset='UTF-8'>");
		out.println("<title>Ámbitos de datos</title></head>");
		out.println("<body>");

		out.println("<h1>Ámbitos de datos en un Servlet</h1>");

		out.println("<p>Variable local: " + contadorLocal + "</p>");
		out.println("<p>Contador del servlet: " + visitasServlet + "</p>");
		out.println("<p>Contador de esta sesión: " + visitasSesion + "</p>");
		out.println("<p>Contador de la aplicación: " + visitasAplicacion + "</p>");
		
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
