package net.esc20.txeis.sample.domains;

public class Student {
	private String firstName = "";
	private String middleName = "";
	private String lastName = "";
	private String ssn = "";
	private String grade = "";
	
	Student() {
		return;
	}
	
	Student (String fName, String mName, String lName, String ssn, String grade) {
		this.firstName = fName;
		this.middleName = mName;
		this.lastName = lName;
		this.ssn = ssn;
		this.grade = grade;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	
}
