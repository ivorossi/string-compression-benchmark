package com.brightsector.stringcompressionbenchmarkivo.compressionlimit;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brightsector.stringcompressionbenchmarkivo.InputReader;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.CompressionAlgorithm;

public class CompressLimit {
	private static final Logger LOG = LoggerFactory.getLogger(CompressLimit.class);

	public static void main(String[] args) {
		String path = args[0];
		String compressionAlgorithm = args[1];
		int pagesLimit = Integer.parseInt(args[2]);
		int dateLengthLimit = Integer.parseInt(args[3]);
		try (BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(path));
				InputStream inputStream = new BZip2CompressorInputStream(buffer, true)) {
			CompressionAlgorithm algorithm = CompressionAlgorithm.ALGORITHMS.get(compressionAlgorithm);
			InputReader.readPages(inputStream, pagesLimit, (title, text) -> {
				String item = "title".equals("text") ? title : text;
				if (item.getBytes().length > dateLengthLimit) {
					List<CompressLimitDataPoint> compressLimitDataPoints = new ArrayList<>();
					for (int lenght = 1; lenght < dateLengthLimit; lenght++) {
						byte[] byteToCompress = Arrays.copyOf(item.getBytes(), lenght);
						compressLimitDataPoints
								.add(new CompressLimitDataPoint(lenght, algorithm.compress(byteToCompress).length));
					}
					LOG.debug("Algorithm: {} rates of compressing: {}", compressionAlgorithm, compressLimitDataPoints);
				}
			});
		} catch (IOException e) {
			throw new IllegalArgumentException("Error reading file: " + path, e);
		}
	}
}
