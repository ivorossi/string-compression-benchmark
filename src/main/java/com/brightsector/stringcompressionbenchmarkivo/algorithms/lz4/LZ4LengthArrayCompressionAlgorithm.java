package com.brightsector.stringcompressionbenchmarkivo.algorithms.lz4;

import java.nio.ByteBuffer;

import com.brightsector.stringcompressionbenchmarkivo.algorithms.CompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.Util;

import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4FastDecompressor;

public class LZ4LengthArrayCompressionAlgorithm implements CompressionAlgorithm {

	public static final CompressionAlgorithm LZ4_HC_MIN_LENGTH = new LZ4LengthArrayCompressionAlgorithm(
			LZ4Factory.fastestInstance().highCompressor(Util.LZ4_MIN_LEVEL));
	public static final CompressionAlgorithm LZ4_HC_LOW_LENGTH = new LZ4LengthArrayCompressionAlgorithm(
			LZ4Factory.fastestInstance().highCompressor(Util.LZ4_LOW_LEVEL));
	public static final CompressionAlgorithm LZ4_HC_MID_LENGTH = new LZ4LengthArrayCompressionAlgorithm(
			LZ4Factory.fastestInstance().highCompressor(Util.LZ4_MID_LEVEL));
	public static final CompressionAlgorithm LZ4_HC_HIGH_LENGTH = new LZ4LengthArrayCompressionAlgorithm(
			LZ4Factory.fastestInstance().highCompressor(Util.LZ4_HIGH_LEVEL));
	public static final CompressionAlgorithm LZ4_HC_MAX_LENGTH = new LZ4LengthArrayCompressionAlgorithm(
			LZ4Factory.fastestInstance().highCompressor(Util.LZ4_MAX_LEVEL));
	public static final CompressionAlgorithm LZ4_FC_LENGTH = new LZ4LengthArrayCompressionAlgorithm(
			LZ4Factory.fastestInstance().fastCompressor());
	private final LZ4Compressor compressor;
	private final LZ4FastDecompressor decompressor = LZ4Factory.safeInstance().fastDecompressor();

	public LZ4LengthArrayCompressionAlgorithm(LZ4Compressor compressor) {
		this.compressor = compressor;
	}

	@Override
	public byte[] compress(byte[] data) {
		byte[] compressedData = compressor.compress(data);
		return ByteBuffer.allocate(Integer.BYTES + compressedData.length).putInt(data.length).put(compressedData)
				.array();
	}

	@Override
	public byte[] uncompress(byte[] data) {
		return decompressor.decompress(data, Integer.BYTES, Util.getOriginalLength(data));
	}

}
