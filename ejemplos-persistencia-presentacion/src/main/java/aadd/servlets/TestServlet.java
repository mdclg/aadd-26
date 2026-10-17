package aadd.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public TestServlet() {
		// TODO Auto-generated constructor stub
	}

	private final AtomicInteger peticiones = new AtomicInteger(0);
	private final AtomicInteger get = new AtomicInteger(0);
	private final AtomicInteger post = new AtomicInteger(0);

	/*
	 * No se sobreescribe normalmente
	 */
	@Override
	public void init() throws ServletException {
		super.init();

		System.out.println("==================================");
		System.out.println("INIT: Servlet inicializado");
		System.out.println("Instancia: " + this);
		System.out.println("==================================");
	}

	/*
	 * No se sobreescribe normalmente
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int numero = peticiones.incrementAndGet();

		System.out.println("----------------------------------");
		System.out.println("SERVICE: petición número " + numero);
		System.out.println("Método HTTP: " + req.getMethod());
		System.out.println("Hilo: " + Thread.currentThread().getName());

		// Delega en doGet, doPost, etc.
		super.service(req, resp);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int numeroGet = get.incrementAndGet();

		System.out.println("DO GET: petición GET número " + numeroGet);

		mostrarRespuesta(request, response, "GET");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int numeroPost = post.incrementAndGet();

		System.out.println("DO POST: petición POST número " + numeroPost);

		mostrarRespuesta(request, response, "POST");
	}

	private void mostrarRespuesta(HttpServletRequest request, HttpServletResponse response, String metodo)
			throws IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html lang='es'>");
		out.println("<head><meta charset='UTF-8'>");
		out.println("<title>Ciclo de vida Servlet</title></head>");
		out.println("<body>");
		out.println("<h1>TestServlet</h1>");
		out.println("<p>Método HTTP: " + metodo + "</p>");
		out.println("<p>Total de peticiones: " + peticiones.get() + "</p>");
		out.println("<p>Peticiones GET: " + get.get() + "</p>");
		out.println("<p>Peticiones POST: " + post.get() + "</p>");
		out.println(
				"<p>Hora del servidor: " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "</p>");

		out.println("<form method='post' action='" + request.getContextPath() + "/TestServlet'>");
		out.println("<button type='submit'>Enviar POST</button>");
		out.println("</form>");

		out.println("</body></html>");
	}

	/*
	 * No se sobreescribe normalmente
	 */
	@Override
	public void destroy() {
		System.out.println("==================================");
		System.out.println("DESTROY: Servlet destruido");
		System.out.println("Total de peticiones: " + peticiones.get());
		System.out.println("==================================");
		super.destroy();
	}

}
