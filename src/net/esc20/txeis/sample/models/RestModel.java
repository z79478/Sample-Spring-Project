package net.esc20.txeis.sample.models;

import java.util.List;

import net.esc20.txeis.sample.domains.Student;
import net.esc20.txeis.sample.domains.User;

public class RestModel {

	private String id1 = "";
	private String id2 = "";
	private String id3 = "";
	private String id4 = "";
	
	private String name1 = "";
	private String name2 = "";
	
	private String profession1 = "";
	private String profession2 = "";
	
	private String outputBox = "";

	private List<User> users;
	private List<Student> students;
	
	private String server = "";
	
	public String getId1() {
		return id1;
	}

	public void setId1(String id1) {
		this.id1 = id1;
	}

	public String getId2() {
		return id2;
	}

	public void setId2(String id2) {
		this.id2 = id2;
	}

	public String getId3() {
		return id3;
	}

	public void setId3(String id3) {
		this.id3 = id3;
	}

	public String getId4() {
		return id4;
	}

	public void setId4(String id4) {
		this.id4 = id4;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getProfession1() {
		return profession1;
	}

	public void setProfession1(String profession1) {
		this.profession1 = profession1;
	}

	public String getProfession2() {
		return profession2;
	}

	public void setProfession2(String profession2) {
		this.profession2 = profession2;
	}

	public String getOutputBox() {
		return outputBox;
	}

	public void setOutputBox(String outputBox) {
		this.outputBox = outputBox;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	
}
