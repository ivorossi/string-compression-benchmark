package com.ivo.stringcompressionbenchmarktest.algorithmstest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.ivo.stringcompressionbenchmark.algorithms.CompressionUtil;

public class CompressionUtilTest {
	private final byte[] testArray = new byte[1];

	@Test
	public void oneBytegetGetOriginalLengthTest() {
		assertEquals(1, CompressionUtil.getOriginalLength(CompressionUtil.addOriginalLengthTo(testArray, testArray.length)));
	}

	@Test
	public void oneBytegetAddOriginalLengthTest() {
		assertEquals(5, CompressionUtil.addOriginalLengthTo(testArray, testArray.length).length);
	}

}
