package com.skillstorm.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PunchCardControllerServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TimesheetDAO timesheetDAO = new TimesheetDAO();
	public TimeSheet time = new TimeSheet();
	public User user = new User();
//	public PunchCardControllerServlet p = new PunchCardControllerServlet();

	// @Override
//	public void init() throws ServletException {
//		super.init();
//
//		try {
////			timesheetDAO = new TimesheetDAO();
////			time = new TimeSheet();
//		} catch (Exception ex) {
//			throw new ServletException(ex);
//		}
//
//	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();

		String theCommand = req.getParameter("command");

		if (theCommand == null) {
			theCommand = "LOGIN";
		}

		try {
			switch (theCommand) {

			case "LOGIN":
				login(req, resp);
				break;
			case "ADD":
				try {
					addTimeSheet(req, resp);
				} catch (NumberFormatException ex) {
					resp.sendRedirect("http://localhost:8080/punchCard/error_page.html");
				}
				break;
			case "DELETE":
				timesheetDAO.delete(1);
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
				out.println("<div align=center><h1><h1>Welcome : " + userName + "</h1></div>");
				viewTimeSheet(req, resp);
//				System.out.println(getUser(req, resp));
			} else {
				credentials += "Invalid Name or Password!!!";
			}
			out.println("<div align=center><h1>" + credentials + "</h1></div>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void viewTimeSheet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();

		out.println("<html>");
		out.println("<table border=1 CELLPADDING=0 CELLSPACING=0 WIDTH=50% align=center>");

		out.println("<thead>");
		out.println("<th colspan=10>");
		out.println("<h1>Weekly Hours</h1>");
		out.println("</th>");
		out.println("</thead");

		out.println("<thead>");
		out.println("<th><p>Monday</p></th>");
		out.println("<th><p>Tuesday</p></th>");
		out.println("<th><p>Wednesday</p></th>");
		out.println("<th><p>Thursday</p></th>");
		out.println("<th><p>Friday</p></th>");
		out.println("<th><p>Saturday</p></th>");
		out.println("<th><p>Sunday</p></th>");
		out.println("<th><p>Edit</p></th>");
		out.println("<th><p>Update</p></th>");
		out.println("</thead>");

		out.println("<tr align=center>");
		out.println("<td align=center>" + time.getMonday() + "</td>");
		out.println("<td align=center>" + time.getTuesday() + "</td>");
		out.println("<td align=center>" + time.getWednesday() + "</td>");
		out.println("<td align=center>" + time.getThursday() + "</td>");
		out.println("<td align=center>" + time.getFriday() + "</td>");
		out.println("<td align=center>" + time.getSaturday() + "</td>");
		out.println("<td align=center>" + time.getSunday() + "</td>");
		out.println("<td align=center><a href=http://localhost:8080/punchCard/enter_time.html>Edit</a></td>");
		out.println("<td align=center><a href=#>Delete</a></td>");
		out.println("</tr>");

		out.println("</table>");
		out.println("</html>");

		System.out.println("TEST");
	}

	public void addTimeSheet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String monday = req.getParameter("monday");
		String tuesday = req.getParameter("tuesday");
		String wednesday = req.getParameter("wednesday");
		String thursday = req.getParameter("thursday");
		String friday = req.getParameter("friday");
		String saturday = req.getParameter("saturday");
		String sunday = req.getParameter("sunday");

		int mon = Integer.parseInt(monday);
		int tues = Integer.parseInt(tuesday);
		int wed = Integer.parseInt(wednesday);
		int thurs = Integer.parseInt(thursday);
		int fri = Integer.parseInt(friday);
		int sat = Integer.parseInt(saturday);
		int sun = Integer.parseInt(sunday);

		PrintWriter out = resp.getWriter();

		time = new TimeSheet(mon, tues, wed, thurs, fri, sat, sun);
		timesheetDAO.createTimeSheet(time);

		System.out.println(time.toString());

		viewTimeSheet(req, resp);
	}

	public String getUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		List<User> users = timesheetDAO.getUsers();
		String test = null;
		for (User user : users) {
			System.out.println(user.getUserName() + " " + user.getPassword());
			test = user.getUserName() + " " + user.getPassword();
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
