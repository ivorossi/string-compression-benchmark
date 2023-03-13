package com.brightsector.stringcompressionbenchmarkivo.compressinglimit;

public class LenghtvsCompressingRate {
	private final int lenghtUncompress;
	private final int lenghtCompress;

	public LenghtvsCompressingRate(int lenghtUncompress, int lenghtCompress) {
		this.lenghtUncompress = lenghtUncompress;
		this.lenghtCompress = lenghtCompress;
	}

	@Override
	public String toString() {
		return String.format("{%d, %.2f}", lenghtUncompress, ((float) lenghtUncompress / (float) lenghtCompress));
	}
}
