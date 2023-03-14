package com.brightsector.stringcompressionbenchmarkivo.algorithms;

import java.nio.ByteBuffer;

import com.github.luben.zstd.Zstd;

public class ZstdByteIntArrayCompressionAlgorithm implements CompressionAlgorithm {

	@Override
	public byte[] compress(byte[] data) {
		byte[] compressedData = Zstd.compress(data, Util.ZSTD_MID_LEVEL);
		return ByteBuffer.allocate(Integer.BYTES + compressedData.length).putInt(data.length).put(compressedData)
				.array();
	}

	@Override
	public byte[] uncompress(byte[] data) {
		byte[] uncompresseData = new byte[Util.getOriginalLength(data)];
		Zstd.decompressByteArray(uncompresseData, 0, uncompresseData.length, data, Integer.BYTES,
				data.length - Integer.BYTES);
		return uncompresseData;
	}

}
