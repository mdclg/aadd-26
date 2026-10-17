package aadd.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContadorServlet
 */
public class ContadorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int contador = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContadorServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		++contador;
		
		try {
		    Thread.sleep(1000);
		} catch (InterruptedException e) {
		    Thread.currentThread().interrupt();
		    throw new ServletException(e);
		}

		response.setContentType("text/plain");
		response.getWriter().println("Contador: " + contador);
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
