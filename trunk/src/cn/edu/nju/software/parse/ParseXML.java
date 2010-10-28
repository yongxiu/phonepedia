package cn.edu.nju.software.parse;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.Locator;
import org.xml.sax.helpers.DefaultHandler;

public class ParseXML extends DefaultHandler {

	
	private List<String> groupArray;
	private List<List<String>> childArray;
	private int _index;

	public ParseXML(List<String> groupArray, List<List<String>> childArray) {
		this.groupArray = groupArray;
		this.childArray = childArray;
	}

	public void setDocumentLocator(Locator locator) {
	}

	public void startDocument() throws SAXException {
		_index = 0;
		System.out.println("*******Starting parsering the doc*******");
	}

	public void endDocument() throws SAXException {
		System.out.println("*******Finished parsering the doc*******");
	}

	public void startPrefixMapping(String prefix, String uri) {
		System.out.println(" Prefix map " + prefix + " start!" + " it's URI is:" + uri);
	}

	public void endPrefixMapping(String prefix) {
		System.out.println(" Prefix map: " + prefix + " end!");
	}

	public void processingInstruction(String target, String instruction)
			throws SAXException {
	}

	public void ignorableWhitespace(char[] chars, int start, int length)
			throws SAXException {
	}

	public void skippedEntity(String name) throws SAXException {
	}

	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) {
		System.out.println("*******Start parser element*******");
		System.out.println("element name" + qName);
		
		for (int i = 0; i < atts.getLength(); i++) {
			if(qName.equals("dd") && atts.getLocalName(i).equals("name")) {
				groupArray.add(atts.getValue(i));
				childArray.add(new ArrayList<String>());
				_index ++;
				break;
			} else if(qName.equals("li") && atts.getLocalName(i).equals("name")) {
				childArray.get(_index - 1).add(atts.getValue(i));
				break;
			}
		}
	}

	public void endElement(String namespaceURI, String localName,
			String fullName) throws SAXException {
		System.out.println("******Element parser ending********");
	}

	public void characters(char[] chars, int start, int length)
			throws SAXException {
	}
}