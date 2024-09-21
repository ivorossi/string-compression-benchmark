package com.ivo.stringcompressionbenchmark.algorithms;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;

public abstract class StreamCompressionAlgorithm implements CompressionAlgorithm {

	protected abstract OutputStream getCompressAlgorithm(ByteArrayOutputStream output, int textSize) throws IOException;

	protected abstract InputStream getUncompressAlgorithm(ByteArrayInputStream input) throws IOException;

	public byte[] compress(byte[] data) {
		try {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			OutputStream compress = getCompressAlgorithm(output, data.length);
			compress.write(data);
			compress.close();
			return output.toByteArray();
		} catch (IOException e) {
			throw new UncheckedIOException("Error compressing: ", e);
		}
	}

	public byte[] uncompress(byte[] data) {
		try {
			ByteArrayInputStream input = new ByteArrayInputStream(data);
			InputStream uncompress = getUncompressAlgorithm(input);
			byte[] dataUncompress = uncompress.readAllBytes();
			uncompress.close();
			return dataUncompress;
		} catch (IOException e) {
			throw new UncheckedIOException("Error decompressing: ", e);
		}
	}

}
