package msa.inputgenerator;

import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class InputReader {
	private static final String PAGE_TAG_NAME = "page";
	private static final String TITLE_TAG_NAME = "title";
	private static final String TEXT_TAG_NAME = "text";

	private static boolean isPageStart(XMLStreamReader xmlReader, int eventCode) {
		return XMLStreamConstants.START_ELEMENT == eventCode
				&& xmlReader.getLocalName().equalsIgnoreCase(PAGE_TAG_NAME);
	}

	private static boolean isPageEnd(XMLStreamReader xmlReader, int eventCode) {
		return XMLStreamConstants.END_ELEMENT == eventCode
				&& xmlReader.getLocalName().equalsIgnoreCase(PAGE_TAG_NAME);
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

	public static Map<String, String> readPages(InputStream inputStream, int pagesLimit) {
		int eventCode;
		int pageNumber = 0;
		Map<String, String> output = new TreeMap<String, String>();
		try {
			XMLStreamReader xmlReader = XMLInputFactory.newInstance().createXMLStreamReader(inputStream);
			while (xmlReader.hasNext() && pageNumber < pagesLimit) {
				eventCode = xmlReader.next();
				if (isPageStart(xmlReader, eventCode)) {
					pageNumber++;
					String key = null;
					String value = null;
					while (!isPageEnd(xmlReader, eventCode)) {
						eventCode = xmlReader.next();
						if (XMLStreamConstants.START_ELEMENT == eventCode) {
							if (xmlReader.getLocalName().equalsIgnoreCase(TITLE_TAG_NAME)) {
								key = readCharacters(xmlReader);
							}
							if (xmlReader.getLocalName().equalsIgnoreCase(TEXT_TAG_NAME)) {
								value = readCharacters(xmlReader);
							}
						}
					}
					output.put(key, value);
				}
			}
			xmlReader.close();
		} catch (XMLStreamException e) {
			throw new IllegalStateException("Error processing XML", e);
		}
		return output;
	}

}
