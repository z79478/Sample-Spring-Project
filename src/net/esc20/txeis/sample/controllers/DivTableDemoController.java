package net.esc20.txeis.sample.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("divTableDemo")
public class DivTableDemoController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String get() {
		return "divTableDemo";
	}

}
