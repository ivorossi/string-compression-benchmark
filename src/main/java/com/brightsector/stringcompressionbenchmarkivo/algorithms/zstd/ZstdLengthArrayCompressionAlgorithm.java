package com.brightsector.stringcompressionbenchmarkivo.algorithms.zstd;

import com.brightsector.stringcompressionbenchmarkivo.algorithms.CompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.CompressionUtil;
import com.github.luben.zstd.Zstd;

public class ZstdLengthArrayCompressionAlgorithm implements CompressionAlgorithm {

	public static final CompressionAlgorithm ZSTD_MIN_LENGTH = new ZstdLengthArrayCompressionAlgorithm(
			CompressionUtil.ZSTD_MIN_LEVEL);
	public static final CompressionAlgorithm ZSTD_LOW_LENGTH = new ZstdLengthArrayCompressionAlgorithm(
			CompressionUtil.ZSTD_LOW_LEVEL);
	public static final CompressionAlgorithm ZSTD_MID_LENGTH = new ZstdLengthArrayCompressionAlgorithm(
			CompressionUtil.ZSTD_MID_LEVEL);
	public static final CompressionAlgorithm ZSTD_HIGH_LENGTH = new ZstdLengthArrayCompressionAlgorithm(
			CompressionUtil.ZSTD_HIGH_LEVEL);
	public static final CompressionAlgorithm ZSTD_MAX_LENGTH = new ZstdLengthArrayCompressionAlgorithm(
			CompressionUtil.ZSTD_MAX_LEVEL);
	private final int level;

	public ZstdLengthArrayCompressionAlgorithm(int level) {
		this.level = level;
	}

	@Override
	public byte[] compress(byte[] data) {
		byte[] compressedData = Zstd.compress(data, level);
		return CompressionUtil.addOriginalLengthTo(compressedData, data.length);
	}

	@Override
	public byte[] uncompress(byte[] data) {
		byte[] uncompresseData = new byte[CompressionUtil.getOriginalLength(data)];
		Zstd.decompressByteArray(uncompresseData, 0, uncompresseData.length, data, Integer.BYTES,
				data.length - Integer.BYTES);
		return uncompresseData;
	}

}
