package msa.GeneratorInput;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;

public class ReaderFile {
	static XMLStreamReader generator(String path) throws XMLStreamException, FactoryConfigurationError, IOException {
		BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(path));
		BZip2CompressorInputStream inputStream = new BZip2CompressorInputStream(buffer, true);
		XMLStreamReader xmlReader = XMLInputFactory.newInstance().createXMLStreamReader(inputStream);
		return xmlReader;
	}
}
