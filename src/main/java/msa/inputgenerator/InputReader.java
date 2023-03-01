package msa.inputgenerator;

import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class InputReader {
	private static final String TAG_ARTICLE_ROOT = "page";
	private static final String TAG_ARTICLE_TITLE = "title";
	private static final String TAG_ARTICLE_DESCRIPTION = "text";
	private static String REGEX_REF_TAG = "<ref.*>.*</ref>|<ref.*/>";

	static private boolean isPageStart(XMLStreamReader xmlReader, int eventCode) {
		return XMLStreamConstants.START_ELEMENT == eventCode
				&& xmlReader.getLocalName().equalsIgnoreCase(TAG_ARTICLE_ROOT);
	}

	static private boolean isPageEnd(XMLStreamReader xmlReader, int eventCode) {
		return XMLStreamConstants.END_ELEMENT == eventCode
				&& xmlReader.getLocalName().equalsIgnoreCase(TAG_ARTICLE_ROOT);
	}

	static private String addCharactersElement(XMLStreamReader xmlReader, int eventCode) throws XMLStreamException {
		eventCode = xmlReader.next();
		StringBuilder characters = new StringBuilder();
		while (XMLStreamConstants.CHARACTERS == eventCode) {
			characters.append(xmlReader.getText());
			eventCode = xmlReader.next();
		}
		return characters.toString().replaceAll(REGEX_REF_TAG, "");
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
							if (xmlReader.getLocalName().equalsIgnoreCase(TAG_ARTICLE_TITLE)) {
								key = addCharactersElement(xmlReader, eventCode);
							}
							if (xmlReader.getLocalName().equalsIgnoreCase(TAG_ARTICLE_DESCRIPTION)) {
								value = addCharactersElement(xmlReader, eventCode);
							}
						}
					}
					if (key != null && value != null) {
						output.put(key, value);
					}
				}
			}
			xmlReader.close();
		} catch (XMLStreamException e) {
			throw new IllegalArgumentException("Error reading file: "+ e);
		}

		return output;
	}

}
