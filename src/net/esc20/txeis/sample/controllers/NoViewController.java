package net.esc20.txeis.sample.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NoViewController {

	@RequestMapping(value="sampleCss", method = RequestMethod.GET)
	public String getCss() {
		return "sampleCss";
	}

	@RequestMapping(value="ws-simple", method = RequestMethod.GET)
	public String getWsSimple() {
		return "ws-simple";
	}
	
	@RequestMapping(value="rest-ws", method = RequestMethod.GET)
	public String getRestPage() {
		return "rest-ws";
	}

}
