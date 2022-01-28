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

@WebServlet("/studentfetch")
public class StudentFetchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentDAO studentDAO = new StudentDAO();
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		
		var id = Integer.parseInt(request.getParameter("id"));
		
		var student = studentDAO.getStudentByID(id);
		// toJson converts to javascript object
		var studentJson = gson.toJson(student);
		
		out.println(studentJson);
		
	}

}
