package com.ivo.stringcompressionbenchmark.algorithms.zstd;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.compress.compressors.zstandard.ZstdCompressorInputStream;
import org.apache.commons.compress.compressors.zstandard.ZstdCompressorOutputStream;

import com.ivo.stringcompressionbenchmark.algorithms.CompressionUtil;
import com.ivo.stringcompressionbenchmark.algorithms.StreamCompressionAlgorithm;

public class ZStandardStreamCompressionAlgorithm extends StreamCompressionAlgorithm {

	@Override
	public OutputStream getCompressAlgorithm(ByteArrayOutputStream output, int textSize) throws IOException {
		return new ZstdCompressorOutputStream(output, CompressionUtil.ZSTD_MID_LEVEL);
	}

	@Override
	public InputStream getUncompressAlgorithm(ByteArrayInputStream input) throws IOException {
		return new ZstdCompressorInputStream(input);
	}

}
