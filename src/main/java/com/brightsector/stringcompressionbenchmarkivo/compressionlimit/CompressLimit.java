package com.brightsector.stringcompressionbenchmarkivo.compressionlimit;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

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
		LOG.debug("Algorithm: {}.", compressionAlgorithm);
		try (BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(path));
				InputStream inputStream = new BZip2CompressorInputStream(buffer, true)) {
			CompressionAlgorithm algorithm = CompressionAlgorithm.ALGORITHMS.get(compressionAlgorithm);
			InputReader.readPages(inputStream, pagesLimit, (title, text) -> {
				String item = "title".equals("text") ? title : text;
				if (item.getBytes().length > dateLengthLimit) {
					for (int length = 1; length < dateLengthLimit; length++) {
						byte[] byteToCompress = Arrays.copyOf(item.getBytes(), length);
						LOG.debug("length: {}, length compress: {}.", length,
								algorithm.compress(byteToCompress).length);
					}
				}
			});
		} catch (IOException e) {
			throw new IllegalArgumentException("Error reading file: " + path, e);
		}
	}

}
