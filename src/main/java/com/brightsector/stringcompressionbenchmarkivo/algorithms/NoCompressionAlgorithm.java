package com.brightsector.stringcompressionbenchmarkivo.algorithms;

public class NoCompressionAlgorithm implements CompressionAlgorithm {

	@Override
	public byte[] compress(byte[] data) {
		return data;
	}

	@Override
	public byte[] uncompress(byte[] data) {
		return data;
	}
}
