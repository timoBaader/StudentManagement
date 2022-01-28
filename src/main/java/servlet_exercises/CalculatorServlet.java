package servlet_exercises;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class CalculatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. Get a PrintWriter object for writing the text to be sent to the browser
		PrintWriter out = response.getWriter();

		// 2. Get the values of the request parameters
		String xAsString = request.getParameter("x");
		String yAsString = request.getParameter("y");

		String operation = request.getParameter("operation");

		int x = Integer.parseInt(xAsString);
		int y = Integer.parseInt(yAsString);

		int result = 0;

		if (operation.equals("add")) {
			result = add(x, y);
			out.println(x + " + " + y + " = " + result);
		} else if (operation.equals("multiply")) {
			result = multiply(x, y);
			out.println(x + " * " + y + " = " + result);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public int add(int x, int y) {
		return x + y;
	}

	public int multiply(int x, int y) {
		return x * y;
	}

}
