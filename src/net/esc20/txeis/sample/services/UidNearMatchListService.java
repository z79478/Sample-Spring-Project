package net.esc20.txeis.sample.services;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import net.esc20.txeis.sample.models.UIDModel;

public class UidNearMatchListService extends UidXmlService {

	@Override
	public SOAPMessage createRequest(UIDModel model) throws Exception {
		MessageFactory mf = MessageFactory.newInstance();
		SOAPMessage request = mf.createMessage();
		SOAPPart soapPart = request.getSOAPPart();
		
		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration("soapenv", "http://schemas.xmlsoap.org/soap/envelope/");
		envelope.addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		envelope.addNamespaceDeclaration("xsd", "http://www.w3.org/2001/XMLSchema");
		envelope.addNamespaceDeclaration("wsv2", "http://wsv2.escholar.com");
			
		SOAPBody body = envelope.getBody();
		SOAPElement nearMatches = body.addChildElement("getNearMatches", "ns0", "http://ws.escholar.com");
		nearMatches.setAttribute("soapenv:encodingstyle", "http://schemas.xmlsoap.org/soap/encoding/");
			
		SOAPElement submission = nearMatches.addChildElement("submissionId");
		submission.setAttribute("xsi:type", "xsd:string");
		submission.addTextNode(model.getId());
			
		SOAPElement userid = nearMatches.addChildElement("userId");
		userid.setAttribute("xsi:type", "xsd:string");
		userid.addTextNode(UIDModel.USERID);
			
		SOAPElement password = nearMatches.addChildElement("password");
		password.setAttribute("xsi:type", "xsd:string");
		password.addTextNode(UIDModel.PASSWORD);
			
		request.saveChanges();
		return request;
	}
}
