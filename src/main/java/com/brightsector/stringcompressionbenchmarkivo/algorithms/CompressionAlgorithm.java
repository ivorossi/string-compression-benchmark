package com.brightsector.stringcompressionbenchmarkivo.algorithms;

public interface CompressionAlgorithm {

	public byte[] compress(byte[] data);

	public byte[] uncompress(byte[] data);
}
