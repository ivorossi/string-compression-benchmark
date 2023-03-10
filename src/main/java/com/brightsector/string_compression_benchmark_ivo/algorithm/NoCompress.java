package com.brightsector.string_compression_benchmark_ivo.algorithm;

public class NoCompress implements CompressionAlgorithm {

	@Override
	public byte[] compress(byte[] data) {
		return data;
	}

	@Override
	public byte[] uncompress(byte[] data) {
		return data;
	}

}
