package com.brightsector.stringcompressionbenchmarkivo.compressionlimit;

public class CompressLimitDataPoint {
	private final int uncompressLength;
	private final int compressLength;

	public CompressLimitDataPoint(int lenghtUncompress, int lenghtCompress) {
		this.uncompressLength = lenghtUncompress;
		this.compressLength = lenghtCompress;
	}

	@Override
	public String toString() {
		return String.format("{%d, %.2f}", uncompressLength, ((float) uncompressLength / (float) compressLength));
	}
}
