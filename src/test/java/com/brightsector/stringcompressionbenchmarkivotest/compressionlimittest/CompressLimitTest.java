package com.brightsector.stringcompressionbenchmarkivotest.compressionlimittest;

import java.io.File;

import org.junit.jupiter.api.Test;

import com.brightsector.stringcompressionbenchmarkivo.compressionlimit.CompressLimit;

public class CompressLimitTest {
	private final File logFile = new File("work/logs/ivoTest.log");
	private final String path = "src/test/resources/enwiki-test.xml.bz2";
	private final String algorithm = "NoCompress";
	
	@Test
	public void mainCreate() {
		CompressLimit.main(new String[] { path, "20", "text", algorithm, "1000" });
	}
}
