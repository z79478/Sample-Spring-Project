package com.heb.models;

import java.util.List;

import com.heb.domains.Products;

public class ProductsModel {

	private String dateFilter; 
	
	private Products prodFilter = new Products();
	
	private List<Products> products;
	
	public ProductsModel() {
		
	}
	
	public List<Products> getProducts() {
		return products;
	}

	public void setProducts(List<Products> products) {
		this.products = products;
	}

	public Products getProdFilter() {
		return prodFilter;
	}

	public void setProdFilter(Products prodFilter) {
		this.prodFilter = prodFilter;
	}

	public String getDateFilter() {
		return dateFilter;
	}

	public void setDateFilter(String dateFilter) {
		this.dateFilter = dateFilter;
	}
	
}
