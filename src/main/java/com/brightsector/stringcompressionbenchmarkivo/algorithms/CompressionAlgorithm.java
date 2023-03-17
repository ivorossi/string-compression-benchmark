package com.brightsector.stringcompressionbenchmarkivo.algorithms;

import java.util.Map;

import com.brightsector.stringcompressionbenchmarkivo.algorithms.lz4.LZ4BlockStreamCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.lz4.LZ4LengthArrayCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.lz4.LZ4WhileArrayCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.lz4.LZ4FramedStreamCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.zstd.ZStandardStreamCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.zstd.ZstdLengthArrayCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.zstd.ZstdSizeArrayCompressionAlgorithm;

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
			Map.entry("LZ4HCMaxLength", LZ4LengthArrayCompressionAlgorithm.LZ4_HC_MAX_LENGTH),
			Map.entry("LZ4HCHighLength", LZ4LengthArrayCompressionAlgorithm.LZ4_HC_HIGH_LENGTH),
			Map.entry("LZ4HCMidLength", LZ4LengthArrayCompressionAlgorithm.LZ4_HC_MID_LENGTH),
			Map.entry("LZ4HCLowLength", LZ4LengthArrayCompressionAlgorithm.LZ4_HC_LOW_LENGTH),
			Map.entry("LZ4HCMinLength", LZ4LengthArrayCompressionAlgorithm.LZ4_HC_MIN_LENGTH),
			Map.entry("LZ4FCLength", LZ4LengthArrayCompressionAlgorithm.LZ4_FC_LENGTH),
			Map.entry("LZ4HCMaxWhile", LZ4WhileArrayCompressionAlgorithm.LZ4_HC_MAX_WHILE),
			Map.entry("LZ4HCHighWhile", LZ4WhileArrayCompressionAlgorithm.LZ4_HC_HIGH_WHILE),
			Map.entry("LZ4HCMidWhile", LZ4WhileArrayCompressionAlgorithm.LZ4_HC_MID_WHILE),
			Map.entry("LZ4HCLowWhile", LZ4WhileArrayCompressionAlgorithm.LZ4_HC_LOW_WHILE),
			Map.entry("LZ4HCMinWhile", LZ4WhileArrayCompressionAlgorithm.LZ4_HC_MIN_WHILE),
			Map.entry("LZ4FCWhile", LZ4WhileArrayCompressionAlgorithm.LZ4_FC_WHILE),
			Map.entry("ZStdMaxSize", ZstdSizeArrayCompressionAlgorithm.ZSTD_MAX_SIZE),
			Map.entry("ZStdHighSize", ZstdSizeArrayCompressionAlgorithm.ZSTD_HIGH_SIZE),
			Map.entry("ZStdMidSize", ZstdSizeArrayCompressionAlgorithm.ZSTD_MID_SIZE),
			Map.entry("ZStdLowSize", ZstdSizeArrayCompressionAlgorithm.ZSTD_LOW_SIZE),
			Map.entry("ZStdMinSize", ZstdSizeArrayCompressionAlgorithm.ZSTD_MIN_SIZE),
			Map.entry("ZStdMaxLength", ZstdLengthArrayCompressionAlgorithm.ZSTD_MAX_LENGTH),
			Map.entry("ZStdHighLength", ZstdLengthArrayCompressionAlgorithm.ZSTD_HIGH_LENGTH),
			Map.entry("ZStdMidLength", ZstdLengthArrayCompressionAlgorithm.ZSTD_MID_LENGTH),
			Map.entry("ZStdLowLength", ZstdLengthArrayCompressionAlgorithm.ZSTD_LOW_LENGTH),
			Map.entry("ZStdMinLength", ZstdLengthArrayCompressionAlgorithm.ZSTD_MIN_LENGTH),
			Map.entry("LZFBytes", new LZFBytesCompressionAlgortihm()),
			Map.entry("NoCompress", new NoCompressionAlgorithm())));

	public byte[] compress(byte[] data);

	public byte[] uncompress(byte[] data);

}
