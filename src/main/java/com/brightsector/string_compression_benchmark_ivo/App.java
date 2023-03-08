package com.brightsector.string_compression_benchmark_ivo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
	private static final Logger LOG = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		String path = args[0];
		int pagesLimit = Integer.parseInt(args[1]);
		String tagToExtract = args[2];
		String compressionAlgorithm = args[3];
		try (BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(path));
				InputStream inputStream = new BZip2CompressorInputStream(buffer, true)) {
			List<byte[]> compressedItems = new ArrayList<byte[]>();
			AtomicLong lengthArticlesText = new AtomicLong();
			long startReadingAndCompressTime = System.currentTimeMillis();
			InputReader.readPages(inputStream, pagesLimit, (title, text) -> {
				String item = "title".equals(tagToExtract) ? title : text;
				lengthArticlesText.addAndGet(item.length());
				compressedItems.add(CompressUncompressAlgorithm.compress(item, compressionAlgorithm));
			});
			long endReadingAndCompressTime = System.currentTimeMillis();
			long byteUncompress = 0;
			int bytsCompress = 0;
			long startUncompressTime = System.currentTimeMillis();
			for (byte[] compressedItem : compressedItems) {
				bytsCompress += compressedItem.length;
				String textUncompress = CompressUncompressAlgorithm.uncompress(compressedItem, compressionAlgorithm);
				byteUncompress += textUncompress.length();
			}
			long endUncompressTime = System.currentTimeMillis();
			LOG.debug("\nsource: " + path + "\n"
					+ "technology: " + compressionAlgorithm
					+ "\t total memory: " + Runtime.getRuntime().totalMemory()
					+ "\t free memory : " + Runtime.getRuntime().freeMemory() + "\n"
					+ "time reading and compressing: " + (endReadingAndCompressTime - startReadingAndCompressTime)
					+ "\t articles byte size: " + lengthArticlesText.toString() 
					+ "\tcompress byte size: " + bytsCompress + "\n" 
					+ "time uncompress: " + (endUncompressTime - startUncompressTime) 
					+ "\t uncompress byte size: " + byteUncompress + "\n"
					+ "articles read: " + pagesLimit);
		} catch (IOException e) {
			throw new IllegalArgumentException("Error reading file: " + path, e);
		}
	}
}
