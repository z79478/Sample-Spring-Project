package net.esc20.txeis.sample.services;

import java.util.HashMap;
import java.util.Map;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import net.esc20.txeis.sample.models.UIDModel;

public class UidSearchStudentService extends UidXmlService {

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
		
		SOAPElement searchStudent = body.addChildElement("wsv2:searchStudent");
		searchStudent.setAttribute("soapenv:encodingstyle", "http://schemas.xmlsoap.org/soap/encoding/");
		
		SOAPElement student = searchStudent.addChildElement("student");
		student.setAttribute("xsi:type", "q0:Student");
		
		Map<String, String> required = new HashMap<String, String>();
		required.put("ssn", model.getSsn());
		required.put("birthdate", model.getBirthdate());
		required.put("districtcode", model.getDistrict());
		required.put("gender", model.getGender());
		required.put("gradelevel", model.getGradeLvl());
		required.put("localstudentid", model.getLocalId());
		required.put("raceethniccode", model.getRaceCode());
		required.put("schoolcode", model.getSchoolCode());
		required.put("schoolyear", model.getSchoolYear());
		required.put("studentfirstname", model.getfName());
		required.put("studentlastname", model.getlName());
		required.put("sourcesiscode", model.getSis());
		required.put("ethnicityindicator", model.getEthnicity());
		
		Map<String, String> optional = new HashMap<String, String>();
		optional.put("alternateid", model.getAltId());
		optional.put("alternatesource", model.getAltSource());
		optional.put("residentdistrictcode", model.getResidentDistrict());
		optional.put("stateStudentId", model.getId());
		optional.put("studentsuffix", model.getSuffix());
		optional.put("studentmiddlename", model.getmName());
		optional.put("previouslastname", model.getPrevLName());
		optional.put("race2code", model.getRaceCode2());
		optional.put("race3code", model.getRaceCode3());
		optional.put("race4code", model.getRaceCode4());
		optional.put("race5code", model.getRaceCode5());
		optional.put("cdfnumbersarray", model.getCdfNumbers());
		optional.put("cdfvaluesarray", model.getCdfValues());
		
		addAttributes ( student, required, optional );

		SOAPElement userid = searchStudent.addChildElement("userId");
		userid.setAttribute("xsi:type", "xsd:string");
		userid.addTextNode(UIDModel.USERID);
			
		SOAPElement password = searchStudent.addChildElement("password");
		password.setAttribute("xsi:type", "xsd:string");
		password.addTextNode(UIDModel.PASSWORD);
			
		request.saveChanges();
		return request;
	}
}
