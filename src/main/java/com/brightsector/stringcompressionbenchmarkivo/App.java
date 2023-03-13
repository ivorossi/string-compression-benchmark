package com.brightsector.stringcompressionbenchmarkivo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brightsector.stringcompressionbenchmarkivo.algorithms.B2ZipStreamCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.CompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.DeflaterStreamCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.GzipStreamCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.LZ4BlockStreamCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.LZ4ByteIntArrayCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.LZ4ByteWhileArrayCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.LZ4FramedStreamCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.LZFBytesCompressionAlgortihm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.LZMAStreamCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.NoCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.SnappyByteArrayCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.SnnapyStandardStreamCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.XZStreamCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.ZstdByteArrayCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.ZStandardStreamCompressionAlgorithm;

public class App {
	private static final Logger LOG = LoggerFactory.getLogger(App.class);
	public static Map<String, CompressionAlgorithm> ALGORITHMS = new HashMap<>();
	static {
		ALGORITHMS.putAll(Map.of(
				"GZIPStream", new GzipStreamCompressionAlgorithm(),
				"B2ZipStream",new B2ZipStreamCompressionAlgorithm(),
				"DeflaterStream", new DeflaterStreamCompressionAlgorithm(),
				"LZ4FramedStream", new LZ4FramedStreamCompressionAlgorithm(),
				"LZ4BlockStream", new LZ4BlockStreamCompressionAlgorithm(),
				"LZMAStream", new LZMAStreamCompressionAlgorithm(), 
				"ZStdStream", new ZStandardStreamCompressionAlgorithm(),
				"XZStream", new XZStreamCompressionAlgorithm(),
				"SnnapyStdStream", new SnnapyStandardStreamCompressionAlgorithm()));
		ALGORITHMS.putAll(Map.of(
				"SnappyBytes", new SnappyByteArrayCompressionAlgorithm(),
				"LZ4BytesInt", new LZ4ByteIntArrayCompressionAlgorithm(),
				"LZ4BytesWhile", new LZ4ByteWhileArrayCompressionAlgorithm(),
				"ZStdByte", new ZstdByteArrayCompressionAlgorithm(),
				"LZFBytes", new LZFBytesCompressionAlgortihm(),
				"NoCompress", new NoCompressionAlgorithm()));
	}
	public static void main(String[] args) {
		String path = args[0];
		int pagesLimit = Integer.parseInt(args[1]);
		String tagToExtract = args[2];
		String compressionAlgorithm = args[3];
		try (BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(path));
				InputStream inputStream = new BZip2CompressorInputStream(buffer, true)) {
			CompressionAlgorithm algorithm = ALGORITHMS.get(compressionAlgorithm);
			List<byte[]> compressedItems = new ArrayList<>();
			System.gc();
			AtomicLong lengthArticlesText = new AtomicLong();
			long startReadingAndCompressTime = System.currentTimeMillis();
			InputReader.readPages(inputStream, pagesLimit, (title, text) -> {
				String item = "title".equals(tagToExtract) ? title : text;
				lengthArticlesText.addAndGet(item.getBytes(StandardCharsets.UTF_8).length);
				compressedItems.add(algorithm.compress(item.getBytes(StandardCharsets.UTF_8)));
			});
			long endReadingAndCompressTime = System.currentTimeMillis();
			long bytsCompress = 0;
			long startUncompressTime = System.currentTimeMillis();
			for (byte[] compressedItem : compressedItems) {
				bytsCompress += compressedItem.length;
				algorithm.uncompress(compressedItem);
			}
			long endUncompressTime = System.currentTimeMillis();
			LOG.debug("source: {}, algorithm: {}.", path, compressionAlgorithm);
			LOG.debug("total memory: {}, free memory: {}.", Runtime.getRuntime().totalMemory(),
					Runtime.getRuntime().freeMemory());
			LOG.debug("time reading and compressing: {}, time decompress: {}.",
					(endReadingAndCompressTime - startReadingAndCompressTime),
					(endUncompressTime - startUncompressTime));
			LOG.debug("articles byte size: {}, compress byte size: {}", lengthArticlesText, bytsCompress);
		} catch (IOException e) {
			throw new IllegalArgumentException("Error reading file: " + path, e);
		}
	}
}
