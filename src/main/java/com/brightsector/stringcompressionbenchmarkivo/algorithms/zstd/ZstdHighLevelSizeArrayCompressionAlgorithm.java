package com.brightsector.stringcompressionbenchmarkivo.algorithms.zstd;

import com.brightsector.stringcompressionbenchmarkivo.algorithms.Util;

public class ZstdHighLevelSizeArrayCompressionAlgorithm extends ZstdSizeArrayCompressionAlgorithm {

	@Override
	public int setLevel() {
		return Util.ZSTD_HIGH_LEVEL;
	}

}
