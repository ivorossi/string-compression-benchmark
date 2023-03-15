package com.brightsector.stringcompressionbenchmarkivo.algorithms;

import java.nio.ByteBuffer;
import java.util.Arrays;

public final class Util {

	private Util() {
	}

	public static final int ZSTD_MID_LEVEL = 10;
	public static final int LZ4_MAX_LEVEL = 17;
	public static final int LZ4_HIGH_LEVEL = 13;
	public static final int LZ4_MID_LEVEL = 9;
	public static final int LZ4_LOW_LEVEL = 5;
	public static final int LZ4_MIN_LEVEL = 1;

	public static int getOriginalLength(byte[] data) {
		byte[] originalLength = Arrays.copyOf(data, Integer.BYTES);
		return ByteBuffer.wrap(originalLength).getInt();
	}
}
