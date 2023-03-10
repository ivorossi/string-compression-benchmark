package com.brightsector.string_compression_benchmark_ivo.algorithm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.jpountz.lz4.LZ4BlockInputStream;
import net.jpountz.lz4.LZ4BlockOutputStream;
import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;

public class LZ4BlockCompressionAlgorithm extends StreamCompressionAlgorithm {

	@Override
	public OutputStream getCompressAlgorithm(ByteArrayOutputStream output, int textSize) throws IOException {
		LZ4Compressor lz4HihgCompressor = LZ4Factory.fastestJavaInstance().highCompressor(17);
		return new LZ4BlockOutputStream(output, 921600, lz4HihgCompressor);
	}

	@Override
	public InputStream getUncompressAlgorithm(ByteArrayInputStream input) throws IOException {
		return new LZ4BlockInputStream(input);
	}

}
