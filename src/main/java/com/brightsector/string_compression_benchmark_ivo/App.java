package com.brightsector.string_compression_benchmark_ivo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brightsector.string_compression_benchmark_ivo.algorithm.B2ZipCompressionAlgorithm;
import com.brightsector.string_compression_benchmark_ivo.algorithm.CompressionAlgorithm;
import com.brightsector.string_compression_benchmark_ivo.algorithm.DeflaterCompressionAlgorithm;
import com.brightsector.string_compression_benchmark_ivo.algorithm.GzipCompressionAlgorithm;
import com.brightsector.string_compression_benchmark_ivo.algorithm.LZ4BlockCompressionAlgorithm;
import com.brightsector.string_compression_benchmark_ivo.algorithm.LZ4FramedCompressionAlgorithm;
import com.brightsector.string_compression_benchmark_ivo.algorithm.LZMACompressionAlgorithm;
import com.brightsector.string_compression_benchmark_ivo.algorithm.NoCompress;
import com.brightsector.string_compression_benchmark_ivo.algorithm.SnnapystdCompressionAlgorithm;
import com.brightsector.string_compression_benchmark_ivo.algorithm.XZCompressionAlgorithm;
import com.brightsector.string_compression_benchmark_ivo.algorithm.ZstdCompressionAlgorithm;

public class App {
	private static final Logger LOG = LoggerFactory.getLogger(App.class);
	public static Map<String, CompressionAlgorithm> ALGORITHMS = Map.of(
			  "GZIP", new GzipCompressionAlgorithm(),
			  "B2Zip", new B2ZipCompressionAlgorithm(),
			  "Deflater", new DeflaterCompressionAlgorithm(),
			  "LZ4Framed", new LZ4FramedCompressionAlgorithm(),
			  "LZ4Block", new LZ4BlockCompressionAlgorithm(),
			  "LZMA", new LZMACompressionAlgorithm(),
			  "Zstd", new ZstdCompressionAlgorithm(),
			  "XZ", new XZCompressionAlgorithm(),
			  "Snnapystd", new SnnapystdCompressionAlgorithm(),
			  "NoCompress", new NoCompress()); 
	public static void main(String[] args) {
		String path = args[0];
		int pagesLimit = Integer.parseInt(args[1]);
		String tagToExtract = args[2];
		String compressionAlgorithm = args[3];
		try (BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(path));
				InputStream inputStream = new BZip2CompressorInputStream(buffer, true)) {
			CompressionAlgorithm algorithm = ALGORITHMS.get(compressionAlgorithm);
			List<byte[]> compressedItems = new ArrayList<byte[]>();
			AtomicLong lengthArticlesText = new AtomicLong();
			long startReadingAndCompressTime = System.currentTimeMillis();
			InputReader.readPages(inputStream, pagesLimit, (title, text) -> {
				String item = "title".equals(tagToExtract) ? title : text;
				lengthArticlesText.addAndGet(item.length());
				compressedItems.add(algorithm.compress(item));
			});
			long endReadingAndCompressTime = System.currentTimeMillis();
			long bytsCompress = 0;
			long startUncompressTime = System.currentTimeMillis();
			for (byte[] compressedItem : compressedItems) {
				bytsCompress += compressedItem.length;
				algorithm.uncompress(compressedItem);
			}
			long endUncompressTime = System.currentTimeMillis();
			LOG.debug("\nsource: " + path + "\n"
					+ "algorithm: " + compressionAlgorithm
					+ "\t total memory: " + Runtime.getRuntime().totalMemory()
					+ "\t free memory : " + Runtime.getRuntime().freeMemory() + "\n"
					+ "time reading and compressing: " + (endReadingAndCompressTime - startReadingAndCompressTime)
					+ "\t articles byte size: " + lengthArticlesText.toString()+ "\n" 
					+ "time uncompress: " + (endUncompressTime - startUncompressTime) 
					+ "\t compress byte size: " + bytsCompress + "\n"
					+ "articles read: " + pagesLimit + "\n");
		} catch (IOException e) {
			throw new IllegalArgumentException("Error reading file: " + path, e);
		}
	}
}
