package com.brightsector.stringcompressionbenchmarkivotest.algorithmstest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.brightsector.stringcompressionbenchmarkivo.algorithms.compressionUtil;

public class UtilTest {
	private final byte[] oneByete = new byte[1];

	@Test
	public void oneBytegetGetOriginalLengthTest() {
		assertEquals(1, compressionUtil.getOriginalLength(compressionUtil.addOriginalLengthTo(oneByete, oneByete.length)));
	}

	@Test
	public void oneBytegetAddOriginalLengthTest() {
		assertEquals(5, compressionUtil.addOriginalLengthTo(oneByete, oneByete.length).length);
	}

}
