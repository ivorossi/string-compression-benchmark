package com.ivo.stringcompressionbenchmarktest;

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

import com.ivo.stringcompressionbenchmark.StringCompressionBenchmarkMain;
import com.ivo.stringcompressionbenchmark.algorithms.CompressionUtil;

public class StringCompressionBenchmarkMainTest {

	private final File logFile = new File("work/logs/string-compression-benchmark-ivotest.log");

	@BeforeEach
	public void mainLogDelete() {
		logFile.delete();
	}

	@Test
	public void mainLogCreateTest() {
		int linesCount = 5;
		String srcPath = "src/test/resources/enwiki-test.xml.bz2";
		Path logPath =  Paths.get(logFile.getPath());
		int metaDataLog = 106;
		CompressionUtil.ALGORITHMS.forEach((algorithm, value) -> {
			StringCompressionBenchmarkMain.main(new String[] { srcPath, "20", "text", algorithm });
			assertTrue(logFile.exists());
			String[] expected = { String.format("source: %s, algorithm: %s.", srcPath, algorithm),
					"total memory: , free memory: .", "time reading and compressing: , articles byte size: .",
					"time decompress: , compress byte size: .", "decompress byte size: ." };
			List<String> lastLines;
			try {
				lastLines = Files.lines(logPath)
						.skip(Math.max(0, Files.lines(logPath).count() - linesCount))
						.collect(Collectors.toList());
			} catch (IOException e) {
				throw  new UncheckedIOException("Error reading file: " + srcPath, e);
			}
			assertEquals(expected[0], lastLines.get(0).subSequence(metaDataLog, lastLines.get(0).length()));
			for (int i = 1; i < linesCount; i++) {
				String line = (String) lastLines.get(i).subSequence(metaDataLog, lastLines.get(i).length());
				assertEquals(expected[i], line.replaceAll("\\d+", ""));
			}
		});
	}

}
