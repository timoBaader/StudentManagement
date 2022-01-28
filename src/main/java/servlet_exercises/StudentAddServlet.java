package servlet_exercises;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import data_access.StudentDAO;
import model.Status;
import model.Student;

@WebServlet("/addStudent")
public class StudentAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		StudentDAO studentDAO = new StudentDAO();
		Student student = new Student();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		student.setId(Integer.parseInt(request.getParameter("id")));
		student.setFirstName(request.getParameter("firstName"));
		student.setLastName(request.getParameter("lastName"));
		student.setAddress(request.getParameter("address"));
		student.setPostCode(request.getParameter("postCode"));
		student.setPostOffice(request.getParameter("postOffice"));
				
		
		
		// return code 0 = success, 1 = id already in use
		var errorCode = studentDAO.insertStudent(student);
		
		Gson gson = new Gson();
		String json = gson.toJson(new Status(errorCode));
		out.print(json);

	}

}
