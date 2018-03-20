package net.esc20.txeis.sample.controllers;

import net.esc20.txeis.sample.models.XMLModel;
import net.esc20.txeis.sample.services.XMLFileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("XMLFileDemo")
@SessionAttributes("XMLModel")
public class XMLFileDemoController {

	
	private XMLFileService fileService;
	
	@Autowired
	public XMLFileDemoController(XMLFileService service) {
		fileService = service;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String getCall(Model model){
		XMLModel xmlModel = new XMLModel();
		xmlModel.setFinished(false);
		model.addAttribute("XMLModel", xmlModel);
		return "XMLFileDemo";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String execute(@ModelAttribute("XMLModel") XMLModel xmlModel, BindingResult result, Model model) {
		xmlModel.setFinished(false);
		fileService.writeFile();
		xmlModel.setFinished(true);
		model.addAttribute("XMLModel", xmlModel);
		return "XMLFileDemo";
	}
	
}
