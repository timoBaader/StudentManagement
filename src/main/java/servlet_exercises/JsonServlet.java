package servlet_exercises;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Student;

@WebServlet("/Jsontest")
public class JsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		List<Student> studentList = new ArrayList<>();

		Student anton = new Student();
		anton.setId(1);
		anton.setFirstName("Anton");
		anton.setLastName("Phillip");
		anton.setAddress("Swedenvägen");
		anton.setPostCode("00500");
		anton.setPostOffice("Stockholm");

		Student timo = new Student();
		timo.setId(2);
		timo.setFirstName("Timo");
		timo.setLastName("Baader");
		timo.setAddress("Opastinsilta");
		timo.setPostCode("00500");
		timo.setPostOffice("Helsinki");

		studentList.add(anton);
		studentList.add(timo);

		Gson gson = new Gson();

		var json = gson.toJson(studentList);

		out.println(json);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
