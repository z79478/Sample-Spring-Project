package com.heb.controllers;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.heb.models.ProductsModel;
import com.heb.services.ProductsService;

@Controller
@RequestMapping("products")
@SessionAttributes("ProductsModel")
public class ProductsController {

	@Autowired
	ProductsService productService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		//This is to translate BigDecimal class for empty values and strip commas
		binder.registerCustomEditor(BigDecimal.class, new NumericFormat());
	}
	
	public final class NumericFormat extends PropertyEditorSupport {
		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			try {
				super.setValue(BigDecimal.valueOf(Double.parseDouble(text.replace(",", ""))).setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			catch (Exception e) {
				e.printStackTrace();
				super.setValue(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
			}
		}
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model) {
		ProductsModel prodModel = new ProductsModel();
		
		model.addAttribute("ProductsModel", prodModel);
		return "products";
	}
	
	@RequestMapping(value="/retrieve", method = RequestMethod.POST)
	public String retrieveProducts(@ModelAttribute("ProductsModel") ProductsModel prodModel, BindingResult result) {
		
		//Convert string date filter to actual date field if entered
		if (prodModel.getDateFilter().length() > 0) {
			try {
				Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(prodModel.getDateFilter());
				prodModel.getProdFilter().setLastsold(date1);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}	
		} else {
			prodModel.getProdFilter().setLastsold(null);
		}
		
		//Retrieve list of products based on filter
		prodModel.setProducts(productService.getProducts(prodModel.getProdFilter()));
		
		return "products";
	}
	
}
