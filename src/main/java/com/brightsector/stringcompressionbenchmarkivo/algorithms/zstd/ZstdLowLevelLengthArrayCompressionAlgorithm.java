package com.brightsector.stringcompressionbenchmarkivo.algorithms.zstd;

import com.brightsector.stringcompressionbenchmarkivo.algorithms.Util;

public class ZstdLowLevelLengthArrayCompressionAlgorithm extends ZstdLengthArrayCompressionAlgorithm {

	@Override
	public int setLevel() {
		return Util.ZSTD_LOW_LEVEL;
	}

}
