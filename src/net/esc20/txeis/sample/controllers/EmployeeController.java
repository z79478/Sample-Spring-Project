package net.esc20.txeis.sample.controllers;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.esc20.txeis.sample.models.EmployeeModel;
import net.esc20.txeis.sample.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("employee")
@SessionAttributes("EmployeeModel")
public class EmployeeController {

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
		return "employee";
	}
	
	
	@RequestMapping(value="/retrieve", method = RequestMethod.POST)
	public String retrieveEmployee(@ModelAttribute("EmployeeModel") EmployeeModel empModel, BindingResult result, Model model) {
		
		empModel.setEmployee(employeeService.getEmployeeByNbr(empModel.getRetrieveName()));
		empModel.setShowImage(false);
		
		model.addAttribute("EmployeeModel", empModel);
		
		return "employee";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveeEmployee(@ModelAttribute("EmployeeModel") EmployeeModel empModel, BindingResult errors, Model model) {
		
		try {
			this.employeeService.saveEmployee(empModel.getEmployee());
			Double temp = empModel.getEmployee().getNumberField();  //not stored in database
			empModel.setEmployee(employeeService.getEmployeeByNbr(empModel.getRetrieveName()));
			empModel.getEmployee().setNumberField(temp);
			model.addAttribute("saveSuccess", "Save successful.");
		} catch (Exception e) {
			e.printStackTrace();
			errors.rejectValue("", "-1", e.getMessage());
		}
		
		empModel.setShowImage(false);
		
		model.addAttribute("EmployeeModel", empModel);
		
		return "employee";
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		
	    binder.registerCustomEditor(Double.class, new StrictNumericFormat());		
	}
	
	public final class StrictNumericFormat extends PropertyEditorSupport
	{
		@Override
	    public void setAsText(String text) throws IllegalArgumentException 
	    {
	        try {
	        	super.setValue(Double.parseDouble(text.replace(",", "")));
	        } catch (Exception e) {
	        	e.printStackTrace();
	        	super.setValue(0);
	        }
	        
	    }
	}
}
