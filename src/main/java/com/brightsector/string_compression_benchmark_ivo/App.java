package com.brightsector.string_compression_benchmark_ivo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
	private static final Logger LOG = LogManager.getLogger(App.class);

	public static void main(String[] args) {
		String path = args[0];
		int pagesLimit = Integer.parseInt(args[1]);
		String tagToExtract = args[2];
		String technology =  args[3];
		try (BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(path));
				InputStream inputStream = new BZip2CompressorInputStream(buffer, true)) {
			long startReadingTime = System.currentTimeMillis();
			List<String> items = InputReader.readPages(inputStream, pagesLimit, tagToExtract);
			long endReadingTime = System.currentTimeMillis();
			long lengthArticlesText = 0;
			List<byte[]> compressedItems = new ArrayList<byte[]>();
			long startCompressTime = System.currentTimeMillis();
			for (String item : items) {
				lengthArticlesText += item.length();
				compressedItems.add(CompressUncompressTechnologies.compress(item, technology));
			}
			long endCompressTime = System.currentTimeMillis();
			items = new ArrayList<String>();
			int bytsCompress = 0;
			long startUncompressTime = System.currentTimeMillis();
			for (byte[] compressedItem : compressedItems) {
				bytsCompress += compressedItem.length;
				items.add(CompressUncompressTechnologies.uncompress(compressedItem, technology));
			}
			long endUncompressTime = System.currentTimeMillis();
			LOG.debug("source: " + path + "\n"
					+"technology: " + technology
					+ "\t total memory: " + Runtime.getRuntime().totalMemory() 
					+ "\t free memory : " + Runtime.getRuntime().freeMemory() + "\n"
					+ "time reading: " + (endReadingTime - startReadingTime) 
					+ "\t articles byte size: " + lengthArticlesText 
					+ "\t articles read: " + pagesLimit + "\n"
					+ "time compress: " + (endCompressTime - startCompressTime)
					+ "\t compress byte size: " + bytsCompress + "\n"
					+ "time uncompress: " + (endUncompressTime - startUncompressTime)
					+"\t uncompress byte size: " + lengthArticlesText);
		} catch (IOException e) {
			throw new IllegalArgumentException("Error reading file: " + path, e);
		}
	}
}
