package com.brightsector.stringcompressionbenchmarkivo.algorithms;

import java.nio.ByteBuffer;
import java.util.Arrays;

public interface Util {

	public static final int ZSTD_MID_LEVEL = 10;
	public static final int LZ4_MAX_LEVEL = 17;

	public static int getOriginalLength(byte[] data) {
		byte[] originalLength = Arrays.copyOf(data, Integer.BYTES);
		return ByteBuffer.wrap(originalLength).getInt();
	}
}
