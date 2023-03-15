package com.brightsector.stringcompressionbenchmarkivo.algorithms.zstd;

import com.brightsector.stringcompressionbenchmarkivo.algorithms.Util;

public class ZstdHighLevelLengthArrayCompressionAlgorithm extends ZstdLengthArrayCompressionAlgorithm {

	@Override
	public int setLevel() {
		return Util.ZSTD_HIGH_LEVEL;
	}

}
