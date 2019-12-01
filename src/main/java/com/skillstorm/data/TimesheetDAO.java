package com.skillstorm.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class TimesheetDAO {

	private ConnectionFactory factory;
	public User user = new User();
	public TimeSheet time = new TimeSheet();

	public TimesheetDAO() {
	};
	
	public List<TimeSheet> viewTimeSheets() {
		List<TimeSheet> listTimeSheets = new LinkedList<>();
		Connection connection = null;
		ResultSet result = null;
		Statement statement = null;
		TimeSheet timeSheets = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			connection = getConnection();
			String sql = "select * from time_sheet";
			statement = connection.createStatement();
			result = statement.executeQuery(sql);

			while (result.next()) {
				timeSheets = new TimeSheet(result.getInt(1), result.getInt(2), result.getInt(3), result.getInt(4),
						result.getInt(5), result.getInt(6), result.getInt(7), result.getInt(8), result.getInt(9),
						result.getInt(10));
				listTimeSheets.add(timeSheets);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// JDBC objects
			close(connection, statement, result);
		}
		return listTimeSheets;
	}

	public List<TimeSheet> getTimeSheets(int userId) {
		List<TimeSheet> listTimeSheets = new LinkedList<>();
		Connection connection = null;
		ResultSet result = null;
		Statement statement = null;
		TimeSheet timeSheets = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			connection = getConnection();
			String sql = "select * from time_sheet where userId = " + userId;
			statement = connection.createStatement();
			result = statement.executeQuery(sql);

			while (result.next()) {
				timeSheets = new TimeSheet(result.getInt(1), result.getInt(2), result.getInt(3), result.getInt(4),
						result.getInt(5), result.getInt(6), result.getInt(7), result.getInt(8), result.getInt(9),
						result.getInt(10));
				listTimeSheets.add(timeSheets);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// JDBC objects
			close(connection, statement, result);
		}
		return listTimeSheets;
	}

	public List<User> getUsers() {
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
			String sql = "select * from user order by lastName";
			statement = connection.createStatement();
			result = statement.executeQuery(sql);

			while (result.next()) {
				User users = new User(result.getInt(1), result.getString(2), result.getString(3), result.getString(4),
						result.getString(5));

				listUsers.add(users);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// JDBC objects
			close(connection, statement, result);
		}
		return listUsers;
	}

	public List<User> getUser() {
		List<User> listUser = new LinkedList<>();
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
			connection = getConnection();
			String sql = "select userName, password from user where userName = ? AND password = ?";
			ps = connection.prepareStatement(sql);

			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());

			result = ps.executeQuery();

			if (result.next()) {
				User user = new User(result.getString(1), result.getString(2));
				listUser.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// JDBC objects
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listUser;
	}

	public TimeSheet createTimeSheet(TimeSheet timesheet) {
		Connection connection = null;
		ResultSet keys = null;
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			connection = getConnection();
			String sql = "insert into time_sheet(monday, tuesday, wednesday, thursday, friday, saturday, sunday, total_hours, userId)"
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

			ps = connection.prepareStatement(sql, new String[] { "timesheet_id" });

			System.out.println(timesheet.getMonday());

			ps.setInt(1, timesheet.getMonday());
			ps.setInt(2, timesheet.getTuesday());
			ps.setInt(3, timesheet.getWednesday());
			ps.setInt(4, timesheet.getThursday());
			ps.setInt(5, timesheet.getFriday());
			ps.setInt(6, timesheet.getSaturday());
			ps.setInt(7, timesheet.getSunday());
			ps.setInt(8, timesheet.getTotalHours());
			ps.setInt(9, timesheet.getUserId());

			ps.executeUpdate();

			keys = ps.getGeneratedKeys();

			while (keys.next()) {
				int timeSheetId = keys.getInt(1);
				timesheet.setTimeSheetId(timeSheetId);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			close(connection, ps, keys);
		}
		return timesheet;
	}

	public Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/time_sheet", "root",
				"password");
		return connection;
	}

	public void close(Connection connection, Statement statement, ResultSet result) {
		try {
			if (connection != null) {
				connection.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (result != null) {
				result.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void delete(String id) {
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
			connection = getConnection();
			String sql = "delete from time_sheet where timeSheetId = ?";
			ps = connection.prepareStatement(sql);

			int timeSheetId = Integer.parseInt(id);

			ps.setInt(1, timeSheetId);

			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// JDBC objects
			close(connection, ps, result);
		}
	}

	public TimeSheet getTimeSheet(String timeSheetId) throws Exception {
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
			int theTimeSheetId = Integer.parseInt(timeSheetId);

			connection = getConnection();
			String sql = "select * from time_sheet where timeSheetId = ?";

			ps = connection.prepareStatement(sql);

			ps.setInt(1, theTimeSheetId);

			result = ps.executeQuery();

			if (result.next()) {
				int monday = result.getInt("monday");
				int tuesday = result.getInt("tuesday");
				int wednesday = result.getInt("wednesday");
				int thursday = result.getInt("thursday");
				int friday = result.getInt("friday");
				int saturday = result.getInt("saturday");
				int sunday = result.getInt("sunday");
				int totalHours = result.getInt("total_hours");
				int userId = result.getInt("userId");

				time = new TimeSheet(theTimeSheetId, monday, tuesday, wednesday, thursday, friday, saturday, sunday,
						totalHours, userId);
			} else {
				throw new Exception("Could not find time sheet id : " + timeSheetId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// JDBC objects
			close(connection, ps, result);
		}
		return time;

	}

	public void updateTimeSheet(TimeSheet timeSheet) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			connection = getConnection();

			String sql = "update time_sheet "
					+ "set monday = ?, tuesday = ?, wednesday = ?, thursday = ?, friday = ?, saturday = ?, sunday = ?, userId = ? "
					+ "where timeSheetId = ?";

			ps = connection.prepareStatement(sql);

			ps.setInt(1, timeSheet.getMonday());
			ps.setInt(2, timeSheet.getTuesday());
			ps.setInt(3, timeSheet.getWednesday());
			ps.setInt(4, timeSheet.getThursday());
			ps.setInt(5, timeSheet.getFriday());
			ps.setInt(6, timeSheet.getSaturday());
			ps.setInt(7, timeSheet.getSunday());
//			ps.setInt(8, timeSheet.getTotalHours());
			ps.setInt(8, timeSheet.getUserId());
			ps.setInt(9, timeSheet.getTimeSheetId());

			ps.executeUpdate();
		} finally {
			// JDBC objects
			close(connection, ps, null);
		}

	}

}
