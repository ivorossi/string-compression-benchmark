package com.brightsector.string_compression_benchmark_ivo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class InputReader {
	private static final String PAGE_TAG_NAME = "page";

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

	public static List<String> readPages(InputStream inputStream, int pagesLimit, String tagToExtract) {
		try {
			int pageNumber = 0;
			List<String> output = new ArrayList<String>();
			XMLStreamReader xmlReader = XMLInputFactory.newInstance().createXMLStreamReader(inputStream);
			while (xmlReader.hasNext() && pageNumber < pagesLimit) {
				int eventCode = xmlReader.next();
				if (isPageStart(xmlReader, eventCode)) {
					pageNumber++;
					while (!isPageEnd(xmlReader, eventCode)) {
						eventCode = xmlReader.next();
						if (XMLStreamConstants.START_ELEMENT == eventCode
								&& tagToExtract.equals(xmlReader.getLocalName())) {
							output.add(readCharacters(xmlReader));
						}
					}

				}
			}
			xmlReader.close();
			return output;
		} catch (XMLStreamException e) {
			throw new IllegalStateException("Error processing XML", e);
		}
	}

}
