package com.brightsector.stringcompressionbenchmarkivo.algorithms.lz4;

import com.brightsector.stringcompressionbenchmarkivo.algorithms.Util;

import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;

public class LZ4HCLowLevelLengthArrayCompressionAlgorithm extends LZ4LengthArrayCompressionAlgorithm {

	@Override
	protected LZ4Compressor setLevel() {
		return LZ4Factory.fastestInstance().highCompressor(Util.LZ4_LOW_LEVEL);
	}

}
