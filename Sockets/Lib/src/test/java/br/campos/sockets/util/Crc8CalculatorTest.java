package br.campos.sockets.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Crc8CalculatorTest {
	Crc8Calculator crc = new Crc8Calculator();
	private byte[] casoControle = new byte[]{0x09, 0x01, 0x31, 0x32, 0x33, 0x34};
	private byte[] casoExtra = new byte[]{(byte) 0xF9, 0x01, 0x71, (byte) 0xC2, (byte) 0xE3, 0x34};
	private byte[] casoString = "Teste123@".getBytes();

	@Test
	void test() {
		assertEquals(0xC6, crc.calc(casoControle));
		assertEquals(0xB4, crc.calc(casoExtra));
		assertEquals(0x7F, crc.calc(casoString));
	}

}
