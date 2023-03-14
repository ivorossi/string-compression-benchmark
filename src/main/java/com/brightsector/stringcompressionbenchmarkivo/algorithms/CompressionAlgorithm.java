package com.brightsector.stringcompressionbenchmarkivo.algorithms;

import java.util.Map;

public interface CompressionAlgorithm {

	public static Map<String, CompressionAlgorithm> ALGORITHMS = (Map.ofEntries(
			Map.entry("GZIPStream", new GzipStreamCompressionAlgorithm()),
			Map.entry("B2ZipStream", new B2ZipStreamCompressionAlgorithm()),
			Map.entry("DeflaterStream", new DeflaterStreamCompressionAlgorithm()),
			Map.entry("LZ4FramedStream", new LZ4FramedStreamCompressionAlgorithm()),
			Map.entry("LZ4BlockStream", new LZ4BlockStreamCompressionAlgorithm()),
			Map.entry("LZMAStream", new LZMAStreamCompressionAlgorithm()),
			Map.entry("ZStdStream", new ZStandardStreamCompressionAlgorithm()),
			Map.entry("XZStream", new XZStreamCompressionAlgorithm()),
			Map.entry("SnnapyStdStream", new SnnapyStandardStreamCompressionAlgorithm()),
			Map.entry("SnappyBytes", new SnappyByteArrayCompressionAlgorithm()),
			Map.entry("LZ4BytesInt", new LZ4ByteIntArrayCompressionAlgorithm()),
			Map.entry("LZ4BytesWhile", new LZ4ByteWhileArrayCompressionAlgorithm()),
			Map.entry("ZStdByte", new ZstdByteArrayCompressionAlgorithm()),
			Map.entry("ZStdByteInt", new ZstdByteIntArrayCompressionAlgorithm()),
			Map.entry("LZFBytes", new LZFBytesCompressionAlgortihm()),
			Map.entry("NoCompress", new NoCompressionAlgorithm())));

	public byte[] compress(byte[] data);

	public byte[] uncompress(byte[] data);

}
