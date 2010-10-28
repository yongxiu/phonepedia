package cn.edu.nju.software.parse;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.Locator;
import org.xml.sax.helpers.DefaultHandler;

public class ParseXML extends DefaultHandler {
	
	private StringBuffer buf;

	public ParseXML() {
		super();
	}

	public void setDocumentLocator(Locator locator) {
	}

	public void startDocument() throws SAXException {
		buf = new StringBuffer();
		System.out.println("*******开始解析文档*******");
	}

	public void endDocument() throws SAXException {
		System.out.println("*******文档解析结束*******");
	}

	public void startPrefixMapping(String prefix, String uri) {
		System.out.println(" 前缀映射: " + prefix + " 开始!" + " 它的URI是:" + uri);
	}

	public void endPrefixMapping(String prefix) {
		System.out.println(" 前缀映射: " + prefix + " 结束!");
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
		System.out.println("*******开始解析元素*******");
		System.out.println("元素名" + qName);
		for (int i = 0; i < atts.getLength(); i++) {
			System.out.println("元素名" + atts.getLocalName(i) + "属性值"
					+ atts.getValue(i));
		}
	}

	public void endElement(String namespaceURI, String localName,
			String fullName) throws SAXException {
		System.out.println("******元素解析结束********");
	}

	public void characters(char[] chars, int start, int length)
			throws SAXException {
		// 将元素内容累加到StringBuffer中
		buf.append(chars, start, length);
	}
}