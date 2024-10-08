package com.ivo.stringcompressionbenchmark.algorithms.lz4;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.ivo.stringcompressionbenchmark.algorithms.CompressionUtil;
import com.ivo.stringcompressionbenchmark.algorithms.StreamCompressionAlgorithm;

import net.jpountz.lz4.LZ4BlockInputStream;
import net.jpountz.lz4.LZ4BlockOutputStream;
import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;

public class LZ4BlockStreamCompressionAlgorithm extends StreamCompressionAlgorithm {

	private final LZ4Compressor lz4HihgCompressor = LZ4Factory.fastestJavaInstance().highCompressor(CompressionUtil.LZ4_MAX_LEVEL);

	@Override
	public OutputStream getCompressAlgorithm(ByteArrayOutputStream output, int textSize) throws IOException {
		return new LZ4BlockOutputStream(output, 921600, lz4HihgCompressor);
	}

	@Override
	public InputStream getUncompressAlgorithm(ByteArrayInputStream input) throws IOException {
		return new LZ4BlockInputStream(input);
	}

}
