package com.heb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model) {
		ProductsModel prodModel = new ProductsModel();
		
		model.addAttribute("ProductsModel", prodModel);
		return "products";
	}
	
	@RequestMapping(value="/retrieve", method = RequestMethod.POST)
	public String retrieveProducts(@ModelAttribute("ProductsModel") ProductsModel prodModel, BindingResult result) {
		
		prodModel.setProducts(productService.getProducts(prodModel.getProdFilter()));
		
		return "products";
	}
	
}
