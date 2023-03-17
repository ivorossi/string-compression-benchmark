package com.brightsector.stringcompressionbenchmarkivo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brightsector.stringcompressionbenchmarkivo.algorithms.CompressionAlgorithm;

public class App {

	private static final Logger LOG = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		String path = args[0];
		int pagesLimit = Integer.parseInt(args[1]);
		String tagToExtract = args[2];
		String compressionAlgorithm = args[3];
		AtomicLong lengthArticlesText = new AtomicLong();
		List<byte[]> compressedItems = new ArrayList<>();
		CompressionAlgorithm algorithm = CompressionAlgorithm.ALGORITHMS.get(compressionAlgorithm);
		long startReadingAndCompressTime = System.currentTimeMillis();
		try (BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(path));
				InputStream inputStream = new BZip2CompressorInputStream(buffer, true)) {
			InputReader.readPages(inputStream, pagesLimit, (title, text) -> {
				String item = "title".equals(tagToExtract) ? title : text;
				lengthArticlesText.addAndGet(item.getBytes(StandardCharsets.UTF_8).length);
				compressedItems.add(algorithm.compress(item.getBytes(StandardCharsets.UTF_8)));
			});
		} catch (IOException e) {
			throw new IllegalArgumentException("Error reading file: " + path, e);
		}
		long endReadingAndCompressTime = System.currentTimeMillis();
		System.gc();
		LOG.debug("source: {}, algorithm: {}.", path, compressionAlgorithm);
		LOG.debug("total memory: {}, free memory: {}.", Runtime.getRuntime().totalMemory(),
				Runtime.getRuntime().freeMemory());
		LOG.debug("time reading and compressing: {}, articles byte size: {}.",
				(endReadingAndCompressTime - startReadingAndCompressTime), lengthArticlesText);
		long bytsCompress = 0;
		long startUncompressTime = System.currentTimeMillis();
		long bytsDecompress = 0;
		for (byte[] compressedItem : compressedItems) {
			bytsCompress += compressedItem.length;
			byte[] decompress = algorithm.uncompress(compressedItem);
			bytsDecompress += decompress.length;
		}
		long endUncompressTime = System.currentTimeMillis();
		LOG.debug("time decompress: {}, compress byte size: {}.", (endUncompressTime - startUncompressTime),
				bytsCompress);
		LOG.debug("decompress byte size: {}.", bytsDecompress);
	}

}