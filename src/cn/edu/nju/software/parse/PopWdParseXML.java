package cn.edu.nju.software.parse;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class PopWdParseXML extends DefaultHandler {

	
	private List<String> strArray;

	public PopWdParseXML(List<String> strArray) {
		this.strArray = strArray;
	}


	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) {
		System.out.println("*******Start parser element*******");
		System.out.println("element name" + qName);
		
		for (int i = 0; i < atts.getLength(); i++) {
			if(qName.equals("dd") && atts.getLocalName(i).equals("name")) {
				strArray.add(atts.getValue(i));
				break;
			} 
		}
	}

}