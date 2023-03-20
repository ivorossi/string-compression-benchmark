package com.brightsector.stringcompressionbenchmarkivo.algorithms.lz4;

import com.brightsector.stringcompressionbenchmarkivo.algorithms.CompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.Util;

import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Exception;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4SafeDecompressor;

public class LZ4WhileArrayCompressionAlgorithm implements CompressionAlgorithm {

	public static final CompressionAlgorithm LZ4_HC_MIN_WHILE = new LZ4WhileArrayCompressionAlgorithm(
			LZ4Factory.fastestInstance().highCompressor(Util.LZ4_MIN_LEVEL));
	public static final CompressionAlgorithm LZ4_HC_LOW_WHILE = new LZ4WhileArrayCompressionAlgorithm(
			LZ4Factory.fastestInstance().highCompressor(Util.LZ4_LOW_LEVEL));
	public static final CompressionAlgorithm LZ4_HC_MID_WHILE = new LZ4WhileArrayCompressionAlgorithm(
			LZ4Factory.fastestInstance().highCompressor(Util.LZ4_MID_LEVEL));
	public static final CompressionAlgorithm LZ4_HC_HIGH_WHILE = new LZ4WhileArrayCompressionAlgorithm(
			LZ4Factory.fastestInstance().highCompressor(Util.LZ4_HIGH_LEVEL));
	public static final CompressionAlgorithm LZ4_HC_MAX_WHILE = new LZ4WhileArrayCompressionAlgorithm(
			LZ4Factory.fastestInstance().highCompressor(Util.LZ4_MAX_LEVEL));
	public static final CompressionAlgorithm LZ4_FC_WHILE = new LZ4WhileArrayCompressionAlgorithm(
			LZ4Factory.fastestInstance().fastCompressor());
	private final LZ4Compressor compressor;
	private final LZ4SafeDecompressor decompressor = LZ4Factory.fastestInstance().safeDecompressor();

	public LZ4WhileArrayCompressionAlgorithm(LZ4Compressor compressor) {
		this.compressor = compressor;
	}

	@Override
	public byte[] compress(byte[] data) {
		return compressor.compress(data);
	}

	@Override
	public byte[] uncompress(byte[] data) {
		int bufferLength = data.length * 3;
		do {
			try {
				return decompressor.decompress(data, bufferLength);
			} catch (LZ4Exception e) {
				bufferLength += bufferLength;
			}
		} while (true);
	}

}