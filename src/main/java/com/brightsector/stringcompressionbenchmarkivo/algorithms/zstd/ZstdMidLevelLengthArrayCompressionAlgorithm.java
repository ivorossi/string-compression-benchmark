package com.brightsector.stringcompressionbenchmarkivo.algorithms.zstd;

import com.brightsector.stringcompressionbenchmarkivo.algorithms.Util;

public class ZstdMidLevelLengthArrayCompressionAlgorithm extends ZstdLengthArrayCompressionAlgorithm {

	@Override
	public int setLevel() {
		return Util.ZSTD_MID_LEVEL;
	}

}
