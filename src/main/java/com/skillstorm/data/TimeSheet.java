package com.skillstorm.data;

public class TimeSheet {
	private int timeSheetId;
	private int monday;
	private int tuesday;
	private int wednesday;
	private int thursday;
	private int friday;
	private int saturday;
	private int sunday;
	
	public TimeSheet() {};

	public TimeSheet(int monday, int tuesday, int wednesday, int thursday, int friday, int saturday, int sunday) {
		this.monday = monday;
		this.tuesday = tuesday;
		this.wednesday = wednesday;
		this.thursday = thursday;
		this.friday = friday;
		this.saturday = saturday;
		this.sunday = sunday;
	}

	public int getTimeSheetId() {
		return timeSheetId;
	}

	public void setTimeSheetId(int timeSheetId) {
		this.timeSheetId = timeSheetId;
	}

	public int getMonday() {
		return monday;
	}

	public void setMonday(int monday) {
		this.monday = monday;
	}

	public int getTuesday() {
		return tuesday;
	}

	public void setTuesday(int tuesday) {
		this.tuesday = tuesday;
	}

	public int getWednesday() {
		return wednesday;
	}

	public void setWednesday(int wednesday) {
		this.wednesday = wednesday;
	}

	public int getThursday() {
		return thursday;
	}

	public void setThursday(int thursday) {
		this.thursday = thursday;
	}

	public int getFriday() {
		return friday;
	}

	public void setFriday(int friday) {
		this.friday = friday;
	}

	public int getSaturday() {
		return saturday;
	}

	public void setSaturday(int saturday) {
		this.saturday = saturday;
	}

	public int getSunday() {
		return sunday;
	}

	public void setSunday(int sunday) {
		this.sunday = sunday;
	}

	@Override
	public String toString() {
		return "TimeSheet [timeSheetId=" + timeSheetId + ", monday=" + monday + ", tuesday=" + tuesday + ", wednesday="
				+ wednesday + ", thursday=" + thursday + ", friday=" + friday + ", saturday=" + saturday + ", sunday="
				+ sunday + "]";
	};
}



//private int timeSheetId;
//private String monday;
//private String tuesday;
//private String wednesday;
//private String thursday;
//private String friday;
//private String saturday;
//private String sunday;
//
//public TimeSheet() {
//	this.monday = "";
//	this.tuesday = "";
//	this.wednesday = "";
//	this.thursday = "";
//	this.friday = "";
//	this.saturday = "";
//	this.sunday = "";
//};
//
//public TimeSheet(String monday, String tuesday, String wednesday, String thursday, String friday,
//		String saturday, String sunday) {
//	this.timeSheetId = timeSheetId;
//	this.monday = monday;
//	this.tuesday = tuesday;
//	this.wednesday = wednesday;
//	this.thursday = thursday;
//	this.friday = friday;
//	this.saturday = saturday;
//	this.sunday = sunday;
//}
//
//public int getTimeSheetId() {
//	return timeSheetId;
//}
//
//public void setTimeSheetId(int timeSheetId) {
//	this.timeSheetId = timeSheetId;
//}
//
//public String getMonday() {
//	return monday;
//}
//
//public void setMonday(String monday) {
//	this.monday = monday;
//}
//
//public String getTuesday() {
//	return tuesday;
//}
//
//public void setTuesday(String tuesday) {
//	this.tuesday = tuesday;
//}
//
//public String getWednesday() {
//	return wednesday;
//}
//
//public void setWednesday(String wednesday) {
//	this.wednesday = wednesday;
//}
//
//public String getThursday() {
//	return thursday;
//}
//
//public void setThursday(String thursday) {
//	this.thursday = thursday;
//}
//
//public String getFriday() {
//	return friday;
//}
//
//public void setFriday(String friday) {
//	this.friday = friday;
//}
//
//public String getSaturday() {
//	return saturday;
//}
//
//public void setSaturday(String saturday) {
//	this.saturday = saturday;
//}
//
//public String getSunday() {
//	return sunday;
//}
//
//public void setSunday(String sunday) {
//	this.sunday = sunday;
//}
//
//@Override
//public String toString() {
//	return "TimeSheet [timeSheetId=" + timeSheetId + ", monday=" + monday + ", tuesday=" + tuesday + ", wednesday="
//			+ wednesday + ", thursday=" + thursday + ", friday=" + friday + ", saturday=" + saturday + ", sunday="
//			+ sunday + "]";
//}