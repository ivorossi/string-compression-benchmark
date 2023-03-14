package com.brightsector.stringcompressionbenchmarkivo.algorithms;

import java.nio.ByteBuffer;
import java.util.Arrays;

import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4FastDecompressor;

public class LZ4ByteIntArrayCompressionAlgorithm implements CompressionAlgorithm {

	private final LZ4Factory factory = LZ4Factory.fastestInstance();
	private final LZ4Compressor compressor = factory.fastCompressor();
	private final LZ4FastDecompressor decompressor = factory.fastDecompressor();

	@Override
	public byte[] compress(byte[] data) {
		byte[] compress = compressor.compress(data);
		return ByteBuffer.allocate(Integer.BYTES + compress.length).putInt(data.length).put(compress).array();
	}

	@Override
	public byte[] uncompress(byte[] data) {
		byte[] byteLenght = Arrays.copyOf(data, Integer.BYTES);
		int length = ByteBuffer.wrap(byteLenght).getInt();
		return decompressor.decompress(data, Integer.BYTES, length);
	}

}
