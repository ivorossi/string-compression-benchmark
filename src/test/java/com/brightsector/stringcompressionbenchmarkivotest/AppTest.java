package com.brightsector.stringcompressionbenchmarkivotest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import com.brightsector.stringcompressionbenchmarkivo.App;

public class AppTest {

	private final File logFile = new File("work/logs/ivoTest");
	private final String path = "src/test/resources/enwiki-test.xml.bz2";
	private final String algorithm = "NoCompress";

	@Before
	public void mainLogDeleteTest() {
		logFile.delete();
	}

	@Test
	public void mainLogCreateTest() {
		App.main(new String[] { path, "20", "text", algorithm });
		assertTrue(logFile.exists());
	}

	@Test
	public void mainLogContainsTest() throws FileNotFoundException {
		Scanner scanner = new Scanner(logFile);
		String line = scanner.nextLine();
		String expected = String.format("source: %s, algorithm: %s.", path, algorithm);
		assertEquals(expected, line.subSequence(79, line.length()));
		line = scanner.nextLine();
		expected = "total memory: , free memory: .";
		assertEquals(expected, ((String) line.subSequence(79, line.length())).replaceAll("\\d+", ""));
		line = scanner.nextLine();
		expected = "time reading and compressing: , articles byte size: .";
		assertEquals(expected, ((String) line.subSequence(79, line.length())).replaceAll("\\d+", ""));
		line = scanner.nextLine();
		expected = "time decompress: , compress byte size: .";
		assertEquals(expected, ((String) line.subSequence(79, line.length())).replaceAll("\\d+", ""));
		line = scanner.nextLine();
		expected = "decompress byte size: .";
		assertEquals(expected, ((String) line.subSequence(79, line.length())).replaceAll("\\d+", ""));
		assertThrows(NoSuchElementException.class, () -> {
			scanner.nextLine();
		});
		scanner.close();
	}

}
