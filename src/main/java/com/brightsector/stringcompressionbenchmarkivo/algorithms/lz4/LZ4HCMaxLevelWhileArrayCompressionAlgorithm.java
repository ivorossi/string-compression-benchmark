package com.brightsector.stringcompressionbenchmarkivo.algorithms.lz4;

import com.brightsector.stringcompressionbenchmarkivo.algorithms.Util;

import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;

public class LZ4HCMaxLevelWhileArrayCompressionAlgorithm extends LZ4WhileArrayCompressionAlgorithm {

	@Override
	protected LZ4Compressor setLevel() {
		return LZ4Factory.fastestInstance().highCompressor(Util.LZ4_MAX_LEVEL);
	}

}
