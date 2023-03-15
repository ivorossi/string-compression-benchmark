package com.brightsector.stringcompressionbenchmarkivo.algorithms.lz4;

import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;

public class LZ4FCWhileArrayCompressionAlgorithm extends LZ4WhileArrayCompressionAlgorithm {

	@Override
	protected LZ4Compressor setLevel() {
		return LZ4Factory.fastestInstance().fastCompressor();
	}

}
