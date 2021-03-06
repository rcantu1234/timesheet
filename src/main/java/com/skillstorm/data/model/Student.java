package com.skillstorm.data.model;

public class Student {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	
	public Student() {};
	
	public Student(int id, String firstName, String lastName, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String displayStudents() {
		return  "Student : " + getId() + "\nFirst Name : " + getFirstName() + "\nLast Name : " + getLastName() + "\nEmail : " + getEmail() + "\n";
	}
	
	@Override
	public String toString() {
		return "Student : " + id + "\nFirst Name : " + firstName + "\nLast Name : " + lastName + "\nEmail : " + email + "\n";
	}	
}
