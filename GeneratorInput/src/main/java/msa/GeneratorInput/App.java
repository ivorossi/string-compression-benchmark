package msa.GeneratorInput;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;


public class App {

	public static void main(String[] args) {
		// ruta del archivo comprimido
		String path = "C:\\Users\\rossi\\OneDrive\\Escritorio\\enwiki-20230201-pages-articles-multistream.xml.bz2";
		// cantidad de paginaciones
		int amountPages = 1;
		try {
			WriterFileTest writer = new WriterFileTest(path);
			writer.generator(ReaderFile.generator(path), amountPages);
		} catch (IOException | XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
