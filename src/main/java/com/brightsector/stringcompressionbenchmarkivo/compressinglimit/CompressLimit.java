package com.brightsector.stringcompressionbenchmarkivo.compressinglimit;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brightsector.stringcompressionbenchmarkivo.InputReader;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.CompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.LZ4ByteIntArrayCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.ZstdByteArrayCompressionAlgorithm;

public class CompressLimit {
	private static final Logger LOG = LoggerFactory.getLogger(CompressLimit.class);
	private static final int TEST_LIMIT = 1000;
	public static Map<String, CompressionAlgorithm> ALGORITHMS = Map.of(
			"LZ4BytesInt", new LZ4ByteIntArrayCompressionAlgorithm(),
			"ZStdByte", new ZstdByteArrayCompressionAlgorithm());

	public static void main(String[] args) {
		String path = args[0];
		String compressionAlgorithm = args[1];
		int pagesLimit = Integer.parseInt(args[2]);
		try (BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(path));
				InputStream inputStream = new BZip2CompressorInputStream(buffer, true)) {
			CompressionAlgorithm algorithm = ALGORITHMS.get(compressionAlgorithm);
			InputReader.readPages(inputStream, pagesLimit, (title, text) -> {
				String item = "title".equals("text") ? title : text;
				if (item.getBytes().length > TEST_LIMIT) {
					List<LenghtvsCompressingRate> lenghtVsRatio = new ArrayList<>();
					for (int lenght = 1; lenght < TEST_LIMIT; lenght++) {
						byte[] byteToCompress = new byte[lenght];
						System.arraycopy(item.getBytes(), 0, byteToCompress, 0, lenght);
						lenghtVsRatio.add(new LenghtvsCompressingRate(lenght, algorithm.compress(byteToCompress).length));
					}
					LOG.debug("Algorithm: {} rates of compressing: {}", compressionAlgorithm, lenghtVsRatio);
				}
			});
		} catch (IOException e) {
			throw new IllegalArgumentException("Error reading file: " + path, e);
		}
	}
}
