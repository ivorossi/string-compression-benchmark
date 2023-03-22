package com.brightsector.stringcompressionbenchmarkivotest.algorithmstest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.brightsector.stringcompressionbenchmarkivo.algorithms.CompressionUtil;

public class CompressionUtilTest {
	private final byte[] arrayTest = new byte[1];

	@Test
	public void oneBytegetGetOriginalLengthTest() {
		assertEquals(1, CompressionUtil.getOriginalLength(CompressionUtil.addOriginalLengthTo(arrayTest, arrayTest.length)));
	}

	@Test
	public void oneBytegetAddOriginalLengthTest() {
		assertEquals(5, CompressionUtil.addOriginalLengthTo(arrayTest, arrayTest.length).length);
	}

}
