package br.campos.sockets.util;

import java.nio.charset.Charset;

public abstract class Constants {
	public static final String ASCII = "US-ASCII";
	public static final Charset CHARSET = Charset.forName(ASCII);
	public static final String HOSTNAME = "127.0.0.1";
	public static final int PORT = 5050;
	public static final int TIMEOUT = 1000;
	public static final byte INIT = 0x0A;
	public static final byte END = 0x0D;
	public static final int BYTE_MASK = 0xFF;

	public enum Frame {
		ACK(0xA0), TEXT_MESSAGE(0xA1), USER_INFORMATION(0xA2), CURRENT_TIME(0xA3);

		private byte value;
		private String strValue;

		Frame(int value) {
			this.value = (byte) value;
			this.strValue = byteToString(value);
		}

		public static Frame valueOf(byte value) {
			// "& BYTE_MASK" remove o sinal do byte e converte em int
			return valueOf(value & BYTE_MASK);
		}

		public static Frame valueOf(int value) {
			switch (value) {
			case 0xA0:
				return ACK;
			case 0xA1:
				return TEXT_MESSAGE;
			case 0xA2:
				return USER_INFORMATION;
			case 0xA3:
				return CURRENT_TIME;
			default:
				return null;
			}
		}

		public byte getValue() {
			return this.value;
		}

		@Override
		public String toString() {
			return this.strValue;
		}
	}

	public static String byteToString(byte value) {
		// "& BYTE_MASK" remove o sinal do byte e converte em int
		return byteToString(value & BYTE_MASK);
	}

	public static String byteToString(int value) {
		return String.valueOf((char) value);
	}
}
