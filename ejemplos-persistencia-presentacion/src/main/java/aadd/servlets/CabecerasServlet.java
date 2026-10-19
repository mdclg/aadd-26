package aadd.servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CabecerasServlet
 */
@WebServlet("/cabeceras")
public class CabecerasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CabecerasServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userAgent = request.getHeader("User-Agent");
        String idioma = request.getHeader("Accept-Language");
        String accept = request.getHeader("Accept");

        response.setHeader("X-Aplicacion", "Clase Servlets");
        response.setHeader("Cache-Control", "no-store");

        if (accept != null && accept.contains("application/json")) {

            response.setContentType("application/json;charset=UTF-8");

            response.getWriter().println("{");
            response.getWriter().println(
                "  \"userAgent\": \"" + userAgent + "\",");
            response.getWriter().println(
                "  \"idioma\": \"" + idioma + "\"");
            response.getWriter().println("}");

        } else {

            response.setContentType("text/html;charset=UTF-8");

            response.getWriter().println("<!DOCTYPE html>");
            response.getWriter().println("<html lang=\"es\">");
            response.getWriter().println("<head>");
            response.getWriter().println(
                "<meta charset=\"UTF-8\">");
            response.getWriter().println(
                "<title>Inspector de cabeceras</title>");
            response.getWriter().println("</head><body>");

            response.getWriter().println(
                "<h1>Cabeceras de tu navegador</h1>");

            response.getWriter().println("<h2>User-Agent</h2>");
            response.getWriter().println(
                "<p>" + userAgent + "</p>");

            response.getWriter().println("<h2>Accept-Language</h2>");
            response.getWriter().println(
                "<p>" + idioma + "</p>");

            response.getWriter().println("<h2>Accept</h2>");
            response.getWriter().println(
                "<p>" + accept + "</p>");

            
            response.getWriter().println("</body></html>");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
