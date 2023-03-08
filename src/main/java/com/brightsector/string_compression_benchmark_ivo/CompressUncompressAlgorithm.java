package com.brightsector.string_compression_benchmark_ivo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.apache.commons.compress.compressors.deflate.DeflateCompressorInputStream;
import org.apache.commons.compress.compressors.deflate.DeflateCompressorOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.compress.compressors.lzma.LZMACompressorInputStream;
import org.apache.commons.compress.compressors.lzma.LZMACompressorOutputStream;
import org.apache.commons.compress.compressors.snappy.SnappyCompressorInputStream;
import org.apache.commons.compress.compressors.snappy.SnappyCompressorOutputStream;
import org.apache.commons.compress.compressors.xz.XZCompressorInputStream;
import org.apache.commons.compress.compressors.xz.XZCompressorOutputStream;
import org.apache.commons.compress.compressors.zstandard.ZstdCompressorInputStream;
import org.apache.commons.compress.compressors.zstandard.ZstdCompressorOutputStream;

import net.jpountz.lz4.LZ4BlockInputStream;
import net.jpountz.lz4.LZ4BlockOutputStream;
import net.jpountz.lz4.LZ4FrameInputStream;
import net.jpountz.lz4.LZ4FrameOutputStream;

public class CompressUncompressAlgorithm {

	public static byte[] compress(String text, String algorithm) {
		try {
			byte[] textByte = text.getBytes(StandardCharsets.UTF_8);
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			OutputStream compress = compressTechnology(algorithm, output, textByte.length);
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

	public static String uncompress(byte[] textCompress, String algorithm) {
		try {
			ByteArrayInputStream input = new ByteArrayInputStream(textCompress);
			InputStream uncompress = uncompressTechnology(algorithm, input);
			if (uncompress != null) {
				byte[] buffer = uncompress.readAllBytes();
				uncompress.close();
				return new String(buffer, 0, buffer.length, StandardCharsets.UTF_8);
			} else {
				return new String(textCompress, 0, textCompress.length, StandardCharsets.UTF_8);
			}
		} catch (IOException e) {
			throw new UncheckedIOException("Error decompressing: ", e);
		}
	}

	private static OutputStream compressTechnology(String algorithm, ByteArrayOutputStream output, int textSize)
			throws IOException {
		if (algorithm.equals("DEFLATER")) {
			return new DeflateCompressorOutputStream(output);
		} else if (algorithm.equals("GZIP")) {
			return new GzipCompressorOutputStream(output);
		} else if (algorithm.equals("LZ4Framed")) {
			return new LZ4FrameOutputStream(output);
		} else if (algorithm.equals("LZ4Block")) {
			return new LZ4BlockOutputStream(output);
		} else if (algorithm.equals("LZMA")) {
			return new LZMACompressorOutputStream(output);
		} else if (algorithm.equals("SNAPPYStandard")) {
			return new SnappyCompressorOutputStream(output, textSize);
		} else if (algorithm.equals("XZ")) {
			return new XZCompressorOutputStream(output);
		} else if (algorithm.equals("Zstandard")) {
			return new ZstdCompressorOutputStream(output);
		} else if (algorithm.equals("BZIP2")) {
			return new BZip2CompressorOutputStream(output);
		} else {
			return null;
		}
	}

	private static InputStream uncompressTechnology(String algorithm, ByteArrayInputStream input) throws IOException {
		if (algorithm.equals("DEFLATER")) {
			return new DeflateCompressorInputStream(input);
		} else if (algorithm.equals("GZIP")) {
			return new GzipCompressorInputStream(input);
		} else if (algorithm.equals("LZ4Framed")) {
			return new LZ4FrameInputStream(input);
		} else if (algorithm.equals("LZ4Block")) {
			return new LZ4BlockInputStream(input);
		} else if (algorithm.equals("LZMA")) {
			return new LZMACompressorInputStream(input);
		} else if (algorithm.equals("SNAPPYStandard")) {
			return new SnappyCompressorInputStream(input);
		} else if (algorithm.equals("XZ")) {
			return new XZCompressorInputStream(input);
		} else if (algorithm.equals("Zstandard")) {
			return new ZstdCompressorInputStream(input);
		} else if (algorithm.equals("BZIP2")) {
			return new BZip2CompressorInputStream(input);
		} else {
			return null;
		}
	}
}
