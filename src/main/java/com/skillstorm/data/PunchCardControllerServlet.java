package com.skillstorm.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
				viewTimeSheets(req, resp);
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
				} catch (ParseException ex) {
					System.out.println(ex);
				}
				break;
			case "UPDATE":
				try {
					updateTimeSheet(req, resp);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			case "LOAD":
				try {
					loadTimeSheet(req, resp);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "DELETE":
				try {
					try {
						delete(req, resp);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (NumberFormatException ex) {
					System.out.println(ex);
				}
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

	private void updateTimeSheet(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, IOException, ServletException {
		int timeSheetId = Integer.parseInt(req.getParameter("timeSheetId"));

		System.out.println("Inside updateTimeSheet(). Time Sheet Id is : " + timeSheetId);

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

		int totalHours = mon + tues + wed + thurs + fri + sat + sun;

		TimeSheet time = new TimeSheet(timeSheetId, mon, tues, wed, thurs, fri, sat, sun, uId);

		time.toString();

		timesheetDAO.updateTimeSheet(time);

		getAllTimeSheets(req, resp);
	}

	private void loadTimeSheet(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String timeSheetId = req.getParameter("timeSheetId");

		TimeSheet theTimeSheet = timesheetDAO.getTimeSheet(timeSheetId);

		req.setAttribute("theTimeSheet", theTimeSheet);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/update-timesheet-form.jsp");
		dispatcher.forward(req, resp);
		System.out.println("Inside loadTimeSheet(). Time Sheet Id is : " + theTimeSheet.toString());
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		PrintWriter out = resp.getWriter();
		String timeSheetId = req.getParameter("timeSheetId");

		timesheetDAO.delete(timeSheetId);
		
		viewTimeSheets(req, resp);
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
				HttpSession session = req.getSession();
				session.setAttribute("userName", userName);
				System.out.println(req.getSession().getAttribute("userName"));
				viewTimeSheets(req, resp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void viewTimeSheets(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		PrintWriter out = resp.getWriter();
		
		List<TimeSheet> timeSheets = timesheetDAO.viewTimeSheets();

		out.println("<html>");
		out.println("<link href=\"resources/time_sheet.css\" rel=\"stylesheet\" type=\"text/css\" />\n");
		out.println(
				"<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css'>");
		out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js'></script>");
		out.println("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js'></script>");
		
		out.println("<div class=topnav>");
		out.println("<input type=\"hidden\" name=\"command\" value=\"HOME\" />");
		out.println("<div class=topnav>");
		out.println("<input type=hidden name=command value=HOME /> <a class=active href=http://localhost:8080/punchCard/home.jsp>Home</a><a href=http://localhost:8080/punchCard/enter_time.html>CreateTime Sheet</a> <a href=http://localhost:8080/punchCard/view_time_sheets.html>ViewTime Sheets</a><a href=http://localhost:8080/punchCard/logout.jsp>Logout</a></div></div>");

		out.println("<head><style>h1{color: black;}</style></head>");
		out.println("<table class='table table-hover' id=table border=1 CELLPADDING=0 CELLSPACING=0 WIDTH=50%>");	

		out.println("<thead>");
		out.println("<th colspan=10>");
		out.println("<h1 align=center>Time Sheets</h1>");
		out.println("</th>");
		out.println("</thead>");

		out.println("<thead align=center>");
		out.println("<th>Time Sheet Id</th>");
		out.println("<th>Monday</th>");
		out.println("<th>Tuesday</th>");
		out.println("<th>Wednesday</th>");
		out.println("<th>Thursday</th>");
		out.println("<th>Friday</th>");
		out.println("<th>Saturday</th>");
		out.println("<th>Sunday</th>");
		out.println("<th>Total Hours</th>");
		out.println("<th>User Id</th>");
		out.println("</thead>");

		for (TimeSheet list : timeSheets) {
			out.println("<tr class=success align=center> ");
			out.println("<td align=center>" + list.getTimeSheetId() + "</td>");
			out.println("<td align=center>" + list.getMonday() + "</td>");
			out.println("<td align=center>" + list.getTuesday() + "</td>");
			out.println("<td align=center>" + list.getWednesday() + "</td>");
			out.println("<td align=center>" + list.getThursday() + "</td>");
			out.println("<td align=center>" + list.getFriday() + "</td>");
			out.println("<td align=center>" + list.getSaturday() + "</td>");
			out.println("<td align=center>" + list.getSunday() + "</td>");
			out.println("<td align=center>" + list.getTotalHours() + "</td>");
			out.println("<td align=center>" + list.getUserId() + "</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("</html>");
	}

	// reflects enter_time.html which returns all results after entering hours.
	public void getAllTimeSheets(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		PrintWriter out = resp.getWriter();

		String userId = req.getParameter("userId");

		int uId = Integer.parseInt(userId);

		time.getUserId();

		List<TimeSheet> timeSheets = timesheetDAO.getTimeSheets(uId);

		req.setAttribute("timeSheets", timeSheets);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/test.jsp");
		dispatcher.forward(req, resp);
	}

	public void addTimeSheet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ParseException, ServletException {

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

		int totalHours = mon + tues + wed + thurs + fri + sat + sun;

		time = new TimeSheet(mon, tues, wed, thurs, fri, sat, sun, totalHours, uId);
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
