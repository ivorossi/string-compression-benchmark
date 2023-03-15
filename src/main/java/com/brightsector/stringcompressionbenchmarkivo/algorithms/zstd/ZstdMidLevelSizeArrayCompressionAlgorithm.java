package com.brightsector.stringcompressionbenchmarkivo.algorithms.zstd;

import com.brightsector.stringcompressionbenchmarkivo.algorithms.Util;

public class ZstdMidLevelSizeArrayCompressionAlgorithm extends ZstdSizeArrayCompressionAlgorithm {

	@Override
	public int setLevel() {
		return Util.ZSTD_MID_LEVEL;
	}

}
