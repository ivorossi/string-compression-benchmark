package com.brightsector.stringcompressionbenchmarkivo.algorithms;

import java.nio.ByteBuffer;

import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4FastDecompressor;

public class LZ4ByteIntArrayCompressionAlgorithm implements CompressionAlgorithm {

	private final LZ4Factory factory = LZ4Factory.fastestInstance();
	private final LZ4Compressor compressor = factory.fastCompressor();
	private final LZ4FastDecompressor decompressor = factory.fastDecompressor();

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
