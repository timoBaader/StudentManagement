package data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import model.Student;

public class StudentDAO {

	public StudentDAO() {
		try {
			Class.forName(ConnectionParameters.jdbcDriver);
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		}
	}

	private Connection openConnection() throws SQLException {
		return DriverManager.getConnection(ConnectionParameters.connectionString, ConnectionParameters.username,
				ConnectionParameters.password);
	}

	public List<Student> getAllStudents() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Student> studentList = new ArrayList<>();

		try {
			connection = openConnection();

			String sqlText = "SELECT * FROM Student ORDER BY id";

			preparedStatement = connection.prepareStatement(sqlText);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String address = resultSet.getString("streetaddress");
				String postCode = resultSet.getString("postcode");
				String postOffice = resultSet.getString("postoffice");

				studentList.add(new Student(id, firstName, lastName, address, postCode, postOffice));
			}

		} catch (SQLException sqle) {
			System.out.println("\n[ERROR] StudentDAO: getAllStudents() failed. " + sqle.getMessage() + "\n");
			studentList = null;

		} finally {
			DbUtils.closeQuietly(resultSet, preparedStatement, connection);
		}

		return studentList;
	}

	public Student getStudentByID(int studentId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Student student = new Student();

		try {
			connection = openConnection();

			String sqlText = "SELECT * FROM Student WHERE id = ? ORDER BY id";

			preparedStatement = connection.prepareStatement(sqlText);
			preparedStatement.setInt(1, studentId);

			resultSet = preparedStatement.executeQuery();

			boolean rowFound = false;

			while (resultSet.next()) {
				rowFound = true;

				int id = resultSet.getInt("id");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String address = resultSet.getString("streetaddress");
				String postCode = resultSet.getString("postcode");
				String postOffice = resultSet.getString("postoffice");

				student.setId(id);
				student.setFirstName(firstName);
				student.setLastName(lastName);
				student.setAddress(address);
				student.setPostCode(postCode);
				student.setPostOffice(postOffice);
			}

			if (!rowFound) {
				System.out.println("No student with the id " + studentId + " was found.");
			}

		} catch (SQLException sqle) {
			System.out.println("[ERROR] StudentDAO: getStudentByID() failed. " + sqle.getMessage() + "\n");
			student = null;

		} finally {
			DbUtils.closeQuietly(resultSet, preparedStatement, connection);
		}

		return student;
	}

	public String getAllStudentsJSON() {
		var studentList = getAllStudents();
		Gson gson = new Gson();

		String result = gson.toJson(studentList);

		return result;
	}

	public int insertStudent(Student student) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int errorCode = -1;

		try {
			connection = openConnection();

			String sqlText = "INSERT INTO Student (id, firstName, lastName, streetaddress, postcode, postoffice) VALUES (?,?,?,?,?,?)";

			preparedStatement = connection.prepareStatement(sqlText);
			preparedStatement.setInt(1, student.getId());
			preparedStatement.setString(2, student.getFirstName());
			preparedStatement.setString(3, student.getLastName());
			preparedStatement.setString(4, student.getAddress());
			preparedStatement.setString(5, student.getPostCode());
			preparedStatement.setString(6, student.getPostOffice());

			preparedStatement.executeUpdate();
			errorCode = 0;

		} catch (SQLException sqle) {
			if (sqle.getErrorCode() == ConnectionParameters.PK_VIOLATION_ERROR) {
				errorCode = 1;
				System.out.println(
						"Cannot add the student. " + "The student id (" + student.getId() + ") is already in use.");
			} else {
				System.out.println("\n[ERROR] StudentDAO: insertStudent() failed. " + sqle.getMessage() + "\n");
				errorCode = -1;
			}

		} finally {
			DbUtils.closeQuietly(preparedStatement, connection);
		}

		return errorCode;
	}

	public int studentDelete(int studentId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int errorCode = -1;

		try {
			connection = openConnection();

			String sqlText = "DELETE FROM Student WHERE id = ?";

			preparedStatement = connection.prepareStatement(sqlText);
			preparedStatement.setInt(1, studentId);

			var count = preparedStatement.executeUpdate();
			if (count == 0) {
				throw new SQLException();
			}
			errorCode = 0;

		} catch (SQLException sqle) {
			System.out.println("No rows where deleted");
			errorCode = -1;
		} finally {
			DbUtils.closeQuietly(preparedStatement, connection);
		}
		return errorCode;
	}

	public int updateStudent(Student student) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int errorCode = -1;

		try {
			connection = openConnection();

			String sqlText = "UPDATE Student SET firstName = ?, lastName = ?, streetaddress = ?, postcode = ?, postoffice = ?  WHERE id = ?";

			preparedStatement = connection.prepareStatement(sqlText);
			preparedStatement.setString(1, student.getFirstName());
			preparedStatement.setString(2, student.getLastName());
			preparedStatement.setString(3, student.getAddress());
			preparedStatement.setString(4, student.getPostCode());
			preparedStatement.setString(5, student.getPostOffice());
			preparedStatement.setInt(6, student.getId());

			// returns rows affected
			var result = preparedStatement.executeUpdate();
			
			// check if student ID exists...
			
			var foundStudent = getStudentByID(student.getId());
			
			if(foundStudent == null) {
				errorCode = 1;
				throw new SQLException();
			}else if(result == 0) {
				errorCode = -1;
				throw new SQLException();
			}
			
			errorCode = 0;

		} catch (SQLException sqle) {
			System.out.println("No rows where Updated");
			errorCode = -1;
		} finally {
			DbUtils.closeQuietly(preparedStatement, connection);
		}
		return errorCode;
	}
}
