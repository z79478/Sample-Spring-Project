package net.esc20.txeis.sample.controllers;

import java.util.ArrayList;
import java.util.List;

import net.esc20.txeis.sample.domains.Employee;
import net.esc20.txeis.sample.models.EmployeeModel;
import net.esc20.txeis.sample.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/employeeAjax")
@SessionAttributes("EmployeeModel")
public class EmployeeAjaxController {

	@Autowired	
	private EmployeeService employeeService;
	
	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@ModelAttribute("sexOptions")
	public List<String> getSexOptions() {
		List<String> options = new ArrayList<String>();
		options.add("M Male");
		options.add("F Female");
		return options;
	}

	@ModelAttribute("maritalOptions")
	public List<String> getMaritalOptions() {
		List<String> options = new ArrayList<String>();
		options.add("M Married");
		options.add("S Single");
		options.add("D Divorced");
		options.add("W Widow");
		return options;
	}		

	//Initial page request
	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model) {
		EmployeeModel empModel = new EmployeeModel();
		empModel.setEmployee(null);
		try {
			empModel.setCountCustomers(employeeService.getCustomerCount());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		model.addAttribute("EmployeeModel", empModel);
		return "employee2";
	}

	//Jquery LOAD ajax method
	@RequestMapping(value="/{employeeId}", method=RequestMethod.POST)
	public ModelAndView getEmployee(@ModelAttribute("EmployeeModel") EmployeeModel empModel, BindingResult errors, @PathVariable String employeeId, @RequestParam("empNbr") String empNbr) {
		ModelAndView model = new ModelAndView();
		empModel.setEmployee(employeeService.getEmployeeByNbr(employeeId));
		empModel.setEmployee(employeeService.getEmployeeByNbr(empNbr));
		model.setViewName("employeeTable");
		model.addObject("EmployeeModel", empModel);
		
		return model;
	}
	
	//Regular post to controller
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView getPostEmployee(@ModelAttribute("EmployeeModel") EmployeeModel empModel, BindingResult errors) {
		ModelAndView model = new ModelAndView();
		empModel.setEmployee(employeeService.getEmployeeByNbr(empModel.getRetrieveName()));
		model.setViewName("employee2");
		model.addObject("EmployeeModel", empModel);
		
		return model;
	}
	
	//Get JSON call 
	@RequestMapping(value="/getJSON/{employeeId}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, headers="Accept=application/json")
	public @ResponseBody Employee getJSONEmployee(@PathVariable String employeeId) {
		Employee emp = new Employee();
		emp = employeeService.getEmployeeByNbr(employeeId);		
		return emp;
	}
	
	
	//Get JSON call 
	@RequestMapping(value="/getJquery/{employeeId}", method=RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE, headers="Accept=application/json")
	public @ResponseBody Employee getJqueryEmployee(@PathVariable String employeeId) {
		Employee emp = new Employee();
		emp = employeeService.getEmployeeByNbr(employeeId);		
		return emp;
	}
}
