package com.brightsector.stringcompressionbenchmarkivo.algorithms;

import java.io.IOException;
import java.io.UncheckedIOException;

import org.xerial.snappy.Snappy;

public class SnappyByteArrayCompressionAlgorithm implements CompressionAlgorithm {

	@Override
	public byte[] compress(byte[] data) {
		try {
			return Snappy.compress(data);
		} catch (IOException e) {
			throw new UncheckedIOException("Error compressing: ", e);
		}
	}

	@Override
	public byte[] uncompress(byte[] data) {
		try {
			return Snappy.uncompress(data);
		} catch (IOException e) {
			throw new UncheckedIOException("Error decompressing: ", e);
		}
	}

}
