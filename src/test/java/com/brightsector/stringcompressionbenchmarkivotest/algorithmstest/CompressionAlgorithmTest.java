package com.brightsector.stringcompressionbenchmarkivotest.algorithmstest;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import com.brightsector.stringcompressionbenchmarkivo.algorithms.CompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.NoCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.CompressionUtil;

public class CompressionAlgorithmTest {

	private final byte[] testArray31Length = "The early bird catches the worm".getBytes(StandardCharsets.UTF_8);
	private final byte[] testArray661Length = "Life is a journey full of twists and turns, and it is up to us to navigate the path ahead with courage, resilience, and a positive outlook. Along the way, we will encounter challenges that test our limits, setbacks that teach us valuable lessons, and moments of triumph that inspire us to reach even higher. Whether we are pursuing our passions, building meaningful relationships, or simply exploring the world around us, we have the power to create a life that is truly extraordinary. By staying true to our values, embracing change, and committing ourselves to constant growth and self-improvement, we can overcome any obstacle and achieve our wildest dreams."
			.getBytes(StandardCharsets.UTF_8);

	@Test
	public void badCompressionUncompressionTest() {
		for (CompressionAlgorithm compressor : CompressionUtil.ALGORITHMS.values()) {
			byte[] compress = compressor.compress(testArray31Length);
			byte[] uncompress = compressor.uncompress(compress);
			assertArrayEquals(testArray31Length, uncompress);
		}
	}

	@Test
	public void goodCompressionUncompressionTest() {
		for (CompressionAlgorithm compressor : CompressionUtil.ALGORITHMS.values()) {
			byte[] compress = compressor.compress(testArray661Length);
			byte[] uncompress = compressor.uncompress(compress);
			assertArrayEquals(testArray661Length, uncompress);
		}
	}

	@Test
	public void badCompressionTest() {
		for (CompressionAlgorithm compressor : CompressionUtil.ALGORITHMS.values()) {
			byte[] compress = compressor.compress(testArray31Length);
			if (NoCompressionAlgorithm.class.isInstance(compressor)) {
				assertEquals(testArray31Length.length, compress.length);
			} else {
				assertTrue(testArray31Length.length < compress.length);
			}
		}
	}

	@Test
	public void goodCompressionTest() {
		for (CompressionAlgorithm compressor : CompressionUtil.ALGORITHMS.values()) {
			byte[] compress = compressor.compress(testArray661Length);
			if (NoCompressionAlgorithm.class.isInstance(compressor)) {
				assertEquals(testArray661Length.length, compress.length);
			} else {
				assertTrue(testArray661Length.length > compress.length);
			}
		}
	}

	@Test
	public void voidCompressionUncompressionTest() {
		byte[] emtyArray = new byte[0];
		for (CompressionAlgorithm compressor : CompressionUtil.ALGORITHMS.values()) {
			byte[] compress = compressor.compress(emtyArray);
			byte[] uncompress = compressor.uncompress(compress);
			assertArrayEquals(emtyArray, uncompress);
		}
	}

}
