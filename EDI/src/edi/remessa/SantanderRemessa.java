package edi.remessa;

import edi.EdiParseable;

public class SantanderRemessa extends EdiParseable {
	private SantanderRemessaHeader header;
	private SantanderRemessaDetalhe detalhe;

	public SantanderRemessaHeader getHeader() {
		return header;
	}

	public void setHeader(SantanderRemessaHeader header) {
		this.header = header;
	}

	public SantanderRemessaDetalhe getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(SantanderRemessaDetalhe detalhe) {
		this.detalhe = detalhe;
	}

	@Override
	public String getParsed() {
		StringBuilder sb = new StringBuilder();
		if (header != null) {
			sb.append(header.getParsed());
			sb.append(System.lineSeparator());
		}
		if (detalhe != null) {
			sb.append(detalhe.getParsed());
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}
}
