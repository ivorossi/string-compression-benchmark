package com.brightsector.stringcompressionbenchmarkivo.algorithms.lz4;

import com.brightsector.stringcompressionbenchmarkivo.algorithms.CompressionAlgorithm;
import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Exception;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4SafeDecompressor;

public abstract class LZ4WhileArrayCompressionAlgorithm implements CompressionAlgorithm {

	private final LZ4Compressor compressor;
	private final LZ4SafeDecompressor decompressor = LZ4Factory.fastestJavaInstance().safeDecompressor();

	public LZ4WhileArrayCompressionAlgorithm() {
		this.compressor = setLevel();
	}

	protected abstract LZ4Compressor setLevel();

	@Override
	public byte[] compress(byte[] data) {
		return compressor.compress(data);
	}

	@Override
	public byte[] uncompress(byte[] data) {
		int bufferLength = data.length * 3;
		do {
			try {
				return decompressor.decompress(data, bufferLength);
			} catch (LZ4Exception e) {
				bufferLength += bufferLength;
			}
		} while (true);
	}

}