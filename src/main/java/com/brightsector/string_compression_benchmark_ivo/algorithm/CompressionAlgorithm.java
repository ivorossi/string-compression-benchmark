package com.brightsector.string_compression_benchmark_ivo.algorithm;

public interface CompressionAlgorithm {

	public byte[] compress(byte[] data);

	public byte[] uncompress(byte[] data);
}
