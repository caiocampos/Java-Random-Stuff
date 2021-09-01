package br.campos.sockets.util;

public class Crc8Calculator {
	private final int mask = 0xFF;
	private final int[] table = new int[256];

	public Crc8Calculator() {
		final int hashSize = 8;
		final int poly = 0x7;
		final int lastBit = 1 << (hashSize - 1);
		for (int i = 0; i < this.table.length; i++) {
			int result = i;
			for (int j = 0; j < 8; j++) {
				final int resultLShift = result << 1;
				result = (result & lastBit) != 0 ? (resultLShift ^ poly) : resultLShift;
			}
			this.table[i] = result & this.mask;
		}
	}

	public int calc(byte[] data) {
		return this.calc(data, 0, data.length);
	}

	public int calc(byte[] data, int offset, int length) {
		int crc = 0x0;
		for (int limit = offset + length, i = offset; i < limit; i++) {
			final int tableIndex = (crc ^ data[i]) & this.mask;
			crc = (this.table[tableIndex] ^ (crc << 8)) & this.mask;
		}
		return crc;
	}
}