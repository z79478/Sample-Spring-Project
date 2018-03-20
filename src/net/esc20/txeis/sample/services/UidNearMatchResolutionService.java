package net.esc20.txeis.sample.services;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import net.esc20.txeis.sample.models.UIDModel;

public class UidNearMatchResolutionService extends UidXmlService {

	@Override
	public SOAPMessage createRequest(UIDModel model) throws Exception {
		MessageFactory mf = MessageFactory.newInstance();
		SOAPMessage request = mf.createMessage();
		SOAPPart soapPart = request.getSOAPPart();
		
		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration("soapenv", "http://schemas.xmlsoap.org/soap/envelope/");
		envelope.addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		envelope.addNamespaceDeclaration("xsd", "http://www.w3.org/2001/XMLSchema");
			
		SOAPBody body = envelope.getBody();
		
		SOAPElement nearMatch = body.addChildElement(model.getSubType(), "ns0", "http://ws.escholar.com");
		nearMatch.setAttribute("soapenv:encodingstyle", "http://schemas.xmlsoap.org/soap/encoding/");
		
		SOAPElement tranSerial = nearMatch.addChildElement("tranSerialNumber");
		tranSerial.setAttribute("xsi:type", "xsd:string");
		tranSerial.addTextNode(model.getTranSerialNumber());
		
		if ( !model.getSubType().equals("cancel") ) {
			SOAPElement stateId = nearMatch.addChildElement("stateStudentId");
			stateId.setAttribute("xsi:type", "xsd:string");
			stateId.addTextNode(model.getId());
		}
			
		SOAPElement userid = nearMatch.addChildElement("userId");
		userid.setAttribute("xsi:type", "xsd:string");
		userid.addTextNode(UIDModel.USERID);
			
		SOAPElement password = nearMatch.addChildElement("password");
		password.setAttribute("xsi:type", "xsd:string");
		password.addTextNode(UIDModel.PASSWORD);

		request.saveChanges();
		return request;
	}
}
