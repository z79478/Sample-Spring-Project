package net.esc20.txeis.sample.models;

import net.esc20.txeis.sample.domains.Employee;

public class EmployeeModel {

	Employee employee = new Employee();
	String retrieveName = "";
	boolean showImage = false; 
	
	long countCustomers = 0;
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getRetrieveName() {
		return retrieveName;
	}

	public void setRetrieveName(String retrieveName) {
		this.retrieveName = retrieveName;
	}

	public long getCountCustomers() {
		return countCustomers;
	}

	public void setCountCustomers(long countCustomers) {
		this.countCustomers = countCustomers;
	}

	public boolean isShowImage() {
		return showImage;
	}

	public void setShowImage(boolean showImage) {
		this.showImage = showImage;
	}
}
