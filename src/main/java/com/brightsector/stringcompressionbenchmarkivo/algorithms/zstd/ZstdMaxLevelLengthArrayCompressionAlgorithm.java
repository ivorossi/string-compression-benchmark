package com.brightsector.stringcompressionbenchmarkivo.algorithms.zstd;

import com.brightsector.stringcompressionbenchmarkivo.algorithms.Util;

public class ZstdMaxLevelLengthArrayCompressionAlgorithm extends ZstdLengthArrayCompressionAlgorithm {

	@Override
	public int setLevel() {
		return Util.ZSTD_MAX_LEVEL;
	}

}
