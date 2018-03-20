package com.heb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Component;
import com.heb.domains.Products;

@Component
public class ProductDao extends NamedParameterJdbcDaoSupport {

	@Autowired
	public ProductDao(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	
	public List<Products> getProducts(Products prodFilter) {
		StringBuilder sql = new StringBuilder();
		StringBuilder where = new StringBuilder();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		sql.append("select id, description, lastsold, shelflife, department, price, unit, xfor, cost from products ");
				
		if (prodFilter.getId().length() > 0) {
			where.append("id like '%");
			where.append(prodFilter.getId());
			where.append("%' ");
		}
		
		if (where.length() > 0) {
			sql.append("where ");
			sql.append(where);
		}
		
		//Map<String, Object> paramMap = new HashMap<String, Object>();
		List<Products> prods = new ArrayList<Products>();
		
		try {
			prods = this.getNamedParameterJdbcTemplate().query(sql.toString(), new ProductMapper());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return prods;
	}
	
	static class ProductMapper implements ParameterizedRowMapper<Products> {
		@Override
		public Products mapRow(ResultSet rs, int row) throws SQLException {
			Products prod = new Products();
			
			prod.setId(rs.getString(1));
			prod.setDescription(rs.getString(2));
			prod.setLastsold(rs.getDate(3));
			prod.setShelflife(rs.getString(4));
			prod.setDepartment(rs.getString(5));
			prod.setPrice(rs.getBigDecimal(6));
			prod.setUnit(rs.getString(7));
			prod.setXfor(rs.getInt(8));
			prod.setCost(rs.getBigDecimal(9));
			
			return prod;
		}
	}
}
