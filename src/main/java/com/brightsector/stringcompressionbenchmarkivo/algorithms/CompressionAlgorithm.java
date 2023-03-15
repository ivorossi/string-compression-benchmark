package com.brightsector.stringcompressionbenchmarkivo.algorithms;

import java.util.Map;

import com.brightsector.stringcompressionbenchmarkivo.algorithms.lz4.LZ4BlockStreamCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.lz4.LZ4FCLengthArrayCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.lz4.LZ4FCWhileArrayCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.lz4.LZ4HCMaxLevelWhileArrayCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.lz4.LZ4HCMidLevelLengthArrayCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.lz4.LZ4HCMidLevelWhileArrayCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.lz4.LZ4HCMinLevelLengthArrayCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.lz4.LZ4HCMinLevelWhileArrayCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.lz4.LZ4FramedStreamCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.lz4.LZ4HCHighLevelLengthArrayCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.lz4.LZ4HCHighLevelWhileArrayCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.lz4.LZ4HCLowLevelLengthArrayCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.lz4.LZ4HCLowLevelWhileArrayCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.lz4.LZ4HCMaxLevelLengthArrayCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.zstd.ZStandardStreamCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.zstd.ZstdMaxLevelSizeArrayCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.zstd.ZstdHighLevelLengthArrayCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.zstd.ZstdHighLevelSizeArrayCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.zstd.ZstdLowLevelLengthArrayCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.zstd.ZstdLowLevelSizeArrayCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.zstd.ZstdMaxLevelLengthArrayCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.zstd.ZstdMidLevelLengthArrayCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.zstd.ZstdMidLevelSizeArrayCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.zstd.ZstdMinLevelLengthArrayCompressionAlgorithm;
import com.brightsector.stringcompressionbenchmarkivo.algorithms.zstd.ZstdMinLevelSizeArrayCompressionAlgorithm;

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
			Map.entry("LZ4HCMaxLength", new LZ4HCMaxLevelLengthArrayCompressionAlgorithm()),
			Map.entry("LZ4HCHighLength", new LZ4HCHighLevelLengthArrayCompressionAlgorithm()),
			Map.entry("LZ4HCMidLength", new LZ4HCMidLevelLengthArrayCompressionAlgorithm()),
			Map.entry("LZ4HCLowLength", new LZ4HCLowLevelLengthArrayCompressionAlgorithm()),
			Map.entry("LZ4HCMinLength", new LZ4HCMinLevelLengthArrayCompressionAlgorithm()),
			Map.entry("LZ4FCLength", new LZ4FCLengthArrayCompressionAlgorithm()),
			Map.entry("LZ4HCMaxWhile", new LZ4HCMaxLevelWhileArrayCompressionAlgorithm()),
			Map.entry("LZ4HCHighWhile", new LZ4HCHighLevelWhileArrayCompressionAlgorithm()),
			Map.entry("LZ4HCMidWhile", new LZ4HCMidLevelWhileArrayCompressionAlgorithm()),
			Map.entry("LZ4HCLowWhile", new LZ4HCLowLevelWhileArrayCompressionAlgorithm()),
			Map.entry("LZ4HCMinWhile", new LZ4HCMinLevelWhileArrayCompressionAlgorithm()),
			Map.entry("LZ4FCWhile", new LZ4FCWhileArrayCompressionAlgorithm()),
			Map.entry("ZStdMaxSize", new ZstdMaxLevelSizeArrayCompressionAlgorithm()),
			Map.entry("ZStdHighSize", new ZstdHighLevelSizeArrayCompressionAlgorithm()),
			Map.entry("ZStdMidSize", new ZstdMidLevelSizeArrayCompressionAlgorithm()),
			Map.entry("ZStdLowSize", new ZstdLowLevelSizeArrayCompressionAlgorithm()),
			Map.entry("ZStdMinSize", new ZstdMinLevelSizeArrayCompressionAlgorithm()),
			Map.entry("ZStdMaxLength", new ZstdMaxLevelLengthArrayCompressionAlgorithm()),
			Map.entry("ZStdHighLength", new ZstdHighLevelLengthArrayCompressionAlgorithm()),
			Map.entry("ZStdMidLength", new ZstdMidLevelLengthArrayCompressionAlgorithm()),
			Map.entry("ZStdLowLength", new ZstdLowLevelLengthArrayCompressionAlgorithm()),
			Map.entry("ZStdMinLength", new ZstdMinLevelLengthArrayCompressionAlgorithm()),
			Map.entry("LZFBytes", new LZFBytesCompressionAlgortihm()),
			Map.entry("NoCompress", new NoCompressionAlgorithm())));

	public byte[] compress(byte[] data);

	public byte[] uncompress(byte[] data);

}
