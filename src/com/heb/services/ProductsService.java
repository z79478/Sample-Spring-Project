package com.heb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heb.dao.ProductDao;
import com.heb.domains.Products;

@Service
@Transactional
public class ProductsService {

	@Autowired
	ProductDao productDao;
	
	public List<Products> getProducts(Products prodFilter) {
		return productDao.getProducts(prodFilter);
	}
	
}
