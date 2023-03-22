package com.brightsector.stringcompressionbenchmarkivotest.compressionlimittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.brightsector.stringcompressionbenchmarkivo.algorithms.CompressionUtil;
import com.brightsector.stringcompressionbenchmarkivo.compressionlimit.CompressLimit;

public class CompressLimitTest {

	private final File logFile = new File("work/logs/string-compression-benchmark-ivotest.log");

	@BeforeEach
	public void mainDeleteLog() {
		logFile.delete();
	}

	@Test
	public void mainCreateTest() {
		int dataLengthLimit = 20;
		String srcPath = "src/test/resources/enwiki-test.xml.bz2";
		Path logPath =  Paths.get(logFile.getPath());
		int metaDataLog = 106;
		CompressionUtil.ALGORITHMS.forEach((algorithm, value) -> {
			CompressLimit.main(new String[] { srcPath, "20", "text", algorithm, String.valueOf(dataLengthLimit) });
			assertTrue(logFile.exists());
			List<String> lastLines;
			try {
				lastLines = Files.lines(logPath)
						.skip(Math.max(0, Files.lines(logPath).count() - dataLengthLimit))
						.collect(Collectors.toList());
			} catch (IOException e) {
				throw new UncheckedIOException("Error reading file: " + srcPath, e);
			}
			String expected = String.format("Algorithm: %s.", algorithm);
			String line = (String) lastLines.get(0).subSequence(metaDataLog, lastLines.get(0).length());
			assertEquals(expected, line);
			expected = "length: , rate: .";
			for (int i = 1; i < dataLengthLimit; i++) {
				line = (String) lastLines.get(i).subSequence(metaDataLog, lastLines.get(i).length());
				assertEquals(expected, line.replaceAll("\\d+", ""));
			}
		});
	}

}
