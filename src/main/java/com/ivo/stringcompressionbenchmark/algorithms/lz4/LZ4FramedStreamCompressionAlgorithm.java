package com.ivo.stringcompressionbenchmark.algorithms.lz4;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.ivo.stringcompressionbenchmark.algorithms.StreamCompressionAlgorithm;

import net.jpountz.lz4.LZ4FrameInputStream;
import net.jpountz.lz4.LZ4FrameOutputStream;

public class LZ4FramedStreamCompressionAlgorithm extends StreamCompressionAlgorithm {

	@Override
	public OutputStream getCompressAlgorithm(ByteArrayOutputStream output, int textSize) throws IOException {
		return new LZ4FrameOutputStream(output);
	}

	@Override
	public InputStream getUncompressAlgorithm(ByteArrayInputStream input) throws IOException {
		return new LZ4FrameInputStream(input);
	}

}
