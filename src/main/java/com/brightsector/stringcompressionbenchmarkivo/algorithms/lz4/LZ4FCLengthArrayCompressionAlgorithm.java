package com.brightsector.stringcompressionbenchmarkivo.algorithms.lz4;

import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;

public class LZ4FCLengthArrayCompressionAlgorithm extends LZ4LengthArrayCompressionAlgorithm {

	@Override
	protected LZ4Compressor setLevel() {
		return LZ4Factory.fastestInstance().fastCompressor();
	}

}
