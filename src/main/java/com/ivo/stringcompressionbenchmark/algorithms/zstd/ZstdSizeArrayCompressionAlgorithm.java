package com.ivo.stringcompressionbenchmark.algorithms.zstd;

import com.github.luben.zstd.Zstd;
import com.ivo.stringcompressionbenchmark.algorithms.CompressionAlgorithm;
import com.ivo.stringcompressionbenchmark.algorithms.CompressionUtil;

public class ZstdSizeArrayCompressionAlgorithm implements CompressionAlgorithm {

	public static final CompressionAlgorithm ZSTD_MIN_SIZE = new ZstdSizeArrayCompressionAlgorithm(CompressionUtil.ZSTD_MIN_LEVEL);
	public static final CompressionAlgorithm ZSTD_LOW_SIZE = new ZstdSizeArrayCompressionAlgorithm(CompressionUtil.ZSTD_LOW_LEVEL);
	public static final CompressionAlgorithm ZSTD_MID_SIZE = new ZstdSizeArrayCompressionAlgorithm(CompressionUtil.ZSTD_MID_LEVEL);
	public static final CompressionAlgorithm ZSTD_HIGH_SIZE = new ZstdSizeArrayCompressionAlgorithm(
			CompressionUtil.ZSTD_HIGH_LEVEL);
	public static final CompressionAlgorithm ZSTD_MAX_SIZE = new ZstdSizeArrayCompressionAlgorithm(CompressionUtil.ZSTD_MAX_LEVEL);
	private int level;

	public ZstdSizeArrayCompressionAlgorithm(int level) {
		this.level = level;
	}

	@Override
	public byte[] compress(byte[] data) {
		return Zstd.compress(data, level);
	}

	@Override
	public byte[] uncompress(byte[] data) {
		return Zstd.decompress(data, (int) Zstd.decompressedSize(data));
	}

}
