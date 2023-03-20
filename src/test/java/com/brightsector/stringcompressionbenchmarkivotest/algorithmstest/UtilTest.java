package com.brightsector.stringcompressionbenchmarkivotest.algorithmstest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.brightsector.stringcompressionbenchmarkivo.algorithms.Util;

public class UtilTest {
	private final byte[] oneByete = new byte[1];

	@Test
	public void oneBytegetGetOriginalLengthTest() {
		assertEquals(1, Util.getOriginalLength(Util.addOriginalLengthTo(oneByete, oneByete.length)));

	}

	@Test
	public void oneBytegetSetOriginalLengthTest() {
		assertEquals(5, Util.addOriginalLengthTo(oneByete, oneByete.length).length);
	}

}
