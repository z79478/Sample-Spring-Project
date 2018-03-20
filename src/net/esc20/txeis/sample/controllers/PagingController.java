package net.esc20.txeis.sample.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import net.esc20.txeis.sample.models.PagingModel;
import net.esc20.txeis.sample.services.EmployeeService;

import net.esc20.txeis.sample.domains.Employee;
import net.esc20.txeis.sample.domains.Page;
import net.esc20.txeis.sample.domains.PagesAll;

@Controller
@RequestMapping("paging")
@SessionAttributes("PagingModel")
public class PagingController {

	private PagesAll<Employee> pagesAll = new PagesAll<Employee>();
	private int PAGESIZE = 20;
	
	@Autowired
	private EmployeeService employeeService;
	
	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model, HttpServletRequest request) {
		PagingModel pagingModel = new PagingModel();
		
		buildPages();
		
		request.getSession().setAttribute("AllPages", pagesAll);
		
		pagingModel.setCurrentPage(1);
		pagingModel.setMaxPages(pagesAll.getTotalPages());
		pagingModel.setPage(pagesAll.getPages().get(0));
		pagingModel.setPrevPage(1);
		if (pagesAll.getTotalPages() > 1) {
			pagingModel.setNextPage(2);
		} else {
			pagingModel.setNextPage(1);
		}
			
		model.addAttribute("PagingModel", pagingModel);
		
		return "paging";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getPage", method=RequestMethod.POST)
	public ModelAndView getNewPage(@ModelAttribute("PagingModel") PagingModel pagingModel, BindingResult errors, @RequestParam("pageNum") int newPageNum, @RequestParam("tableData") String tableData, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		ObjectMapper mapper = new ObjectMapper();
		List<Employee> data = new ArrayList<Employee>();
		
		try {
			data = mapper.readValue(tableData, new TypeReference<ArrayList<Employee>>(){});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		pagesAll = (PagesAll<Employee>) request.getSession().getAttribute("AllPages");
		
		//Replace page in memory
		Page<Employee> replPage = new Page<Employee>();
		replPage.setPageItems(data);
		replPage.setPageNumber(pagingModel.getCurrentPage());
		replPage.setTotalItems(data.size());
		pagesAll.getPages().set(pagingModel.getCurrentPage() - 1, replPage);
		
		//Get New page to model
		@SuppressWarnings("rawtypes")
		Page page = pagesAll.getPages().get(newPageNum - 1);
		
		pagingModel.setCurrentPage(newPageNum);
		if (newPageNum < pagingModel.getMaxPages()) {
			pagingModel.setNextPage(newPageNum + 1);
		}
		if (newPageNum > 1) {
			pagingModel.setPrevPage(newPageNum - 1);
		}
		pagingModel.setPage(page);
		
		//Save data objects in memory
		request.getSession().setAttribute("AllPages", pagesAll);
		model.addObject("PagingModel", pagingModel);
		model.setViewName("paging");
		
		return model;
	}
	
	
	@SuppressWarnings("rawtypes")
	private void buildPages() {
		List<Employee> emps = employeeService.getAllEmployees();
		List<Page> pages = new ArrayList<Page>();
		Page<Employee> page = new Page<Employee>();
		int i = 0;
		int pageNum = 1;
		
		for (Employee emp: emps) {
			i++;
			
			if (i > PAGESIZE) {
				i = 1;
				page.setPageNumber(pageNum);
				page.setTotalItems(PAGESIZE);
				pages.add(page);
				page = new Page<Employee>();
				pageNum++;
			}
			
			page.getPageItems().add(emp);
		}
		
		//Add last page if any left over
		if (i > 0) {
			page.setPageNumber(pageNum);
			page.setTotalItems(i);
			pages.add(page);
		} else {
			pageNum--;
		}
		
		pagesAll.setCurrentPage(1);
		pagesAll.setPageNumber(1);
		pagesAll.setTotalItems(pageNum);
		pagesAll.setTotalPages(pageNum);
		pagesAll.setPages(pages);
	}
}
