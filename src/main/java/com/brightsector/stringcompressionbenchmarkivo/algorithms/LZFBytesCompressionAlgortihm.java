package com.brightsector.stringcompressionbenchmarkivo.algorithms;

import com.ning.compress.lzf.LZFDecoder;
import com.ning.compress.lzf.LZFEncoder;
import com.ning.compress.lzf.LZFException;

public class LZFBytesCompressionAlgortihm implements CompressionAlgorithm {

	@Override
	public byte[] compress(byte[] data) {
		return LZFEncoder.encode(data);
	}

	@Override
	public byte[] uncompress(byte[] data) {
		try {
			return LZFDecoder.decode(data);
		} catch (LZFException e) {
			throw new IllegalStateException("Error decompressing: ", e);
		}
	}

}
