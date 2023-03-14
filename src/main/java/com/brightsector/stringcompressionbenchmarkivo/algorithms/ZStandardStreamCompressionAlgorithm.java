package com.brightsector.stringcompressionbenchmarkivo.algorithms;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.compress.compressors.zstandard.ZstdCompressorInputStream;
import org.apache.commons.compress.compressors.zstandard.ZstdCompressorOutputStream;

public class ZStandardStreamCompressionAlgorithm extends StreamCompressionAlgorithm {

	public static final int ZSTD_MID_LEVEL = 10;

	@Override
	public OutputStream getCompressAlgorithm(ByteArrayOutputStream output, int textSize) throws IOException {
		return new ZstdCompressorOutputStream(output, ZSTD_MID_LEVEL);
	}

	@Override
	public InputStream getUncompressAlgorithm(ByteArrayInputStream input) throws IOException {
		return new ZstdCompressorInputStream(input);
	}

}
