package com.skillstorm.data;

import com.skillstorm.data.dao.TimesheetDAO;

public class App {

	public static void main(String[] args) {

		User user = new User();
		TimesheetDAO timesheetDAO = new TimesheetDAO();

		System.out.println(timesheetDAO.getTimeSheets(3));

	}

}
