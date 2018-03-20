package com.heb.domains;

import java.math.BigDecimal;
import java.util.Date;

public class Products {
	
	private String id = "";
	private String description = "";
	private Date lastsold = new Date();
	private String shelflife = "";
	private String department = "";
	private BigDecimal price = new BigDecimal(0.00);
	private String unit = "";
	private int xfor = 0;
	private BigDecimal cost = new BigDecimal(0.00);
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getLastsold() {
		return lastsold;
	}
	public void setLastsold(Date lastsold) {
		this.lastsold = lastsold;
	}
	public String getShelflife() {
		return shelflife;
	}
	public void setShelflife(String shelflife) {
		this.shelflife = shelflife;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getXfor() {
		return xfor;
	}
	public void setXfor(int xfor) {
		this.xfor = xfor;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

}
