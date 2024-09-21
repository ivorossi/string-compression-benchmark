package com.ivo.stringcompressionbenchmark.algorithms;

public interface CompressionAlgorithm {

	public byte[] compress(byte[] data);

	public byte[] uncompress(byte[] data);

}
