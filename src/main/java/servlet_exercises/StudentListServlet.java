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

@WebServlet("/students")
public class StudentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		StudentDAO studentDAO = new StudentDAO();
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		
		var studentList = studentDAO.getAllStudents();
		// toJson converts to javascript object
		var studentListJson = gson.toJson(studentList);
		
		out.println(studentListJson);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
