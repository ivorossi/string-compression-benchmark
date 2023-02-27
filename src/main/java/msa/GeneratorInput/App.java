package msa.GeneratorInput;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;


public class App {

	public static void main(String[] args) {
		// ruta del archivo comprimido
		String path = args[0];
		// cantidad de paginaciones
		int amountPages = Integer.parseInt(args[1]);
		try {
			WriterFileTest writer = new WriterFileTest(path);
			writer.generator(ReaderFile.generator(path), amountPages);
		} catch (IOException | XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
