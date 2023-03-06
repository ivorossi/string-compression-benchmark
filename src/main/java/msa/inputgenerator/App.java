package msa.inputgenerator;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
	private static final Logger LOG = LogManager.getLogger(App.class);

	public static void main(String[] args) {
		String path = args[0];
		int pagesLimit = Integer.parseInt(args[1]);
		try (BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(path));
				InputStream inputStream = new BZip2CompressorInputStream(buffer, true)) {
			long startReadingTime = System.currentTimeMillis();
			Map<String, String> articles = InputReader.readPages(inputStream, pagesLimit);
			long endReadingTime = System.currentTimeMillis();
			long lengthArticlesText = 0;
			for (String article : articles.values()) {
				lengthArticlesText += article.getBytes().length;
			}
			LOG.debug("source: " + path + "\ntotal memory: " + Runtime.getRuntime().totalMemory() + "|| free memory : "
					+ Runtime.getRuntime().freeMemory() + "\narticles read: " + articles.size() + "|| reading time: "
					+ (endReadingTime - startReadingTime) + "|| length bytes all articles: " + lengthArticlesText
					+ "\n");
		} catch (IOException e) {
			throw new IllegalArgumentException("Error reading file: " + path, e);
		}
	}
}
