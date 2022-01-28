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

@WebServlet("/deleteStudent")
public class StudentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		StudentDAO studentDAO = new StudentDAO();
		
		var studentToDeleteId = Integer.parseInt(request.getParameter("id"));
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");		
		
		var errorCode = studentDAO.studentDelete(studentToDeleteId);
		
		Gson gson = new Gson();
		String json = gson.toJson(new Status(errorCode));
		out.print(json);
	
	}

}
