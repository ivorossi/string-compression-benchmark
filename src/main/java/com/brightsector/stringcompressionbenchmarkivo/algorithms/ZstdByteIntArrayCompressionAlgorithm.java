package com.brightsector.stringcompressionbenchmarkivo.algorithms;

import java.nio.ByteBuffer;
import java.util.Arrays;

import com.github.luben.zstd.Zstd;

public class ZstdByteIntArrayCompressionAlgorithm implements CompressionAlgorithm {

	@Override
	public byte[] compress(byte[] data) {
		byte[] compress = Zstd.compress(data, ZSTD_MID_LEVEL);
		return ByteBuffer.allocate(Integer.BYTES + compress.length).putInt(data.length).put(compress).array();
	}

	@Override
	public byte[] uncompress(byte[] data) {
		byte[] byteLenght = Arrays.copyOf(data, Integer.BYTES);
		int lenght = ByteBuffer.wrap(byteLenght).getInt();
		byte[] dst = new byte[lenght];
		Zstd.decompressByteArray(dst, 0, lenght, data, Integer.BYTES, data.length - Integer.BYTES);
		return dst;
	}
}
