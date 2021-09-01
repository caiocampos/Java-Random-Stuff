public class Crc8Calculator {
	private final int hashSize = 8;
	private final long poly = 0x7;
	private final long mask = (1L << this.hashSize) - 1;
	private final long[] table = new long[256];

	Crc8Calculator() {
		final long lastBit = 1L << (this.hashSize - 1);
		for (int i = 0; i < this.table.length; i++) {
			long r = (long) i;
			for (int j = 0; j < 8; j++) {
				r = (r & lastBit) != 0 ? ((r << 1) ^ this.poly) : r << 1;
			}
			this.table[i] = r & this.mask;
		}
	}

	private long calc(byte[] data, int offset, int length) {
		long crc = 0x0;
		for (int i = offset; i < offset + length; i++) {
			final int tableIndex = (int) (crc ^ data[i]) & 0xFF;
			crc = this.table[tableIndex] ^ (crc << 8);
			crc &= this.mask;
		}
		return crc & this.mask;
	}

	public static void main(String[] args) {
		String s = "123";
		byte[] bytes = null;
		try {
			bytes = s.getBytes("US-ASCII");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Crc8Calculator crc = new Crc8Calculator();
		System.out.println(Long.toHexString(crc.calc(bytes, 0, bytes.length)).toUpperCase());
	}
}