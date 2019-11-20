package com.skillstorm.data;

public class App {

	public static void main(String[] args) {
//		TimeSheet t = new TimeSheet(8, 7, 5, 4, 6, 6, 0);

		User user = new User();
		TimesheetDAO timesheetDAO = new TimesheetDAO();
		
//		System.out.println(timesheetDAO.getTimeSheets(10));
		
		System.out.println(timesheetDAO.getUsers());
		System.out.println(user.getUserId());
		

	}

}
