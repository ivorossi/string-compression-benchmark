package com.brightsector.string_compression_benchmark_ivo.algorithm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

public abstract class CompressionAlgorithm {

	protected abstract OutputStream getCompressAlgorithm(ByteArrayOutputStream output, int textSize) throws IOException;

	protected abstract InputStream getUncompressAlgorithm(ByteArrayInputStream input) throws IOException;

	public byte[] compress(String text) {
		try {
			byte[] textByte = text.getBytes(StandardCharsets.UTF_8);
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			OutputStream compress = getCompressAlgorithm(output, textByte.length);
			if (compress != null) {
				compress.write(textByte);
				compress.close();
				return output.toByteArray();
			} else {
				return textByte;
			}
		} catch (IOException e) {
			throw new UncheckedIOException("Error compressing: ", e);
		}
	}

	public byte[] uncompress(byte[] dataCompress) {
		try {
			ByteArrayInputStream input = new ByteArrayInputStream(dataCompress);
			InputStream uncompress = getUncompressAlgorithm(input);
			if (uncompress != null) {
				byte[] dataUncompress = uncompress.readAllBytes();
				uncompress.close();
				return dataUncompress;
			} else {
				return dataCompress;
			}
		} catch (IOException e) {
			throw new UncheckedIOException("Error decompressing: ", e);
		}
	}
}
