package br.campos.sockets.model;

import java.util.Arrays;

import br.campos.sockets.util.Constants;
import br.campos.sockets.util.Constants.Frame;
import br.campos.sockets.util.Crc8Calculator;

public class Protocol {
	private byte init;
	private int bytes;
	private Frame frame;
	private int crc;
	private byte end;
	private ProtocolData data;

	public static Crc8Calculator crcCalculator = new Crc8Calculator();

	private Protocol() {
	}

	public static Protocol valueOf(byte[] protocol) throws Exception {
		final int size = protocol.length;
		if (size < 5 || size > 255) {
			throw new Exception("Invalid message size!");
		}
		final Protocol res = new Protocol();
		res.init = protocol[0];
		if (res.init != Constants.INIT) {
			throw new Exception("Invalid INIT!");
		}
		res.end = protocol[size - 1];
		if (res.end != Constants.END) {
			throw new Exception("Invalid END!");
		}
		// "& BYTE_MASK" remove o sinal do byte e converte em int
		res.bytes = protocol[1] & Constants.BYTE_MASK;
		if (res.bytes != size) {
			throw new Exception("Invalid BYTES!");
		}
		res.frame = Frame.valueOf(protocol[2]);
		if (res.frame == null) {
			throw new Exception("Invalid FRAME!");
		}
		res.crc = protocol[size - 2] & Constants.BYTE_MASK;
		if (res.crc != crcCalculator.calc(protocol, 1, size - 3)) {
			throw new Exception("Invalid CRC!");
		}
		byte[] data = Arrays.copyOfRange(protocol, 3, size - 2);
		res.data = ProtocolData.valueOf(res.frame, data);
		return res;
	}

	public byte getInit() {
		return init;
	}

	public void setInit(byte init) {
		this.init = init;
	}

	public int getBytes() {
		return bytes;
	}

	public void setBytes(int bytes) {
		this.bytes = bytes;
	}

	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public int getCrc() {
		return crc;
	}

	public void setCrc(int crc) {
		this.crc = crc;
	}

	public byte getEnd() {
		return end;
	}

	public void setEnd(byte end) {
		this.end = end;
	}

	public ProtocolData getData() {
		return data;
	}

	public void setData(ProtocolData data) {
		this.data = data;
	}

}
