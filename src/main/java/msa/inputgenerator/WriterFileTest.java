package msa.inputgenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class WriterFileTest {
	private static final String ROOT_TAG = "wikidata";
	private static final String[] TAGS = new String[] { "page", "title", "text" };
	private static String REGEX_REF_TAG = "<ref.*>.*</ref>|<ref.*/>";
	private final XMLEventFactory eventFactory = XMLEventFactory.newInstance();
	private final XMLOutputFactory factory = XMLOutputFactory.newInstance();
	private final XMLEventWriter writer;
	private int eventCode;

	public WriterFileTest(String path) throws FileNotFoundException, XMLStreamException {
		this.writer = factory.createXMLEventWriter(new FileOutputStream(path.replaceAll(".xml.bz2", "-test.xml")));
	}

	public void generator(XMLStreamReader xmlReader, int amountPages) throws XMLStreamException {
		int pageNumber = 0;
		this.startDocmunent();
		while (xmlReader.hasNext() && pageNumber < amountPages) {
			eventCode = xmlReader.next();
			if (XMLStreamConstants.START_ELEMENT == eventCode) {
				for (String tag : TAGS) {
					if (xmlReader.getLocalName().equalsIgnoreCase(tag)) {
						this.addStartElement(tag);
						this.addCharactersElement(xmlReader);
					}
				}
			}
			if (XMLStreamConstants.END_ELEMENT == eventCode) {
				for (String tag : TAGS) {
					if (xmlReader.getLocalName().equalsIgnoreCase(tag)) {
						pageNumber += this.addEndElement(tag);
					}
				}
			}
		}
		this.endDocmunent();
	}

	private void startDocmunent() throws XMLStreamException {
		writer.add(eventFactory.createStartDocument());
		writer.add(eventFactory.createStartElement("", "", ROOT_TAG));
	}

	private void addStartElement(String tag) throws XMLStreamException {
		writer.add(eventFactory.createStartElement("", "", tag));
	}

	private void addCharactersElement(XMLStreamReader xmlReader) throws XMLStreamException {
		eventCode = xmlReader.next();
		StringBuilder characters = new StringBuilder();
		while (XMLStreamConstants.CHARACTERS == eventCode) {
			characters.append(xmlReader.getText());
			eventCode = xmlReader.next();
		}
		writer.add(eventFactory.createCharacters(characters.toString().replaceAll(REGEX_REF_TAG, "")));
	}

	private int addEndElement(String tag) throws XMLStreamException {
		writer.add(eventFactory.createEndElement("", "", tag));
		return tag == TAGS[0] ? 1 : 0;
	}

	private void endDocmunent() throws XMLStreamException {
		writer.add(eventFactory.createEndElement("", "", ROOT_TAG));
		writer.add(eventFactory.createEndDocument());
	}
}
