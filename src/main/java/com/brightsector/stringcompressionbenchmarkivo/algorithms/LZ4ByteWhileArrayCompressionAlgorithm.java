package com.brightsector.stringcompressionbenchmarkivo.algorithms;

import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Exception;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4SafeDecompressor;

public class LZ4ByteWhileArrayCompressionAlgorithm implements CompressionAlgorithm {
	private final LZ4Factory factory = LZ4Factory.fastestInstance();
	private final LZ4Compressor compressor = factory.highCompressor(17);
	private final LZ4SafeDecompressor decompressor = factory.safeDecompressor();

	@Override
	public byte[] compress(byte[] data) {
		return compressor.compress(data);
	}

	@Override
	public byte[] uncompress(byte[] data) {
		int lengthBuffer = data.length * 3;
		do {
			try {
				return decompressor.decompress(data, lengthBuffer);
			} catch (LZ4Exception e) {
				lengthBuffer += lengthBuffer;
			}
		} while (true);
	}
}
