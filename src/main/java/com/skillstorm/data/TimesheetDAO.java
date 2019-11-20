package com.skillstorm.data;

import java.sql.Connection;
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

	public List<TimeSheet> getTimeSheets(int timeSheetId) {
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
			String sql = "select * from time_sheet where timeSheetId = " + timeSheetId;
			statement = connection.createStatement();
			result = statement.executeQuery(sql);

			while (result.next()) {
				timeSheets = new TimeSheet(result.getInt(1), result.getInt(2), result.getInt(3), result.getInt(4),
						result.getInt(5), result.getInt(6), result.getInt(7));
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
			String sql = "insert into time_sheet(monday, tuesday, wednesday, thursday, friday, saturday, sunday)"
					+ "values(?, ?, ?, ?, ?, ?, ?)";

			ps = connection.prepareStatement(sql, new String[] { "timesheet_id" });
			
			System.out.println(timesheet.getMonday());

			ps.setInt(1, timesheet.getMonday());
			ps.setInt(2, timesheet.getTuesday());
			ps.setInt(3, timesheet.getWednesday());
			ps.setInt(4, timesheet.getThursday());
			ps.setInt(5, timesheet.getFriday());
			ps.setInt(6, timesheet.getSaturday());
			ps.setInt(7, timesheet.getSunday());

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

//	public User findByUserName(String userName) {
//		
//	}
//	
//	public List<Timesheet> findTimesheetsByUser(int id) {
//		
//	}
//	
//	public Timesheet findTimesheetById(int id) {
//		
//	}
//	
//	public Timesheet save(Timesheet t) {
//		
//	}
//	
//	public Timesheet update(Timesheet t) {
//		
//	}
//	
	public void delete(int id) {
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

			ps.setInt(1, id);

			ps.execute();
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
	}

}
