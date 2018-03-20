package net.esc20.txeis.sample.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import net.esc20.txeis.sample.models.UIDModel;

public abstract class UidXmlService {
	public abstract SOAPMessage createRequest ( UIDModel model ) throws Exception;
	public List<Map<String, String>> parseResponse ( SOAPMessage response ) throws Exception {
		List<Map<String, String>> retval = new ArrayList<Map<String, String>>();
		SOAPBody body = response.getSOAPBody();
		Iterator<SOAPElement> it = body.getChildElements();
		SOAPElement multiRef = null;
		while ( it.hasNext() ) {
			multiRef = it.next();
			Map<String, String> ref = new HashMap<String, String>();
			Iterator<SOAPElement> mrIt = multiRef.getChildElements();
			while ( mrIt.hasNext() ) {
				SOAPElement elem = mrIt.next();
				ref.put(elem.getNodeName(), elem.getTextContent());
			}

			retval.add(ref);
		}
		
		return retval;
	}

	protected void addAttributes ( SOAPElement root, Map<String, String> reqAttributes, Map<String, String> optAttributes ) throws SOAPException {
		for ( String key : reqAttributes.keySet() ) {
			SOAPElement e = root.addChildElement ( key );
			e.setAttribute("xsi:type", "xsd:string");
			e.addTextNode(reqAttributes.get(key));
		}

		for ( String key : optAttributes.keySet() ) {
			if ( optAttributes.get(key) != null && !optAttributes.get(key).trim().equals("") ) {
				SOAPElement e = root.addChildElement ( key );
				e.setAttribute("xsi:type", "xsd:string");
				e.addTextNode(optAttributes.get(key));
			}
		}
	}
}
