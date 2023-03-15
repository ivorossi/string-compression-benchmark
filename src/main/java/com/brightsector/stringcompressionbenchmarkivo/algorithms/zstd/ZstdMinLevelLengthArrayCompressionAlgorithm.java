package com.brightsector.stringcompressionbenchmarkivo.algorithms.zstd;

import com.brightsector.stringcompressionbenchmarkivo.algorithms.Util;

public class ZstdMinLevelLengthArrayCompressionAlgorithm extends ZstdLengthArrayCompressionAlgorithm {

	@Override
	public int setLevel() {
		return Util.ZSTD_MIN_LEVEL;
	}

}
