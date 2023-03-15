package com.brightsector.stringcompressionbenchmarkivo.algorithms.zstd;

import com.brightsector.stringcompressionbenchmarkivo.algorithms.CompressionAlgorithm;
import com.github.luben.zstd.Zstd;

public abstract class ZstdSizeArrayCompressionAlgorithm implements CompressionAlgorithm {

	public abstract int setLevel();

	@Override
	public byte[] compress(byte[] data) {
		return Zstd.compress(data, setLevel());
	}

	@Override
	public byte[] uncompress(byte[] data) {
		return Zstd.decompress(data, (int) Zstd.decompressedSize(data));
	}

}
