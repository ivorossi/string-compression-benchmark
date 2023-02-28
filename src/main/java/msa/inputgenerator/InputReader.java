package msa.inputgenerator;

import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class InputReader {
	private static final String TAG_ARTICLE_ROOT = "page";
	private static final String TAG_ARTICLE_TITLE = "title";
	private static final String TAG_ARTICLE_DESCRIPTION = "text";
	private static String REGEX_REF_TAG = "<ref.*>.*</ref>|<ref.*/>";
	private final XMLStreamReader xmlReader;
	private int eventCode;

	public InputReader(InputStream inputStream) throws XMLStreamException, FactoryConfigurationError {
		xmlReader = XMLInputFactory.newInstance().createXMLStreamReader(inputStream);
	}

	public Map<String, String> generator(int amountPages) throws XMLStreamException {
		int pageNumber = 0;
		Map<String, String> output = new TreeMap<String, String>();
		while (xmlReader.hasNext() && pageNumber < amountPages) {
			eventCode = xmlReader.next();
			if (this.isPageStart()) {
				pageNumber++;
				String key = null;
				String value = null;
				while (!this.isPageEnd()) {
					eventCode = xmlReader.next();
					if (XMLStreamConstants.START_ELEMENT == eventCode) {
						if (xmlReader.getLocalName().equalsIgnoreCase(TAG_ARTICLE_TITLE)) {
							key = this.addCharactersElement(xmlReader);
						}
						if (xmlReader.getLocalName().equalsIgnoreCase(TAG_ARTICLE_DESCRIPTION)) {
							value = this.addCharactersElement(xmlReader);
						}
					}
				}
				if (key != null && value != null) {
					output.put(key, value);
				}
			}
		}
		return output;
	}

	private boolean isPageStart() {
		return XMLStreamConstants.START_ELEMENT == eventCode
				&& xmlReader.getLocalName().equalsIgnoreCase(TAG_ARTICLE_ROOT);
	}

	private boolean isPageEnd() {
		return XMLStreamConstants.END_ELEMENT == eventCode
				&& xmlReader.getLocalName().equalsIgnoreCase(TAG_ARTICLE_ROOT);
	}

	private String addCharactersElement(XMLStreamReader xmlReader) throws XMLStreamException {
		eventCode = xmlReader.next();
		StringBuilder characters = new StringBuilder();
		while (XMLStreamConstants.CHARACTERS == eventCode) {
			characters.append(xmlReader.getText());
			eventCode = xmlReader.next();
		}
		return characters.toString().replaceAll(REGEX_REF_TAG, "");
	}

	public void close() throws XMLStreamException {
		this.xmlReader.close();

	}

}
