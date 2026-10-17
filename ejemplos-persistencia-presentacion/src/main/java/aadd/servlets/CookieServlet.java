package aadd.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieServlet
 */
@WebServlet("/CookieServlet")
public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idioma = "no seleccionado";

		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("idioma".equals(cookie.getName())) {
					idioma = cookie.getValue();
					break;
				}
			}
		}

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html lang='es'>");
		out.println("<head><meta charset='UTF-8'>");
		out.println("<title>Prueba de cookies</title></head>");
		out.println("<body>");

		out.println("<h1>Preferencia de idioma</h1>");
		out.println("<p>Idioma guardado: " + idioma + "</p>");

		out.println("<form method='post' action='" + request.getContextPath() + "/CookieServlet'>");

		out.println("<button name='idioma' value='es'>Español</button>");
		out.println("<button name='idioma' value='en'>English</button>");
		out.println("</form>");

		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idioma = request.getParameter("idioma");

		if ("es".equals(idioma) || "en".equals(idioma)) {
			Cookie cookie = new Cookie("idioma", idioma);

			// Disponible durante 7 días
			cookie.setMaxAge(7 * 24 * 60 * 60);

			// Disponible en las rutas de esta aplicación
			cookie.setPath(request.getContextPath().isEmpty() ? "/" : request.getContextPath());

			response.addCookie(cookie);
		}

		response.sendRedirect(request.getContextPath() + "/CookieServlet");
	}

}
