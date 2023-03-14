package com.brightsector.stringcompressionbenchmarkivo.algorithms;

import com.github.luben.zstd.Zstd;

public class ZstdByteArrayCompressionAlgorithm implements CompressionAlgorithm {
	 
	@Override
	public byte[] compress(byte[] data) {
		return Zstd.compress(data, ZSTD_MID_LEVEL);
	}

	@Override
	public byte[] uncompress(byte[] data) {
		return Zstd.decompress(data, (int) Zstd.decompressedSize(data));
	}
}
