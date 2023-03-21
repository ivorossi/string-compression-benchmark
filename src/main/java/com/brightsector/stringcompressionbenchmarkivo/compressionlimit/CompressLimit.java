package com.brightsector.stringcompressionbenchmarkivo.compressionlimit;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brightsector.stringcompressionbenchmarkivo.InputReader;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.CompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.CompressionUtil;

public class CompressLimit {

	private static final Logger LOG = LoggerFactory.getLogger(CompressLimit.class);

	public static void main(String[] args) {

		String path = args[0];
		int pagesLimit = Integer.parseInt(args[1]);
		String tagToExtract = args[2];
		String compressionAlgorithm = args[3];
		int dateLengthLimit = Integer.parseInt(args[4]);
		List<List<Float>> matrix = new ArrayList<>();
		LOG.debug("Algorithm: {}.", compressionAlgorithm);
		try (BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(path));
				InputStream inputStream = new BZip2CompressorInputStream(buffer, true)) {
			CompressionAlgorithm algorithm = CompressionUtil.ALGORITHMS.get(compressionAlgorithm);
			InputReader.readPages(inputStream, pagesLimit, (title, text) -> {
				String item = "title".equals(tagToExtract) ? title : text;
				byte[] toParse = item.getBytes(StandardCharsets.UTF_8);
				if (toParse.length > dateLengthLimit) {
					List<Float> list = new ArrayList<>();
					for (int length = 1; length < dateLengthLimit; length++) {
						byte[] toCompress = Arrays.copyOf(toParse, length);
						list.add((float) length / (float) algorithm.compress(toCompress).length);
					}
					matrix.add(list);
				}
			});
		} catch (IOException e) {
			throw new IllegalArgumentException("Error reading file: " + path, e);
		}
		for (int i = 0; i < matrix.get(0).size(); i++) {
			float rate = 0;
			for (int j = 0; j < matrix.size(); j++) {
				rate += matrix.get(j).get(i);
			}
			rate = rate / matrix.size();
			LOG.debug("length: {}, rate: {}", (i + 1), Math.round(rate * 10000f) / 10000f);
		}
	}

}