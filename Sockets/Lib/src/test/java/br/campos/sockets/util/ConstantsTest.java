package br.campos.sockets.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.campos.sockets.util.Constants.Frame;

class ConstantsTest {

	@Test
	void stringTest() {
		assertEquals("¡", Frame.TEXT_MESSAGE.toString());
		assertEquals("¢", Frame.USER_INFORMATION.toString());
		assertEquals("£", Frame.CURRENT_TIME.toString());
		assertEquals("\n", Constants.byteToString(Constants.INIT));
		assertEquals("\r", Constants.byteToString(Constants.END));
	}

	@Test
	void byteTest() {
		// Testando valores negativos
		assertEquals(Integer.parseInt("A1", 16), Frame.TEXT_MESSAGE.getValue() & 0xFF);
		assertEquals(Integer.parseInt("A2", 16), Frame.USER_INFORMATION.getValue() & 0xFF);
		assertEquals(Integer.parseInt("A3", 16), Frame.CURRENT_TIME.getValue() & 0xFF);
		// Testando valores positivos
		assertEquals(Byte.parseByte("0A", 16), Constants.INIT);
		assertEquals(Byte.parseByte("0D", 16), Constants.END);
	}
}
