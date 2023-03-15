package com.brightsector.stringcompressionbenchmarkivo.algorithms.lz4;

import java.nio.ByteBuffer;

import com.brightsector.stringcompressionbenchmarkivo.algorithms.CompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.Util;

import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4FastDecompressor;

public abstract class LZ4LengthArrayCompressionAlgorithm implements CompressionAlgorithm {

	private final LZ4Compressor compressor;
	private final LZ4FastDecompressor decompressor = LZ4Factory.safeInstance().fastDecompressor();

	public LZ4LengthArrayCompressionAlgorithm() {
		this.compressor = setLevel();
	}

	protected abstract LZ4Compressor setLevel();

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
