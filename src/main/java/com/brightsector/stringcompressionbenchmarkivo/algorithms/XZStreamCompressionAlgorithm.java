package com.brightsector.stringcompressionbenchmarkivo.algorithms;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.compress.compressors.xz.XZCompressorInputStream;
import org.apache.commons.compress.compressors.xz.XZCompressorOutputStream;

public class XZStreamCompressionAlgorithm extends StreamCompressionAlgorithm {

	@Override
	public OutputStream getCompressAlgorithm(ByteArrayOutputStream output, int textSize) throws IOException {
		return new XZCompressorOutputStream(output);
	}

	@Override
	public InputStream getUncompressAlgorithm(ByteArrayInputStream input) throws IOException {
		return new XZCompressorInputStream(input);
	}

}
