package net.esc20.txeis.sample.domains;

import java.util.Date;

public class Employee {

	String emp_nbr = "";
	String name_f = "";
	String name_l = "";
	String name_m = "";
	Date dob = null;
	String sex = "";
	String marital_stat = "";
	Boolean race_white = false;
	Double numberField = 1000.55;
	Boolean selected = false;
	
	public String getEmp_nbr() {
		return emp_nbr;
	}
	public void setEmp_nbr(String emp_nbr) {
		this.emp_nbr = emp_nbr;
	}
	public String getName_f() {
		return name_f;
	}
	public void setName_f(String name_f) {
		this.name_f = name_f;
	}
	public String getName_l() {
		return name_l;
	}
	public void setName_l(String name_l) {
		this.name_l = name_l;
	}
	public String getName_m() {
		return name_m;
	}
	public void setName_m(String name_m) {
		this.name_m = name_m;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMarital_stat() {
		return marital_stat;
	}
	public void setMarital_stat(String marital_stat) {
		this.marital_stat = marital_stat;
	}
	public Boolean getRace_white() {
		return race_white;
	}
	public void setRace_white(Boolean race_white) {
		this.race_white = race_white;
	}
	
	public String getFullName() {
		return this.name_f + " " + this.name_m + " " + this.name_l;
	}
	public Double getNumberField() {
		return numberField;
	}
	public void setNumberField(Double numberField) {
		this.numberField = numberField;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
}
