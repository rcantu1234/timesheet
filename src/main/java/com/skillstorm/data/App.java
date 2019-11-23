package com.skillstorm.data;

import java.sql.Date;

public class App {

	public static void main(String[] args) {

		User user = new User();
		TimesheetDAO timesheetDAO = new TimesheetDAO();

		System.out.println(timesheetDAO.getTimeSheets(3));

		String str = "2015-03-31";
		Date date = Date.valueOf(str);// converting string into sql date
		System.out.println(date);

	}

}
