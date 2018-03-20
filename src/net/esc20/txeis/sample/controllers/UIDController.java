package net.esc20.txeis.sample.controllers;

import java.io.ByteArrayOutputStream;

import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;

import net.esc20.txeis.sample.models.UIDModel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/**/uid")
public class UIDController {
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView loadUIDPage ( Model model ) {
		model.addAttribute("model", new UIDModel());
		return new ModelAndView ( "uid" );
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView makeRequest ( @ModelAttribute("model") UIDModel model ) throws Exception {
        SOAPConnectionFactory scf = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = scf.createConnection();
		SOAPMessage message = model.getXmlService().createRequest(model);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		message.writeTo(out);
		String x = new String(out.toByteArray());
		SOAPMessage soapResponse = soapConnection.call(message, model.getUrl());
		
		out = new ByteArrayOutputStream();
		soapResponse.writeTo(out);
		x = new String(out.toByteArray());
		soapConnection.close();

		return new ModelAndView ( "uid", "data", model.getXmlService().parseResponse(soapResponse) );
	}
}