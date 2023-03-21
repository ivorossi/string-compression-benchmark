package com.brightsector.stringcompressionbenchmarkivotest.algorithmstest;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import com.brightsector.stringcompressionbenchmarkivo.algorithms.CompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.NoCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.CompressionUtil;

public class CompressionAlgorithmTest {

	private final byte[] test31Characters = "The early bird catches the worm".getBytes(StandardCharsets.UTF_8);
	private final byte[] test280Characters = "Life is a journey full of twists and turns, and it is up to us to navigate the path ahead with courage, resilience, and a positive outlook. Along the way, we will encounter challenges that test our limits, setbacks that teach us valuable lessons, and moments of triumph that inspire us to reach even higher. Whether we are pursuing our passions, building meaningful relationships, or simply exploring the world around us, we have the power to create a life that is truly extraordinary. By staying true to our values, embracing change, and committing ourselves to constant growth and self-improvement, we can overcome any obstacle and achieve our wildest dreams."
			.getBytes(StandardCharsets.UTF_8);

	@Test
	public void badCompressionUncompressionTest() {
		for (CompressionAlgorithm compressor : CompressionUtil.ALGORITHMS.values()) {
			byte[] compress = compressor.compress(test31Characters);
			byte[] uncompress = compressor.uncompress(compress);
			assertArrayEquals(test31Characters, uncompress);
		}
	}

	@Test
	public void goodCompressionUncompressionTest() {
		for (CompressionAlgorithm compressor : CompressionUtil.ALGORITHMS.values()) {
			byte[] compress = compressor.compress(test280Characters);
			byte[] uncompress = compressor.uncompress(compress);
			assertArrayEquals(test280Characters, uncompress);
		}
	}

	@Test
	public void badCompressionTest() {
		for (CompressionAlgorithm compressor : CompressionUtil.ALGORITHMS.values()) {
			byte[] compress = compressor.compress(test31Characters);
			if (NoCompressionAlgorithm.class.equals(compressor.getClass())) {
				assertTrue(test31Characters.length < compress.length);
			} else {
				assertTrue(test31Characters.length == compress.length);
			}
		}
	}

	@Test
	public void goodCompressionTest() {
		for (CompressionAlgorithm compressor : CompressionUtil.ALGORITHMS.values()) {
			byte[] compress = compressor.compress(test280Characters);
			if (NoCompressionAlgorithm.class.equals(compressor.getClass())) {
				assertTrue(test280Characters.length > compress.length);
			} else {
				assertTrue(test280Characters.length == compress.length);
			}
		}
	}

	@Test
	public void voidCompressionUncompressionTest() {
		for (CompressionAlgorithm compressor : CompressionUtil.ALGORITHMS.values()) {
			byte[] compress = compressor.compress(new byte[0] );
			byte[] uncompress = compressor.uncompress(compress);
			assertArrayEquals(new byte[0], uncompress);
		}
	}

}
