package br.campos.sockets.model;

public abstract class ProtocolData {
	public enum Type {
		EMPTY, TEXT_MESSAGE, USER_INFORMATION, TIME
	}

	protected Type type;

	protected static ProtocolData valueOf(Type type, byte[] data) {
		if (type == null || data == null || data.length == 0) {
			return null;
		}
		return null;
	}

}
