package com.skillstorm.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.skillstorm.data.dao.TimesheetDAO;

public class PunchCardControllerServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TimesheetDAO timesheetDAO = new TimesheetDAO();
	public TimeSheet time = new TimeSheet();
	public User user = new User();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();

		String theCommand = req.getParameter("command");

		if (theCommand == null) {
			theCommand = "LOGIN";
		}

		try {
			switch (theCommand) {

			case "HOME":
				resp.sendRedirect("http://localhost:8080/punchCard/home.html");
				break;
			case "LOGIN":
				login(req, resp);
				break;
			case "VIEW":
				getAllTimeSheets(req, resp);
				break;
			case "ADD":
				try {
					addTimeSheet(req, resp);
				} catch (NumberFormatException ex) {
					System.out.println(ex);
					resp.sendRedirect("http://localhost:8080/punchCard/error_page.html");
				}
				break;
			case "DELETE":
				delete(req, resp);
				break;
			default:
				login(req, resp);
				break;
			}
		} catch (NullPointerException ex) {
			ex.printStackTrace();
			System.out.println("No values coming in!!!");
		}
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String timeSheetId = req.getParameter("timeSheetId");

		int timeSheet = Integer.parseInt(timeSheetId);

		timesheetDAO.delete(timeSheet);

		resp.sendRedirect("http://localhost:8080/punchCard/home.html");
	}

	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		PrintWriter out = resp.getWriter();

		String userName = req.getParameter("userName");
		String password = req.getParameter("password");

		Connection connection = null;
		ResultSet result = null;
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			connection = timesheetDAO.getConnection();
			String sql = "select userName, password from user where userName = ? AND password = ?";
			ps = connection.prepareStatement(sql);

			ps.setString(1, userName);
			ps.setString(2, password);

			result = ps.executeQuery();

			String credentials = "";

			if (result.next()) {
//				out.println("<div align=center><h1><h1>Welcome : " + user.getFirstName() + " " + user.getLastName() + "</h1></div>");
				resp.sendRedirect("http://localhost:8080/punchCard/home.html");
				System.out.println(getUsers(req, resp));
			} else {
				credentials += "Invalid Name or Password!!!";
			}
			out.println("<div align=center><h1>" + credentials + "</h1></div>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getAllTimeSheets(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();	
		
		String userId = req.getParameter("userId");
		
		int uId = Integer.parseInt(userId);
		
		List<TimeSheet> timeSheets = timesheetDAO.getTimeSheets(uId);
		
		out.println("<html>");
		out.println("<table border=1 CELLPADDING=0 CELLSPACING=0 WIDTH=50% align=center>");

		out.println("<thead>");
		out.println("<th colspan=9>");
		out.println("<h1>Weekly Hours</h1>");
		out.println("</th>");
		out.println("</thead");

		out.println("<thead>");
		out.println("<th><p>Time Sheet Id</p></th>");
		out.println("<th><p>Monday</p></th>");
		out.println("<th><p>Tuesday</p></th>");
		out.println("<th><p>Wednesday</p></th>");
		out.println("<th><p>Thursday</p></th>");
		out.println("<th><p>Friday</p></th>");
		out.println("<th><p>Saturday</p></th>");
		out.println("<th><p>Sunday</p></th>");
		out.println("<th><p>User Id</p></th>");		
		out.println("</thead>");
	
		for(TimeSheet list : timeSheets) {
			out.println("<tr align=center>");
			out.println("<td align=center>" + list.getTimeSheetId() + "</td>");
			out.println("<td align=center>" + list.getMonday() + "</td>");
			out.println("<td align=center>" + list.getTuesday() + "</td>");
			out.println("<td align=center>" + list.getWednesday() + "</td>");
			out.println("<td align=center>" + list.getThursday() + "</td>");
			out.println("<td align=center>" + list.getFriday() + "</td>");
			out.println("<td align=center>" + list.getSaturday() + "</td>");
			out.println("<td align=center>" + list.getSunday() + "</td>");
			out.println("<td align=center>" + list.getUserId() + "</td>");
			out.println("</tr>");
//			out.println("<td align=center><a href=http://localhost:8080/punchCard/enter_time.html>Edit</a></td>");
//			out.println("<td align=center><a href=http://localhost:8080/punchCard/delete_time.html>Delete</a></td>");
		}
			
		out.println("<tr align=center>");
		out.println("<td align=center colspan=4><input type=submit value=Delete /></td>");
		out.println("<td align=center colspan=4><input type=submit value=Save /></td>");
		out.println("</tr>");

		out.println("</table>");
		out.println("</html>");
	}

	public void viewTimeSheet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();

		out.println("<html>");
		out.println("<table border=1 CELLPADDING=0 CELLSPACING=0 WIDTH=50% align=center>");

		out.println("<thead>");
		out.println("<th colspan=9>");
		out.println("<h1>Weekly Hours</h1>");
		out.println("</th>");
		out.println("</thead");

		out.println("<thead>");
		out.println("<th><p>Time Sheet Id</p></th>");
		out.println("<th><p>Monday</p></th>");
		out.println("<th><p>Tuesday</p></th>");
		out.println("<th><p>Wednesday</p></th>");
		out.println("<th><p>Thursday</p></th>");
		out.println("<th><p>Friday</p></th>");
		out.println("<th><p>Saturday</p></th>");
		out.println("<th><p>Sunday</p></th>");
		out.println("<th><p>User Id</p></th>");
		
		out.println("</thead>");
	
		out.println("<tr align=center>");
		out.println("<td align=center>" + time.getTimeSheetId() + "</td>");
		out.println("<td align=center>" + time.getMonday() + "</td>");
		out.println("<td align=center>" + time.getTuesday() + "</td>");
		out.println("<td align=center>" + time.getWednesday() + "</td>");
		out.println("<td align=center>" + time.getThursday() + "</td>");
		out.println("<td align=center>" + time.getFriday() + "</td>");
		out.println("<td align=center>" + time.getSaturday() + "</td>");
		out.println("<td align=center>" + time.getSunday() + "</td>");
		out.println("<td align=center>" + time.getUserId() + "</td>");
		out.println("</tr>");
			
		out.println("<tr align=center>");
		out.println("<td align=center colspan=4><input type=submit value=Delete /></td>");
		out.println("<td align=center colspan=4><input type=submit value=Save /></td>");
		out.println("</tr>");

		out.println("</table>");
		out.println("</html>");
	}

	public void addTimeSheet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String monday = req.getParameter("monday");
		String tuesday = req.getParameter("tuesday");
		String wednesday = req.getParameter("wednesday");
		String thursday = req.getParameter("thursday");
		String friday = req.getParameter("friday");
		String saturday = req.getParameter("saturday");
		String sunday = req.getParameter("sunday");
		String userId = req.getParameter("userId");

		int mon = Integer.parseInt(monday);
		int tues = Integer.parseInt(tuesday);
		int wed = Integer.parseInt(wednesday);
		int thurs = Integer.parseInt(thursday);
		int fri = Integer.parseInt(friday);
		int sat = Integer.parseInt(saturday);
		int sun = Integer.parseInt(sunday);
		int uId = Integer.parseInt(userId);

		time = new TimeSheet(mon, tues, wed, thurs, fri, sat, sun, uId);
		timesheetDAO.createTimeSheet(time);

		System.out.println(time.toString());

		getAllTimeSheets(req, resp);
	}

	public List<TimeSheet> getTimeSheets(HttpServletRequest req, HttpServletResponse resp) {
		String timeSheetId = req.getParameter("timeSheetId");

		int id = Integer.parseInt(timeSheetId);

		List<TimeSheet> listSheets = timesheetDAO.getTimeSheets(id);

		return listSheets;
	}

	public String getUsers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		List<User> users = timesheetDAO.getUsers();
		String test = null;
		for (User user : users) {
			System.out.println(user.getUserName() + " " + user.getPassword());
			out.println(user.getUserName() + " " + user.getPassword());
		}
		return test;
	}

	public void listUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		List<User> users = timesheetDAO.getUsers();

		out.println("<html>");
		out.println("<table border=1 CELLPADDING=0 CELLSPACING=0 WIDTH=50% align=center>");

		out.println("<thead>");
		out.println("<th colspan=5>");
		out.println("<h1>USERS</h1>");
		out.println("</th>");
		out.println("</thead");

		for (User list : users) {
			out.println("<tr>");
			out.println("<td align=center>" + list.getUserId() + "</td>");
			out.println("<td align=center>" + list.getFirstName() + "</td>");
			out.println("<td align=center>" + list.getLastName() + "</td>");
			out.println("<td align=center>" + list.getUserName() + "</td>");
			out.println("<td align=center>" + list.getPassword() + "</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("</html>");
	}
}
