package com.brightsector.stringcompressionbenchmarkivo;

import java.io.InputStream;
import java.util.function.BiConsumer;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class InputReader {

	private static final String PAGE_TAG_NAME = "page";
	private static final String TITLE_TAG_NAME = "title";
	private static final String TEXT_TAG_NAME = "text";

	private static boolean isPageStart(XMLStreamReader xmlReader, int eventCode) {
		return XMLStreamConstants.START_ELEMENT == eventCode && PAGE_TAG_NAME.equals(xmlReader.getLocalName());
	}

	private static boolean isPageEnd(XMLStreamReader xmlReader, int eventCode) {
		return XMLStreamConstants.END_ELEMENT == eventCode && PAGE_TAG_NAME.equals(xmlReader.getLocalName());
	}

	private static String readCharacters(XMLStreamReader xmlReader) throws XMLStreamException {
		int eventCode = xmlReader.next();
		StringBuilder characters = new StringBuilder();
		while (XMLStreamConstants.CHARACTERS == eventCode) {
			characters.append(xmlReader.getText());
			eventCode = xmlReader.next();
		}
		return characters.toString();
	}

	public static void readPages(InputStream inputStream, int pagesLimit, BiConsumer<String, String> reader) {
		try {
			int pageNumber = 0;
			XMLStreamReader xmlReader = XMLInputFactory.newInstance().createXMLStreamReader(inputStream);
			while (xmlReader.hasNext() && pageNumber < pagesLimit) {
				int eventCode = xmlReader.next();
				if (isPageStart(xmlReader, eventCode)) {
					pageNumber++;
					String title = null;
					String text = null;
					while (!isPageEnd(xmlReader, eventCode)) {
						eventCode = xmlReader.next();
						if (XMLStreamConstants.START_ELEMENT == eventCode) {
							if (TITLE_TAG_NAME.equals(xmlReader.getLocalName())) {
								title = readCharacters(xmlReader);
							}
							if (TEXT_TAG_NAME.equals(xmlReader.getLocalName())) {
								text = readCharacters(xmlReader);
							}
						}
					}
					reader.accept(title, text);
				}
			}
			xmlReader.close();
		} catch (XMLStreamException e) {
			throw new IllegalStateException("Error processing XML", e);
		}
	}

}
