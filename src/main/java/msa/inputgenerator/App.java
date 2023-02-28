package msa.inputgenerator;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.stream.XMLStreamException;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.log4j.Logger;

public class App {
	private static Logger LOGJAVA = Logger.getLogger(App.class.getPackageName());

	public static void main(String[] args) {
		String path = args[0];
		int pagesLimit = Integer.parseInt(args[1]);

		try (BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(path));
				InputStream inputStream = new BZip2CompressorInputStream(buffer, true)) {
			InputReader inputReader = new InputReader(inputStream);

			long startReadingTime = System.currentTimeMillis();
			Map<String, String> articles = inputReader.generator(pagesLimit);
			long endReadingTime = System.currentTimeMillis();
			inputReader.close();
			long lengthArticlesText = 0;

			for (String article : articles.values()) {
				lengthArticlesText += article.getBytes().length;
			}

			Pattern pattern = Pattern.compile("[^\\\\/]+$");
			Matcher matcher = pattern.matcher(path);
			String filename = "";
			if (matcher.find()) {
				filename = matcher.group();
			}

			LOGJAVA.debug("source: " + filename + "\ntotal memory: " + Runtime.getRuntime().totalMemory()
					+ "|| free memory : " + Runtime.getRuntime().freeMemory() + "\narticles read: " + articles.size()
					+ "|| reading time: " + (endReadingTime - startReadingTime) + "|| length bytes all articles: "
					+ lengthArticlesText + "\n");

		} catch (XMLStreamException | IOException e) {
			throw new IllegalArgumentException("Error reading file: " + path, e);
		}
	}
}
