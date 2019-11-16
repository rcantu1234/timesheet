package com.skillstorm.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PunchCardServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PunchCardServlet punch = new PunchCardServlet();		
		punch.getUsers(req, resp);		
	}
	
	public List<User> getUsers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		
		resp.setContentType("text/html");
		
		List<User> listUsers = new LinkedList<>();
		Connection connection = null;
		ResultSet result = null;
		Statement statement = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			connection = getConnection();
			String sql = "select * from user";
			statement = connection.createStatement();
			result = statement.executeQuery(sql);
					
			while(result.next()) {
				User users = new User(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5));
				
				listUsers.add(users);
			}
			out.println("<html>");
			out.println("<table border=1 CELLPADDING=0 CELLSPACING=0 WIDTH=50% align=center =center>");
			
			for(User list : listUsers) {
				out.println("<tr>");
				out.println("<td align=center>" + list.getUserId() + "</td>");
				out.println("<td align=center>" + list.getFirstName() + "</td>");
				out.println("<td align=center>" + list.getLastName() + "</td>");
				out.println("<td align=center>" + list.getUserName()+ "</td>");
				out.println("<td align=center>" + list.getPassword()+ "</td>");
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("</html>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listUsers;
	}
	
	public static Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/time_sheet", "root", "password");

		return connection;
	}
	
	public static List<Student> getStudents() {
		List<Student> listStudents = new LinkedList();
		Connection connection = null;
		ResultSet result = null;
		Statement statement = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			connection = getConnection();
			String sql = "select * from student";
			
			statement = connection.createStatement();
			
			result = statement.executeQuery(sql);
			
			while(result.next()) {
				Student students = new Student(result.getInt(1), result.getString(2), result.getString(3), result.getString(4));
				
				listStudents.add(students);
			}
			
			for(Student list : listStudents) {
				list.displayStudents();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listStudents;
	}
}
