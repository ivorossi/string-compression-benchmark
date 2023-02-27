package msa.inputgenerator;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;

public class App {

	public static void main(String[] args) {
		String path = args[0];
		int PagesLimit = Integer.parseInt(args[1]);
		try (BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(path));
				BZip2CompressorInputStream inputStream = new BZip2CompressorInputStream(buffer, true);){ 
			XMLStreamReader xmlReader = XMLInputFactory.newInstance().createXMLStreamReader(inputStream);
			WriterFileTest writer = new WriterFileTest(path);
			writer.generator(xmlReader, PagesLimit);

		} catch (IOException | XMLStreamException e) {
			throw new IllegalArgumentException("Error reading file: " + path, e);
		}
	}
}
