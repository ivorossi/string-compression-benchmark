package com.brightsector.stringcompressionbenchmarkivo.algorithms.zstd;

import java.nio.ByteBuffer;

import com.brightsector.stringcompressionbenchmarkivo.algorithms.CompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.Util;
import com.github.luben.zstd.Zstd;

public abstract class ZstdLengthArrayCompressionAlgorithm implements CompressionAlgorithm {

	public abstract int setLevel();

	@Override
	public byte[] compress(byte[] data) {
		byte[] compressedData = Zstd.compress(data, setLevel());
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
