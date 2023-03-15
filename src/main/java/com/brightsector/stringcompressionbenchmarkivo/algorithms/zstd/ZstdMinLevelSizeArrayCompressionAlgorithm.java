package com.brightsector.stringcompressionbenchmarkivo.algorithms.zstd;

import com.brightsector.stringcompressionbenchmarkivo.algorithms.Util;

public class ZstdMinLevelSizeArrayCompressionAlgorithm extends ZstdSizeArrayCompressionAlgorithm {

	@Override
	public int setLevel() {
		return Util.ZSTD_MIN_LEVEL;
	}

}
